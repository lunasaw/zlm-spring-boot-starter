package io.github.lunasaw.zlm.hook.service;

import io.github.lunasaw.zlm.hook.param.OnServerKeepaliveHookParam;

/**
 * @author weidian
 * @version 1.0
 * @date 2023/12/3
 * @description:
 */
public interface ZlmHookService {

    void onServerKeepLive(OnServerKeepaliveHookParam param);

}
