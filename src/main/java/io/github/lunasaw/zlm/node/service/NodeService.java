package io.github.lunasaw.zlm.node.service;

import io.github.lunasaw.zlm.config.ZlmNode;

import java.util.List;

/**
 * ZLM节点服务接口
 * 提供节点选择、管理和查询功能
 *
 * @author luna
 * @date 2025/01/23
 */
public interface NodeService {

    /**
     * 根据节点key获取指定节点
     *
     * @param nodeKey 节点key
     * @return 指定的节点
     * @throws IllegalArgumentException 当指定的节点不存在时抛出
     */
    ZlmNode getAvailableNode(String nodeKey);

    /**
     * 使用负载均衡策略选择节点
     *
     * @param key 负载均衡选择key，通常用于标识业务类型
     * @return 选中的节点
     * @throws IllegalStateException 当没有可用节点时抛出
     */
    ZlmNode selectNode(String key);

    /**
     * 使用默认负载均衡策略选择节点
     *
     * @return 选中的节点
     * @throws IllegalStateException 当没有可用节点时抛出
     */
    default ZlmNode selectNode() {
        return selectNode("default");
    }

    /**
     * 获取所有可用节点列表
     *
     * @return 节点列表
     */
    List<ZlmNode> getAllNodes();

    /**
     * 检查指定节点是否存在
     *
     * @param nodeKey 节点key
     * @return 如果节点存在返回true，否则返回false
     */
    boolean hasNode(String nodeKey);

    /**
     * 获取可用节点数量
     *
     * @return 节点数量
     */
    int getNodeCount();
} 