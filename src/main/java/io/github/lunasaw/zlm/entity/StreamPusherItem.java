package io.github.lunasaw.zlm.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import com.alibaba.fastjson2.JSON;
import lombok.Data;

import java.util.Map;

/**
 * This class represents a stream with various properties.
 */
@Data
public class StreamPusherItem {

    /**
     * 添加的流的虚拟主机，例如__defaultVhost__
     */
    @JSONField(name = "vhost")
    private String  vHost;

    /**
     * 协议，例如 rtsp或rtmp
     */
    @JSONField(name = "schema")
    private String  schema;

    /**
     * 添加的流的应用名，例如live
     */
    @JSONField(name = "app")
    private String  app;

    /**
     * 需要转推的流id
     */
    @JSONField(name = "stream")
    private String  stream;

    /**
     * 目标转推url，带参数需要自行url转义
     */
    @JSONField(name = "dst_url")
    private String  dstUrl;

    /**
     * 转推失败重试次数，默认无限重试
     */
    @JSONField(name = "retry_count")
    private Integer retryCount;

    /**
     * rtsp推流时，推流方式，0：tcp，1：udp
     */
    @JSONField(name = "rtp_type")
    private Integer rtpType;

    /**
     * 推流超时时间，单位秒，float类型
     */
    @JSONField(name = "timeout_sec")
    private Integer timeoutSec;

    /**
     * Constructor with all required fields.
     */
    public StreamPusherItem(String vHost, String schema, String app, String stream, String dstUrl) {
        this.vHost = vHost;
        this.schema = schema;
        this.app = app;
        this.stream = stream;
        this.dstUrl = dstUrl;
    }

    public Map<String, String> toMap() {
        return JSON.parseObject(JSON.toJSONString(this), Map.class);
    }
    // Getters and setters...
}
