package io.github.lunasaw.zlm.node.impl;

import io.github.lunasaw.zlm.config.ZlmNode;
import io.github.lunasaw.zlm.config.ZlmProperties;
import io.github.lunasaw.zlm.enums.LoadBalancerEnums;
import io.github.lunasaw.zlm.node.LoadBalancer;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author luna
 * @date 2024/1/5
 */
public class ConsistentHashingLoadBalancer implements LoadBalancer {

    static final TreeMap<Integer, String> VIRTUAL_NODE_MAP = new TreeMap<>();

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

    public void init() {
        for (String nodeName : ZlmProperties.nodeMap.keySet()) {
            ZlmNode nodeConfig = ZlmProperties.nodeMap.get(nodeName);
            int weight = nodeConfig.getWeight();
            for (int i = 0; i < weight * 10; i++) {
                String virtualNodeName = nodeName + "#" + i;
                int hash = getHash(virtualNodeName);
                VIRTUAL_NODE_MAP.put(hash, virtualNodeName);
            }
        }
    }

    @Override
    public ZlmNode selectNode(String key) {
        int hash = getHash(key);
        Map.Entry<Integer, String> entry = VIRTUAL_NODE_MAP.ceilingEntry(hash);
        if (entry == null) {
            entry = VIRTUAL_NODE_MAP.firstEntry();
        }
        String virtualNodeName = entry.getValue();
        String nodeName = virtualNodeName.split("#")[0];
        return ZlmProperties.nodeMap.get(nodeName);
    }

    @Override
    public String getType() {
        return LoadBalancerEnums.CONSISTENT_HASHING.getType();
    }
}
