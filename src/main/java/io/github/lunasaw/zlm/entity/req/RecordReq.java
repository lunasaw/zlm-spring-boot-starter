package io.github.lunasaw.zlm.entity.req;

import java.util.Map;

import com.alibaba.fastjson2.annotation.JSONField;

import lombok.Data;

/**
 * @author luna
 * @version 1.0
 * @date 2023/12/3
 * @description:
 */
@Data
public class RecordReq extends MediaReq {

    /**
     * 流的录像日期，格式为2020-02-01,如果不是完整的日期，那么是搜索录像文件夹列表，否则搜索对应日期下的mp4文件列表
     */
    private String period;

    /**
     * 自定义搜索路径，与startRecord方法中的customized_path一样，默认为配置文件的路径
     */
    @JSONField(name = "customized_path")
    private String customizedPath;

    /**
     * mp4录像切片时间大小,单位秒，置0则采用配置项
     */
    @JSONField(name = "max_seconds")
    private String maxSeconds;

    /**
     * 0为hls，1为mp4
     */
    private int    type;

    /**
     * 要设置的录像倍速 eg.2.0
     */
    private String speed;

    /**
     * 要设置的录像播放位置
     */
    private String stamp;

    public Map<String, String> getMap() {
        Map<String, String> map = toMap();
        map.put("period", period);
        map.put("customized_path", customizedPath);
        return map;
    }

    public Map<String, String> getSaveMp4Map() {
        Map<String, String> map = toMap();
        map.put("customized_path", customizedPath);
        map.put("max_seconds", maxSeconds);
        map.put("type", String.valueOf(type));
        return map;
    }

    public Map<String, String> getRecordSpeedMap() {
        Map<String, String> map = toMap();
        map.put("speed", speed);
        return map;
    }

    public Map<String, String> getSeekRecordStampMap() {
        Map<String, String> map = toMap();
        map.put("stamp", stamp);
        return map;
    }

    public Map<String, String> getStopRecordMap() {
        Map<String, String> map = toMap();
        map.put("type", String.valueOf(type));
        return map;
    }

    public Map<String, String> getIsRecordingMap() {
        Map<String, String> map = toMap();
        map.put("type", String.valueOf(type));
        return map;
    }
}
