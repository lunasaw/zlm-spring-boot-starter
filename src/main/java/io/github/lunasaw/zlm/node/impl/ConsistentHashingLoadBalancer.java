package io.github.lunasaw.zlm.node.impl;

import io.github.lunasaw.zlm.config.ZlmNode;
import io.github.lunasaw.zlm.enums.LoadBalancerEnums;
import io.github.lunasaw.zlm.node.LoadBalancer;
import io.github.lunasaw.zlm.node.NodeSupplier;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * 一致性哈希负载均衡器
 * 每次选择节点时重新构建哈希环，确保使用最新节点列表
 * @author luna
 * @date 2024/1/5
 */
@Slf4j
public class ConsistentHashingLoadBalancer implements LoadBalancer {

    private static final int VIRTUAL_NODE_COUNT = 10;
    private volatile NodeSupplier nodeSupplier;

    @Override
    public void setNodeSupplier(NodeSupplier nodeSupplier) {
        this.nodeSupplier = nodeSupplier;
        log.info("设置节点提供器: {}", nodeSupplier != null ? nodeSupplier.getName() : "null");
    }

    @Override
    public ZlmNode selectNode(String key) {
        if (key == null) {
            return null;
        }

        List<ZlmNode> nodes = getCurrentNodes();
        if (nodes == null || nodes.isEmpty()) {
            return null;
        }

        // 构建哈希环
        TreeMap<Integer, ZlmNode> hashRing = buildHashRing(nodes);
        if (hashRing.isEmpty()) {
            return null;
        }

        // 选择节点
        int hash = getHash(key);
        Map.Entry<Integer, ZlmNode> entry = hashRing.ceilingEntry(hash);
        if (entry == null) {
            entry = hashRing.firstEntry();
        }

        return entry.getValue();
    }

    @Override
    public String getType() {
        return LoadBalancerEnums.CONSISTENT_HASHING.getType();
    }

    /**
     * 构建哈希环
     *
     * @param nodes 节点列表
     * @return 哈希环
     */
    private TreeMap<Integer, ZlmNode> buildHashRing(List<ZlmNode> nodes) {
        TreeMap<Integer, ZlmNode> hashRing = new TreeMap<>();

        for (ZlmNode node : nodes) {
            int weight = node.getWeight();
            String serverId = node.getServerId();

            // 根据权重创建虚拟节点
            for (int i = 0; i < weight * VIRTUAL_NODE_COUNT; i++) {
                String virtualNodeName = serverId + "#" + i;
                int hash = getHash(virtualNodeName);
                hashRing.put(hash, node);
            }
        }

        return hashRing;
    }

    /**
     * 计算哈希值
     *
     * @param str 字符串
     * @return 哈希值
     */
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

    /**
     * 获取当前可用节点列表
     *
     * @return 节点列表
     */
    private List<ZlmNode> getCurrentNodes() {
        if (nodeSupplier == null) {
            log.warn("NodeSupplier未设置，无法获取节点列表");
            return null;
        }

        try {
            return nodeSupplier.getNodes();
        } catch (Exception e) {
            log.error("从NodeSupplier获取节点列表失败", e);
            return null;
        }
    }
}
