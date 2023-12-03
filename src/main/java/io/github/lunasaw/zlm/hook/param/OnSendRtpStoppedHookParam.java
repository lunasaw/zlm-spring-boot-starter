package io.github.lunasaw.zlm.hook.param;

import lombok.Data;

/**
 * zlm hook事件中的on_send_rtp_stopped事件的参数
 * 
 * @author luna
 */
@Data
public class OnSendRtpStoppedHookParam extends HookParam {
    private String app;
    private String stream;

}
