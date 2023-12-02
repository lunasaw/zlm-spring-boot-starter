package io.github.lunasaw.zlm.entity;

import lombok.Data;

/**
 * @author weidian
 * @version 1.0
 * @date 2023/12/2
 * @description:
 */
@Data
public class StreamKey {

    private String key;

    @Data
    public static class StringDelFlag {

        private String flag;
    }
}
