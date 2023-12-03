package io.github.lunasaw.zlm.entity.rtp;

import java.util.Map;

import io.github.lunasaw.zlm.entity.req.MediaReq;
import lombok.Data;

/**
 * @author luna
 * @version 1.0
 * @date 2023/12/3
 * @description:
 */
@Data
public class CloseSendRtpReq extends MediaReq {

    /**
     * 停止GB28181 ps-rtp推流
     */
    private String ssrc;

    public Map<String, String> getMap() {
        Map<String, String> map = toMap();
        map.put("ssrc", ssrc);
        return map;
    }
}
