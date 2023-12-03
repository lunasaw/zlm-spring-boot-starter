package io.github.lunasaw.zlm.hook.param;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HookResultForOnPublish extends HookResult {

    @JSONField(name = "enable_audio")
    private boolean enableAudio;
    @JSONField(name = "enable_mp4")
    private boolean enableMp4;
    @JSONField(name = "mp4_max_second")
    private int     mp4MaxSecond;
    @JSONField(name = "mp4_save_path")
    private String  mp4SavePath;
    @JSONField(name = "stream_replace")
    private String  streamReplace;

    public static HookResultForOnPublish SUCCESS() {
        return new HookResultForOnPublish(0, "success");
    }

    public HookResultForOnPublish(int code, String msg) {
        setCode(code);
        setMsg(msg);
    }
}
