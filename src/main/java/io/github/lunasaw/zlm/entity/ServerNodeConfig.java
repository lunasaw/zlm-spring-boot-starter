package io.github.lunasaw.zlm.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class ServerNodeConfig {

    @JSONField(name = "ffmpeg.restart_sec")
    private String ffmpegRestartSec;

    @JSONField(name = "rtp.rtpMaxSize")
    private String rtpRtpMaxSize;

    @JSONField(name = "protocol.hls_demand")
    private String protocolHlsDemand;

    @JSONField(name = "rtp_proxy.opus_pt")
    private String rtpProxyOpusPt;

    @JSONField(name = "rtp_proxy.timeoutSec")
    private String rtpProxyTimeoutSec;

    @JSONField(name = "rtmp.port")
    private String rtmpPort;

    @JSONField(name = "hook.on_ip_not_found")
    private String hookOnIpNotFound;

    @JSONField(name = "record.fileRepeat")
    private String recordFileRepeat;

    @JSONField(name = "general.flowThreshold")
    private String generalFlowThreshold;

    @JSONField(name = "rtsp.rtpTransportType")
    private String rtspRtpTransportType;

    @JSONField(name = "hook.retry_delay")
    private String hookRetryDelay;

    @JSONField(name = "http.rootPath")
    private String httpRootPath;

    @JSONField(name = "rtsp.keepAliveSecond")
    private String rtspKeepAliveSecond;

    @JSONField(name = "hook.on_server_started")
    private String hookOnServerStarted;

    @JSONField(name = "api.defaultSnap")
    private String apiDefaultSnap;

    @JSONField(name = "cluster.origin_url")
    private String clusterOriginUrl;

    @JSONField(name = "http.port")
    private String httpPort;

    @JSONField(name = "http.virtualPath")
    private String httpVirtualPath;

    @JSONField(name = "http.keepAliveSecond")
    private String httpKeepAliveSecond;

    @JSONField(name = "ffmpeg.log")
    private String ffmpegLog;

    @JSONField(name = "hook.on_flow_report")
    private String hookOnFlowReport;

    @JSONField(name = "http.dirMenu")
    private String httpDirMenu;

    @JSONField(name = "rtsp.directProxy")
    private String rtspDirectProxy;

    @JSONField(name = "ffmpeg.cmd")
    private String ffmpegCmd;

    @JSONField(name = "rtp.lowLatency")
    private String rtpLowLatency;

    @JSONField(name = "protocol.enable_rtsp")
    private String protocolEnableRtsp;

    @JSONField(name = "rtsp.port")
    private String rtspPort;

    @JSONField(name = "rtmp.sslport")
    private String rtmpSslport;

    @JSONField(name = "protocol.hls_save_path")
    private String protocolHlsSavePath;

    @JSONField(name = "http.charSet")
    private String httpCharSet;

    @JSONField(name = "http.sendBufSize")
    private String httpSendBufSize;

    @JSONField(name = "hls.broadcastRecordTs")
    private String hlsBroadcastRecordTs;

    @JSONField(name = "api.apiDebug")
    private String apiApiDebug;

    @JSONField(name = "general.mergeWriteMS")
    private String generalMergeWriteMS;

    @JSONField(name = "http.forbidCacheSuffix")
    private String httpForbidCacheSuffix;

    @JSONField(name = "http.notFound")
    private String httpNotFound;

    @JSONField(name = "hook.retry")
    private String hookRetry;

    @JSONField(name = "record.appName")
    private String recordAppName;

    @JSONField(name = "hls.fileBufSize")
    private String hlsFileBufSize;

    @JSONField(name = "hook.timeoutSec")
    private String hookTimeoutSec;

    @JSONField(name = "rtsp.sslport")
    private String rtspSslport;

    @JSONField(name = "hls.deleteDelaySec")
    private String hlsDeleteDelaySec;

    @JSONField(name = "hook.on_rtp_server_timeout")
    private String hookOnRtpServerTimeout;

    @JSONField(name = "hook.on_send_rtp_stopped")
    private String hookOnSendRtpStopped;

    @JSONField(name = "hook.on_record_mp4")
    private String hookOnRecordMp4;

    @JSONField(name = "hook.alive_interval")
    private String hookAliveInterval;

    @JSONField(name = "rtmp.handshakeSecond")
    private String rtmpHandshakeSecond;

    @JSONField(name = "hook.stream_changed_schemas")
    private String hookStreamChangedSchemas;

    @JSONField(name = "rtc.externIP")
    private String rtcExternIP;

    @JSONField(name = "rtc.rembBitRate")
    private String rtcRembBitRate;

    @JSONField(name = "general.streamNoneReaderDelayMS")
    private String generalStreamNoneReaderDelayMS;

    @JSONField(name = "protocol.mp4_max_second")
    private String protocolMp4MaxSecond;

    @JSONField(name = "hook.on_publish")
    private String hookOnPublish;

    @JSONField(name = "rtp_proxy.port")
    private String rtpProxyPort;

    @JSONField(name = "http.sslport")
    private String httpSslport;

    @JSONField(name = "rtp.audioMtuSize")
    private String rtpAudioMtuSize;

    @JSONField(name = "general.check_nvidia_dev")
    private String generalCheckNvidiaDev;

    @JSONField(name = "record.fastStart")
    private String recordFastStart;

    @JSONField(name = "hook.on_stream_not_found")
    private String hookOnStreamNotFound;

    @JSONField(name = "rtp_proxy.port_range")
    private String rtpProxyPortRange;

    @JSONField(name = "protocol.enable_rtmp")
    private String protocolEnableRtmp;

    @JSONField(name = "srt.timeoutSec")
    private String srtTimeoutSec;

    @JSONField(name = "rtsp.handshakeSecond")
    private String rtspHandshakeSecond;

    @JSONField(name = "hls.segDur")
    private String hlsSegDur;

    @JSONField(name = "protocol.mp4_as_player")
    private String protocolMp4AsPlayer;

    @JSONField(name = "api.secret")
    private String apiSecret;

    @JSONField(name = "hls.segRetain")
    private String hlsSegRetain;

    @JSONField(name = "protocol.rtsp_demand")
    private String protocolRtspDemand;

    @JSONField(name = "srt.port")
    private String srtPort;

    @JSONField(name = "srt.pktBufSize")
    private String srtPktBufSize;

    @JSONField(name = "rtp_proxy.gop_cache")
    private String rtpProxyGopCache;

    @JSONField(name = "shell.maxReqSize")
    private String shellMaxReqSize;

    @JSONField(name = "ffmpeg.snap")
    private String ffmpegSnap;

    @JSONField(name = "general.maxStreamWaitMS")
    private String generalMaxStreamWaitMS;

    @JSONField(name = "multicast.addrMax")
    private String multicastAddrMax;

    @JSONField(name = "general.wait_add_track_ms")
    private String generalWaitAddTrackMs;

    @JSONField(name = "http.allow_cross_domains")
    private String httpAllowCrossDomains;

    @JSONField(name = "protocol.modify_stamp")
    private String protocolModifyStamp;

    @JSONField(name = "rtp.videoMtuSize")
    private String rtpVideoMtuSize;

    @JSONField(name = "api.snapRoot")
    private String apiSnapRoot;

    @JSONField(name = "protocol.enable_audio")
    private String protocolEnableAudio;

    @JSONField(name = "hook.on_server_keepalive")
    private String hookOnServerKeepalive;

    @JSONField(name = "multicast.addrMin")
    private String multicastAddrMin;

    @JSONField(name = "protocol.ts_demand")
    private String protocolTsDemand;

    @JSONField(name = "protocol.enable_fmp4")
    private String protocolEnableFmp4;

    @JSONField(name = "rtsp.lowLatency")
    private String rtspLowLatency;

    @JSONField(name = "http.allow_ip_range")
    private String httpAllowIpRange;

    @JSONField(name = "hook.on_rtsp_realm")
    private String hookOnRtspRealm;

    @JSONField(name = "hook.on_stream_changed")
    private String hookOnStreamChanged;

    @JSONField(name = "http.forwarded_ip_header")
    private String httpForwardedIpHeader;

    @JSONField(name = "rtp_proxy.h265_pt")
    private String rtpProxyH265Pt;

    @JSONField(name = "hook.on_del_mp4")
    private String hookOnDelMp4;

    @JSONField(name = "protocol.enable_hls")
    private String protocolEnableHls;

    @JSONField(name = "protocol.enable_mp4")
    private String protocolEnableMp4;

    @JSONField(name = "rtc.port")
    private String rtcPort;

    @JSONField(name = "protocol.fmp4_demand")
    private String protocolFmp4Demand;

    @JSONField(name = "record.sampleMS")
    private String recordSampleMS;

    @JSONField(name = "shell.port")
    private String shellPort;

    @JSONField(name = "hook.on_shell_login")
    private String hookOnShellLogin;

    @JSONField(name = "cluster.retry_count")
    private String clusterRetryCount;

    @JSONField(name = "general.enableVhost")
    private String generalEnableVhost;

    @JSONField(name = "general.unready_frame_cache")
    private String generalUnreadyFrameCache;

    @JSONField(name = "rtc.preferredCodecV")
    private String rtcPreferredCodecV;

    @JSONField(name = "rtp_proxy.h264_pt")
    private String rtpProxyH264Pt;

    @JSONField(name = "protocol.auto_close")
    private String protocolAutoClose;

    @JSONField(name = "srt.latencyMul")
    private String srtLatencyMul;

    @JSONField(name = "hook.on_server_exited")
    private String hookOnServerExited;

    @JSONField(name = "general.resetWhenRePlay")
    private String generalResetWhenRePlay;

    @JSONField(name = "protocol.mp4_save_path")
    private String protocolMp4SavePath;

    @JSONField(name = "protocol.continue_push_ms")
    private String protocolContinuePushMs;

    @JSONField(name = "rtp_proxy.dumpDir")
    private String rtpProxyDumpDir;

    @JSONField(name = "rtp_proxy.ps_pt")
    private String rtpProxyPsPt;

    @JSONField(name = "hook.enable")
    private String hookEnable;

    @JSONField(name = "rtc.timeoutSec")
    private String rtcTimeoutSec;

    @JSONField(name = "rtc.preferredCodecA")
    private String rtcPreferredCodecA;

    @JSONField(name = "hls.segKeep")
    private String hlsSegKeep;

    @JSONField(name = "multicast.udpTTL")
    private String multicastUdpTTL;

    @JSONField(name = "rtp.h264_stap_a")
    private String rtpH264StapA;

    @JSONField(name = "hook.on_stream_none_reader")
    private String hookOnStreamNoneReader;

    @JSONField(name = "hook.on_record_ts")
    private String hookOnRecordTs;

    @JSONField(name = "ffmpeg.bin")
    private String ffmpegBin;

    @JSONField(name = "protocol.enable_ts")
    private String protocolEnableTs;

    @JSONField(name = "protocol.enable_hls_fmp4")
    private String protocolEnableHlsFmp4;

    @JSONField(name = "hls.segNum")
    private String hlsSegNum;

    @JSONField(name = "http.maxReqSize")
    private String httpMaxReqSize;

    @JSONField(name = "rtc.tcpPort")
    private String rtcTcpPort;

    @JSONField(name = "cluster.timeout_sec")
    private String clusterTimeoutSec;

    @JSONField(name = "general.enable_ffmpeg_log")
    private String generalEnableFfmpegLog;

    @JSONField(name = "general.mediaServerId")
    private String generalMediaServerId;

    @JSONField(name = "hook.on_http_access")
    private String hookOnHttpAccess;

    @JSONField(name = "general.wait_track_ready_ms")
    private String generalWaitTrackReadyMs;

    @JSONField(name = "rtsp.authBasic")
    private String rtspAuthBasic;

    @JSONField(name = "hook.on_rtsp_auth")
    private String hookOnRtspAuth;

    @JSONField(name = "protocol.rtmp_demand")
    private String protocolRtmpDemand;

    @JSONField(name = "protocol.add_mute_audio")
    private String protocolAddMuteAudio;

    @JSONField(name = "record.fileBufSize")
    private String recordFileBufSize;

    @JSONField(name = "rtmp.keepAliveSecond")
    private String rtmpKeepAliveSecond;

    @JSONField(name = "hook.on_play")
    private String hookOnPlay;
}