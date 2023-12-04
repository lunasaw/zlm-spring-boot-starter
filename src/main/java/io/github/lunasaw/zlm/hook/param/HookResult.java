package io.github.lunasaw.zlm.hook.param;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class HookResult {


    /**
     * 错误代码，0代表允许推流
     */
    @JSONField(name = "code")
    private int code;

    /**
     * 不允许推流时的错误提示
     */
    @JSONField(name = "msg")
    private String msg;

    public HookResult() {
    }

    public HookResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static HookResult SUCCESS() {
        return new HookResult(0, "success");
    }

    public static HookResult Fail() {
        return new HookResult(-1, "fail");
    }

}
