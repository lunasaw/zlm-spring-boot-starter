package io.github.lunasaw.zlm.node.impl;

import io.github.lunasaw.zlm.config.ZlmNode;
import io.github.lunasaw.zlm.enums.LoadBalancerEnums;
import io.github.lunasaw.zlm.node.LoadBalancer;
import io.github.lunasaw.zlm.node.NodeSupplier;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 轮询负载均衡器
 * 每次选择节点时直接从NodeSupplier获取最新节点列表
 * @author luna
 * @date 2024/1/5
 */
@Slf4j
public class RoundRobinLoadBalancer implements LoadBalancer {

    private final AtomicInteger sequence = new AtomicInteger(0);
    private volatile NodeSupplier nodeSupplier;

    @Override
    public void setNodeSupplier(NodeSupplier nodeSupplier) {
        this.nodeSupplier = nodeSupplier;
        // 重置序号
        sequence.set(0);
        log.info("设置节点提供器: {}", nodeSupplier != null ? nodeSupplier.getName() : "null");
    }

    @Override
    public ZlmNode selectNode(String key) {
        List<ZlmNode> nodes = getCurrentNodes();
        if (nodes == null || nodes.isEmpty()) {
            return null;
        }

        int index = sequence.getAndIncrement() % nodes.size();
        return nodes.get(index);
    }

    @Override
    public String getType() {
        return LoadBalancerEnums.ROUND_ROBIN.getType();
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
