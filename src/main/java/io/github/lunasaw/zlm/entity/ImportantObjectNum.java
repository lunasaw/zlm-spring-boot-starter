package io.github.lunasaw.zlm.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class ImportantObjectNum {

    @JSONField(name = "Buffer")
    private Integer buffer;

    @JSONField(name = "RtpPacket")
    private Integer rtpPacket;

    @JSONField(name = "Frame")
    private Integer frame;

    @JSONField(name = "RtmpPacket")
    private Integer rtmpPacket;

    @JSONField(name = "TcpSession")
    private Integer tcpSession;

    @JSONField(name = "UdpServer")
    private Integer udpServer;

    @JSONField(name = "TcpServer")
    private Integer tcpServer;

    @JSONField(name = "FrameImp")
    private Integer frameImp;

    @JSONField(name = "BufferList")
    private Integer bufferList;

    @JSONField(name = "BufferRaw")
    private Integer bufferRaw;

    @JSONField(name = "MediaSource")
    private Integer mediaSource;

    @JSONField(name = "MultiMediaSourceMuxer")
    private Integer multiMediaSourceMuxer;

    @JSONField(name = "TcpClient")
    private Integer tcpClient;

    @JSONField(name = "BufferLikeString")
    private Integer bufferLikeString;

    @JSONField(name = "Socket")
    private Integer socket;

    @JSONField(name = "UdpSession")
    private Integer udpSession;
}