package io.github.lunasaw.zlm.hook.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import io.github.lunasaw.zlm.entity.ServerNodeConfig;
import io.github.lunasaw.zlm.hook.param.*;
import io.github.lunasaw.zlm.hook.service.ZlmHookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.web.bind.annotation.*;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author luna
 * @version 1.0
 * @date 2023/12/3
 * @description: ZLM钩子控制器
 */
@Slf4j
@RestController
@ConditionalOnProperty(value = "zlm.hook-enable", havingValue = "true")
@RequestMapping("/index/hook/")
@Tag(name = "ZLM钩子事件", description = "ZLMediaKit钩子事件回调接口，用于处理各种媒体流事件")
public class ZlmHookController {

    @Autowired
    private ZlmHookService zlmHookService;

    @Qualifier("taskExecutor")
    @Autowired
    private AsyncTaskExecutor executor;

    /**
     * 处理同步Hook事件的通用方法
     *
     * @param hookName Hook事件名称，用于日志输出
     * @param param    Hook参数
     * @param function Hook处理函数
     * @param <T>      参数类型
     * @param <R>      返回类型
     * @return Hook响应结果
     */
    private <T, R> R handleSyncHookEvent(String hookName, T param, Function<T, R> function) {
        try {
            log.info("{}::param = {}", hookName, JSON.toJSONString(param));
            R result = function.apply(param);
            log.info("{} success, result = {}", hookName, JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            log.error("{} fail, param = {}", hookName, JSON.toJSONString(param), e);
            throw e;
        }
    }

    /**
     * 处理同步Hook事件的通用方法（带HttpServletRequest参数）
     *
     * @param hookName Hook事件名称，用于日志输出
     * @param param    Hook参数
     * @param request  HTTP请求对象
     * @param function Hook处理函数
     * @param <T>      参数类型
     * @param <R>      返回类型
     * @return Hook响应结果
     */
    private <T, R> R handleSyncHookEventWithRequest(String hookName, T param, HttpServletRequest request, BiFunction<T, HttpServletRequest, R> function) {
        try {
            log.info("{}::param = {}", hookName, JSON.toJSONString(param));
            R result = function.apply(param, request);
            log.info("{} success, result = {}", hookName, JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            log.error("{} fail, param = {}", hookName, JSON.toJSONString(param), e);
            throw e;
        }
    }

    /**
     * 处理异步Hook事件的通用方法
     *
     * @param hookName Hook事件名称，用于日志输出
     * @param param    Hook参数
     * @param consumer Hook处理函数
     * @param <T>      参数类型
     * @return Hook响应结果（异步处理总是返回SUCCESS）
     */
    private <T> HookResult handleAsyncHookEvent(String hookName, T param, Consumer<T> consumer) {
        try {
            log.info("{}::param = {}", hookName, JSON.toJSONString(param));
            executor.execute(() -> {
                try {
                    consumer.accept(param);
                    log.info("{} async success", hookName);
                } catch (Exception e) {
                    log.error("{} async fail, param = {}", hookName, JSON.toJSONString(param), e);
                }
            });
            return HookResult.SUCCESS();
        } catch (Exception e) {
            log.error("{} fail, param = {}", hookName, JSON.toJSONString(param), e);
            return HookResult.SUCCESS();
        }
    }

    /**
     * 处理异步Hook事件的通用方法（带HttpServletRequest参数）
     *
     * @param hookName Hook事件名称，用于日志输出
     * @param param    Hook参数
     * @param request  HTTP请求对象
     * @param consumer Hook处理函数
     * @param <T>      参数类型
     * @return Hook响应结果（异步处理总是返回SUCCESS）
     */
    private <T> HookResult handleAsyncHookEventWithRequest(String hookName, T param, HttpServletRequest request, BiConsumer<T, HttpServletRequest> consumer) {
        try {
            log.info("{}::param = {}", hookName, JSON.toJSONString(param));
            executor.execute(() -> {
                try {
                    consumer.accept(param, request);
                    log.info("{} async success", hookName);
                } catch (Exception e) {
                    log.error("{} async fail, param = {}", hookName, JSON.toJSONString(param), e);
                }
            });
            return HookResult.SUCCESS();
        } catch (Exception e) {
            log.error("{} fail, param = {}", hookName, JSON.toJSONString(param), e);
            return HookResult.SUCCESS();
        }
    }

    /**
     * 服务器定时上报时间，上报间隔可配置，默认10s上报一次
     *
     * @param param
     * @return
     */
    @PostMapping(value = "/on_server_keepalive", produces = "application/json;charset=UTF-8")
    @Operation(summary = "服务器心跳上报", description = "服务器定时上报心跳信息，默认10秒上报一次")
    @ApiResponse(responseCode = "200", description = "处理成功",
            content = @Content(schema = @Schema(implementation = HookResult.class)))
    public HookResult onServerKeepalive(
            @Parameter(description = "服务器心跳参数") @RequestBody OnServerKeepaliveHookParam param, HttpServletRequest request) {
        return handleAsyncHookEventWithRequest("onServerKeepalive", param, request, zlmHookService::onServerKeepLive);
    }

    /**
     * 播放器鉴权事件，rtsp/rtmp/http-flv/ws-flv/hls的播放都将触发此鉴权事件。
     */
    @ResponseBody
    @PostMapping(value = "/on_play", produces = "application/json;charset=UTF-8")
    @Operation(summary = "播放器鉴权事件", description = "rtsp/rtmp/http-flv/ws-flv/hls播放触发的鉴权事件")
    @ApiResponse(responseCode = "200", description = "鉴权成功",
            content = @Content(schema = @Schema(implementation = HookResult.class)))
    public HookResult onPlay(
            @Parameter(description = "播放鉴权参数") @RequestBody OnPlayHookParam param, HttpServletRequest request) {
        return handleSyncHookEventWithRequest("onPlay", param, request, zlmHookService::onPlay);
    }

    /**
     * rtsp/rtmp/rtp推流鉴权事件。
     */
    @ResponseBody
    @PostMapping(value = "/on_publish", produces = "application/json;charset=UTF-8")
    @Operation(summary = "推流鉴权事件", description = "rtsp/rtmp/rtp推流时触发的鉴权事件")
    @ApiResponse(responseCode = "200", description = "鉴权成功",
            content = @Content(schema = @Schema(implementation = HookResultForOnPublish.class)))
    public HookResultForOnPublish onPublish(
            @Parameter(description = "推流鉴权参数") @RequestBody OnPublishHookParam param, HttpServletRequest request) {
        return handleSyncHookEventWithRequest("onPublish", param, request, zlmHookService::onPublish);
    }

    /**
     * rtsp/rtmp流注册或注销时触发此事件；此事件对回复不敏感。
     */
    @ResponseBody
    @PostMapping(value = "/on_stream_changed", produces = "application/json;charset=UTF-8")
    @Operation(summary = "流状态变化事件", description = "rtsp/rtmp流注册或注销时触发，此事件对回复不敏感")
    @ApiResponse(responseCode = "200", description = "处理成功",
            content = @Content(schema = @Schema(implementation = HookResult.class)))
    public HookResult onStreamChanged(
            @Parameter(description = "流状态变化参数") @RequestBody OnStreamChangedHookParam param, HttpServletRequest request) {
        return handleAsyncHookEventWithRequest("onStreamChanged", param, request, zlmHookService::onStreamChanged);
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
    public HookResultForStreamNoneReader onStreamNoneReader(@RequestBody OnStreamNoneReaderHookParam param, HttpServletRequest request) {
        return handleSyncHookEventWithRequest("onStreamNoneReader", param, request, zlmHookService::onStreamNoneReader);
    }

    /**
     * 流未找到事件，用户可以在此事件触发时，立即去拉流，这样可以实现按需拉流；此事件对回复不敏感。
     *
     * @return code    int	错误代码，0代表允许播放 msg	string	不允许播放时的错误提示
     */
    @ResponseBody
    @PostMapping(value = "/on_stream_not_found", produces = "application/json;charset=UTF-8")
    @Operation(summary = "流未找到事件", description = "流未找到时触发，可在此时进行按需拉流，此事件对回复不敏感")
    @ApiResponse(responseCode = "200", description = "处理成功",
            content = @Content(schema = @Schema(implementation = HookResult.class)))
    public HookResult onStreamNotFound(
            @Parameter(description = "流未找到参数") @RequestBody OnStreamNotFoundHookParam param, HttpServletRequest request) {
        return handleAsyncHookEventWithRequest("onStreamNotFound", param, request, zlmHookService::onStreamNotFound);
    }

    /**
     * 服务器启动事件，可以用于监听服务器崩溃重启；此事件对回复不敏感。
     */
    @ResponseBody
    @PostMapping(value = "/on_server_started", produces = "application/json;charset=UTF-8")
    @Operation(summary = "服务器启动事件", description = "服务器启动时触发，可用于监听服务器崩溃重启，此事件对回复不敏感")
    @ApiResponse(responseCode = "200", description = "处理成功",
            content = @Content(schema = @Schema(implementation = HookResult.class)))
    public HookResult onServerStarted(
            @Parameter(description = "服务器配置参数") @RequestBody ServerNodeConfig param, HttpServletRequest request) {
        log.info("onServerStarted::param = {}", param);
        executor.execute(() -> zlmHookService.onServerStarted(param, request));
        return HookResult.SUCCESS();
    }

    @ResponseBody
    @PostMapping(value = "/on_server_exited", produces = "application/json;charset=UTF-8")
    @Operation(summary = "服务器退出事件", description = "服务器退出时触发的事件")
    @ApiResponse(responseCode = "200", description = "处理成功",
            content = @Content(schema = @Schema(implementation = HookResult.class)))
    public HookResult onServerExited(
            @Parameter(description = "钩子参数") @RequestBody HookParam param, HttpServletRequest request) {
        return handleAsyncHookEventWithRequest("onServerExited", param, request, zlmHookService::onServerExited);
    }

    /**
     * 发送rtp(startSendRtp)被动关闭时回调
     */
    @ResponseBody
    @PostMapping(value = "/on_send_rtp_stopped", produces = "application/json;charset=UTF-8")
    @Operation(summary = "RTP发送停止事件", description = "发送RTP(startSendRtp)被动关闭时的回调事件")
    @ApiResponse(responseCode = "200", description = "处理成功",
            content = @Content(schema = @Schema(implementation = HookResult.class)))
    public HookResult onSendRtpStopped(
            @Parameter(description = "RTP发送停止参数") @RequestBody OnSendRtpStoppedHookParam param, HttpServletRequest request) {
        return handleAsyncHookEventWithRequest("onSendRtpStopped", param, request, zlmHookService::onSendRtpStopped);
    }

    /**
     * rtpServer收流超时
     * 调用openRtpServer 接口，rtp server 长时间未收到数据,执行此web hook,对回复不敏感
     */
    @ResponseBody
    @PostMapping(value = "/on_rtp_server_timeout", produces = "application/json;charset=UTF-8")
    @Operation(summary = "RTP服务器超时事件", description = "RTP服务器长时间未收到数据时触发，对回复不敏感")
    @ApiResponse(responseCode = "200", description = "处理成功",
            content = @Content(schema = @Schema(implementation = HookResult.class)))
    public HookResult onRtpServerTimeout(
            @Parameter(description = "RTP服务器超时参数") @RequestBody OnRtpServerTimeoutHookParam param, HttpServletRequest request) {
        return handleAsyncHookEventWithRequest("onRtpServerTimeout", param, request, zlmHookService::onRtpServerTimeout);
    }

    /**
     * 访问http文件服务器上hls之外的文件时触发。结果会被缓存Cookie
     *
     * @param param
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/on_http_access", produces = "application/json;charset=UTF-8")
    @Operation(summary = "HTTP访问事件", description = "访问HTTP文件服务器上HLS之外的文件时触发，结果会被缓存")
    @ApiResponse(responseCode = "200", description = "访问成功",
            content = @Content(schema = @Schema(implementation = HookResultForOnHttpAccess.class)))
    public HookResultForOnHttpAccess onHttpAccess(
            @Parameter(description = "HTTP访问参数") @RequestBody OnHttpAccessParam param, HttpServletRequest request) {
        return handleSyncHookEventWithRequest("onHttpAccess", param, request, zlmHookService::onHttpAccess);
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
    @Operation(summary = "RTSP域鉴权事件", description = "判断RTSP流是否开启专用鉴权方式，开启后会触发on_rtsp_auth事件")
    @ApiResponse(responseCode = "200", description = "鉴权成功",
            content = @Content(schema = @Schema(implementation = HookResultForOnRtspRealm.class)))
    public HookResultForOnRtspRealm onRtspRealm(
            @Parameter(description = "RTSP域鉴权参数") @RequestBody OnRtspRealmHookParam param, HttpServletRequest request) {
        return handleSyncHookEventWithRequest("onRtspRealm", param, request, zlmHookService::onRtspRealm);
    }

    /**
     * rtsp专用的鉴权事件，先触发on_rtsp_realm事件然后才会触发on_rtsp_auth事件。
     *
     * @param param
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/on_rtsp_auth", produces = "application/json;charset=UTF-8")
    @Operation(summary = "RTSP专用鉴权事件", description = "RTSP专用鉴权，先触发on_rtsp_realm事件后才会触发此事件")
    @ApiResponse(responseCode = "200", description = "鉴权成功",
            content = @Content(schema = @Schema(implementation = HookResultForOnRtspAuth.class)))
    public HookResultForOnRtspAuth onRtspAuth(
            @Parameter(description = "RTSP鉴权参数") @RequestBody OnRtspAuthHookParam param, HttpServletRequest request) {
        return handleSyncHookEventWithRequest("onRtspAuth", param, request, zlmHookService::onRtspAuth);
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
    @Operation(summary = "流量统计事件", description = "播放器或推流器断开时且流量超过阈值时触发，对回复不敏感")
    @ApiResponse(responseCode = "200", description = "处理成功",
            content = @Content(schema = @Schema(implementation = HookResult.class)))
    public HookResult onFlowReport(
            @Parameter(description = "流量统计参数") @RequestBody OnFlowReportHookParam param, HttpServletRequest request) {
        return handleAsyncHookEventWithRequest("onFlowReport", param, request, zlmHookService::onFlowReport);
    }

    @ResponseBody
    @PostMapping(value = "/on_record_mp4", produces = "application/json;charset=UTF-8")
    @Operation(summary = "MP4录制事件", description = "MP4录制完成时触发的事件")
    @ApiResponse(responseCode = "200", description = "处理成功",
            content = @Content(schema = @Schema(implementation = HookResult.class)))
    public HookResult onRecordMp4(
            @Parameter(description = "MP4录制参数") @RequestBody OnRecordMp4HookParam param, HttpServletRequest request) {
        return handleAsyncHookEventWithRequest("onRecordMp4", param, request, zlmHookService::onRecordMp4);
    }
}
