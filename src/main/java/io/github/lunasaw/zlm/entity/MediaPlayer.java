package io.github.lunasaw.zlm.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

/**
 * This class represents a WebRTC session with various properties.
 */
@Data
public class MediaPlayer {

    /**
     * The unique identifier for the session, e.g., "3-309".
     */
    @JSONField(name = "identifier")
    private String identifier;

    /**
     * The local IP address. "::" is a shorthand in IPv6 for representing multiple groups of zeros.
     */
    @JSONField(name = "local_ip")
    private String localIp;

    /**
     * The local port number.
     */
    @JSONField(name = "local_port")
    private int    localPort;

    /**
     * The IP address of the peer in the session.
     */
    @JSONField(name = "peer_ip")
    private String peerIp;

    /**
     * The port number of the peer.
     */
    @JSONField(name = "peer_port")
    private int    peerPort;

    /**
     * The type identifier for the session, indicating it's a WebRTC session from MediaKit.
     */
    @JSONField(name = "typeid")
    private String typeId;

    // Getters and setters...
}
