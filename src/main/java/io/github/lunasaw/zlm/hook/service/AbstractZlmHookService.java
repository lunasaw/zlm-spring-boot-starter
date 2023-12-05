package io.github.lunasaw.zlm.hook.service;

import io.github.lunasaw.zlm.entity.ServerNodeConfig;
import io.github.lunasaw.zlm.hook.param.*;
import io.github.lunasaw.zlm.hook.service.ZlmHookService;
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
    public void onServerKeepLive(OnServerKeepaliveHookParam param) {
    }

    @Override
    public HookResult onPlay(OnPlayHookParam param) {
        return HookResult.SUCCESS();
    }

    @Override
    public HookResultForOnPublish onPublish(OnPublishHookParam param) {
        return HookResultForOnPublish.SUCCESS();
    }

    @Override
    public void onStreamChanged(OnStreamChangedHookParam param) {

    }

    @Override
    public HookResultForStreamNoneReader onStreamNoneReader(OnStreamNoneReaderHookParam param) {
        return HookResultForStreamNoneReader.SUCCESS();
    }

    @Override
    public void onStreamNotFound(OnStreamNotFoundHookParam param) {
    }

    @Override
    public void onServerStarted(ServerNodeConfig param) {
    }

    @Override
    public void onSendRtpStopped(OnSendRtpStoppedHookParam param) {
    }

    @Override
    public void onRtpServerTimeout(OnRtpServerTimeoutHookParam param) {

    }

    @Override
    public HookResultForOnHttpAccess onHttpAccess(OnHttpAccessParam param) {
        return HookResultForOnHttpAccess.SUCCESS();
    }

    @Override
    public HookResultForOnRtspRealm onRtspRealm(OnRtspRealmHookParam param) {
        return HookResultForOnRtspRealm.SUCCESS();
    }

    @Override
    public HookResultForOnRtspAuth onRtspAuth(OnRtspAuthHookParam param) {
        return HookResultForOnRtspAuth.SUCCESS();
    }

    @Override
    public void onFlowReport(OnFlowReportHookParam param) {

    }
}
