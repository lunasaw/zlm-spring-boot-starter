package io.github.lunasaw.zlm.entity.rtp;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 * @author luna
 * @version 1.0
 * @date 2023/12/3
 * @description:
 */
@Data
public class ConnectRtpServerReq {

    /**
     * tcp主动模式时服务端端口
     */
    @JSONField(name = "dst_port")
    private int    dstPort;

    /**
     * tcp主动模式时服务端地址
     */
    @JSONField(name = "dst_url")
    private int    dstUrl;

    /**
     * 该端口绑定的流ID，该端口只能创建这一个流(而不是根据ssrc创建多个)。
     */
    @JSONField(name = "stream_id")
    private String streamId;

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put("dst_port", String.valueOf(dstPort));
        map.put("dst_url", String.valueOf(dstUrl));
        map.put("stream_id", streamId);

        return map;
    }
}
