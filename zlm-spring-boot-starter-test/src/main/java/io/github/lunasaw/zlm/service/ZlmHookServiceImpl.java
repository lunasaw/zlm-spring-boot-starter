package io.github.lunasaw.zlm.service;

import com.alibaba.fastjson2.JSON;
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
    }

    @Override
    public HookResult onPlay(OnPlayHookParam param) {
        log.info("onPlay::param = {}", JSON.toJSONString(param));
        return HookResult.SUCCESS();
    }

    @Override
    public HookResultForOnPublish onPublish(OnPublishHookParam param) {
        return HookResultForOnPublish.SUCCESS();
    }

    @Override
    public void onStreamChanged(OnStreamChangedHookParam param) {
        log.info("onStreamChanged::param = {}", JSON.toJSONString(param));
    }

    @Override
    public HookResultForStreamNoneReader onStreamNoneReader(OnStreamNoneReaderHookParam param) {
        return HookResultForStreamNoneReader.SUCCESS();
    }

    @Override
    public void onStreamNotFound(OnStreamNotFoundHookParam param) {
        log.info("onStreamNotFound::param = {}", JSON.toJSONString(param));
    }

    @Override
    public void onServerStarted(ServerNodeConfig param) {
        log.info("onServerStarted::param = {}", JSON.toJSONString(param));
    }

    @Override
    public void onSendRtpStopped(OnSendRtpStoppedHookParam param) {
        log.info("onSendRtpStopped::param = {}", JSON.toJSONString(param));
    }

    @Override
    public void onRtpServerTimeout(OnRtpServerTimeoutHookParam param) {
        log.info("onRtpServerTimeout::param = {}", JSON.toJSONString(param));
    }

    @Override
    public HookResultForOnHttpAccess onHttpAccess(OnHttpAccessParam param) {
        log.info("onHttpAccess::param = {}", JSON.toJSONString(param));
        return HookResultForOnHttpAccess.SUCCESS();
    }

    @Override
    public HookResultForOnRtspRealm onRtspRealm(OnRtspRealmHookParam param) {
        log.info("onRtspRealm::param = {}", JSON.toJSONString(param));
        return HookResultForOnRtspRealm.SUCCESS();
    }

    @Override
    public HookResultForOnRtspAuth onRtspAuth(OnRtspAuthHookParam param) {
        log.info("onRtspAuth::param = {}", JSON.toJSONString(param));
        return new HookResultForOnRtspAuth();
    }

    @Override
    public void onFlowReport(OnFlowReportHookParam param) {
        log.info("onFlowReport::param = {}", JSON.toJSONString(param));
    }
}
