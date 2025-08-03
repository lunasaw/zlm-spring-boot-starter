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

    /**
     * 存活时间，单位秒。
     */
    @JSONField(name = "aliveSecond")
    private int aliveSecond;

    /**
     * 应用名。
     */
    @JSONField(name = "app")
    private String app;

    /**
     * 数据产生速度，单位byte/s。
     */
    @JSONField(name = "bytesSpeed")
    private long bytesSpeed;

    /**
     * 数据产生时间戳。
     */
    @JSONField(name = "createStamp")
    private long createStamp;

    /**
     * 是否正在录制HLS。
     */
    @JSONField(name = "isRecordingHLS")
    private boolean isRecordingHLS;

    /**
     * 是否正在录制MP4。
     */
    @JSONField(name = "isRecordingMP4")
    private boolean isRecordingMP4;

    /**
     * 源套接字信息。
     */
    @JSONField(name = "originSock")
    private OriginSock originSock;

    /**
     * 源类型编号。
     */
    @JSONField(name = "originType")
    private int originType;

    /**
     * 源类型字符串。
     */
    @JSONField(name = "originTypeStr")
    private String originTypeStr;

    /**
     * 源URL。
     */
    @JSONField(name = "originUrl")
    private String originUrl;

    /**
     * 参数。
     */
    @JSONField(name = "params")
    private String params;

    /**
     * 协议。
     */
    @JSONField(name = "schema")
    private String schema;

    /**
     * 流ID。
     */
    @JSONField(name = "stream")
    private String stream;

    /**
     * 累计接收数据总字节数。
     */
    @JSONField(name = "totalBytes")
    private long totalBytes;

    /**
     * 虚拟主机。
     */
    @JSONField(name = "vhost")
    private String vhost;

    // Getters and setters...

    /**
     * 这个类代表了具有各种属性的轨道。
     */

}
