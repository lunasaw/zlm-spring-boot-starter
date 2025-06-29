package io.github.lunasaw.zlm.node.impl;

import io.github.lunasaw.zlm.config.ZlmNode;
import io.github.lunasaw.zlm.config.ZlmProperties;
import io.github.lunasaw.zlm.enums.LoadBalancerEnums;
import io.github.lunasaw.zlm.node.LoadBalancer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 加权随机负载均衡器
 * @author luna
 * @date 2024/1/5
 */
public class WeightRandomLoadBalancer implements LoadBalancer {

    private final AtomicInteger totalWeight = new AtomicInteger(0);
    private final Map<String, ZlmNode> nodeMap = new ConcurrentHashMap<>();
    private final Map<String, Integer> weightMap = new ConcurrentHashMap<>();
    private final Random random = new Random();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public WeightRandomLoadBalancer() {
        init();
    }

    @Override
    public void init() {
        lock.writeLock().lock();
        try {
            nodeMap.clear();
            weightMap.clear();
            totalWeight.set(0);
            // 从配置中初始化节点
            if (ZlmProperties.nodeMap != null) {
                for (Map.Entry<String, ZlmNode> entry : ZlmProperties.nodeMap.entrySet()) {
                    addNodeInternal(entry.getValue());
                }
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void addNode(ZlmNode node) {
        if (node == null || node.getServerId() == null) {
            return;
        }
        lock.writeLock().lock();
        try {
            addNodeInternal(node);
        } finally {
            lock.writeLock().unlock();
        }
    }

    private void addNodeInternal(ZlmNode node) {
        String serverId = node.getServerId();
        if (!nodeMap.containsKey(serverId)) {
            nodeMap.put(serverId, node);
            int weight = node.getWeight();
            weightMap.put(serverId, weight);
            totalWeight.addAndGet(weight);
        }
    }

    @Override
    public void removeNode(String serverId) {
        if (serverId == null) {
            return;
        }
        lock.writeLock().lock();
        try {
            ZlmNode node = nodeMap.remove(serverId);
            if (node != null) {
                Integer weight = weightMap.remove(serverId);
                if (weight != null) {
                    totalWeight.addAndGet(-weight);
                }
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public List<ZlmNode> getNodes() {
        lock.readLock().lock();
        try {
            return new ArrayList<>(nodeMap.values());
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public boolean hasNode(String serverId) {
        lock.readLock().lock();
        try {
            return nodeMap.containsKey(serverId);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public ZlmNode selectNode(String key) {
        lock.readLock().lock();
        try {
            if (nodeMap.isEmpty() || totalWeight.get() <= 0) {
                return null;
            }
            int randomNum = random.nextInt(totalWeight.get());
            int currentWeight = 0;
            for (Map.Entry<String, ZlmNode> entry : nodeMap.entrySet()) {
                String serverId = entry.getKey();
                currentWeight += weightMap.get(serverId);
                if (currentWeight > randomNum) {
                    return entry.getValue();
                }
            }
            return null;
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void clear() {
        lock.writeLock().lock();
        try {
            nodeMap.clear();
            weightMap.clear();
            totalWeight.set(0);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public String getType() {
        return LoadBalancerEnums.WEIGHT_RANDOM.getType();
    }
}
