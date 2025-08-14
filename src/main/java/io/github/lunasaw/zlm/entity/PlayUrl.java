package io.github.lunasaw.zlm.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

/**
 * @author luna
 * @version 1.0
 * @date 2023/12/2
 * @description: 播放地址模型，包含不同协议的播放URL
 */
@Data
public class PlayUrl {

    /**
     * RTSP协议播放地址
     */
    @JSONField(name = "rtsp")
    private String rtsp;

    /**
     * RTMP协议播放地址
     */
    @JSONField(name = "rtmp")
    private String rtmp;

    /**
     * HTTP-FLV协议播放地址
     */
    @JSONField(name = "http_flv")
    private String httpFlv;

    /**
     * WebSocket-FLV协议播放地址
     */
    @JSONField(name = "ws_flv")
    private String wsFlv;

    /**
     * HLS协议播放地址
     */
    @JSONField(name = "hls")
    private String hls;

    /**
     * HTTP-TS协议播放地址
     */
    @JSONField(name = "http_ts")
    private String httpTs;

    /**
     * WebSocket-TS协议播放地址
     */
    @JSONField(name = "ws_ts")
    private String wsTs;

    /**
     * HTTP-fMP4协议播放地址
     */
    @JSONField(name = "http_fmp4")
    private String httpFmp4;

    /**
     * WebSocket-fMP4协议播放地址
     */
    @JSONField(name = "ws_fmp4")
    private String wsFmp4;
}