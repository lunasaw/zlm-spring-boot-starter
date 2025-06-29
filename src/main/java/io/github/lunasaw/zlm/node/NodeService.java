package io.github.lunasaw.zlm.node;

import io.github.lunasaw.zlm.config.ZlmNode;
import io.github.lunasaw.zlm.config.ZlmProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 节点管理服务
 * 负载均衡器会通过NodeSupplier实时获取节点列表，所以此服务只需管理配置中的节点
 * @author luna
 * @date 2023/12/4
 */
@Service
@ConditionalOnBean(ZlmProperties.class)
public class NodeService {

    @Autowired
    private ZlmProperties zlmProperties;

    @Autowired
    private LoadBalancer loadBalancer;

    /**
     * 读写锁，用于保证并发安全
     */
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    /**
     * 添加节点
     *
     * @param zlmNode 节点信息
     */
    public void addNode(ZlmNode zlmNode) {
        if (zlmNode == null || zlmNode.getServerId() == null) {
            return;
        }

        lock.writeLock().lock();
        try {
            // 更新配置中的节点列表
            zlmProperties.getNodes().removeIf(node -> zlmNode.getServerId().equals(node.getServerId()));
            zlmProperties.getNodes().add(zlmNode);
            zlmProperties.getNodeMap().put(zlmNode.getServerId(), zlmNode);
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * 移除节点
     *
     * @param serverId 服务器ID
     */
    public void removeNode(String serverId) {
        if (serverId == null) {
            return;
        }

        lock.writeLock().lock();
        try {
            // 从配置中移除节点
            zlmProperties.getNodes().removeIf(node -> serverId.equals(node.getServerId()));
            zlmProperties.getNodeMap().remove(serverId);
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * 获取所有节点
     *
     * @return 节点列表
     */
    public List<ZlmNode> getNodes() {
        lock.readLock().lock();
        try {
            return zlmProperties.getNodes();
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * 检查节点是否存在
     *
     * @param serverId 服务器ID
     * @return 是否存在
     */
    public boolean hasNode(String serverId) {
        lock.readLock().lock();
        try {
            return zlmProperties.getNodeMap().containsKey(serverId);
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * 获取节点信息
     *
     * @param serverId 服务器ID
     * @return 节点信息
     */
    public ZlmNode getNode(String serverId) {
        lock.readLock().lock();
        try {
            return zlmProperties.getNodeMap().get(serverId);
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * 更新节点信息
     *
     * @param zlmNode 节点信息
     */
    public void updateNode(ZlmNode zlmNode) {
        if (zlmNode == null || zlmNode.getServerId() == null) {
            return;
        }

        lock.writeLock().lock();
        try {
            String serverId = zlmNode.getServerId();

            // 更新配置中的节点
            zlmProperties.getNodes().removeIf(node -> serverId.equals(node.getServerId()));
            zlmProperties.getNodes().add(zlmNode);
            zlmProperties.getNodeMap().put(serverId, zlmNode);
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * 清空所有节点
     */
    public void clearNodes() {
        lock.writeLock().lock();
        try {
            zlmProperties.getNodes().clear();
            zlmProperties.getNodeMap().clear();
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * 使用默认key选择节点
     * @return 选中的节点
     */
    public ZlmNode selectNode() {
        return loadBalancer.selectNode(null);
    }

    /**
     * 根据指定key选择节点
     * @param key 选择key
     * @return 选中的节点
     */
    public ZlmNode selectNode(String key) {
        return loadBalancer.selectNode(key);
    }

    /**
     * 获取负载均衡器类型
     *
     * @return 负载均衡器类型
     */
    public String getLoadBalancerType() {
        return loadBalancer.getType();
    }
}
