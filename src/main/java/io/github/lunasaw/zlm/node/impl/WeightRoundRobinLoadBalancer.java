package io.github.lunasaw.zlm.node.impl;

import io.github.lunasaw.zlm.config.ZlmNode;
import io.github.lunasaw.zlm.enums.LoadBalancerEnums;
import io.github.lunasaw.zlm.node.LoadBalancer;
import io.github.lunasaw.zlm.node.NodeSupplier;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Random;

/**
 * 加权轮询负载均衡器
 * 每次选择节点时基于权重进行随机选择，不维护本地状态
 * @author luna
 * @date 2024/1/5
 */
@Slf4j
public class WeightRoundRobinLoadBalancer implements LoadBalancer {

    private final Random random = new Random();
    private volatile NodeSupplier nodeSupplier;

    @Override
    public void setNodeSupplier(NodeSupplier nodeSupplier) {
        this.nodeSupplier = nodeSupplier;
        log.info("设置节点提供器: {}", nodeSupplier != null ? nodeSupplier.getName() : "null");
    }

    @Override
    public ZlmNode selectNode(String key) {
        List<ZlmNode> nodes = getCurrentNodes();
        if (nodes == null || nodes.isEmpty()) {
            return null;
        }

        // 计算总权重
        int totalWeight = nodes.stream().mapToInt(ZlmNode::getWeight).sum();
        if (totalWeight <= 0) {
            // 如果总权重为0，则随机选择
            return nodes.get(random.nextInt(nodes.size()));
        }

        // 基于权重随机选择
        int randomNum = random.nextInt(totalWeight);
        int currentWeight = 0;

        for (ZlmNode node : nodes) {
            currentWeight += node.getWeight();
            if (currentWeight > randomNum) {
                return node;
            }
        }

        // 兜底返回最后一个节点
        return nodes.get(nodes.size() - 1);
    }

    @Override
    public String getType() {
        return LoadBalancerEnums.WEIGHT_ROUND_ROBIN.getType();
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
