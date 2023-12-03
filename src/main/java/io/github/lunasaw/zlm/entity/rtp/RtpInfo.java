package io.github.lunasaw.zlm.entity.rtp;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 * 获取rtp代理时的某路ssrc rtp信息。
 */
@Data
public class RtpInfo {

    /**
     * 状态码。
     */
    @JSONField(name = "code")
    private int     code;

    /**
     * 会话是否存在。
     */
    @JSONField(name = "exist")
    private boolean exist;

    /**
     * 推流客户端ip。
     */
    @JSONField(name = "peer_ip")
    private String  peerIp;

    /**
     * 客户端端口号。
     */
    @JSONField(name = "peer_port")
    private int     peerPort;

    /**
     * 本地监听的网卡ip。
     */
    @JSONField(name = "local_ip")
    private String  localIp;

    /**
     * 本地端口号。
     */
    @JSONField(name = "local_port")
    private int     localPort;

    // Getters and setters...
}
