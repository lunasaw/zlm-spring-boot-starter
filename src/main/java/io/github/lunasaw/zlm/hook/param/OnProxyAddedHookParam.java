package io.github.lunasaw.zlm.hook.param;

import lombok.Data;

/**
 * 拉流代理添加成功时触发此事件
 *
 * @author luna
 */
@Data
public class OnProxyAddedHookParam extends HookParam {

    /**
     * 应用名称
     */
    private String app;

    /**
     * 流ID
     */
    private String stream;

    /**
     * 拉流地址
     */
    private String url;

    /**
     * ZLM返回的代理key
     */
    private String key;

    /**
     * 虚拟主机，例如__defaultVhost__
     */
    private String vhost;

    /**
     * 拉流重试次数
     */
    private Integer retryCount;

    /**
     * rtsp拉流时，拉流方式，0：tcp，1：udp，2：组播
     */
    private Integer rtpType;

    /**
     * 拉流超时时间，单位秒
     */
    private Integer timeoutSec;

    /**
     * 是否转换成hls-mpegts协议
     */
    private Boolean enableHls;

    /**
     * 是否转换成hls-fmp4协议
     */
    private Boolean enableHlsFmp4;

    /**
     * 是否允许mp4录制
     */
    private Boolean enableMp4;

    /**
     * 是否转rtsp协议
     */
    private Boolean enableRtsp;

    /**
     * 是否转rtmp/flv协议
     */
    private Boolean enableRtmp;

    /**
     * 是否转http-ts/ws-ts协议
     */
    private Boolean enableTs;

    /**
     * 是否转http-fmp4/ws-fmp4协议
     */
    private Boolean enableFmp4;
}