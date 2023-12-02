package io.github.lunasaw.zlm.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * Class representing the input structure.
 */
@Data
public class TcpLink {
    /**
     * 该tcp链接唯一id
     */
    @JSONField(name = "id")
    public String id;
    /**
     * 本机网卡ip
     */
    @JSONField(name = "local_ip")
    public String localIp;
    /**
     * 本机端口号 (这是个rtmp播放器或推流器)
     */
    @JSONField(name = "local_port")
    public int    localPort;
    /**
     * 客户端ip
     */
    @JSONField(name = "peer_ip")
    public String peerIp;
    /**
     * 客户端端口号
     */
    @JSONField(name = "peer_port")
    public int    peerPort;
    /**
     * 客户端TCPSession typeid
     */
    @JSONField(name = "typeid")
    public String typeId;
}
