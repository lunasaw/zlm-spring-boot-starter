package io.github.lunasaw.zlm.entity.req;

import java.util.Map;

import lombok.Data;

/**
 * @author luna
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