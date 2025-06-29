package io.github.lunasaw.zlm.node.impl;

import io.github.lunasaw.zlm.config.ZlmNode;
import io.github.lunasaw.zlm.config.ZlmProperties;
import io.github.lunasaw.zlm.enums.LoadBalancerEnums;
import io.github.lunasaw.zlm.node.LoadBalancer;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 一致性哈希负载均衡器
 * @author luna
 * @date 2024/1/5
 */
public class ConsistentHashingLoadBalancer implements LoadBalancer {

    private final TreeMap<Integer, String> virtualNodeMap = new TreeMap<>();
    private final Map<String, ZlmNode> nodeMap = new ConcurrentHashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private static final int VIRTUAL_NODE_COUNT = 10;

    public ConsistentHashingLoadBalancer() {
        init();
    }

    private int getHash(String str) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash ^ str.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        return hash;
    }

    @Override
    public void init() {
        lock.writeLock().lock();
        try {
            virtualNodeMap.clear();
            nodeMap.clear();
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
        nodeMap.put(serverId, node);
        int weight = node.getWeight();
        for (int i = 0; i < weight * VIRTUAL_NODE_COUNT; i++) {
            String virtualNodeName = serverId + "#" + i;
            int hash = getHash(virtualNodeName);
            virtualNodeMap.put(hash, serverId);
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
                // 移除所有虚拟节点
                virtualNodeMap.entrySet().removeIf(entry -> serverId.equals(entry.getValue()));
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
        if (key == null) {
            return null;
        }
        lock.readLock().lock();
        try {
            if (virtualNodeMap.isEmpty()) {
                return null;
            }
            int hash = getHash(key);
            Map.Entry<Integer, String> entry = virtualNodeMap.ceilingEntry(hash);
            if (entry == null) {
                entry = virtualNodeMap.firstEntry();
            }
            String serverId = entry.getValue();
            return nodeMap.get(serverId);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void clear() {
        lock.writeLock().lock();
        try {
            virtualNodeMap.clear();
            nodeMap.clear();
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public String getType() {
        return LoadBalancerEnums.CONSISTENT_HASHING.getType();
    }
}
