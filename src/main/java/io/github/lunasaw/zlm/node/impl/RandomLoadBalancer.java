package io.github.lunasaw.zlm.node.impl;

import io.github.lunasaw.zlm.config.ZlmNode;
import io.github.lunasaw.zlm.config.ZlmProperties;
import io.github.lunasaw.zlm.enums.LoadBalancerEnums;
import io.github.lunasaw.zlm.node.LoadBalancer;

import java.util.Random;

/**
 * @author luna
 * @date 2024/1/5
 */
public class RandomLoadBalancer implements LoadBalancer {
    static final Random RANDOM = new Random();

    @Override
    public void init() {

    }

    @Override
    public ZlmNode selectNode(String key) {
        int index = RANDOM.nextInt(ZlmProperties.nodes.size());
        return ZlmProperties.nodes.get(index);
    }

    @Override
    public String getType() {
        return LoadBalancerEnums.RANDOM.getType();
    }
}
