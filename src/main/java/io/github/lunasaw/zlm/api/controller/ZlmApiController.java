package io.github.lunasaw.zlm.api.controller;

import com.luna.common.check.Assert;
import io.github.lunasaw.zlm.api.ZlmRestService;
import io.github.lunasaw.zlm.config.ZlmNode;
import io.github.lunasaw.zlm.config.ZlmProperties;
import io.github.lunasaw.zlm.entity.*;
import io.github.lunasaw.zlm.entity.req.CloseStreamsReq;
import io.github.lunasaw.zlm.entity.req.MediaReq;
import io.github.lunasaw.zlm.entity.req.RecordReq;
import io.github.lunasaw.zlm.entity.req.SnapshotReq;
import io.github.lunasaw.zlm.entity.rtp.*;
import io.github.lunasaw.zlm.node.LoadBalancer;
import io.github.lunasaw.zlm.node.NodeSupplier;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

/**
 * @author luna
 * @version 1.0
 * @date 2023/12/2
 * @description: ZLM REST API 控制器
 */
@Slf4j
@RestController
@ConditionalOnProperty(value = "zlm.enable", havingValue = "true")
@RequestMapping("/zlm/api")
@Tag(name = "ZLM媒体服务器管理", description = "ZLMediaKit流媒体服务器管理相关接口")
public class ZlmApiController {

    @Autowired
    private ZlmProperties zlmProperties;

    @Autowired
    private LoadBalancer loadBalancer;

    @Autowired
    private NodeSupplier nodeSupplier;

    @Autowired
    private HttpServletRequest request;

    /**
     * 获取可用的 ZLM 节点
     * 支持通过请求头 X-Node-Key 指定节点
     */
    private ZlmNode getAvailableNode() {
        String nodeKey = request.getHeader("X-Node-Key");
        return getAvailableNode(nodeKey);
    }

    /**
     * 获取可用的 ZLM 节点
     *
     * @param nodeKey 节点key，如果为空则使用负载均衡策略选择节点
     */
    private ZlmNode getAvailableNode(String nodeKey) {
        ZlmNode selectNode;
        if (nodeKey != null && !nodeKey.trim().isEmpty()) {
            // 使用指定的节点key获取节点
            selectNode = nodeSupplier.getNode(nodeKey);
            Assert.notNull(selectNode, "指定的节点不存在: " + nodeKey);
        } else {
            // 使用负载均衡策略选择节点
            selectNode = loadBalancer.selectNode("default");
            Assert.notNull(selectNode, "未找到可用的ZLM节点");
        }
        return selectNode;
    }

    // ==================== 系统信息接口 ====================

    /**
     * 获取版本信息
     */
    @GetMapping("/version")
    @Operation(summary = "获取版本信息", description = "获取ZLMediaKit服务器的版本信息")
    @ApiResponse(responseCode = "200", description = "获取成功",
            content = @Content(schema = @Schema(implementation = ServerResponse.class)))
    public ServerResponse<Version> getVersion() {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.getVersion(node.getHost(), node.getSecret());
    }

