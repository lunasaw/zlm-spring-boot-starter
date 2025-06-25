package io.github.lunasaw.zlm.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

/**
 * @author weidian
 */
@Data
public class ServerResponse<T> {

    @JSONField(name = "code")
    private Integer code;

    @JSONField(name = "data")
    private T data;

    @JSONField(name = "msg")
    private String msg = "success";

    @JSONField(name = "result")
    private String result = "success";

    public static ServerResponse fail(String msg) {
        ServerResponse response = new ServerResponse<>();
        response.setCode(500);
        response.setMsg(msg);
        return response;
    }
}