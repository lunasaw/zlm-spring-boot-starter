package io.github.lunasaw.zlm.hook.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HookResultForStreamNoneReader extends HookResult {

    private boolean close;

    public static HookResultForStreamNoneReader SUCCESS() {
        return new HookResultForStreamNoneReader(0, "success");
    }

    public HookResultForStreamNoneReader(int code, String msg) {
        setCode(code);
        setMsg(msg);
    }
}
