package io.github.lunasaw.zlm.node;

import io.github.lunasaw.zlm.config.ZlmNode;

import java.util.List;

/**
 * 节点提供器接口
 * 用于动态获取节点列表的hook机制，支持外部实现自定义的节点获取逻辑
 *
 * @author luna
 * @date 2025/01/23
 */
public interface NodeSupplier {

    /**
     * 获取当前可用的节点列表
     * 该方法会被LoadBalancer定期调用以获取最新的节点信息
     *
     * @return 节点列表，如果没有可用节点则返回空列表
     */
    List<ZlmNode> getNodes();

    /**
     * 获取节点提供器的名称标识
     *
     * @return 提供器名称
     */
    default String getName() {
        return this.getClass().getSimpleName();
    }
}