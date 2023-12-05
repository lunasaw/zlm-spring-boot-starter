package io.github.lunasaw.zlm.hook.param;

import lombok.Data;

/**
 * @author luna
 * @date 2023/12/5
 */
@Data
public class HookResultForOnRtspRealm extends HookResult {

    /**
     * 该rtsp流是否需要rtsp专有鉴权，空字符串代码不需要鉴权
     */
    private String realm;

    public static HookResultForOnRtspRealm SUCCESS() {
        return new HookResultForOnRtspRealm();
    }
}
