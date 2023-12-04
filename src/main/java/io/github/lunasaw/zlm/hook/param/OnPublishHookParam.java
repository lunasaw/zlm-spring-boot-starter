package io.github.lunasaw.zlm.hook.param;

import lombok.Data;

/**
 * zlm hook事件中的on_publish事件的参数
 * 
 * @author luna
 */
@Data
public class OnPublishHookParam extends HookParam {
    /**
     * app	string	流应用名
     * id	string	TCP链接唯一ID
     * ip	string	推流器ip
     * params	string	推流url参数
     * port	unsigned short	推流器端口号
     * schema	string	推流的协议，可能是rtsp、rtmp
     * stream	string	流ID
     * vhost	string	流虚拟主机
     * mediaServerId	string	服务器id,通过配置文件设置
     */
    private String id;
    private String app;
    private String stream;
    private String ip;
    private String params;
    private int    port;
    private String schema;
    private String vhost;

}
