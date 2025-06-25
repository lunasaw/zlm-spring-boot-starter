package io.github.lunasaw.zlm.hook.param;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Java 类表示
 */
@Data
public class OnHttpAccessParam extends HookParam {

    /**
     * 接受头
     */
    @JSONField(name = "header.Accept")
    @JsonProperty("header.Accept")
    private String headerAccept;

    /**
     * 接受编码头
     */
    @JSONField(name = "header.Accept-Encoding")
    @JsonProperty("header.Accept-Encoding")
    private String headerAcceptEncoding;

    /**
     * 接受语言头
     */
    @JSONField(name = "header.Accept-Language")
    @JsonProperty("header.Accept-Language")
    private String headerAcceptLanguage;

    /**
     * 缓存控制头
     */
    @JSONField(name = "header.Cache-Control")
    @JsonProperty("header.Cache-Control")
    private String headerCacheControl;

    /**
     * 连接头
     */
    @JSONField(name = "header.Connection")
    @JsonProperty("header.Connection")
    private String headerConnection;

    /**
     * 主机头
     */
    @JSONField(name = "header.Host")
    @JsonProperty("header.Host")
    private String headerHost;

    /**
     * 升级不安全请求头
     */
    @JSONField(name = "header.Upgrade-Insecure-Requests")
    @JsonProperty("header.Upgrade-Insecure-Requests")
    private String headerUpgradeInsecureRequests;

    /**
     * 用户代理头
     */
    @JSONField(name = "header.User-Agent")
    @JsonProperty("header.User-Agent")
    private String headerUserAgent;

    /**
     * ID
     */
    @JSONField(name = "id")
    @JsonProperty("id")
    private String id;

    /**
     * IP
     */
    @JSONField(name = "ip")
    @JsonProperty("ip")
    private String ip;

    /**
     * 是否为目录
     */
    @JSONField(name = "is_dir")
    @JsonProperty("is_dir")
    private boolean isDir;

    /**
     * 参数
     */
    @JSONField(name = "params")
    @JsonProperty("params")
    private String params;

    /**
     * 路径
     */
    @JSONField(name = "path")
    @JsonProperty("path")
    private String path;

    /**
     * 端口
     */
    @JSONField(name = "port")
    @JsonProperty("port")
    private int port;

    // getters and setters...
}
