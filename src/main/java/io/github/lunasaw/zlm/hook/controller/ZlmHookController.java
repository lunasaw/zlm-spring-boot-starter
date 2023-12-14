package io.github.lunasaw.zlm.hook.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import io.github.lunasaw.zlm.entity.ServerNodeConfig;
import io.github.lunasaw.zlm.hook.param.*;
import io.github.lunasaw.zlm.hook.service.ZlmHookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.web.bind.annotation.*;

/**
 * @author luna
 * @version 1.0
 * @date 2023/12/3
 * @description: ZLM钩子控制器
 */
@Slf4j
@RestController
@RequestMapping("/index/hook/")
public class ZlmHookController {

    @Autowired
    private ZlmHookService zlmHookService;

    @Qualifier("taskExecutor")
    @Autowired
    private AsyncTaskExecutor executor;

    /**
     * 服务器定时上报时间，上报间隔可配置，默认10s上报一次
     *
     * @param param
     * @return
     */
    @PostMapping(value = "/on_server_keepalive", produces = "application/json;charset=UTF-8")
    public HookResult onServerKeepalive(@RequestBody OnServerKeepaliveHookParam param) {
        executor.execute(() -> zlmHookService.onServerKeepLive(param));
        return HookResult.SUCCESS();
    }

    /**
     * 播放器鉴权事件，rtsp/rtmp/http-flv/ws-flv/hls的播放都将触发此鉴权事件。
     */
    @ResponseBody
    @PostMapping(value = "/on_play", produces = "application/json;charset=UTF-8")
    public HookResult onPlay(@RequestBody OnPlayHookParam param) {
        return zlmHookService.onPlay(param);
    }

    /**
     * rtsp/rtmp/rtp推流鉴权事件。
     */
    @ResponseBody
    @PostMapping(value = "/on_publish", produces = "application/json;charset=UTF-8")
    public HookResultForOnPublish onPublish(@RequestBody OnPublishHookParam param) {
        return zlmHookService.onPublish(param);
    }

    /**
     * rtsp/rtmp流注册或注销时触发此事件；此事件对回复不敏感。
     */
    @ResponseBody
    @PostMapping(value = "/on_stream_changed", produces = "application/json;charset=UTF-8")
    public HookResult onStreamChanged(@RequestBody OnStreamChangedHookParam param) {
        executor.execute(() -> zlmHookService.onStreamChanged(param));
        return HookResult.SUCCESS();
    }

    /**
     * 流无人观看时事件，用户可以通过此事件选择是否关闭无人看的流。
     * <p>
     * 流无人观看时事件，用户可以通过此事件选择是否关闭无人看的流。 一个直播流注册上线了，如果一直没人观看也会触发一次无人观看事件，触发时的协议schema是随机的，看哪种协议最晚注册(一般为hls)。
     * 后续从有人观看转为无人观看，触发协议schema为最后一名观看者使用何种协议。
     * 目前mp4/hls录制不当做观看人数(mp4录制可以通过配置文件mp4_as_player控制，但是rtsp/rtmp/rtp转推算观看人数，也会触发该事件。
     */
    @ResponseBody
    @PostMapping(value = "/on_stream_none_reader", produces = "application/json;charset=UTF-8")
    public HookResultForStreamNoneReader onStreamNoneReader(@RequestBody OnStreamNoneReaderHookParam param) {
        return zlmHookService.onStreamNoneReader(param);
    }

    /**
     * 流未找到事件，用户可以在此事件触发时，立即去拉流，这样可以实现按需拉流；此事件对回复不敏感。
     *
     * @return code    int	错误代码，0代表允许播放 msg	string	不允许播放时的错误提示
     */
    @ResponseBody
    @PostMapping(value = "/on_stream_not_found", produces = "application/json;charset=UTF-8")
    public HookResult onStreamNotFound(@RequestBody OnStreamNotFoundHookParam param) {
        executor.execute(() -> zlmHookService.onStreamNotFound(param));
        return HookResult.SUCCESS();
    }

    /**
     * 服务器启动事件，可以用于监听服务器崩溃重启；此事件对回复不敏感。
     */
    @ResponseBody
    @PostMapping(value = "/on_server_started", produces = "application/json;charset=UTF-8")
    public HookResult onServerStarted(@RequestBody JSONObject param) {
        executor.execute(() -> zlmHookService.onServerStarted(JSON.parseObject(param.toJSONString(), ServerNodeConfig.class)));
        return HookResult.SUCCESS();
    }

    @ResponseBody
    @PostMapping(value = "/on_server_exited", produces = "application/json;charset=UTF-8")
    public HookResult onServerExited(@RequestBody HookParam param) {
        executor.execute(() -> zlmHookService.onServerExited(param));
        return HookResult.SUCCESS();
    }

    /**
     * 发送rtp(startSendRtp)被动关闭时回调
     */
    @ResponseBody
    @PostMapping(value = "/on_send_rtp_stopped", produces = "application/json;charset=UTF-8")
    public HookResult onSendRtpStopped(@RequestBody OnSendRtpStoppedHookParam param) {
        executor.execute(() -> zlmHookService.onSendRtpStopped(param));
        return HookResult.SUCCESS();
    }


    /**
     * rtpServer收流超时
     * 调用openRtpServer 接口，rtp server 长时间未收到数据,执行此web hook,对回复不敏感
     */
    @ResponseBody
    @PostMapping(value = "/on_rtp_server_timeout", produces = "application/json;charset=UTF-8")
    public HookResult onRtpServerTimeout(@RequestBody OnRtpServerTimeoutHookParam param) {
        executor.execute(() -> zlmHookService.onRtpServerTimeout(param));
        return HookResult.SUCCESS();
    }

    /**
     * 访问http文件服务器上hls之外的文件时触发。结果会被缓存Cookie
     *
     * @param param
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/on_http_access", produces = "application/json;charset=UTF-8")
    public HookResultForOnHttpAccess onHttpAccess(@RequestBody OnHttpAccessParam param) {
        return zlmHookService.onHttpAccess(param);
    }


    /**
     * 该rtsp流是否开启rtsp专用方式的鉴权事件，开启后才会触发on_rtsp_auth事件。
     * <p>
     * 需要指出的是rtsp也支持url参数鉴权，它支持两种方式鉴权。
     *
     * @param param
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/on_rtsp_realm", produces = "application/json;charset=UTF-8")
    public HookResultForOnRtspRealm onRtspRealm(@RequestBody OnRtspRealmHookParam param) {
        return zlmHookService.onRtspRealm(param);
    }

    /**
     * rtsp专用的鉴权事件，先触发on_rtsp_realm事件然后才会触发on_rtsp_auth事件。
     *
     * @param param
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/on_rtsp_auth", produces = "application/json;charset=UTF-8")
    public HookResultForOnRtspAuth onRtspAuth(@RequestBody OnRtspAuthHookParam param) {
        return zlmHookService.onRtspAuth(param);
    }

    /**
     * 流量统计事件，播放器或推流器断开时并且耗用流量超过特定阈值时会触发此事件，
     * 阈值通过配置文件general.flowThreshold配置；此事件对回复不敏感。
     *
     * @param param
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/on_flow_report", produces = "application/json;charset=UTF-8")
    public HookResult onFlowReport(@RequestBody OnFlowReportHookParam param) {
        executor.execute(() -> zlmHookService.onFlowReport(param));
        return HookResult.SUCCESS();
    }


}