package io.github.lunasaw.zlm.entity.req;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 这个类代表了具有各种属性的截图任务。
 */
@Data
public class SnapshotReq {

    /**
     * 需要截图的url，可以是本机的，也可以是远程主机的。
     */
    @JSONField(name = "url")
    private String url;

    /**
     * 截图失败超时时间，防止FFmpeg一直等待截图。
     */
    @JSONField(name = "timeout_sec")
    private int    timeoutSec;

    /**
     * 截图的过期时间，该时间内产生的截图都会作为缓存返回。
     */
    @JSONField(name = "expire_sec")
    private int    expireSec;

    @JSONField(deserialize = false, serialize = false)
    private String savePath;

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put("url", url);
        map.put("timeout_sec", String.valueOf(timeoutSec));
        map.put("expire_sec", String.valueOf(expireSec));
        return map;
    }
}
