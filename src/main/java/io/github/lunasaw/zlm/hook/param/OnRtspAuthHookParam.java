package io.github.lunasaw.zlm.hook.param;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Java 类表示
 */
@Data
public class OnRtspAuthHookParam extends HookParam {

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
     * rtsp播放器ip
     */
    @JSONField(name = "ip")
    @JsonProperty("ip")
    private String ip;

    /**
     * 请求的密码是否必须为明文(base64鉴权需要明文密码)
     */
    @JSONField(name = "must_no_encrypt")
    @JsonProperty("must_no_encrypt")
    private boolean mustNoEncrypt;

    /**
     * rtsp url参数
     */
    @JSONField(name = "params")
    @JsonProperty("params")
    private String params;

    /**
     * rtsp播放器端口号
     */
    @JSONField(name = "port")
    @JsonProperty("port")
    private int port;

    /**
     * rtsp播放鉴权加密realm
     */
    @JSONField(name = "realm")
    @JsonProperty("realm")
    private String realm;

    /**
     * rtsp或rtsps
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
     * 播放用户名
     */
    @JSONField(name = "user_name")
    @JsonProperty("user_name")
    private String userName;

    /**
     * 流虚拟主机
     */
    @JSONField(name = "vhost")
    @JsonProperty("vhost")
    private String vhost;

}
