package io.github.lunasaw.zlm.entity.rtp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import io.github.lunasaw.zlm.entity.req.MediaReq;
import lombok.Data;

import java.util.Map;

/**
 * 作为GB28181客户端，启动ps-rtp推流，支持rtp/udp方式；该接口支持rtsp/rtmp等协议转ps-rtp推流。
 * 第一次推流失败会直接返回错误，成功一次后，后续失败也将无限重试。。
 */
@Data
public class StartSendRtpReq extends MediaReq {

    /**
     * 推流的rtp的ssrc,指定不同的ssrc可以同时推流到多个服务器。
     */
    @JSONField(name = "ssrc")
    private int     ssrc;

    /**
     * 目标ip或域名。
     */
    @JSONField(name = "dst_url")
    private String  dstUrl;

    /**
     * 目标端口。
     */
    @JSONField(name = "dst_port")
    private int     dstPort;

    /**
     * 是否为udp模式,否则为tcp模式。
     */
    @JSONField(name = "is_udp")
    private boolean isUdp;

    /**
     * 使用的本机端口，为0或不传时默认为随机端口。
     */
    @JSONField(name = "src_port")
    private Integer srcPort;

    /**
     * 发送时，rtp的pt（uint8_t）,不传时默认为96。
     */
    @JSONField(name = "pt")
    private Integer pt;

    /**
     * 发送时，rtp的负载类型。为1时，负载为ps；为0时，为es；不传时默认为1。
     */
    @JSONField(name = "use_ps")
    private Integer usePs;

    /**
     * 当use_ps 为0时，有效。为1时，发送音频；为0时，发送视频；不传时默认为0。
     */
    @JSONField(name = "only_audio")
    private Boolean onlyAudio;

    // Getters and setters...

    public JSONObject getMap() {
        return JSON.parseObject(JSON.toJSONString(this));
    }

    /**
     * 作为GB28181 Passive TCP服务器；该接口支持rtsp/rtmp等协议转ps-rtp被动推流。调用该接口，zlm会启动tcp服务器等待连接请求，连接建立后，zlm会关闭tcp服务器，然后源源不断的往客户端推流。
     * 第一次推流失败会直接返回错误，成功一次后，后续失败也将无限重试(不停地建立tcp监听，超时后再关闭)。
     * 
     * @return
     */
    public Map<String, String> getPassiveMap() {
        Map<String, String> map = toMap();
        map.put("ssrc", String.valueOf(ssrc));
        map.put("src_port", String.valueOf(srcPort));
        map.put("pt", String.valueOf(pt));
        map.put("use_ps", String.valueOf(usePs));
        map.put("only_audio", String.valueOf(onlyAudio));
        return map;
    }
}
