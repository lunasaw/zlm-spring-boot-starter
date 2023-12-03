package io.github.lunasaw.zlm.hook.param;

import lombok.Data;

/**
 * zlm hook事件中的on_stream_not_found事件的参数
 * 
 * @author luna
 */
@Data
public class OnStreamNotFoundHookParam extends HookParam {
    private String id;
    private String app;
    private String stream;
    private String ip;
    private String params;
    private int    port;
    private String schema;
    private String vhost;

}
