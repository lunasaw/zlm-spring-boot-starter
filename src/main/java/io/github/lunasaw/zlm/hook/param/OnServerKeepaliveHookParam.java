package io.github.lunasaw.zlm.hook.param;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * zlm hook事件中的on_play事件的参数
 * 
 * @author luna
 */
@Data
public class OnServerKeepaliveHookParam extends HookParam {

    @JSONField(name = "data")
    @JsonProperty("data")
    private KeepLiveData data;

    @JsonProperty("hook_index")
    @JSONField(name = "hook_index")
    private String hookIndex;

    @Data
    public static class KeepLiveData {
        /**
         * 缓冲区
         */
        @JSONField(name = "Buffer")
        @JsonProperty("Buffer")
        private int buffer;

        /**
         * 类似字符串的缓冲区
         */
        @JSONField(name = "BufferLikeString")
        @JsonProperty("BufferLikeString")
        private int bufferLikeString;

        /**
         * 缓冲区列表
         */
        @JSONField(name = "BufferList")
        @JsonProperty("BufferList")
        private int bufferList;

        /**
         * 原始缓冲区
         */
        @JSONField(name = "BufferRaw")
        @JsonProperty("BufferRaw")
        private int bufferRaw;

        /**
         * 框架
         */
        @JSONField(name = "Frame")
        @JsonProperty("Frame")
        private int frame;

        /**
         * 框架实现
         */
        @JSONField(name = "FrameImp")
        @JsonProperty("FrameImp")
        private int frameImp;

        /**
         * 媒体源
         */
        @JSONField(name = "MediaSource")
        @JsonProperty("MediaSource")
        private int mediaSource;

        /**
         * 多媒体源复用器
         */
        @JSONField(name = "MultiMediaSourceMuxer")
        @JsonProperty("MultiMediaSourceMuxer")
        private int multiMediaSourceMuxer;

        /**
         * Rtmp数据包
         */
        @JSONField(name = "RtmpPacket")
        @JsonProperty("RtmpPacket")
        private int rtmpPacket;

        /**
         * Rtp数据包
         */
        @JSONField(name = "RtpPacket")
        @JsonProperty("RtpPacket")
        private int rtpPacket;

        /**
         * 套接字
         */
        @JSONField(name = "Socket")
        @JsonProperty("Socket")
        private int socket;

        /**
         * Tcp客户端
         */
        @JSONField(name = "TcpClient")
        @JsonProperty("TcpClient")
        private int tcpClient;

        /**
         * Tcp服务器
         */
        @JSONField(name = "TcpServer")
        @JsonProperty("TcpServer")
        private int tcpServer;

        /**
         * Tcp会话
         */
        @JSONField(name = "TcpSession")
        @JsonProperty("TcpSession")
        private int tcpSession;

        /**
         * Udp服务器
         */
        @JSONField(name = "UdpServer")
        @JsonProperty("UdpServer")
        private int udpServer;

        /**
         * Udp会话
         */
        @JSONField(name = "UdpSession")
        @JsonProperty("UdpSession")
        private int udpSession;
    }
}
