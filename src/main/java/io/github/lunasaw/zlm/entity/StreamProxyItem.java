package io.github.lunasaw.zlm.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import com.alibaba.fastjson2.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * This class represents a stream with various properties.
 * @author luna
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StreamProxyItem {

    /**
     * 添加的流的虚拟主机，例如__defaultVhost__
     */
    @JSONField(name = "vhost")
    private String  vHost;

    /**
     * 添加的流的应用名，例如live
     */
    @JSONField(name = "app")
    private String  app;

    /**
     * 添加的流的id名，例如test
     */
    @JSONField(name = "stream")
    private String  stream;

    /**
     * 拉流地址，例如rtmp://live.hkstv.hk.lxdns.com/live/hks2
     */
    @JSONField(name = "url")
    private String  url;

    /**
     * 拉流重试次数，默认为-1无限重试
     */
    @JSONField(name = "retry_count")
    private Integer retryCount;

    /**
     * rtsp拉流时，拉流方式，0：tcp，1：udp，2：组播
     */
    @JSONField(name = "rtp_type")
    private Integer rtpType;

    /**
     * 拉流超时时间，单位秒，float类型
     */
    @JSONField(name = "timeout_sec")
    private Integer timeoutSec;

    /**
     * 是否转换成hls-mpegts协议
     */
    @JSONField(name = "enable_hls")
    private Boolean enableHls;

    /**
     * 是否转换成hls-fmp4协议
     */
    @JSONField(name = "enable_hls_fmp4")
    private Boolean enableHlsFmp4;

    /**
     * 是否允许mp4录制
     */
    @JSONField(name = "enable_mp4")
    private Boolean enableMp4;

    /**
     * 是否转rtsp协议
     */
    @JSONField(name = "enable_rtsp")
    private Boolean enableRtsp;

    /**
     * 是否转rtmp/flv协议
     */
    @JSONField(name = "enable_rtmp")
    private Boolean enableRtmp;

    /**
     * 是否转http-ts/ws-ts协议
     */
    @JSONField(name = "enable_ts")
    private Boolean enableTs;

    /**
     * 是否转http-fmp4/ws-fmp4协议
     */
    @JSONField(name = "enable_fmp4")
    private Boolean enableFmp4;

    /**
     * 该协议是否有人观看才生成
     */
    @JSONField(name = "hls_demand")
    private Boolean hlsDemand;

    /**
     * 该协议是否有人观看才生成
     */
    @JSONField(name = "rtsp_demand")
    private Boolean rtspDemand;

    /**
     * 该协议是否有人观看才生成
     */
    @JSONField(name = "rtmp_demand")
    private Boolean rtmpDemand;

    /**
     * 该协议是否有人观看才生成
     */
    @JSONField(name = "ts_demand")
    private Boolean tsDemand;

    /**
     * 该协议是否有人观看才生成
     */
    @JSONField(name = "fmp4_demand")
    private Boolean fmp4Demand;

    /**
     * 转协议时是否开启音频
     */
    @JSONField(name = "enable_audio")
    private Boolean enableAudio;

    /**
     * 转协议时，无音频是否添加静音aac音频
     */
    @JSONField(name = "add_mute_audio")
    private Boolean addMuteAudio;

    /**
     * mp4录制文件保存根目录，置空使用默认
     */
    @JSONField(name = "mp4_save_path")
    private String  mp4SavePath;

    /**
     * mp4录制切片大小，单位秒
     */
    @JSONField(name = "mp4_max_second")
    private Integer mp4MaxSecond;

    /**
     * MP4录制是否当作观看者参与播放人数计数
     */
    @JSONField(name = "mp4_as_player")
    private Boolean mp4AsPlayer;

    /**
     * hls文件保存保存根目录，置空使用默认
     */
    @JSONField(name = "hls_save_path")
    private String  hlsSavePath;

    /**
     * 该流是否开启时间戳覆盖(0:绝对时间戳/1:系统时间戳/2:相对时间戳)
     */
    @JSONField(name = "modify_stamp")
    private Integer modifyStamp;

    /**
     * 无人观看是否自动关闭流(不触发无人观看hook)
     */
    @JSONField(name = "auto_close")
    private Boolean autoClose;

    /**
     * Constructor with all required fields.
     */
    public StreamProxyItem(String vHost, String app, String stream, String url) {
        this.vHost = vHost;
        this.app = app;
        this.stream = stream;
        this.url = url;
    }

    public Map<String, String> toMap() {
        return JSON.parseObject(JSON.toJSONString(this), Map.class);
    }

    public static void main(String[] args) {
        Map<String, String> map = new StreamProxyItem("__defaultVhost__", "live", "test", "rtmp://live.hkstv.hk.lxdns.com/live/hks2").toMap();
        System.out.println(JSON.toJSONString(map));
    }
}
