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

    public static List<ZlmNode> nodes = new CopyOnWriteArrayList<>();

    private boolean enable = true;

    private LoadBalancerEnums balance = LoadBalancerEnums.ROUND_ROBIN;


    public static void addNode(ZlmNode zlmNode) {
        if (zlmNode != null) {
            nodes.add(zlmNode);
            nodeMap.put(zlmNode.getServerId(), zlmNode);
        }
    }

    public Map<String, ZlmNode> getNodeMap() {
        return nodeMap;
    }

    public List<ZlmNode> getNodes() {
        return nodes;
    }

    @Override
    public void afterPropertiesSet() {
        nodeMap = nodes.stream().filter(ZlmNode::isEnabled).collect(Collectors.toMap(ZlmNode::getServerId, node -> node));
    }
}
