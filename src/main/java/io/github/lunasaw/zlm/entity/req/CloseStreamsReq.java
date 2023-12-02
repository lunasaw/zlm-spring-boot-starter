package io.github.lunasaw.zlm.entity.req;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author weidian
 * @version 1.0
 * @date 2023/12/2
 * @description:
 */
@Data
public class CloseStreamsReq extends MediaReq {

    private int force;

    public Map<String, String> getMap() {
        Map<String, String> map = toMap();
        map.put("force", String.valueOf(force));
        return map;
    }

}