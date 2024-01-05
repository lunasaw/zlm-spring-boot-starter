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

/**
 * @author luna
 * @date 2024/1/5
 */
public class WeightRoundRobinLoadBalancer implements LoadBalancer {

    static final AtomicInteger SUM_WEIGHT = new AtomicInteger(0);
    static final Map<String, Integer> WEIGHT_MAP = new ConcurrentHashMap<>();
    static final Map<String, Integer> CURRENT_WEIGHT_MAP = new ConcurrentHashMap<>();


    public WeightRoundRobinLoadBalancer() {
        init();
    }

    public static void init() {
        List<String> nodeList = new ArrayList<>(ZlmProperties.nodeMap.keySet());
        int maxWeight = 0;
        for (String nodeName : nodeList) {
            ZlmNode nodeConfig = ZlmProperties.nodeMap.get(nodeName);
            int weight = nodeConfig.getWeight();
            WEIGHT_MAP.put(nodeName, weight);
            maxWeight = Math.max(maxWeight, weight);
        }
        int weightSum = ZlmProperties.nodeMap.values().stream().map(ZlmNode::getWeight).reduce(Integer::sum).orElse(0);
        SUM_WEIGHT.set(weightSum);

    }

    @Override
    public synchronized ZlmNode selectNode(String key) {
        WEIGHT_MAP.forEach((nodeName, weight) -> CURRENT_WEIGHT_MAP.put(nodeName, weight + CURRENT_WEIGHT_MAP.getOrDefault(nodeName, 0)));

        // 选择最大的权重
        String nodeName = CURRENT_WEIGHT_MAP.entrySet().stream().max((o1, o2) -> {
            int weight1 = o1.getValue();
            int weight2 = o2.getValue();
            return Integer.compare(weight1, weight2);
        }).map(Map.Entry::getKey).orElse(null);

        Integer maxWeight = CURRENT_WEIGHT_MAP.values().stream().reduce(Integer::sum).orElse(0);

        // 修改当前权重
        if (nodeName != null) {
            int currentWeight = CURRENT_WEIGHT_MAP.getOrDefault(nodeName, 0);
            int newWeight = currentWeight - maxWeight;
            CURRENT_WEIGHT_MAP.put(nodeName, newWeight);
        }

        return ZlmProperties.nodeMap.get(nodeName);
    }

    @Override
    public String getType() {
        return LoadBalancerEnums.WEIGHT_ROUND_ROBIN.getType();
    }
}
