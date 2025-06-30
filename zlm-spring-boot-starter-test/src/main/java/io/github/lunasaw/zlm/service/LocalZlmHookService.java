package io.github.lunasaw.zlm.service;

import com.alibaba.fastjson2.JSON;
import io.github.lunasaw.zlm.entity.ServerNodeConfig;
import io.github.lunasaw.zlm.hook.param.*;
import io.github.lunasaw.zlm.hook.service.AbstractZlmHookService;
import jakarta.servlet.http.HttpServletRequest;
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
public class LocalZlmHookService extends AbstractZlmHookService {
    @Override
    public void onServerKeepLive(OnServerKeepaliveHookParam param, HttpServletRequest request) {
    }

    @Override
    public HookResult onPlay(OnPlayHookParam param, HttpServletRequest request) {
        log.info("onPlay::param = {}", JSON.toJSONString(param));
        return HookResult.SUCCESS();
    }

    @Override
    public HookResultForOnPublish onPublish(OnPublishHookParam param, HttpServletRequest request) {
        return HookResultForOnPublish.SUCCESS();
    }

    @Override
    public void onStreamChanged(OnStreamChangedHookParam param, HttpServletRequest request) {
        log.info("onStreamChanged::param = {}", JSON.toJSONString(param));
    }

    @Override
    public HookResultForStreamNoneReader onStreamNoneReader(OnStreamNoneReaderHookParam param, HttpServletRequest request) {
        return HookResultForStreamNoneReader.SUCCESS();
    }

    @Override
    public void onStreamNotFound(OnStreamNotFoundHookParam param, HttpServletRequest request) {
        log.info("onStreamNotFound::param = {}", JSON.toJSONString(param));
    }

    @Override
    public void onServerStarted(ServerNodeConfig param, HttpServletRequest request) {
        log.info("onServerStarted::param = {}", JSON.toJSONString(param));
    }

    @Override
    public void onSendRtpStopped(OnSendRtpStoppedHookParam param, HttpServletRequest request) {
        log.info("onSendRtpStopped::param = {}", JSON.toJSONString(param));
    }

    @Override
    public void onRtpServerTimeout(OnRtpServerTimeoutHookParam param, HttpServletRequest request) {
        log.info("onRtpServerTimeout::param = {}", JSON.toJSONString(param));
    }

    @Override
    public HookResultForOnHttpAccess onHttpAccess(OnHttpAccessParam param, HttpServletRequest request) {
        log.info("onHttpAccess::param = {}", JSON.toJSONString(param));
        return HookResultForOnHttpAccess.SUCCESS();
    }

    @Override
    public HookResultForOnRtspRealm onRtspRealm(OnRtspRealmHookParam param, HttpServletRequest request) {
        log.info("onRtspRealm::param = {}", JSON.toJSONString(param));
        return HookResultForOnRtspRealm.SUCCESS();
    }

    @Override
    public HookResultForOnRtspAuth onRtspAuth(OnRtspAuthHookParam param, HttpServletRequest request) {
        log.info("onRtspAuth::param = {}", JSON.toJSONString(param));
        return new HookResultForOnRtspAuth();
    }

    @Override
    public void onFlowReport(OnFlowReportHookParam param, HttpServletRequest request) {
        log.info("onFlowReport::param = {}", JSON.toJSONString(param));
    }

    @Override
    public void onServerExited(HookParam param, HttpServletRequest request) {
        log.info("onServerExited::param = {}", param);
    }

    @Override
    public void onRecordMp4(OnRecordMp4HookParam param, HttpServletRequest request) {
        log.info("onRecordMp4::param = {}", param);
    }
}
