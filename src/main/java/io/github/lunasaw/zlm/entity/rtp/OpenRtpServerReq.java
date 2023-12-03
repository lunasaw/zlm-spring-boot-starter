package io.github.lunasaw.zlm.entity.rtp;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 * @author weidian
 * @version 1.0
 * @date 2023/12/3
 * @description:
 */
@Data
public class OpenRtpServerReq {

    /**
     * 接收端口，0则为随机端口。
     */
    @JSONField(name = "port")
    private int    port;

    /**
     * 0 udp 模式，1 tcp 被动模式, 2 tcp 主动模式。 (兼容enable_tcp 为0/1)。
     */
    @JSONField(name = "tcp_mode")
    private int    tcpMode;

    /**
     * 该端口绑定的流ID，该端口只能创建这一个流(而不是根据ssrc创建多个)。
     */
    @JSONField(name = "stream_id")
    private String streamId;

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put("port", String.valueOf(port));
        map.put("tcp_mode", String.valueOf(tcpMode));
        map.put("stream_id", streamId);

        return map;
    }
}
