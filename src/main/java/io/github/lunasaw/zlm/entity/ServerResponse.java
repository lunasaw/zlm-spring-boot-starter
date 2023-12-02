package io.github.lunasaw.zlm.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class ServerResponse<T> {

    @JSONField(name = "code")
    private Integer code;

    @JSONField(name = "data")
    private T data;

    @JSONField(name = "msg")
    private String msg;

    @JSONField(name = "result")
    private String result;
}