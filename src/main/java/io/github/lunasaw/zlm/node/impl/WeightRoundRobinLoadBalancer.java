package io.github.lunasaw.zlm.node.impl;

import io.github.lunasaw.zlm.config.ZlmNode;
import io.github.lunasaw.zlm.config.ZlmProperties;
import io.github.lunasaw.zlm.enums.LoadBalancerEnums;
import io.github.lunasaw.zlm.node.LoadBalancer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 加权轮询负载均衡器
 * @author luna
 * @date 2024/1/5
 */
public class WeightRoundRobinLoadBalancer implements LoadBalancer {

    private final AtomicInteger sumWeight = new AtomicInteger(0);
    private final Map<String, ZlmNode> nodeMap = new ConcurrentHashMap<>();
    private final Map<String, Integer> weightMap = new ConcurrentHashMap<>();
    private final Map<String, Integer> currentWeightMap = new ConcurrentHashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public WeightRoundRobinLoadBalancer() {
        init();
    }

    @Override
    public void init() {
        lock.writeLock().lock();
        try {
            nodeMap.clear();
            weightMap.clear();
            currentWeightMap.clear();
            sumWeight.set(0);
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
            currentWeightMap.put(serverId, 0);
            sumWeight.addAndGet(weight);
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
                currentWeightMap.remove(serverId);
                if (weight != null) {
                    sumWeight.addAndGet(-weight);
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
    public synchronized ZlmNode selectNode(String key) {
        if (nodeMap.isEmpty() || sumWeight.get() <= 0) {
            return null;
        }

        // 更新当前权重
        weightMap.forEach((serverId, weight) ->
                currentWeightMap.put(serverId, weight + currentWeightMap.getOrDefault(serverId, 0))
        );

        // 选择最大权重的节点
        String selectedServerId = currentWeightMap.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        if (selectedServerId != null) {
            // 减去总权重
            int currentWeight = currentWeightMap.getOrDefault(selectedServerId, 0);
            int newWeight = currentWeight - sumWeight.get();
            currentWeightMap.put(selectedServerId, newWeight);
            return nodeMap.get(selectedServerId);
        }

        return null;
    }

    @Override
    public void clear() {
        lock.writeLock().lock();
        try {
            nodeMap.clear();
            weightMap.clear();
            currentWeightMap.clear();
            sumWeight.set(0);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public String getType() {
        return LoadBalancerEnums.WEIGHT_ROUND_ROBIN.getType();
    }
}
