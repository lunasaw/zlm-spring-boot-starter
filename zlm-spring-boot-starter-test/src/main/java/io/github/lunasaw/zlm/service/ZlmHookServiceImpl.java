package io.github.lunasaw.zlm.service;

import io.github.lunasaw.zlm.entity.ServerNodeConfig;
import io.github.lunasaw.zlm.hook.param.*;
import io.github.lunasaw.zlm.hook.service.ZlmHookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author luna
 * @version 1.0
 * @date 2023/12/3
 * @description:
 */
@Slf4j
@Service
public class ZlmHookServiceImpl implements ZlmHookService {
    @Override
    public void onServerKeepLive(OnServerKeepaliveHookParam param) {
        log.info("onServerKeepLive::param = {}", param);
    }

    @Override
    public HookResult onPlay(OnPlayHookParam param) {
        log.info("onPlay::param = {}", param);
        return null;
    }

    @Override
    public HookResultForOnPublish onPublish(OnPublishHookParam param) {
        return HookResultForOnPublish.SUCCESS();
    }

    @Override
    public void onStreamChanged(OnStreamChangedHookParam param) {
        log.info("onStreamChanged::param = {}", param);
    }

    @Override
    public HookResultForStreamNoneReader onStreamNoneReader(OnStreamNoneReaderHookParam param) {
        return HookResultForStreamNoneReader.SUCCESS();
    }

    @Override
    public void onStreamNotFound(OnStreamNotFoundHookParam param) {
        log.info("onStreamNotFound::param = {}", param);
    }

    @Override
    public void onServerStarted(ServerNodeConfig param) {
        log.info("onServerStarted::param = {}", param);
    }

    @Override
    public void onSendRtpStopped(OnSendRtpStoppedHookParam param) {
        log.info("onSendRtpStopped::param = {}", param);
    }

    @Override
    public void onRtpServerTimeout(OnRtpServerTimeoutHookParam param) {
        log.info("onRtpServerTimeout::param = {}", param);
    }

    @Override
    public HookResultForOnHttpAccess onHttpAccess(OnHttpAccessParam param) {
        log.info("onHttpAccess::param = {}", param);
        return HookResultForOnHttpAccess.SUCCESS(param.getMediaServerId());
    }
}
