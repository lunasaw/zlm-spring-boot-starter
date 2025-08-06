package io.github.lunasaw.zlm.hook.service;

import io.github.lunasaw.zlm.entity.ServerNodeConfig;
import io.github.lunasaw.zlm.entity.StreamKey;
import io.github.lunasaw.zlm.entity.StreamProxyItem;
import io.github.lunasaw.zlm.hook.param.*;
import jakarta.servlet.http.HttpServletRequest;

/**
 * <a href="https://github.com/zlmediakit/ZLMediaKit/wiki/MediaServer%E6%94%AF%E6%8C%81%E7%9A%84HTTP-HOOK-API">...</a>
 *
 * @author luna
 * @version 1.0
 * @date 2023/12/3
 * @description:
 */
public interface ZlmHookService {

    void onServerKeepLive(OnServerKeepaliveHookParam param, HttpServletRequest request);

    HookResult onPlay(OnPlayHookParam param, HttpServletRequest request);

    HookResultForOnPublish onPublish(OnPublishHookParam param, HttpServletRequest request);

    void onStreamChanged(OnStreamChangedHookParam param, HttpServletRequest request);

    HookResultForStreamNoneReader onStreamNoneReader(OnStreamNoneReaderHookParam param, HttpServletRequest request);

    void onStreamNotFound(OnStreamNotFoundHookParam param, HttpServletRequest request);

    void onServerStarted(ServerNodeConfig param, HttpServletRequest request);

    void onSendRtpStopped(OnSendRtpStoppedHookParam param, HttpServletRequest request);

    void onRtpServerTimeout(OnRtpServerTimeoutHookParam param, HttpServletRequest request);

    HookResultForOnHttpAccess onHttpAccess(OnHttpAccessParam param, HttpServletRequest request);

    HookResultForOnRtspRealm onRtspRealm(OnRtspRealmHookParam param, HttpServletRequest request);

    HookResultForOnRtspAuth onRtspAuth(OnRtspAuthHookParam param, HttpServletRequest request);

    void onFlowReport(OnFlowReportHookParam param, HttpServletRequest request);

    void onServerExited(HookParam param, HttpServletRequest request);

    void onRecordMp4(OnRecordMp4HookParam param, HttpServletRequest request);

    void onProxyAdded(StreamProxyItem param, StreamKey streamKey, HttpServletRequest request);
}
