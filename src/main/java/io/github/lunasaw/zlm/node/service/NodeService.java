package io.github.lunasaw.zlm.node.service;

import io.github.lunasaw.zlm.config.ZlmNode;

import java.util.List;

/**
 * ZLM节点负载均衡服务接口
 * 专注于负载均衡功能，提供节点选择服务
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
    ZlmNode selectNode();
}