package io.github.lunasaw.zlm.node;

import io.github.lunasaw.zlm.config.ZlmNode;

/**
 * @author luna
 * @date 2024/1/5
 */
public interface LoadBalancer {

    ZlmNode selectNode(String key);

    String getType();
}
