package io.github.lunasaw.zlm.hook.param;

import lombok.Data;

/**
 * zlm hook事件中的on_play事件的参数
 * 
 * @author luna
 */
@Data
public class OnServerKeepaliveHookParam extends HookParam {

    private KeepLiveData data;

    @Data
    public static class KeepLiveData {
        /**
         * {
         * "Buffer" : 12,
         * "BufferLikeString" : 0,
         * "BufferList" : 0,
         * "BufferRaw" : 12,
         * "Frame" : 0,
         * "FrameImp" : 0,
         * "MediaSource" : 0,
         * "MultiMediaSourceMuxer" : 0,
         * "RtmpPacket" : 0,
         * "RtpPacket" : 0,
         * "Socket" : 108,
         * "TcpClient" : 0,
         * "TcpServer" : 96,
         * "TcpSession" : 0,
         * "UdpServer" : 12,
         * "UdpSession" : 0
         * }
         */
        private int Buffer;
        private int BufferLikeString;
        private int BufferList;
        private int BufferRaw;
        private int Frame;
        private int FrameImp;
        private int MediaSource;
        private int MultiMediaSourceMuxer;
        private int RtmpPacket;
        private int RtpPacket;
        private int Socket;
        private int TcpClient;
        private int TcpServer;
        private int TcpSession;
        private int UdpServer;
        private int UdpSession;
    }
}
