package io.github.lunasaw.zlm.hook.param;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HookResultForOnPublish extends HookResult {

    /**
     * 是否转换成hls-mpegts协议
     */
    @JSONField(name = "enable_hls")
    @JsonProperty("enable_hls")
    private boolean enableHls;

    /**
     * 是否转换成hls-fmp4协议
     */
    @JSONField(name = "enable_hls_fmp4")
    @JsonProperty("enable_hls_fmp4")
    private boolean enableHlsFmp4;

    /**
     * 是否允许mp4录制
     */
    @JSONField(name = "enable_mp4")
    @JsonProperty("enable_mp4")
    private boolean enableMp4;
    /**
     * 是否转rtsp协议
     */
    @JSONField(name = "enable_rtsp")
    @JsonProperty("enable_rtsp")
    private boolean enableRtsp;

    /**
     * 是否转rtmp/flv协议
     */
    @JSONField(name = "enable_rtmp")
    @JsonProperty("enable_rtmp")
    private boolean enableRtmp;

    /**
     * 是否转http-ts/ws-ts协议
     */
    @JSONField(name = "enable_ts")
    @JsonProperty("enable_ts")
    private boolean enableTs;

    /**
     * 是否转http-fmp4/ws-fmp4协议
     */
    @JSONField(name = "enable_fmp4")
    @JsonProperty("enable_fmp4")
    private boolean enableFmp4;

    /**
     * 该协议是否有人观看才生成
     */
    @JSONField(name = "hls_demand")
    @JsonProperty("hls_demand")
    private boolean hlsDemand;

    /**
     * 该协议是否有人观看才生成
     */
    @JSONField(name = "rtsp_demand")
    @JsonProperty("rtsp_demand")
    private boolean rtspDemand;

    /**
     * 该协议是否有人观看才生成
     */
    @JSONField(name = "rtmp_demand")
    @JsonProperty("rtmp_demand")
    private boolean rtmpDemand;

    /**
     * 该协议是否有人观看才生成
     */
    @JSONField(name = "ts_demand")
    @JsonProperty("ts_demand")
    private boolean tsDemand;

    /**
     * 该协议是否有人观看才生成
     */
    @JSONField(name = "fmp4_demand")
    @JsonProperty("fmp4_demand")
    private boolean fmp4Demand;

    /**
     * 转协议时是否开启音频
     */
    @JSONField(name = "enable_audio")
    @JsonProperty("enable_audio")
    private boolean enableAudio;

    /**
     * 转协议时，无音频是否添加静音aac音频
     */
    @JSONField(name = "add_mute_audio")
    @JsonProperty("add_mute_audio")
    private boolean addMuteAudio;

    /**
     * mp4录制文件保存根目录，置空使用默认
     */
    @JSONField(name = "mp4_save_path")
    @JsonProperty("mp4_save_path")
    private String mp4SavePath;

    /**
     * mp4录制切片大小，单位秒
     */
    @JSONField(name = "mp4_max_second")
    @JsonProperty("mp4_max_second")
    private int mp4MaxSecond;

    /**
     * MP4录制是否当作观看者参与播放人数计数
     */
    @JSONField(name = "mp4_as_player")
    @JsonProperty("mp4_as_player")
    private boolean mp4AsPlayer;

    /**
     * hls文件保存保存根目录，置空使用默认
     */
    @JSONField(name = "hls_save_path")
    @JsonProperty("hls_save_path")
    private String hlsSavePath;

    /**
     * 该流是否开启时间戳覆盖(0:绝对时间戳/1:系统时间戳/2:相对时间戳)
     */
    @JSONField(name = "modify_stamp")
    @JsonProperty("modify_stamp")
    private int modifyStamp;

    /**
     * 断连续推延时，单位毫秒，置空使用配置文件默认值
     */
    @JSONField(name = "continue_push_ms")
    @JsonProperty("continue_push_ms")
    private long continuePushMs;

    /**
     * 无人观看是否自动关闭流(不触发无人观看hook)
     */
    @JSONField(name = "auto_close")
    @JsonProperty("auto_close")
    private boolean autoClose;

    /**
     * 是否修改流id, 通过此参数可以自定义流id(譬如替换ssrc)
     */
    @JSONField(name = "stream_replace")
    @JsonProperty("stream_replace")
    private String streamReplace;

    public HookResultForOnPublish(int code, String msg) {
        setCode(code);
        setMsg(msg);
    }

    public static HookResultForOnPublish SUCCESS() {
        return new HookResultForOnPublish(0, "success");
    }
}
