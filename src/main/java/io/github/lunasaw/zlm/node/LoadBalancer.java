package io.github.lunasaw.zlm.node;

import io.github.lunasaw.zlm.config.ZlmNode;

import java.util.List;

/**
 * 负载均衡器
 * 需要支持外部节点添加和移除，每次添加和移除后，需要重新计算权重
 * @author luna
 * @date 2024/1/5
 */
public interface LoadBalancer {

    /**
     * 添加节点
     *
     * @param node 节点信息
     */
    void addNode(ZlmNode node);

    /**
     * 移除节点
     *
     * @param serverId 服务器ID
     */
    void removeNode(String serverId);

    /**
     * 获取所有节点
     *
     * @return 节点列表
     */
    List<ZlmNode> getNodes();

    /**
     * 检查节点是否存在
     *
     * @param serverId 服务器ID
     * @return 是否存在
     */
    boolean hasNode(String serverId);

    /**
     * 初始化负载均衡器
     */
    void init();

    /**
     * 根据key选择节点
     * @param key 选择key
     * @return 选中的节点
     */
    ZlmNode selectNode(String key);

    /**
     * 获取负载均衡器类型
     * @return 类型标识
     */
    String getType();

    /**
     * 清空所有节点
     */
    void clear();
}
