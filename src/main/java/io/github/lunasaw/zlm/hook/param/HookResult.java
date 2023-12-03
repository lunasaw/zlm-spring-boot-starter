package io.github.lunasaw.zlm.hook.param;

import lombok.Data;

@Data
public class HookResult {

    private int    code;
    private String msg;

    public HookResult() {}

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
