package io.github.lunasaw.zlm.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * 这个类代表了具有各种属性的会话。
 */
@Data
public class MediaInfo {

    /**
     * 状态码。
     */
    @JSONField(name = "code")
    private int         code;

    /**
     * 会话是否在线。
     */
    @JSONField(name = "online")
    private boolean     online;

    /**
     * 本协议的观看人数。
     */
    @JSONField(name = "readerCount")
    private int         readerCount;

    /**
     * 观看总人数，包括hls/rtsp/rtmp/http-flv/ws-flv。
     */
    @JSONField(name = "totalReaderCount")
    private int         totalReaderCount;

    /**
     * 轨道列表。
     */
    @JSONField(name = "tracks")
    private List<Track> tracks;

    // Getters and setters...

    /**
     * 这个类代表了具有各种属性的轨道。
     */

}
