package io.github.lunasaw.zlm.hook.service;

import io.github.lunasaw.zlm.entity.ServerNodeConfig;
import io.github.lunasaw.zlm.hook.param.*;

/**
 * @author weidian
 * @version 1.0
 * @date 2023/12/3
 * @description:
 */
public interface ZlmHookService {

    void onServerKeepLive(OnServerKeepaliveHookParam param);

    HookResult onPlay(OnPlayHookParam param);

    HookResultForOnPublish onPublish(OnPublishHookParam param);

    void onStreamChanged(OnStreamChangedHookParam param);

    HookResultForStreamNoneReader onStreamNoneReader(OnStreamNoneReaderHookParam param);

    void onStreamNotFound(OnStreamNotFoundHookParam param);

    void onServerStarted(ServerNodeConfig param);

    void onSendRtpStopped(OnSendRtpStoppedHookParam param);

    void onRtpServerTimeout(OnRtpServerTimeoutHookParam param);
}
