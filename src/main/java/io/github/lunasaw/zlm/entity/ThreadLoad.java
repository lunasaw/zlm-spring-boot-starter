package io.github.lunasaw.zlm.entity;

import lombok.Data;

/**
 * @author luna
 * @version 1.0
 * @date 2023/12/2
 * @description: 获取各epoll(或select)线程负载以及延时
 */
@Data
public class ThreadLoad {

    /**
     * 该线程延时
     */
    private String delay;

    /**
     * 该线程负载，0 ~ 100
     */
    private String load;
}
