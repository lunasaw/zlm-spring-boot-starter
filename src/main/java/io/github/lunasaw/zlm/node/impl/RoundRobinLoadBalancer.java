package io.github.lunasaw.zlm.node.impl;

import io.github.lunasaw.zlm.config.ZlmNode;
import io.github.lunasaw.zlm.config.ZlmProperties;
import io.github.lunasaw.zlm.enums.LoadBalancerEnums;
import io.github.lunasaw.zlm.node.LoadBalancer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 轮询负载均衡器
 * @author luna
 * @date 2024/1/5
 */
public class RoundRobinLoadBalancer implements LoadBalancer {

    private final AtomicInteger sequence = new AtomicInteger(0);
    private final List<ZlmNode> nodes = new CopyOnWriteArrayList<>();

    public RoundRobinLoadBalancer() {
        init();
    }

    @Override
    public void init() {
        nodes.clear();
        sequence.set(0);
        // 从配置中初始化节点
        if (ZlmProperties.nodes != null) {
            nodes.addAll(ZlmProperties.nodes);
        }
    }

    @Override
    public void addNode(ZlmNode node) {
        if (node != null && !hasNodeInternal(node.getServerId())) {
            nodes.add(node);
        }
    }

    @Override
    public void removeNode(String serverId) {
        if (serverId != null) {
            nodes.removeIf(node -> serverId.equals(node.getServerId()));
            // 重置序号避免越界
            if (!nodes.isEmpty()) {
                sequence.set(sequence.get() % nodes.size());
            }
        }
    }

    @Override
    public List<ZlmNode> getNodes() {
        return new ArrayList<>(nodes);
    }

    @Override
    public boolean hasNode(String serverId) {
        return hasNodeInternal(serverId);
    }

    private boolean hasNodeInternal(String serverId) {
        return nodes.stream().anyMatch(node -> serverId.equals(node.getServerId()));
    }

    @Override
    public ZlmNode selectNode(String key) {
        if (nodes.isEmpty()) {
            return null;
        }
        int index = sequence.getAndIncrement() % nodes.size();
        return nodes.get(index);
    }

    @Override
    public void clear() {
        nodes.clear();
        sequence.set(0);
    }

    @Override
    public String getType() {
        return LoadBalancerEnums.ROUND_ROBIN.getType();
    }
}
