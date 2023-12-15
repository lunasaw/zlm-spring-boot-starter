package io.github.lunasaw.zlm.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ServerNodeConfig {
    /**
     * 
     * The maximum size of RTP packets.
     */
    @JsonProperty("rtp.rtpMaxSize")
    private String rtpRtpMaxSize;
    /**
     * 
     * Whether to enable HLS demand.
     */
    @JsonProperty("protocol.hls_demand")
    private String protocolHlsDemand;
    /**
     * 
     * The Opus payload type used by the RTP proxy.
     */
    @JsonProperty("rtp_proxy.opus_pt")
    private String rtpProxyOpusPt;
    /**
     * 
     * The timeout value for the RTP proxy in seconds.
     */
    @JsonProperty("rtp_proxy.timeoutSec")
    private String rtpProxyTimeoutSec;
    /**
     * 
     * The port used for RTMP.
     */
    @JsonProperty("rtmp.port")
    private String rtmpPort;
    /**
     * 
     * The action to take when the IP address is not found.
     */
    @JsonProperty("hook.on_ip_not_found")
    private String hookOnIpNotFound;
    /**
     * 
     * Whether to allow file repetition during recording.
     */
    @JsonProperty("record.fileRepeat")
    private String recordFileRepeat;
    /**
     * 
     * The threshold for the maximum flow.
     */
    @JsonProperty("general.flowThreshold")
    private String generalFlowThreshold;
    /**
     * 
     * The transport type used for RTP in RTSP.
     */
    @JsonProperty("rtsp.rtpTransportType")
    private String rtspRtpTransportType;
    /**
     * 
     * The delay time for retrying hooks in seconds.
     */
    @JsonProperty("hook.retry_delay")
    private String hookRetryDelay;
    /**
     * 
     * The root path for HTTP requests.
     */
    @JsonProperty("http.rootPath")
    private String httpRootPath;
    /**
     * 
     * The time in seconds to keep the RTSP connection alive.
     */
    @JsonProperty("rtsp.keepAliveSecond")
    private String rtspKeepAliveSecond;
    /**
     * 
     * The action to take when the server is started.
     */
    @JsonProperty("hook.on_server_started")
    private String hookOnServerStarted;
    /**
     * 
     * The default snapshot for the API.
     */
    @JsonProperty("api.defaultSnap")
    private String apiDefaultSnap;
    /**
     * 
     * The origin URL for the cluster.
     */
    @JsonProperty("cluster.origin_url")
    private String clusterOriginUrl;
    /**
     * 
     * The port used for HTTP.
     */
    @JsonProperty("http.port")
    private String httpPort;
    /**
     * 
     * The virtual path for HTTP requests.
     */
    @JsonProperty("http.virtualPath")
    private String httpVirtualPath;
    /**
     * 
     * The time in seconds to keep the HTTP connection alive.
     */
    @JsonProperty("http.keepAliveSecond")
    private String httpKeepAliveSecond;
    /**
     * 
     * The log file for FFmpeg.
     */
    @JsonProperty("ffmpeg.log")
    private String ffmpegLog;
    /**
     * 
     * The action to take when a flow report is received.
     */
    @JsonProperty("hook.on_flow_report")
    private String hookOnFlowReport;
    /**
     * 
     * Whether to enable directory listing for HTTP requests.
     */
    @JsonProperty("http.dirMenu")
    private String httpDirMenu;
    /**
     * 
     * Whether to use direct proxy for RTSP.
     */
    @JsonProperty("rtsp.directProxy")
    private String rtspDirectProxy;
    /**
     * 
     * The command used for FFmpeg.
     */
    @JsonProperty("ffmpeg.cmd")
    private String ffmpegCmd;
    /**
     * 
     * Whether to enable low latency for RTP.
     */
    @JsonProperty("rtp.lowLatency")
    private String rtpLowLatency;
    /**
     * 
     * Whether to enable RTSP.
     */
    @JsonProperty("protocol.enable_rtsp")
    private String protocolEnableRtsp;
    /**
     * 
     * The port used for RTSP.
     */
    @JsonProperty("rtsp.port")
    private String rtspPort;
    /**
     * 
     * The SSL port used for RTMP.
     */
    @JsonProperty("rtmp.sslport")
    private String rtmpSslport;
    /**
     * 
     * The save path for HLS.
     */
    @JsonProperty("protocol.hls_save_path")
    private String protocolHlsSavePath;
    /**
     * 
     * The character set used for HTTP requests.
     */
    @JsonProperty("http.charSet")
    private String httpCharSet;
    /**
     * 
     * The size of the send buffer for HTTP requests.
     */
    @JsonProperty("http.sendBufSize")
    private String httpSendBufSize;
    /**
     * 
     * Whether to broadcast recorded TS files.
     */
    @JsonProperty("hls.broadcastRecordTs")
    private String hlsBroadcastRecordTs;
    /**
     * 
     * Whether to enable API debugging.
     */
    @JsonProperty("api.apiDebug")
    private String apiApiDebug;
    /**
     * 
     * The time in milliseconds for merging writes.
     */
    @JsonProperty("general.mergeWriteMS")
    private String generalMergeWriteMS;
    /**
     * 
     * The suffixes to forbid caching for HTTP requests.
     */
    @JsonProperty("http.forbidCacheSuffix")
    private String httpForbidCacheSuffix;
    /**
     * 
     * The action to take when a resource is not found for HTTP requests.
     */
    @JsonProperty("http.notFound")
    private String httpNotFound;
    /**
     * 
     * The number of times to retry hooks.
     */
    @JsonProperty("hook.retry")
    private String hookRetry;
    /**
     * 
     * The application name for recording.
     */
    @JsonProperty("record.appName")
    private String recordAppName;
    /**
     * 
     * The buffer size for HLS files.
     */
    @JsonProperty("hls.fileBufSize")
    private String hlsFileBufSize;
    /**
     * 
     * The timeout value for hooks in seconds.
     */
    @JsonProperty("hook.timeoutSec")
    private String hookTimeoutSec;
    /**
     * 
     * The SSL port used for RTSP.
     */
    @JsonProperty("rtsp.sslport")
    private String rtspSslport;
    /**
     * 
     * The delay time for deleting HLS files in seconds.
     */
    @JsonProperty("hls.deleteDelaySec")
    private String hlsDeleteDelaySec;
    /**
     * 
     * The action to take when the RTP server times out.
     */
    @JsonProperty("hook.on_rtp_server_timeout")
    private String hookOnRtpServerTimeout;
    /**
     * 
     * The action to take when sending RTP is stopped.
     */
    @JsonProperty("hook.on_send_rtp_stopped")
    private String hookOnSendRtpStopped;
    /**
     * 
     * The action to take when recording MP4 files.
     */
    @JsonProperty("hook.on_record_mp4")
    private String hookOnRecordMp4;
    /**
     * 
     * The interval time for sending keepalive messages for hooks in seconds.
     */
    @JsonProperty("hook.alive_interval")
    private String hookAliveInterval;
    /**
     * 
     * The time in seconds for the RTMP handshake.
     */
    @JsonProperty("rtmp.handshakeSecond")
    private String rtmpHandshakeSecond;
    /**
     * 
     * The schemas to use for stream changes in hooks.
     */
    @JsonProperty("hook.stream_changed_schemas")
    private String hookStreamChangedSchemas;
    /**
     * 
     * The external IP address for RTC.
     */
    @JsonProperty("rtc.externIP")
    private String rtcExternIP;
    /**
     * 
     * The bit rate for REMB in RTC.
     */
    @JsonProperty("rtc.rembBitRate")
    private String rtcRembBitRate;
    /**
     * 
     * The time in milliseconds for waiting for a stream reader.
     */
    @JsonProperty("general.streamNoneReaderDelayMS")
    private String generalStreamNoneReaderDelayMS;
    /**
     * 
     * The maximum duration for MP4 files in seconds.
     */
    @JsonProperty("protocol.mp4_max_second")
    private String protocolMp4MaxSecond;
    /**
     * 
     * The action to take when publishing a stream.
     */
    @JsonProperty("hook.on_publish")
    private String hookOnPublish;
    /**
     * 
     * The port used for the RTP proxy.
     */
    @JsonProperty("rtp_proxy.port")
    private String rtpProxyPort;
    /**
     * 
     * The SSL port used for HTTP.
     */
    @JsonProperty("http.sslport")
    private String httpSslport;
    /**
     * 
     * The MTU size for audio packets in RTP.
     */
    @JsonProperty("rtp.audioMtuSize")
    private String rtpAudioMtuSize;
    /**
     * 
     * Whether to check for NVIDIA devices.
     */
    @JsonProperty("general.check_nvidia_dev")
    private String generalCheckNvidiaDev;
    /**
     * 
     * Whether to enable fast start for recording.
     */
    @JsonProperty("record.fastStart")
    private String recordFastStart;
    /**
     * 
     * The action to take when a stream is not found in hooks.
     */
    @JsonProperty("hook.on_stream_not_found")
    private String hookOnStreamNotFound;
    /**
     * 
     * The port range used for the RTP proxy.
     */
    @JsonProperty("rtp_proxy.port_range")
    private String rtpProxyPortRange;
    /**
     * 
     * Whether to enable RTMP.
     */
    @JsonProperty("protocol.enable_rtmp")
    private String protocolEnableRtmp;
    /**
     * 
     * The timeout value for SRT in seconds.
     */
    @JsonProperty("srt.timeoutSec")
    private String srtTimeoutSec;
    /**
     * 
     * The time in seconds for the RTSP handshake.
     */
    @JsonProperty("rtsp.handshakeSecond")
    private String rtspHandshakeSecond;
    /**
     * 
     * The duration for each HLS segment in seconds.
     */
    @JsonProperty("hls.segDur")
    private String hlsSegDur;
    /**
     * 
     * Whether to use MP4 as a player for the protocol.
     */
    @JsonProperty("protocol.mp4_as_player")
    private String protocolMp4AsPlayer;
    /**
     * 
     * The secret key for the API.
     */
    @JsonProperty("api.secret")
    private String apiSecret;
    /**
     * 
     * The number of HLS segments to retain.
     */
    @JsonProperty("hls.segRetain")
    private String hlsSegRetain;
    /**
     * 
     * Whether to enable demand for RTSP.
     */
    @JsonProperty("protocol.rtsp_demand")
    private String protocolRtspDemand;
    /**
     * 
     * The port used for SRT.
     */
    @JsonProperty("srt.port")
    private String srtPort;
    /**
     * 
     * The packet buffer size for SRT.
     */
    @JsonProperty("srt.pktBufSize")
    private String srtPktBufSize;
    /**
     * 
     * Whether to enable GOP caching for the RTP proxy.
     */
    @JsonProperty("rtp_proxy.gop_cache")
    private String rtpProxyGopCache;
    /**
     * 
     * The maximum size of requests for the shell.
     */
    @JsonProperty("shell.maxReqSize")
    private String shellMaxReqSize;
    /**
     * 
     * Whether to enable snapshots for FFmpeg.
     */
    @JsonProperty("ffmpeg.snap")
    private String ffmpegSnap;
    /**
     * 
     * The maximum time in milliseconds to wait for a stream reader.
     */
    @JsonProperty("general.maxStreamWaitMS")
    private String generalMaxStreamWaitMS;
    /**
     * 
     * The maximum multicast address.
     */
    @JsonProperty("multicast.addrMax")
    private String multicastAddrMax;
    /**
     * 
     * The time in milliseconds to wait for adding a track.
     */
    @JsonProperty("general.wait_add_track_ms")
    private String generalWaitAddTrackMs;
    /**
     * 
     * Whether to allow cross-domain requests for HTTP.
     */
    @JsonProperty("http.allow_cross_domains")
    private String httpAllowCrossDomains;
    /**
     * 
     * Whether to modify the timestamp for the protocol.
     */
    @JsonProperty("protocol.modify_stamp")
    private String protocolModifyStamp;
    /**
     * 
     * The MTU size for video packets in RTP.
     */
    @JsonProperty("rtp.videoMtuSize")
    private String rtpVideoMtuSize;
    /**
     * 
     * The root directory for snapshots in the API.
     */
    @JsonProperty("api.snapRoot")
    private String apiSnapRoot;
    /**
     * 
     * Whether to enable audio for the protocol.
     */
    @JsonProperty("protocol.enable_audio")
    private String protocolEnableAudio;
    /**
     * 
     * The action to take when the server keeps alive.
     */
    @JsonProperty("hook.on_server_keepalive")
    private String hookOnServerKeepalive;
    /**
     * 
     * The minimum multicast address.
     */
    @JsonProperty("multicast.addrMin")
    private String multicastAddrMin;
    /**
     * 
     * Whether to enable demand for TS.
     */
    @JsonProperty("protocol.ts_demand")
    private String protocolTsDemand;
    /**
     * 
     * Whether to enable FMP4 for the protocol.
     */
    @JsonProperty("protocol.enable_fmp4")
    private String protocolEnableFmp4;
    /**
     * 
     * Whether to enable low latency for RTSP.
     */
    @JsonProperty("rtsp.lowLatency")
    private String rtspLowLatency;
    /**
     * 
     * The IP range to allow for HTTP requests.
     */
    @JsonProperty("http.allow_ip_range")
    private String httpAllowIpRange;
    /**
     * 
     * The action to take when the RTSP realm is accessed in hooks.
     */
    @JsonProperty("hook.on_rtsp_realm")
    private String hookOnRtspRealm;
    /**
     * 
     * The action to take when a stream is changed in hooks.
     */
    @JsonProperty("hook.on_stream_changed")
    private String hookOnStreamChanged;
    /**
     * 
     * The header to use for forwarded IP addresses in HTTP requests.
     */
    @JsonProperty("http.forwarded_ip_header")
    private String httpForwardedIpHeader;
    /**
     * 
     * The H.265 payload type used by the RTP proxy.
     */
    @JsonProperty("rtp_proxy.h265_pt")
    private String rtpProxyH265Pt;
    /**
     * 
     * The action to take when an MP4 file is deleted in hooks.
     */
    @JsonProperty("hook.on_del_mp4")
    private String hookOnDelMp4;
    /**
     * 
     * Whether to enable HLS for the protocol.
     */
    @JsonProperty("protocol.enable_hls")
    private String protocolEnableHls;
    /**
     * 
     * Whether to enable MP4 for the protocol.
     */
    @JsonProperty("protocol.enable_mp4")
    private String protocolEnableMp4;
    /**
     * 
     * The port used for RTC.
     */
    @JsonProperty("rtc.port")
    private String rtcPort;
    /**
     * 
     * Whether to enable demand for FMP4.
     */
    @JsonProperty("protocol.fmp4_demand")
    private String protocolFmp4Demand;
    /**
     * 
     * The time in milliseconds for each sample during recording.
     */
    @JsonProperty("record.sampleMS")
    private String recordSampleMS;
    /**
     * 
     * The port used for the shell.
     */
    @JsonProperty("shell.port")
    private String shellPort;
    /**
     * 
     * The action to take when logging in to the shell.
     */
    @JsonProperty("hook.on_shell_login")
    private String hookOnShellLogin;
    /**
     * 
     * The number of times to retry connecting to the cluster.
     */
    @JsonProperty("cluster.retry_count")
    private String clusterRetryCount;
    /**
     * 
     * Whether to enable virtual hosts.
     */
    @JsonProperty("general.enableVhost")
    private String generalEnableVhost;
    /**
     * 
     * The size of the unready frame cache.
     */
    @JsonProperty("general.unready_frame_cache")
    private String generalUnreadyFrameCache;
    /**
     * 
     * The preferred video codec for RTC.
     */
    @JsonProperty("rtc.preferredCodecV")
    private String rtcPreferredCodecV;
    /**
     * 
     * The H.264 payload type used by the RTP proxy.
     */
    @JsonProperty("rtp_proxy.h264_pt")
    private String rtpProxyH264Pt;
    /**
     * 
     * Whether to automatically close connections for the protocol.
     */
    @JsonProperty("protocol.auto_close")
    private String protocolAutoClose;
    /**
     * 
     * The latency multiplier for SRT.
     */
    @JsonProperty("srt.latencyMul")
    private String srtLatencyMul;
    /**
     * 
     * The action to take when the server is exited.
     */
    @JsonProperty("hook.on_server_exited")
    private String hookOnServerExited;
    /**
     * 
     * Whether to reset when replaying.
     */
    @JsonProperty("general.resetWhenRePlay")
    private String generalResetWhenRePlay;
    /**
     * 
     * The save path for MP4 files in the protocol.
     */
    @JsonProperty("protocol.mp4_save_path")
    private String protocolMp4SavePath;
    /**
     * 
     * The time in milliseconds for continuing to push data for the protocol.
     */
    @JsonProperty("protocol.continue_push_ms")
    private String protocolContinuePushMs;
    /**
     * 
     * The dump directory for the RTP proxy.
     */
    @JsonProperty("rtp_proxy.dumpDir")
    private String rtpProxyDumpDir;
    /**
     * 
     * The payload type used for PS in the RTP proxy.
     */
    @JsonProperty("rtp_proxy.ps_pt")
    private String rtpProxyPsPt;
    /**
     * 
     * Whether to enable hooks.
     */
    @JsonProperty("hook.enable")
    private String hookEnable;
    /**
     * 
     * The timeout value for RTC in seconds.
     */
    @JsonProperty("rtc.timeoutSec")
    private String rtcTimeoutSec;
    /**
     * 
     * The preferred audio codec for RTC.
     */
    @JsonProperty("rtc.preferredCodecA")
    private String rtcPreferredCodecA;
    /**
     * 
     * The number of HLS segments to keep.
     */
    @JsonProperty("hls.segKeep")
    private String hlsSegKeep;
    /**
     * 
     * The TTL value for UDP multicast.
     */
    @JsonProperty("multicast.udpTTL")
    private String multicastUdpTTL;
    /**
     * 
     * Whether to enable STAP-A for H.264 in RTP.
     */
    @JsonProperty("rtp.h264_stap_a")
    private String rtpH264StapA;
    /**
     * 
     * The action to take when there are no stream readers in hooks.
     */
    @JsonProperty("hook.on_stream_none_reader")
    private String hookOnStreamNoneReader;
    /**
     * 
     * The action to take when recording TS files in hooks.
     */
    @JsonProperty("hook.on_record_ts")
    private String hookOnRecordTs;
    /**
     * 
     * The path to the FFmpeg binary.
     */
    @JsonProperty("ffmpeg.bin")
    private String ffmpegBin;
    /**
     * 
     * Whether to enable demand for TS in the protocol.
     */
    @JsonProperty("protocol.enable_ts")
    private String protocolEnableTs;
    /**
     * 
     * Whether to enable HLS FMP4 in the protocol.
     */
    @JsonProperty("protocol.enable_hls_fmp4")
    private String protocolEnableHlsFmp4;
    /**
     * 
     * The number of HLS segments.
     */
    @JsonProperty("hls.segNum")
    private String hlsSegNum;
    /**
     * 
     * The maximum size of requests for HTTP.
     */
    @JsonProperty("http.maxReqSize")
    private String httpMaxReqSize;
    /**
     * 
     * The TCP port used for RTC.
     */
    @JsonProperty("rtc.tcpPort")
    private String rtcTcpPort;
    /**
     * 
     * The timeout value for the cluster in seconds.
     */
    @JsonProperty("cluster.timeout_sec")
    private String clusterTimeoutSec;
    /**
     * 
     * Whether to enable FFmpeg logging.
     */
    @JsonProperty("general.enable_ffmpeg_log")
    private String generalEnableFfmpegLog;
    /**
     * 
     * The ID of the media server.
     */
    @JsonProperty("general.mediaServerId")
    private String generalMediaServerId;
    /**
     * 
     * The action to take when accessing HTTP in hooks.
     */
    @JsonProperty("hook.on_http_access")
    private String hookOnHttpAccess;
    /**
     * 
     * The time in milliseconds to wait for a track to be ready.
     */
    @JsonProperty("general.wait_track_ready_ms")
    private String generalWaitTrackReadyMs;
    /**
     * 
     * Whether to enable basic authentication for RTSP.
     */
    @JsonProperty("rtsp.authBasic")
    private String rtspAuthBasic;
    /**
     * 
     * The action to take when authenticating RTSP in hooks.
     */
    @JsonProperty("hook.on_rtsp_auth")
    private String hookOnRtspAuth;
    /**
     * 
     * Whether to enable demand for RTMP in the protocol.
     */
    @JsonProperty("protocol.rtmp_demand")
    private String protocolRtmpDemand;
    /**
     * 
     * Whether to add mute audio for the protocol.
     */
    @JsonProperty("protocol.add_mute_audio")
    private String protocolAddMuteAudio;
    /**
     * 
     * The buffer size for recording files.
     */
    @JsonProperty("record.fileBufSize")
    private String recordFileBufSize;
    /**
     * 
     * The time in seconds to keep the RTMP connection alive.
     */
    @JsonProperty("rtmp.keepAliveSecond")
    private String rtmpKeepAliveSecond;
    /**
     * 
     * The action to take when playing a stream in hooks.
     */
    @JsonProperty("hook.on_play")
    private String hookOnPlay;
}