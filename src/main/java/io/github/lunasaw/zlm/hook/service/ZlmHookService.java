package io.github.lunasaw.zlm.hook.service;

import io.github.lunasaw.zlm.entity.ServerNodeConfig;
import io.github.lunasaw.zlm.hook.param.*;

/**
 * <a href="https://github.com/zlmediakit/ZLMediaKit/wiki/MediaServer%E6%94%AF%E6%8C%81%E7%9A%84HTTP-HOOK-API">...</a>
 * 
 * @author luna
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

    HookResultForOnHttpAccess onHttpAccess(OnHttpAccessParam param);

    HookResultForOnRtspRealm onRtspRealm(OnRtspRealmHookParam param);

    HookResultForOnRtspAuth onRtspAuth(OnRtspAuthHookParam param);

    void onFlowReport(OnFlowReportHookParam param);

    void onServerExited(HookParam param);

    void onRecordMp4(OnRecordMp4HookParam param);
}
