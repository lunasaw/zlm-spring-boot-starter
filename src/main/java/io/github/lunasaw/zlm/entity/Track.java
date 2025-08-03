package io.github.lunasaw.zlm.entity;

import com.alibaba.fastjson2.annotation.JSONField;

import lombok.Data;

@Data
public class Track {

    /**
     * 音频通道数。
     */
    @JSONField(name = "channels")
    private int     channels;

    /**
     * 编码ID。H264 = 0, H265 = 1, AAC = 2, G711A = 3, G711U = 4。
     */
    @JSONField(name = "codec_id")
    private int     codecId;

    /**
     * 编码类型的名称。
     */
    @JSONField(name = "codec_id_name")
    private String  codecIdName;

    /**
     * 类型。视频 = 0, 音频 = 1。
     */
    @JSONField(name = "codec_type")
    private int     codecType;

    /**
     * 视频的帧率。
     */
    @JSONField(name = "fps")
    private double fps;

    /**
     * 视频的高度。
     */
    @JSONField(name = "height")
    private int     height;

    /**
     * 轨道是否准备就绪。
     */
    @JSONField(name = "ready")
    private boolean ready;

    /**
     * 视频的宽度。
     */
    @JSONField(name = "width")
    private int     width;

    /**
     * 累计接收帧数
     */
    @JSONField(name = "frames")
    public int      frames;
    /**
     * 音频采样位数
     */
    @JSONField(name = "sample_bit")
    public int      sampleBit;
    /**
     * 音频采样率
     */
    @JSONField(name = "sample_rate")
    public int      sampleRate;

    /**
     * gop间隔时间，单位毫秒
     */
    @JSONField(name = "gop_interval_ms")
    public int      gopIntervalMs;

    /**
     * gop大小，单位帧数
     */
    @JSONField(name = "gop_size")
    public int gopSize;
    /**
     * 累计接收关键帧数
     */
    @JSONField(name = "key_frames")
    public int      keyFrames;

    /**
     * 轨道时长，单位毫秒
     */
    @JSONField(name = "duration")
    public long duration;

    /**
     * 丢包率，-1表示未知
     */
    @JSONField(name = "loss")
    public double loss;

    // Getters and setters...
}