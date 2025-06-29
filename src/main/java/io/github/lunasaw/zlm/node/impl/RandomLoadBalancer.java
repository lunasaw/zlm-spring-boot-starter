package io.github.lunasaw.zlm.node.impl;

import io.github.lunasaw.zlm.config.ZlmNode;
import io.github.lunasaw.zlm.config.ZlmProperties;
import io.github.lunasaw.zlm.enums.LoadBalancerEnums;
import io.github.lunasaw.zlm.node.LoadBalancer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 随机负载均衡器
 * @author luna
 * @date 2024/1/5
 */
public class RandomLoadBalancer implements LoadBalancer {

    private final Random random = new Random();
    private final List<ZlmNode> nodes = new CopyOnWriteArrayList<>();

    public RandomLoadBalancer() {
        init();
    }

    @Override
    public void init() {
        nodes.clear();
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
        int index = random.nextInt(nodes.size());
        return nodes.get(index);
    }

    @Override
    public void clear() {
        nodes.clear();
    }

    @Override
    public String getType() {
        return LoadBalancerEnums.RANDOM.getType();
    }
}
