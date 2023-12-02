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
    public OriginSock originSock;
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


    /**
     * Class representing the 'originSock' object in the JSON structure.
     */
    @Data
    public static class OriginSock {
        public String identifier;
        public String local_ip;
        public int local_port;
        public String peer_ip;
        public int peer_port;
    }

    /**
     * Class representing the 'tracks' array in the JSON structure.
     */
    @Data
    public static class Track {
        /**
         * 音频通道数
         */
        public int channels;
        /**
         * H264 = 0, H265 = 1, AAC = 2, G711A = 3, G711U = 4
         */
        public int codec_id;
        /**
         * 编码类型名称
         */
        public String codec_id_name;
        /**
         * Video = 0, Audio = 1
         */
        public int codec_type;
        /**
         * 轨道是否准备就绪
         */
        public boolean ready;
        /**
         * 累计接收帧数
         */
        public int frames;
        /**
         * 音频采样位数
         */
        public int sample_bit;
        /**
         * 音频采样率
         */
        public int sample_rate;
        /**
         * 视频fps
         */
        public int fps;
        /**
         * gop间隔时间，单位毫秒
         */
        public int gop_interval_ms;
        /**
         * gop大小，单位帧数
         */
        public int gop_size;
        /**
         * 累计接收关键帧数
         */
        public int key_frames;
        /**
         * 视频高
         */
        public int height;
        /**
         * 视频宽
         */
        public int width;
    }
}