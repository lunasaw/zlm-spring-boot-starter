package io.github.lunasaw.zlm.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

/**
 * 源套接字信息类。
 *
 * @author luna
 */
@Data
public class OriginSock {

    /**
     * 连接标识符。
     */
    @JSONField(name = "identifier")
    private String identifier;

    /**
     * 本地IP地址。
     */
    @JSONField(name = "local_ip")
    private String localIp;

    /**
     * 本地端口。
     */
    @JSONField(name = "local_port")
    private int localPort;

    /**
     * 对端IP地址。
     */
    @JSONField(name = "peer_ip")
    private String peerIp;

    /**
     * 对端端口。
     */
    @JSONField(name = "peer_port")
    private int peerPort;
}