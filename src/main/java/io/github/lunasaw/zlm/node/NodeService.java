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
            // 同步到配置中
            zlmProperties.getNodes().removeIf(node -> zlmNode.getServerId().equals(node.getServerId()));
            zlmProperties.getNodes().add(zlmNode);
            zlmProperties.getNodeMap().put(zlmNode.getServerId(), zlmNode);

            // 通知负载均衡器
            loadBalancer.addNode(zlmNode);
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
            // 从配置中移除
            zlmProperties.getNodes().removeIf(node -> serverId.equals(node.getServerId()));
            zlmProperties.getNodeMap().remove(serverId);

            // 通知负载均衡器
            loadBalancer.removeNode(serverId);
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
            return loadBalancer.getNodes();
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
            return loadBalancer.hasNode(serverId);
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
            // 原子性地更新节点：先从配置中移除，再添加新节点
            String serverId = zlmNode.getServerId();

            // 从配置中移除旧节点
            zlmProperties.getNodes().removeIf(node -> serverId.equals(node.getServerId()));
            zlmProperties.getNodeMap().remove(serverId);

            // 添加新节点到配置
            zlmProperties.getNodes().add(zlmNode);
            zlmProperties.getNodeMap().put(serverId, zlmNode);

            // 通知负载均衡器：先移除再添加
            loadBalancer.removeNode(serverId);
            loadBalancer.addNode(zlmNode);
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
            loadBalancer.clear();
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * 使用默认key选择节点
     * @return 选中的节点
     */
    public ZlmNode selectNode() {
        lock.readLock().lock();
        try {
            return loadBalancer.selectNode(null);
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * 根据指定key选择节点
     * @param key 选择key
     * @return 选中的节点
     */
    public ZlmNode selectNode(String key) {
        lock.readLock().lock();
        try {
            return loadBalancer.selectNode(key);
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * 获取负载均衡器类型
     *
     * @return 负载均衡器类型
     */
    public String getLoadBalancerType() {
        lock.readLock().lock();
        try {
            return loadBalancer.getType();
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * 重新初始化负载均衡器
     */
    public void reinitializeLoadBalancer() {
        lock.writeLock().lock();
        try {
            loadBalancer.init();
        } finally {
            lock.writeLock().unlock();
        }
    }
}
