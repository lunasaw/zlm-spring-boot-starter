package io.github.lunasaw.zlm.api.controller;

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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
public class ZlmApiController {

    @Autowired
    private ZlmProperties zlmProperties;

    @Autowired
    private LoadBalancer loadBalancer;

    /**
     * 获取可用的 ZLM 节点
     */
    private ZlmNode getAvailableNode() {
        return loadBalancer.selectNode("default");
    }

    // ==================== 系统信息接口 ====================

    /**
     * 获取版本信息
     */
    @GetMapping("/version")
    public ServerResponse<Version> getVersion() {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.getVersion(node.getHost(), node.getSecret());
    }

    /**
     * 获取API列表
     */
    @GetMapping("/api/list")
    public ServerResponse<List<String>> getApiList(@RequestParam(required = false) Map<String, String> params) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.getApiList(node.getHost(), node.getSecret(), params);
    }

    /**
     * 获取网络线程负载
     */
    @GetMapping("/threads/load")
    public ServerResponse<List<ThreadLoad>> getThreadsLoad() {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.getThreadsLoad(node.getHost(), node.getSecret());
    }

    /**
     * 获取主要对象个数
     */
    @GetMapping("/statistic")
    public ServerResponse<ImportantObjectNum> getStatistic() {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.getStatistic(node.getHost(), node.getSecret());
    }

    /**
     * 获取后台线程负载
     */
    @GetMapping("/work-threads/load")
    public ServerResponse<List<ThreadLoad>> getWorkThreadsLoad() {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.getWorkThreadsLoad(node.getHost(), node.getSecret());
    }

    /**
     * 获取服务器配置
     */
    @GetMapping("/server/config")
    public ServerResponse<ServerNodeConfig> getServerConfig() {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.getServerConfig(node.getHost(), node.getSecret());
    }

    /**
     * 设置服务器配置
     */
    @PostMapping("/server/config")
    public ServerResponse<String> setServerConfig(@RequestBody Map<String, String> params) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.setServerConfig(node.getHost(), node.getSecret(), params);
    }

    /**
     * 重启服务器
     */
    @PostMapping("/server/restart")
    public ServerResponse<Object> restartServer(@RequestBody Map<String, String> params) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.restartServer(node.getHost(), node.getSecret(), params);
    }

    // ==================== 媒体流管理接口 ====================

    /**
     * 获取流列表
     */
    @PostMapping("/media/list")
    public ServerResponse<List<MediaData>> getMediaList(@RequestBody MediaReq mediaReq) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.getMediaList(node.getHost(), node.getSecret(), mediaReq);
    }

    /**
     * 关断单个流
     */
    @PostMapping("/media/close")
    public ServerResponse<String> closeStream(@RequestBody MediaReq mediaReq) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.closeStream(node.getHost(), node.getSecret(), mediaReq);
    }

    /**
     * 批量关断流
     */
    @PostMapping("/media/close-batch")
    public ServerResponse closeStreams(@RequestBody CloseStreamsReq closeStreamsReq) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.closeStreams(node.getHost(), node.getSecret(), closeStreamsReq.toMap());
    }

    /**
     * 流是否在线
     */
    @PostMapping("/media/online")
    public MediaOnlineStatus isMediaOnline(@RequestBody MediaReq mediaReq) {
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
    public ServerResponse<List<TcpLink>> getAllSession(@RequestParam(required = false) String localPort,
                                                      @RequestParam(required = false) String peerIp) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.getAllSession(node.getHost(), node.getSecret(), localPort, peerIp);
    }

    /**
     * 断开tcp连接
     */
    @DeleteMapping("/session/{sessionId}")
    public ServerResponse<String> kickSession(@PathVariable String sessionId) {
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
    public ServerResponse<StreamKey> addStreamProxy(@RequestBody StreamProxyItem streamProxyItem) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.addStreamProxy(node.getHost(), node.getSecret(), streamProxyItem);
    }

    /**
     * 关闭拉流代理
     */
    @DeleteMapping("/proxy/{key}")
    public ServerResponse<StreamKey.StringDelFlag> delStreamProxy(@PathVariable String key) {
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
    public ServerResponse<StreamKey.StringDelFlag> delStreamPusherProxy(@PathVariable String key) {
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
    public ServerResponse<StreamKey.StringDelFlag> delFFmpegSource(@PathVariable String key) {
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
    public ServerResponse<String> startRecord(@RequestBody RecordReq recordReq) {
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
    public ServerResponse<String> stopRecord(@RequestBody RecordReq recordReq) {
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
    public ServerResponse getMp4RecordSummary(@RequestBody Map<String, String> params) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.getMp4RecordSummary(node.getHost(), node.getSecret(), params);
    }

    // ==================== 截图接口 ====================

    /**
     * 获取截图
     */
    @PostMapping("/snapshot")
    public String getSnap(@RequestBody SnapshotReq snapshotReq) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.getSnap(node.getHost(), node.getSecret(), snapshotReq);
    }

    // ==================== RTP服务器管理接口 ====================

    /**
     * 获取rtp推流信息
     */
    @GetMapping("/rtp/info/{streamId}")
    public RtpInfoResult getRtpInfo(@PathVariable String streamId) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.getRtpInfo(node.getHost(), node.getSecret(), streamId);
    }

    /**
     * 创建RTP服务器
     */
    @PostMapping("/rtp/server/open")
    public OpenRtpServerResult openRtpServer(@RequestBody OpenRtpServerReq req) {
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
    public CloseRtpServerResult closeRtpServer(@PathVariable String streamId) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.closeRtpServer(node.getHost(), node.getSecret(), streamId);
    }

    /**
     * 更新RTP服务器过滤SSRC
     */
    @PutMapping("/rtp/server/{streamId}/ssrc/{ssrc}")
    public ServerResponse<String> updateRtpServerSSRC(@PathVariable String streamId, @PathVariable String ssrc) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.updateRtpServerSSRC(node.getHost(), node.getSecret(), streamId, ssrc);
    }

    /**
     * 暂停RTP超时检查
     */
    @PostMapping("/rtp/server/{streamId}/pause-check")
    public ServerResponse<String> pauseRtpCheck(@PathVariable String streamId) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.pauseRtpCheck(node.getHost(), node.getSecret(), streamId);
    }

    /**
     * 恢复RTP超时检查
     */
    @PostMapping("/rtp/server/{streamId}/resume-check")
    public ServerResponse<String> resumeRtpCheck(@PathVariable String streamId) {
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
    public ServerResponse getStorageSpace(@RequestBody Map<String, String> params) {
        ZlmNode node = getAvailableNode();
        return ZlmRestService.getStorageSpace(node.getHost(), node.getSecret(), params);
    }

    // ==================== 指定节点操作接口 ====================

    /**
     * 指定节点获取版本信息
     */
    @GetMapping("/node/{nodeId}/version")
    public ServerResponse<Version> getVersionByNode(@PathVariable String nodeId) {
        ZlmNode node = zlmProperties.getNodeMap().get(nodeId);
        if (node == null) {
            throw new IllegalArgumentException("节点不存在: " + nodeId);
        }
        return ZlmRestService.getVersion(node.getHost(), node.getSecret());
    }

    /**
     * 指定节点获取流列表
     */
    @PostMapping("/node/{nodeId}/media/list")
    public ServerResponse<List<MediaData>> getMediaListByNode(@PathVariable String nodeId, @RequestBody MediaReq mediaReq) {
        ZlmNode node = zlmProperties.getNodeMap().get(nodeId);
        if (node == null) {
            throw new IllegalArgumentException("节点不存在: " + nodeId);
        }
        return ZlmRestService.getMediaList(node.getHost(), node.getSecret(), mediaReq);
    }

    /**
     * 获取所有节点列表
     */
    @GetMapping("/nodes")
    public List<ZlmNode> getAllNodes() {
        return zlmProperties.getNodes();
    }
}