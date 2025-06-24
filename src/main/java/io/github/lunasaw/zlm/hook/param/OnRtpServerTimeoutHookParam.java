package io.github.lunasaw.zlm.hook.param;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

/**
 * zlm hook事件中的on_rtp_server_timeout事件的参数
 * 
 * @author luna
 */
@Data
public class OnRtpServerTimeoutHookParam extends HookParam {
    @JSONField(name = "local_port")
    private int     localPort;
    @JSONField(name = "stream_id")
    private String  streamId;
    @JSONField(name = "tcpMode")
    private int     tcpMode;
    @JSONField(name = "reUsePort")
    private boolean re_use_port;
    @JSONField(name = "ssrc")
    private String  ssrc;

}
