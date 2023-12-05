package io.github.lunasaw.zlm.hook.param;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Java 类表示
 */
@Data
public class OnRtspRealmHookParam extends HookParam {

    /**
     * 流应用名
     */
    @JSONField(name = "app")
    @JsonProperty("app")
    private String app;

    /**
     * TCP链接唯一ID
     */
    @JSONField(name = "id")
    @JsonProperty("id")
    private String id;

    /**
     * 播放器ip
     */
    @JSONField(name = "ip")
    @JsonProperty("ip")
    private String ip;

    /**
     * 播放url参数
     */
    @JSONField(name = "params")
    @JsonProperty("params")
    private String params;

    /**
     * 播放器端口号
     */
    @JSONField(name = "port")
    @JsonProperty("port")
    private int port;

    /**
     * 播放的协议，可能是rtsp、rtmp、http
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
     * 流虚拟主机
     */
    @JSONField(name = "vhost")
    @JsonProperty("vhost")
    private String vhost;

    /**
     * 服务器id,通过配置文件设置
     */
    @JSONField(name = "mediaServerId")
    @JsonProperty("mediaServerId")
    private String mediaServerId;

    // getters and setters...
}
