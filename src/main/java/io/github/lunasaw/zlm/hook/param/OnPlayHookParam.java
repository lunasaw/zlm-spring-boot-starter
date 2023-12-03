package io.github.lunasaw.zlm.hook.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * zlm hook事件中的on_play事件的参数
 * 
 * @author luna
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnPlayHookParam extends HookParam {

    private String id;
    private String app;
    private String stream;
    private String ip;
    private String params;
    private int    port;
    private String schema;
    private String vhost;

}
