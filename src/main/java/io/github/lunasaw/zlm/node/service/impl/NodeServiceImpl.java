package io.github.lunasaw.zlm.node.service.impl;

import io.github.lunasaw.zlm.config.ZlmNode;
import io.github.lunasaw.zlm.node.LoadBalancer;
import io.github.lunasaw.zlm.node.NodeSupplier;
import io.github.lunasaw.zlm.node.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

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
    public ZlmNode selectNode(String key) {
        Assert.hasText(key, "负载均衡选择key不能为空");
        ZlmNode node = loadBalancer.selectNode(key);
        Assert.notNull(node, "未找到可用的ZLM节点");
        return node;
    }

    @Override
    public List<ZlmNode> getAllNodes() {
        return nodeSupplier.getNodes();
    }

    @Override
    public boolean hasNode(String nodeKey) {
        if (nodeKey == null || nodeKey.trim().isEmpty()) {
            return false;
        }
        return nodeSupplier.getNode(nodeKey) != null;
    }

    @Override
    public int getNodeCount() {
        List<ZlmNode> nodes = getAllNodes();
        return nodes != null ? nodes.size() : 0;
    }
} 