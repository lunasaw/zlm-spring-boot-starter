package io.github.lunasaw.zlm.hook.param;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Java 类表示
 */
@Data
public class OnFlowReportHookParam extends HookParam {

    /**
     * 流应用名
     */
    @JSONField(name = "app")
    @JsonProperty("app")
    private String app;

    /**
     * tcp链接维持时间，单位秒
     */
    @JSONField(name = "duration")
    @JsonProperty("duration")
    private int duration;

    /**
     * 推流或播放url参数
     */
    @JSONField(name = "params")
    @JsonProperty("params")
    private String params;

    /**
     * true为播放器，false为推流器
     */
    @JSONField(name = "player")
    @JsonProperty("player")
    private boolean player;

    /**
     * 播放或推流的协议，可能是rtsp、rtmp、http
     */
    @JSONField(name = "schema")
    @JsonProperty("schema")
    private String schema;

    /**
     * 流ID
     */
    @JSONField(name = "stream")
    @JsonProperty("stream")
    private String stream;

    /**
     * 耗费上下行流量总和，单位字节
     */
    @JSONField(name = "totalBytes")
    @JsonProperty("totalBytes")
    private int totalBytes;

    /**
     * 流虚拟主机
     */
    @JSONField(name = "vhost")
    @JsonProperty("vhost")
    private String vhost;

    /**
     * 客户端ip
     */
    @JSONField(name = "ip")
    @JsonProperty("ip")
    private String ip;

    /**
     * 客户端端口号
     */
    @JSONField(name = "port")
    @JsonProperty("port")
    private int port;

    /**
     * TCP链接唯一ID
     */
    @JSONField(name = "id")
    @JsonProperty("id")
    private String id;

    // getters and setters...
}
