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

/**
 * @author luna
 * @date 2024/1/5
 */
public class WeightRandomLoadBalancer implements LoadBalancer {

    static final AtomicInteger TOTAL_WEIGHT = new AtomicInteger(0);
    static final Map<String, Integer> WEIGHT_MAP = new ConcurrentHashMap<>();
    static final Random RANDOM = new Random();


    public WeightRandomLoadBalancer() {
        init();
    }

    public void init() {
        List<String> nodeList = new ArrayList<>(ZlmProperties.nodeMap.keySet());
        int size = nodeList.size();
        if (size > 0) {
            for (String nodeName : nodeList) {
                ZlmNode nodeConfig = ZlmProperties.nodeMap.get(nodeName);
                int weight = nodeConfig.getWeight();
                WEIGHT_MAP.put(nodeName, weight);
                TOTAL_WEIGHT.addAndGet(weight);
            }
        }
    }

    @Override
    public ZlmNode selectNode(String key) {
        int randomNum = RANDOM.nextInt(TOTAL_WEIGHT.get());
        int currentWeight = 0;
        for (String nodeName : ZlmProperties.nodeMap.keySet()) {
            currentWeight += WEIGHT_MAP.get(nodeName);
            if (currentWeight > randomNum) {
                return ZlmProperties.nodeMap.get(nodeName);
            }
        }
        return null;
    }

    @Override
    public String getType() {
        return LoadBalancerEnums.WEIGHT_RANDOM.getType();
    }
}
