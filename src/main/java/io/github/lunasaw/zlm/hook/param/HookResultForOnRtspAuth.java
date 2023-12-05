package io.github.lunasaw.zlm.hook.param;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Java 类表示
 */
@Data
public class HookResultForOnRtspAuth extends HookResult {

    /**
     * 是否加密
     */
    @JSONField(name = "encrypted")
    @JsonProperty("encrypted")
    private boolean encrypted;

    /**
     * 密码
     * 用户密码明文或摘要(md5(username:realm:password))
     */
    @JSONField(name = "passwd")
    @JsonProperty("passwd")
    private String passwd;

    // getters and setters...

    public static HookResultForOnRtspAuth SUCCESS() {
        return new HookResultForOnRtspAuth();
    }


    public HookResultForOnRtspAuth() {
    }

    public HookResultForOnRtspAuth(boolean encrypted, String passwd) {
        super(0, "");
        this.encrypted = encrypted;
        this.passwd = passwd;
    }
}
