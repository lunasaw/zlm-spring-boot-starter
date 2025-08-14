package io.github.lunasaw.zlm.config;

import io.github.lunasaw.zlm.enums.LoadBalancerEnums;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * @author luna
 * @version 1.0
 * @date 2023/12/2
 * @description:
 */
@ConfigurationProperties(prefix = "zlm")
@Data
public class ZlmProperties implements InitializingBean {
    /**
     * 对外NodeMap
     */
    public static Map<String, ZlmNode> nodeMap = new ConcurrentHashMap<>();

    private boolean enable = true;
    private boolean hookEnable = true;
    private LoadBalancerEnums balance = LoadBalancerEnums.ROUND_ROBIN;
    private List<ZlmNode> nodes = new CopyOnWriteArrayList<>();


    public Map<String, ZlmNode> getNodeMap() {
        return nodeMap;
    }

    public List<ZlmNode> getNodes() {
        return this.nodes;
    }

    @Override
    public void afterPropertiesSet() {
        // 初始化节点映射，只包含启用的节点
        if (this.nodes != null && !this.nodes.isEmpty()) {
            nodeMap = this.nodes.stream()
                    .filter(ZlmNode::isEnabled)
                    .collect(Collectors.toMap(ZlmNode::getServerId, node -> node));
        }
    }
}