    /**
     * 获取API列表
     */
    @GetMapping("/api/list")
    @Operation(summary = "获取API列表", description = "获取ZLMediaKit服务器支持的所有API接口列表")
    @ApiResponse(responseCode = "200", description = "获取成功",
            content = @Content(schema = @Schema(implementation = ServerResponse.class)))
    public ServerResponse<List<String>> getApiList(
            @Parameter(description = "查询参数") @RequestParam(required = false) Map<String, String> params) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.getApiList(node.getHost(), node.getSecret(), params);
    }

    /**
     * 获取网络线程负载
     */
    @GetMapping("/threads/load")
    @Operation(summary = "获取网络线程负载", description = "获取ZLMediaKit服务器网络线程的负载情况")
    @ApiResponse(responseCode = "200", description = "获取成功",
            content = @Content(schema = @Schema(implementation = ServerResponse.class)))
    public ServerResponse<List<ThreadLoad>> getThreadsLoad() {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.getThreadsLoad(node.getHost(), node.getSecret());
    }

    /**
     * 获取主要对象个数
     */
    @GetMapping("/statistic")
    @Operation(summary = "获取统计信息", description = "获取ZLMediaKit服务器主要对象的统计数量")
    @ApiResponse(responseCode = "200", description = "获取成功",
            content = @Content(schema = @Schema(implementation = ServerResponse.class)))
    public ServerResponse<ImportantObjectNum> getStatistic() {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.getStatistic(node.getHost(), node.getSecret());
    }

    /**
     * 获取后台线程负载
     */
    @GetMapping("/work-threads/load")
    @Operation(summary = "获取后台线程负载", description = "获取ZLMediaKit服务器后台工作线程的负载情况")
    @ApiResponse(responseCode = "200", description = "获取成功",
            content = @Content(schema = @Schema(implementation = ServerResponse.class)))
    public ServerResponse<List<ThreadLoad>> getWorkThreadsLoad() {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.getWorkThreadsLoad(node.getHost(), node.getSecret());
    }

    /**
     * 获取服务器配置
     */
    @GetMapping("/server/config")
    @Operation(summary = "获取服务器配置", description = "获取ZLMediaKit服务器的配置信息")
    @ApiResponse(responseCode = "200", description = "获取成功",
            content = @Content(schema = @Schema(implementation = ServerResponse.class)))
    public ServerResponse<List<ServerNodeConfig>> getServerConfig() {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.getServerConfig(node.getHost(), node.getSecret());
    }

    /**
     * 设置服务器配置
     */
    @PostMapping("/server/config")
    @Operation(summary = "设置服务器配置", description = "修改ZLMediaKit服务器的配置参数")
    @ApiResponse(responseCode = "200", description = "设置成功",
            content = @Content(schema = @Schema(implementation = ServerResponse.class)))
    public ServerResponse<String> setServerConfig(
            @Parameter(description = "配置参数") @RequestBody Map<String, String> params) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.setServerConfig(node.getHost(), node.getSecret(), params);
    }

    /**
     * 重启服务器
     */
    @PostMapping("/server/restart")
    @Operation(summary = "重启服务器", description = "重启ZLMediaKit服务器")
    @ApiResponse(responseCode = "200", description = "重启成功",
            content = @Content(schema = @Schema(implementation = ServerResponse.class)))
    public ServerResponse<Object> restartServer(
            @Parameter(description = "重启参数") @RequestBody Map<String, String> params) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.restartServer(node.getHost(), node.getSecret(), params);
    }

    // ==================== 媒体流管理接口 ====================

    /**
     * 获取流列表
     */
    @PostMapping("/media/list")
    @Operation(summary = "获取流列表", description = "获取ZLMediaKit服务器中的媒体流列表")
    @ApiResponse(responseCode = "200", description = "获取成功",
            content = @Content(schema = @Schema(implementation = ServerResponse.class)))
    public ServerResponse<List<MediaData>> getMediaList(
            @Parameter(description = "媒体查询条件") @RequestBody MediaReq mediaReq) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.getMediaList(node.getHost(), node.getSecret(), mediaReq);
    }

    /**
     * 关断单个流
     */
    @PostMapping("/media/close")
    @Operation(summary = "关断单个流", description = "关闭指定的媒体流")
    @ApiResponse(responseCode = "200", description = "关闭成功",
            content = @Content(schema = @Schema(implementation = ServerResponse.class)))
    public ServerResponse<String> closeStream(
            @Parameter(description = "媒体流信息") @RequestBody MediaReq mediaReq) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.closeStream(node.getHost(), node.getSecret(), mediaReq);
    }

    /**
     * 批量关断流
     */
    @PostMapping("/media/close-batch")
    @Operation(summary = "批量关断流", description = "批量关闭多个媒体流")
    @ApiResponse(responseCode = "200", description = "关闭成功",
            content = @Content(schema = @Schema(implementation = ServerResponse.class)))
    public ServerResponse closeStreams(
            @Parameter(description = "批量关流请求") @RequestBody CloseStreamsReq closeStreamsReq) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.closeStreams(node.getHost(), node.getSecret(), closeStreamsReq.toMap());
    }

    /**
     * 流是否在线
     */
    @PostMapping("/media/online")
    @Operation(summary = "检查流是否在线", description = "检查指定媒体流是否在线")
    @ApiResponse(responseCode = "200", description = "检查成功",
            content = @Content(schema = @Schema(implementation = MediaOnlineStatus.class)))
    public MediaOnlineStatus isMediaOnline(
            @Parameter(description = "媒体流信息") @RequestBody MediaReq mediaReq) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.isMediaOnline(node.getHost(), node.getSecret(), mediaReq);
    }

    /**
     * 获取媒体流播放器列表
     */
    @PostMapping("/media/player/list")
    public ServerResponse<MediaPlayer> getMediaPlayerList(@RequestBody MediaReq mediaReq) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.getMediaPlayerList(node.getHost(), node.getSecret(), mediaReq);
    }

    /**
     * 获取流信息
     */
    @PostMapping("/media/info")
    public ServerResponse<MediaInfo> getMediaInfo(@RequestBody MediaReq mediaReq) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.getMediaInfo(node.getHost(), node.getSecret(), mediaReq);
    }

    /**
     * 广播webrtc datachannel消息
     */
    @PostMapping("/broadcast/message")
    public ServerResponse broadcastMessage(@RequestBody Map<String, String> params) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.broadcastMessage(node.getHost(), node.getSecret(), params);
    }

    // ==================== TCP会话管理接口 ====================

    /**
     * 获取所有TcpSession列表
     */
    @GetMapping("/session/list")
    @Operation(summary = "获取TCP会话列表", description = "获取ZLMediaKit服务器中所有TCP连接会话的列表")
    @ApiResponse(responseCode = "200", description = "获取成功",
            content = @Content(schema = @Schema(implementation = ServerResponse.class)))
    public ServerResponse<List<TcpLink>> getAllSession(
            @Parameter(description = "本地端口") @RequestParam(required = false) String localPort,
            @Parameter(description = "对端IP") @RequestParam(required = false) String peerIp) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.getAllSession(node.getHost(), node.getSecret(), localPort, peerIp);
    }

    /**
     * 断开tcp连接
     */
    @DeleteMapping("/session/{sessionId}")
    @Operation(summary = "断开TCP连接", description = "根据会话ID断开指定的TCP连接")
    @ApiResponse(responseCode = "200", description = "断开成功",
            content = @Content(schema = @Schema(implementation = ServerResponse.class)))
    public ServerResponse<String> kickSession(
            @Parameter(description = "会话ID") @PathVariable("sessionId") String sessionId) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.kickSession(node.getHost(), node.getSecret(), sessionId);
    }

    /**
     * 批量断开tcp连接
     */
    @PostMapping("/session/kick-batch")
    public ServerResponse<String> kickSessions(@RequestBody Map<String, String> params) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.kickSessions(node.getHost(), node.getSecret(), params);
    }

    // ==================== 代理流管理接口 ====================

    /**
     * 添加代理拉流
     */
    @PostMapping("/proxy/add")
    @Operation(summary = "添加代理拉流", description = "添加一个拉流代理，用于从外部拉取媒体流")
    @ApiResponse(responseCode = "200", description = "添加成功",
            content = @Content(schema = @Schema(implementation = ServerResponse.class)))
    public ServerResponse<StreamKey> addStreamProxy(
            @Parameter(description = "拉流代理配置") @RequestBody StreamProxyItem streamProxyItem) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.addStreamProxy(node.getHost(), node.getSecret(), streamProxyItem);
    }

    /**
     * 关闭拉流代理
     */
    @DeleteMapping("/proxy/{key}")
    @Operation(summary = "关闭拉流代理", description = "根据代理key关闭指定的拉流代理")
    @ApiResponse(responseCode = "200", description = "关闭成功",
            content = @Content(schema = @Schema(implementation = ServerResponse.class)))
    public ServerResponse<StreamKey.StringDelFlag> delStreamProxy(
            @Parameter(description = "代理key") @PathVariable("key") String key) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.delStreamProxy(node.getHost(), node.getSecret(), key);
    }

    /**
     * 获取拉流代理信息
     */
    @PostMapping("/proxy/info")
    public ServerResponse getProxyInfo(@RequestBody Map<String, String> params) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.getProxyInfo(node.getHost(), node.getSecret(), params);
    }

    /**
     * 添加推流代理
     */
    @PostMapping("/pusher/add")
    public ServerResponse<StreamKey> addStreamPusherProxy(@RequestBody StreamPusherItem streamPusherItem) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.addStreamPusherProxy(node.getHost(), node.getSecret(), streamPusherItem);
    }

    /**
     * 关闭推流代理
     */
    @DeleteMapping("/pusher/{key}")
    public ServerResponse<StreamKey.StringDelFlag> delStreamPusherProxy(@PathVariable("key") String key) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.delStreamPusherProxy(node.getHost(), node.getSecret(), key);
    }

    /**
     * 获取推流代理信息
     */
    @PostMapping("/pusher/info")
    public ServerResponse getProxyPusherInfo(@RequestBody Map<String, String> params) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.getProxyPusherInfo(node.getHost(), node.getSecret(), params);
    }

    // ==================== FFmpeg管理接口 ====================

    /**
     * 添加FFmpeg拉流代理
     */
    @PostMapping("/ffmpeg/add")
    public ServerResponse<StreamKey> addFFmpegSource(@RequestBody StreamFfmpegItem streamFfmpegItem) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.addFFmpegSource(node.getHost(), node.getSecret(), streamFfmpegItem);
    }

    /**
     * 关闭FFmpeg拉流代理
     */
    @DeleteMapping("/ffmpeg/{key}")
    public ServerResponse<StreamKey.StringDelFlag> delFFmpegSource(@PathVariable("key") String key) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.delFFmpegSource(node.getHost(), node.getSecret(), key);
    }

    // ==================== 录制管理接口 ====================

    /**
     * 获取录制文件列表
     */
    @PostMapping("/record/files")
    public ServerResponse<Mp4RecordFile> getMp4RecordFile(@RequestBody RecordReq recordReq) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.getMp4RecordFile(node.getHost(), node.getSecret(), recordReq);
    }

    /**
     * 删除录像文件夹
     */
    @PostMapping("/record/delete-directory")
    public DeleteRecordDirectory deleteRecordDirectory(@RequestBody Map<String, String> params) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.deleteRecordDirectory(node.getHost(), node.getSecret(), params);
    }

    /**
     * 开始录制
     */
    @PostMapping("/record/start")
    @Operation(summary = "开始录制", description = "开始录制指定的媒体流")
    @ApiResponse(responseCode = "200", description = "录制开始成功",
            content = @Content(schema = @Schema(implementation = ServerResponse.class)))
    public ServerResponse<String> startRecord(
            @Parameter(description = "录制配置") @RequestBody RecordReq recordReq) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.startRecord(node.getHost(), node.getSecret(), recordReq);
    }

    /**
     * 设置录像速度
     */
    @PostMapping("/record/speed")
    public ServerResponse<String> setRecordSpeed(@RequestBody RecordReq recordReq) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.setRecordSpeed(node.getHost(), node.getSecret(), recordReq);
    }

    /**
     * 设置录像流播放位置
     */
    @PostMapping("/record/seek")
    public ServerResponse<String> seekRecordStamp(@RequestBody RecordReq recordReq) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.seekRecordStamp(node.getHost(), node.getSecret(), recordReq);
    }

    /**
     * 停止录制
     */
    @PostMapping("/record/stop")
    @Operation(summary = "停止录制", description = "停止录制指定的媒体流")
    @ApiResponse(responseCode = "200", description = "录制停止成功",
            content = @Content(schema = @Schema(implementation = ServerResponse.class)))
    public ServerResponse<String> stopRecord(
            @Parameter(description = "录制配置") @RequestBody RecordReq recordReq) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.stopRecord(node.getHost(), node.getSecret(), recordReq);
    }

    /**
     * 是否正在录制
     */
    @PostMapping("/record/status")
    public ServerResponse<String> isRecording(@RequestBody RecordReq recordReq) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.isRecording(node.getHost(), node.getSecret(), recordReq);
    }

    /**
     * 查询文件概览
     */
    @PostMapping("/record/summary")
    public ServerResponse<String> getMp4RecordSummary(@RequestBody Map<String, String> params) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.getMp4RecordSummary(node.getHost(), node.getSecret(), params);
    }

    // ==================== 截图接口 ====================

    /**
     * 获取截图
     */
    @PostMapping("/snapshot")
    @Operation(summary = "获取截图", description = "获取指定媒体流的截图")
    @ApiResponse(responseCode = "200", description = "截图获取成功",
            content = @Content(schema = @Schema(implementation = String.class)))
    public String getSnap(
            @Parameter(description = "截图配置") @RequestBody SnapshotReq snapshotReq) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.getSnap(node.getHost(), node.getSecret(), snapshotReq);
    }

    // ==================== RTP服务器管理接口 ====================

    /**
     * 获取rtp推流信息
     */
    @GetMapping("/rtp/info/{streamId}")
    @Operation(summary = "获取RTP推流信息", description = "根据流ID获取RTP推流的详细信息")
    @ApiResponse(responseCode = "200", description = "获取成功",
            content = @Content(schema = @Schema(implementation = RtpInfoResult.class)))
    public RtpInfoResult getRtpInfo(
            @Parameter(description = "流ID") @PathVariable("streamId") String streamId) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.getRtpInfo(node.getHost(), node.getSecret(), streamId);
    }

    /**
     * 创建RTP服务器
     */
    @PostMapping("/rtp/server/open")
    @Operation(summary = "创建RTP服务器", description = "创建一个RTP服务器用于接收RTP推流")
    @ApiResponse(responseCode = "200", description = "创建成功",
            content = @Content(schema = @Schema(implementation = OpenRtpServerResult.class)))
    public OpenRtpServerResult openRtpServer(
            @Parameter(description = "RTP服务器配置") @RequestBody OpenRtpServerReq req) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.openRtpServer(node.getHost(), node.getSecret(), req);
    }

    /**
     * 创建多路复用RTP服务器
     */
    @PostMapping("/rtp/server/open-multiplex")
    public OpenRtpServerResult openRtpServerMultiplex(@RequestBody OpenRtpServerReq req) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.openRtpServerMultiplex(node.getHost(), node.getSecret(), req);
    }

    /**
     * 连接RTP服务器
     */
    @PostMapping("/rtp/server/connect")
    public OpenRtpServerResult connectRtpServer(@RequestBody ConnectRtpServerReq req) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.connectRtpServer(node.getHost(), node.getSecret(), req);
    }

    /**
     * 关闭RTP服务器
     */
    @DeleteMapping("/rtp/server/{streamId}")
    @Operation(summary = "关闭RTP服务器", description = "根据流ID关闭指定的RTP服务器")
    @ApiResponse(responseCode = "200", description = "关闭成功",
            content = @Content(schema = @Schema(implementation = CloseRtpServerResult.class)))
    public CloseRtpServerResult closeRtpServer(
            @Parameter(description = "流ID") @PathVariable("streamId") String streamId) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.closeRtpServer(node.getHost(), node.getSecret(), streamId);
    }

    /**
     * 更新RTP服务器过滤SSRC
     */
    @PutMapping("/rtp/server/{streamId}/ssrc/{ssrc}")
    public ServerResponse<String> updateRtpServerSSRC(@PathVariable("streamId") String streamId, @PathVariable("ssrc") String ssrc) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.updateRtpServerSSRC(node.getHost(), node.getSecret(), streamId, ssrc);
    }

    /**
     * 暂停RTP超时检查
     */
    @PostMapping("/rtp/server/{streamId}/pause-check")
    public ServerResponse<String> pauseRtpCheck(@PathVariable("streamId") String streamId) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.pauseRtpCheck(node.getHost(), node.getSecret(), streamId);
    }

    /**
     * 恢复RTP超时检查
     */
    @PostMapping("/rtp/server/{streamId}/resume-check")
    public ServerResponse<String> resumeRtpCheck(@PathVariable("streamId") String streamId) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.resumeRtpCheck(node.getHost(), node.getSecret(), streamId);
    }

    /**
     * 获取RTP服务器列表
     */
    @GetMapping("/rtp/server/list")
    public ServerResponse<List<RtpServer>> listRtpServer() {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.listRtpServer(node.getHost(), node.getSecret());
    }

    // ==================== RTP发送管理接口 ====================

    /**
     * 开始发送rtp
     */
    @PostMapping("/rtp/send/start")
    public StartSendRtpResult startSendRtp(@RequestBody StartSendRtpReq req) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.startSendRtp(node.getHost(), node.getSecret(), req);
    }

    /**
     * 开始tcp passive被动发送rtp
     */
    @PostMapping("/rtp/send/start-passive")
    public StartSendRtpResult startSendRtpPassive(@RequestBody StartSendRtpReq req) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.startSendRtpPassive(node.getHost(), node.getSecret(), req);
    }

    /**
     * 停止发送rtp
     */
    @PostMapping("/rtp/send/stop")
    public ServerResponse<String> stopSendRtp(@RequestBody CloseSendRtpReq req) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.stopSendRtp(node.getHost(), node.getSecret(), req);
    }

    // ==================== MP4文件管理接口 ====================

    /**
     * 多文件推流
     */
    @PostMapping("/mp4/publish/start")
    public ServerResponse startMultiMp4Publish(@RequestBody Map<String, String> params) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.startMultiMp4Publish(node.getHost(), node.getSecret(), params);
    }

    /**
     * 关闭多文件推流
     */
    @PostMapping("/mp4/publish/stop")
    public ServerResponse stopMultiMp4Publish(@RequestBody Map<String, String> params) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.stopMultiMp4Publish(node.getHost(), node.getSecret(), params);
    }

    /**
     * 点播mp4文件
     */
    @PostMapping("/mp4/load")
    public ServerResponse loadMP4File(@RequestBody Map<String, String> params) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.loadMP4File(node.getHost(), node.getSecret(), params);
    }

    // ==================== 存储管理接口 ====================

    /**
     * 获取存储信息
     */
    @PostMapping("/storage/space")
    public ServerResponse<String> getStorageSpace(@RequestBody Map<String, String> params) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.getStorageSpace(node.getHost(), node.getSecret(), params);
    }

    // ==================== 指定节点操作接口 ====================

    /**
     * 指定节点获取版本信息
     */
    @GetMapping("/node/{nodeId}/version")
    @Operation(summary = "指定节点获取版本信息", description = "获取指定ZLM节点的版本信息")
    @ApiResponse(responseCode = "200", description = "获取成功",
            content = @Content(schema = @Schema(implementation = ServerResponse.class)))
    public ServerResponse<Version> getVersionByNode(
            @Parameter(description = "节点ID") @PathVariable("nodeId") String nodeId) {
        ZlmNode node = nodeSupplier.getNode(nodeId);
        if (node == null) {
            throw new IllegalArgumentException("节点不存在: " + nodeId);
        }
        return ZlmRestService.getVersion(node.getHost(), node.getSecret());
    }

    /**
     * 指定节点获取流列表
     */
    @PostMapping("/node/{nodeId}/media/list")
    @Operation(summary = "指定节点获取流列表", description = "获取指定ZLM节点中的媒体流列表")
    @ApiResponse(responseCode = "200", description = "获取成功",
            content = @Content(schema = @Schema(implementation = ServerResponse.class)))
    public ServerResponse<List<MediaData>> getMediaListByNode(
            @Parameter(description = "节点ID") @PathVariable(value = "nodeId") String nodeId,
            @Parameter(description = "媒体查询条件") @RequestBody MediaReq mediaReq) {
        ZlmNode node = nodeSupplier.getNode(nodeId);
        if (node == null) {
            throw new IllegalArgumentException("节点不存在: " + nodeId);
        }
        return ZlmRestService.getMediaList(node.getHost(), node.getSecret(), mediaReq);
    }

    /**
     * 获取所有节点列表
     */
    @GetMapping("/nodes")
    @Operation(summary = "获取所有节点列表", description = "获取当前配置的所有ZLM节点信息")
    @ApiResponse(responseCode = "200", description = "获取成功",
            content = @Content(schema = @Schema(implementation = List.class)))
    public List<ZlmNode> getAllNodes() {
        return zlmProperties.getNodes();
    }

    // ==================== 异常处理 ====================

    /**
     * 处理节点不存在异常
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ServerResponse<String> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("参数错误: {}", e.getMessage());
        ServerResponse<String> response = new ServerResponse<>();
        response.setCode(0);
        response.setMsg(e.getMessage());
        return response;
    }
}