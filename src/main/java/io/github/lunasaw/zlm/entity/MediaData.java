package io.github.lunasaw.zlm.entity;


import lombok.Data;

/**
 * Class representing the 'data' array in the JSON structure.
 */
@Data
public class MediaData {
    /**
     * 应用名
     */
    public String app;
    /**
     * 本协议观看人数
     */
    public int readerCount;
    /**
     * 观看总人数，包括hls/rtsp/rtmp/http-flv/ws-flv
     */
    public int totalReaderCount;
    /**
     * 协议
     */
    public String schema;
    /**
     * 流id
     */
    public String stream;
    /**
     * 客户端和服务器网络信息，可能为null类型
     */
    public MediaPlayer originSock;
    /**
     * 产生源类型，包括 unknown = 0,rtmp_push=1,rtsp_push=2,rtp_push=3,pull=4,ffmpeg_pull=5,mp4_vod=6,device_chn=7
     */
    public int originType;
    public String originTypeStr;
    /**
     * 产生源的url
     */
    public String originUrl;
    /**
     * GMT unix系统时间戳，单位秒
     */
    public long createStamp;
    /**
     * 存活时间，单位秒
     */
    public int aliveSecond;
    /**
     * 数据产生速度，单位byte/s
     */
    public int bytesSpeed;
    /**
     * 音视频轨道
     */
    public Track[] tracks;
    /**
     * 虚拟主机名
     */
    public String vhost;
}