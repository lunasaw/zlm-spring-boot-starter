package io.github.lunasaw.zlm.hook.param;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @author weidian
 * @version 1.0
 * @date 2023/12/14
 * @description:
 */
@Data
public class OnRecordMp4HookParam extends HookParam {
    /**
     * 流应用名
     */
    @JSONField(name = "app")
    @JsonProperty("app")
    private String app;
    /**
     * 文件名
     */
    @JSONField(name = "file_name")
    @JsonProperty("file_name")
    private String fileName;
    /**
     * 
     * 文件绝对路径
     */
    @JSONField(name = "file_path")
    @JsonProperty("file_path")
    private String filePath;
    /**
     * 
     * 文件大小，单位字节
     */
    @JSONField(name = "file_size")
    @JsonProperty("file_size")
    private int    fileSize;
    /**
     * 
     * 文件所在目录路径
     */
    @JSONField(name = "folder")
    @JsonProperty("folder")
    private String folder;
    /**
     * 
     * 开始录制时间戳
     */
    @JSONField(name = "start_time")
    @JsonProperty("start_time")
    private int    startTime;
    /**
     * 
     * 录制的流ID
     */
    @JSONField(name = "stream")
    @JsonProperty("stream")
    private String stream;
    /**
     * 
     * 录制时长，单位秒
     */
    @JSONField(name = "time_len")
    @JsonProperty("time_len")
    private float  timeLen;
    /**
     * 
     * http/rtsp/rtmp点播相对url路径
     */
    @JSONField(name = "url")
    @JsonProperty("url")
    private String url;
    /**
     * 
     * 流虚拟主机
     */
    @JSONField(name = "vhost")
    @JsonProperty("vhost")
    private String vhost;
}
