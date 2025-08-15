package io.github.lunasaw.zlm.node.service.impl;

import io.github.lunasaw.zlm.config.ZlmNode;
import io.github.lunasaw.zlm.node.LoadBalancer;
import io.github.lunasaw.zlm.node.NodeSupplier;
import io.github.lunasaw.zlm.node.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * ZLM节点服务实现类
 * 整合负载均衡器和节点提供器的功能，提供统一的节点管理服务
 *
 * @author luna
 * @date 2025/01/23
 */
@Service
public class NodeServiceImpl implements NodeService {

    @Autowired
    private LoadBalancer loadBalancer;

    @Autowired
    private NodeSupplier nodeSupplier;

    @Override
    public ZlmNode getAvailableNode(String nodeKey) {
        Assert.hasText(nodeKey, "节点key不能为空");
        ZlmNode zlmNode = selectNode(nodeKey);
        if (zlmNode != null) {
            return zlmNode;
        }
        return selectNode();
    }

    @Override
    public ZlmNode selectNode() {
        // 使用默认负载均衡策略选择节点
        return loadBalancer.selectNode("default");
    }

    @Override
    public ZlmNode selectNode(String key) {
        Assert.hasText(key, "负载均衡选择key不能为空");
        ZlmNode supplierNode = nodeSupplier.getNode(key);
        Assert.notNull(supplierNode, "未找到指定key的ZLM节点");
        return supplierNode;
    }
}