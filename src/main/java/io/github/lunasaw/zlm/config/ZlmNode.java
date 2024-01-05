package io.github.lunasaw.zlm.config;

import lombok.Data;

/**
 * @author luna
 * @version 1.0
 * @date 2023/12/2
 * @description: zlm节点配置
 */
@Data
public class ZlmNode {

    /**
     * The id of this node.
     */
    private String serverId = "zlm";

    /**
     * The host of this node. eg: <a href="http://127.0.0.1:9092">node</a>
     */
    private String host = "http://127.0.0.1:9092";

    /**
     * The secret of this host.
     */
    private String secret;

    /**
     * Whether enable this host.
     */
    private boolean enabled = true;

    /**
     * Whether enable hook.
     */
    private boolean hookEnabled = true;

    /**
     * The weight of this host.
     */
    private int weight = 100;
}
