package io.github.lunasaw.zlm.hook.param;

import java.util.List;

import io.github.lunasaw.zlm.entity.MediaPlayer;
import io.github.lunasaw.zlm.entity.Track;
import lombok.Data;

/**
 * rtsp/rtmp流注册或注销时触发此事件；此事件对回复不敏感。
 * 
 * @author luna
 */
@Data
public class OnStreamChangedHookParam extends HookParam {

    /**
     * 注册/注销
     */
    private boolean       regist;

    /**
     * 应用名
     */
    private String        app;

    /**
     * 流id
     */
    private String        stream;

    /**
     * 推流鉴权Id
     */
    private String        callId;

    /**
     * 观看总人数，包括hls/rtsp/rtmp/http-flv/ws-flv
     */
    private String        totalReaderCount;

    /**
     * 协议 包括hls/rtsp/rtmp/http-flv/ws-flv
     */
    private String        schema;

    /**
     * 产生源类型，
     * unknown = 0,
     * rtmp_push=1,
     * rtsp_push=2,
     * rtp_push=3,
     * pull=4,
     * ffmpeg_pull=5,
     * mp4_vod=6,
     * device_chn=7
     */
    private int           originType;

    /**
     * 客户端和服务器网络信息，可能为null类型
     */
    private MediaPlayer   originSock;

    /**
     * 产生源类型的字符串描述
     */
    private String        originTypeStr;

    /**
     * 产生源的url
     */
    private String        originUrl;

    /**
     * 服务器id
     */
    private String        severId;

    /**
     * GMT unix系统时间戳，单位秒
     */
    private Long          createStamp;

    /**
     * 存活时间，单位秒
     */
    private Long          aliveSecond;

    /**
     * 数据产生速度，单位byte/s
     */
    private Long          bytesSpeed;

    /**
     * 音视频轨道
     */
    private List<Track>   tracks;

    /**
     * 音视频轨道
     */
    private String        vhost;

    /**
     * 是否是docker部署， docker部署不会自动更新zlm使用的端口，需要自己手动修改
     */
    private boolean       docker;

    private StreamContent streamInfo;

}
