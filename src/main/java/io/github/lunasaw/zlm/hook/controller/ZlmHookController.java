package io.github.lunasaw.zlm.hook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;

import io.github.lunasaw.zlm.entity.ServerNodeConfig;
import io.github.lunasaw.zlm.hook.param.*;
import io.github.lunasaw.zlm.hook.service.ZlmHookService;

/**
 * @author weidian
 * @version 1.0
 * @date 2023/12/3
 * @description:
 */
@RestController
@RequestMapping("/index/hook/")
public class ZlmHookController {

    @Autowired
    private ZlmHookService    zlmHookService;

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
    public HookResult onServerKeepalive(@RequestBody JSONObject param) {
        executor.execute(
            () -> zlmHookService.onServerKeepLive(
                JSON.parseObject(param.getJSONObject("data").toJSONString(), OnServerKeepaliveHookParam.class)));
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

    /**
     * 发送rtp(startSendRtp)被动关闭时回调
     */
    @ResponseBody
    @PostMapping(value = "/on_send_rtp_stopped", produces = "application/json;charset=UTF-8")
    public HookResult onSendRtpStopped(@RequestBody OnSendRtpStoppedHookParam param) {
        executor.execute(() -> zlmHookService.onSendRtpStopped(param));
        return HookResult.SUCCESS();
    }
}