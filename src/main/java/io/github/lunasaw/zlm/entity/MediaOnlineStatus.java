package io.github.lunasaw.zlm.entity;

import lombok.Data;

/**
 * @author weidian
 * @version 1.0
 * @date 2023/12/2
 * @description:
 */
@Data
public class MediaOnlineStatus extends ServerResponse<String> {
    private String online;

}
