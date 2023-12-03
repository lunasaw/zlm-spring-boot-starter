package io.github.lunasaw.zlm.entity;

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
public class LoadMp4FileReq extends MediaReq {

    /**
     * mp4文件绝对路径
     */
    private String filePath;

    public Map<String, String> getMap() {
        Map<String, String> map = toMap();
        map.put("file_path", filePath);
        return map;
    }
}
