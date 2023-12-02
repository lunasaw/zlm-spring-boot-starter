package io.github.lunasaw.zlm.entity.req;

import com.alibaba.fastjson.JSONObject;
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
public class MediaReq {

    /**
     * 筛选协议，例如 rtsp或rtmp
     */
    public String schema = "rtsp";
    /**
     * 筛选虚拟主机，例如__defaultVhost__
     */
    public String vhost = "__defaultVhost__";
    /**
     * 筛选应用名，例如 live
     */
    public String app;
    /**
     * 筛选流id，例如 test
     */
    public String stream;

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put("schema", schema);
        map.put("vhost", vhost);
        map.put("app", app);
        map.put("stream", stream);
        return map;
    }

    public static Map<String, String> getParam(String app, String stream) {
        Map<String, String> map = new HashMap<>();
        map.put("schema", "rtsp");
        map.put("vhost", "__defaultVhost__");
        map.put("app", app);
        map.put("stream", stream);
        return map;
    }
}
