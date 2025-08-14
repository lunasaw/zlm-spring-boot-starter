package io.github.lunasaw.zlm.hook.service;

import io.github.lunasaw.zlm.entity.ServerNodeConfig;
import io.github.lunasaw.zlm.entity.StreamKey;
import io.github.lunasaw.zlm.entity.StreamProxyItem;
import io.github.lunasaw.zlm.hook.param.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * @author luna
 * @version 1.0
 * @date 2023/12/3
 * @description: 默认的钩子服务实现
 */
@Slf4j
public abstract class AbstractZlmHookService implements ZlmHookService {
    @Override
    public void onServerKeepLive(OnServerKeepaliveHookParam param, HttpServletRequest request) {
    }

    @Override
    public HookResult onPlay(OnPlayHookParam param, HttpServletRequest request) {
        return HookResult.SUCCESS();
    }

    @Override
    public HookResultForOnPublish onPublish(OnPublishHookParam param, HttpServletRequest request) {
        return HookResultForOnPublish.SUCCESS();
    }

    @Override
    public void onStreamChanged(OnStreamChangedHookParam param, HttpServletRequest request) {

    }

    @Override
    public HookResultForStreamNoneReader onStreamNoneReader(OnStreamNoneReaderHookParam param, HttpServletRequest request) {
        return HookResultForStreamNoneReader.SUCCESS();
    }

    @Override
    public void onStreamNotFound(OnStreamNotFoundHookParam param, HttpServletRequest request) {
    }

    @Override
    public void onServerStarted(ServerNodeConfig param, HttpServletRequest request) {
    }

    @Override
    public void onSendRtpStopped(OnSendRtpStoppedHookParam param, HttpServletRequest request) {
    }

    @Override
    public void onRtpServerTimeout(OnRtpServerTimeoutHookParam param, HttpServletRequest request) {

    }

    @Override
    public HookResultForOnHttpAccess onHttpAccess(OnHttpAccessParam param, HttpServletRequest request) {
        return HookResultForOnHttpAccess.SUCCESS();
    }

    @Override
    public HookResultForOnRtspRealm onRtspRealm(OnRtspRealmHookParam param, HttpServletRequest request) {
        return HookResultForOnRtspRealm.SUCCESS();
    }

    @Override
    public HookResultForOnRtspAuth onRtspAuth(OnRtspAuthHookParam param, HttpServletRequest request) {
        return HookResultForOnRtspAuth.SUCCESS();
    }

    @Override
    public void onFlowReport(OnFlowReportHookParam param, HttpServletRequest request) {

    }

    @Override
    public void onServerExited(HookParam param, HttpServletRequest request) {

    }

    @Override
    public void onRecordMp4(OnRecordMp4HookParam param, HttpServletRequest request) {

    }

    @Override
    public void onProxyAdded(StreamProxyItem param, StreamKey streamKey, HttpServletRequest request) {

    }
}
