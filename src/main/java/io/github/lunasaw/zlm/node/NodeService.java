package io.github.lunasaw.zlm.node;

import io.github.lunasaw.zlm.config.ZlmNode;
import io.github.lunasaw.zlm.config.ZlmProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

/**
 * @author luna
 * @date 2023/12/4
 */
@Service
@ConditionalOnBean(ZlmProperties.class)
public class NodeService {

    @Autowired
    private LoadBalancer loadBalancer;


    public void addNode(ZlmNode zlmNode) {
        ZlmProperties.addNode(zlmNode);
    }

    public ZlmNode selectNode() {
        return loadBalancer.selectNode(null);
    }

    public ZlmNode selectNode(String key) {
        return loadBalancer.selectNode(key);
    }
}
