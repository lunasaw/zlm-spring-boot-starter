package io.github.lunasaw.zlm.node.impl;

import io.github.lunasaw.zlm.config.ZlmNode;
import io.github.lunasaw.zlm.config.ZlmProperties;
import io.github.lunasaw.zlm.enums.LoadBalancerEnums;
import io.github.lunasaw.zlm.node.LoadBalancer;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author luna
 * @date 2024/1/5
 */
public class RoundRobinLoadBalancer implements LoadBalancer {

    static final AtomicInteger SEQUENCE = new AtomicInteger(0);

    @Override
    public ZlmNode selectNode(String key) {
        int index = SEQUENCE.getAndIncrement() % ZlmProperties.nodes.size();
        return ZlmProperties.nodes.get(index);
    }

    @Override
    public String getType() {
        return LoadBalancerEnums.RANDOM.getType();
    }
}
