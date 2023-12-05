package io.github.lunasaw.zlm.api;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.google.common.collect.Maps;
import com.luna.common.check.Assert;
import com.luna.common.file.FileTools;
import com.luna.common.net.HttpUtils;

import io.github.lunasaw.zlm.config.ZlmProperties;
import io.github.lunasaw.zlm.constant.ApiConstants;
import io.github.lunasaw.zlm.entity.*;
import io.github.lunasaw.zlm.entity.req.MediaReq;
import io.github.lunasaw.zlm.entity.req.RecordReq;
import io.github.lunasaw.zlm.entity.req.SnapshotReq;
import io.github.lunasaw.zlm.entity.rtp.*;

/**
 * @author luna
 * @version 1.0
 * @date 2023/12/2
 * @description: zlm rest api
 */
public class ZlmRestService {

    private static final String URL = "http://127.0.0.1:9092";
    private static final String SECRET = "zlm";
    /**
     * 获取版本信息
     */
    public static ServerResponse<Version> getVersion(String host, String secret) {
        String s = doApi(host, secret, ApiConstants.GET_VERSION, new HashMap<>());
        return JSON.parseObject(s, new TypeReference<ServerResponse<Version>>() {
        });
    }

    /**
     * 获取流媒体服务器列表
     */
    public static ServerResponse<List<String>> getApiList(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.GET_API_LIST, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse<List<String>>>() {
        });
    }

    /**
     * 获取网络线程负载
     */
    public static ServerResponse<List<ThreadLoad>> getThreadsLoad(String host, String secret) {
        String s = doApi(host, secret, ApiConstants.GET_THREADS_LOAD, new HashMap<>());
        return JSON.parseObject(s, new TypeReference<ServerResponse<List<ThreadLoad>>>() {
        });
    }

    /**
     * 获取主要对象个数
     */
    public static ServerResponse<ImportantObjectNum> getStatistic(String host, String secret) {
        String s = doApi(host, secret, ApiConstants.GET_STATISTIC, new HashMap<>());
        return JSON.parseObject(s, new TypeReference<ServerResponse<ImportantObjectNum>>() {
        });
    }

    /**
     * 获取后台线程负载
     */
    public static ServerResponse<List<ThreadLoad>> getWorkThreadsLoad(String host, String secret) {
        String s = doApi(host, secret, ApiConstants.GET_WORK_THREADS_LOAD, new HashMap<>());
        return JSON.parseObject(s, new TypeReference<ServerResponse<List<ThreadLoad>>>() {
        });
    }

    /**
     * 获取服务器配置
     */
    public static ServerResponse<ServerNodeConfig> getServerConfig(String host, String secret) {
        String s = doApi(host, secret, ApiConstants.GET_SERVER_CONFIG, new HashMap<>());
        return JSON.parseObject(s, new TypeReference<ServerResponse<ServerNodeConfig>>() {
        });
    }

    /**
     * 设置服务器配置
     */
    public static ServerResponse<String> setServerConfig(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.SET_SERVER_CONFIG, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse<String>>() {
        });
    }

    /**
     * 重启服务器
     * 重启服务器,只有Daemon方式才能重启，否则是直接关闭！
     */
    public static ServerResponse<Object> restartServer(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.RESTART_SERVER, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse<Object>>() {
        });
    }

    public static ServerResponse<List<MediaData>> getMediaList(String host, String secret, MediaReq mediaReq) {
        return getMediaList(host, secret, mediaReq.toMap());
    }

    /**
     * 获取流列表
     */
    public static ServerResponse<List<MediaData>> getMediaList(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.GET_MEDIA_LIST, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse<List<MediaData>>>() {
        });
    }

    public static ServerResponse<String> closeStream(String host, String secret, MediaReq mediaReq) {
        return closeStream(host, secret, mediaReq.toMap());
    }

    /**
     * 关断单个流
     */
    public static ServerResponse<String> closeStream(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.CLOSE_STREAM, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse<String>>() {
        });
    }

    /**
     * 批量关断流
     */
    public static ServerResponse closeStreams(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.CLOSE_STREAMS, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse>() {
        });
    }

    /**
     * 获取所有TcpSession列表(获取所有tcp客户端相关信息)
     *
     * @param host
     * @param secret
     * @param localPort 筛选本机端口，例如筛选rtsp链接：554
     * @param peerIp 筛选客户端ip
     * @return
     */
    public static ServerResponse<List<TcpLink>> getAllSession(String host, String secret, String localPort, String peerIp) {
        Map<String, String> params = new HashMap<>();
        params.put("local_port", localPort);
        params.put("peer_ip", peerIp);
        return getAllSession(host, secret, params);
    }

    /**
     * 获取Session列表
     */
    public static ServerResponse<List<TcpLink>> getAllSession(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.GET_ALL_SESSION, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse<List<TcpLink>>>() {
        });
    }

    /**
     * 断开tcp连接
     */
    public static ServerResponse<String> kickSession(String host, String secret, String sessionId) {
        Map<String, String> params = new HashMap<>();
        params.put("id", sessionId);
        String s = doApi(host, secret, ApiConstants.KICK_SESSION, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse<String>>() {
        });
    }

    /**
     * 批量断开tcp连接
     */
    public static ServerResponse<String> kickSessions(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.KICK_SESSIONS, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse<String>>() {
        });
    }

    /**
     * 添加代理拉流
     *
     * @param host
     * @param secret
     * @param streamProxyItem
     * @return
     */
    public static ServerResponse<StreamKey> addStreamProxy(String host, String secret, StreamProxyItem streamProxyItem) {
        return addStreamProxy(host, secret, streamProxyItem.toMap());
    }

    /**
     * 添加rtsp/rtmp/hls拉流代理
     */
    public static ServerResponse<StreamKey> addStreamProxy(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.ADD_STREAM_PROXY, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse<StreamKey>>() {
        });
    }

    /**
     * 关闭拉流代理
     */
    public static ServerResponse<StreamKey.StringDelFlag> delStreamProxy(String host, String secret, String key) {
        Map<String, String> params = Maps.newHashMap();
        params.put("key", key);
        String s = doApi(host, secret, ApiConstants.DEL_STREAM_PROXY, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse<StreamKey.StringDelFlag>>() {
        });
    }

    public static ServerResponse<StreamKey> addStreamPusherProxy(String host, String secret, StreamPusherItem streamPusherItem) {
        return addStreamPusherProxy(host, secret, streamPusherItem.toMap());
    }

    /**
     * 添加rtsp/rtmp推流
     */
    public static ServerResponse<StreamKey> addStreamPusherProxy(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.ADD_STREAM_PUSHER_PROXY, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse<StreamKey>>() {
        });
    }

    /**
     * 关闭推流
     */
    public static ServerResponse<StreamKey.StringDelFlag> delStreamPusherProxy(String host, String secret, String key) {
        Map<String, String> params = Maps.newHashMap();
        params.put("key", key);
        String s = doApi(host, secret, ApiConstants.DEL_STREAM_PUSHER_PROXY, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse<StreamKey.StringDelFlag>>() {
        });
    }

    public static ServerResponse<StreamKey> addFFmpegSource(String host, String secret, StreamFfmpegItem streamFfmpegItem) {
        return addFFmpegSource(host, secret, streamFfmpegItem.toMap());
    }

    /**
     * 添加FFmpeg拉流代理
     */
    public static ServerResponse<StreamKey> addFFmpegSource(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.ADD_FFMPEG_SOURCE, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse<StreamKey>>() {
        });
    }

    /**
     * 关闭FFmpeg拉流代理
     */
    public static ServerResponse<StreamKey.StringDelFlag> delFFmpegSource(String host, String secret, String key) {
        Map<String, String> params = Maps.newHashMap();
        params.put("key", key);
        String s = doApi(host, secret, ApiConstants.DEL_FFMPEG_SOURCE, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse<StreamKey.StringDelFlag>>() {
        });
    }

    public static MediaOnlineStatus isMediaOnline(String host, String secret, MediaReq mediaReq) {
        return isMediaOnline(host, secret, mediaReq.toMap());
    }

    /**
     * 流是否在线
     */
    public static MediaOnlineStatus isMediaOnline(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.IS_MEDIA_ONLINE, params);
        return JSON.parseObject(s, new TypeReference<MediaOnlineStatus>() {
        });
    }

    public static ServerResponse<MediaPlayer> getMediaPlayerList(String host, String secret, MediaReq mediaReq) {
        return getMediaPlayerList(host, secret, mediaReq.toMap());
    }

    /**
     * 获取媒体流播放器列表
     */
    public static ServerResponse<MediaPlayer> getMediaPlayerList(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.GET_MEDIA_PLAYER_LIST, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse<MediaPlayer>>() {
        });
    }

    /**
     * 广播webrtc datachannel消息
     */
    public static ServerResponse broadcastMessage(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.BROADCAST_MESSAGE, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse>() {
        });
    }

    public static ServerResponse<MediaInfo> getMediaInfo(String host, String secret, MediaReq mediaReq) {
        return getMediaInfo(host, secret, mediaReq.toMap());
    }

    /**
     * 获取流信息
     */
    public static ServerResponse<MediaInfo> getMediaInfo(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.GET_MEDIA_INFO, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse<MediaInfo>>() {
        });
    }

    public static ServerResponse<Mp4RecordFile> getMp4RecordFile(String host, String secret, RecordReq recordReq) {
        return getMp4RecordFile(host, secret, recordReq.toMap());
    }

    /**
     * 获取流信息
     */
    public static ServerResponse<Mp4RecordFile> getMp4RecordFile(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.GET_MP4_RECORD_FILE, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse<Mp4RecordFile>>() {
        });
    }

    /**
     * 删除录像文件夹
     */
    public static DeleteRecordDirectory deleteRecordDirectory(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.DELETE_RECORD_DIRECTORY, params);
        return JSON.parseObject(s, new TypeReference<DeleteRecordDirectory>() {
        });
    }

    public static ServerResponse<String> startRecord(String host, String secret, RecordReq recordReq) {
        return startRecord(host, secret, recordReq.getSaveMp4Map());
    }

    /**
     * 开始录制
     */
    public static ServerResponse<String> startRecord(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.START_RECORD, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse<String>>() {
        });
    }

    public static ServerResponse<String> setRecordSpeed(String host, String secret, RecordReq recordReq) {
        return setRecordSpeed(host, secret, recordReq.getRecordSpeedMap());
    }

    /**
     * 设置录像速度
     */
    public static ServerResponse<String> setRecordSpeed(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.SET_RECORD_SPEED, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse<String>>() {
        });
    }

    public static ServerResponse<String> seekRecordStamp(String host, String secret, RecordReq recordReq) {
        return seekRecordStamp(host, secret, recordReq.getSeekRecordStampMap());
    }

    /**
     * 设置录像流播放位置
     */
    public static ServerResponse<String> seekRecordStamp(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.SEEK_RECORD_STAMP, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse<String>>() {
        });
    }

    public static ServerResponse<String> stopRecord(String host, String secret, RecordReq recordReq) {
        return stopRecord(host, secret, recordReq.getStopRecordMap());
    }

    /**
     * 停止录制
     */
    public static ServerResponse<String> stopRecord(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.STOP_RECORD, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse<String>>() {
        });
    }

    public static ServerResponse<String> isRecording(String host, String secret, RecordReq recordReq) {
        return isRecording(host, secret, recordReq.getIsRecordingMap());
    }

    /**
     * 是否正在录制
     */
    public static ServerResponse<String> isRecording(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.IS_RECORDING, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse<String>>() {
        });
    }

    public static String getSnap(String host, String secret, SnapshotReq snapshotReq) {
        return getSnap(host, secret, snapshotReq.toMap(), snapshotReq.getSavePath());
    }

    /**
     * 获取截图
     */
    public static String getSnap(String host, String secret, Map<String, String> params, String file) {
        Path path = Paths.get(file);
        boolean exists = Files.exists(path);
        if (!exists) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                return null;
            }
        }
        return doApiImg(host, secret, ApiConstants.GET_SNAP, params, path.toFile());
    }

    /**
     * 查询文件概览
     */
    public static ServerResponse getMp4RecordSummary(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.GET_MP4_RECORD_SUMMARY, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse>() {
        });
    }

    /**
     * 获取rtp推流信息
     * 
     * @param streamId RTP的ssrc，16进制字符串或者是流的id(openRtpServer接口指定)
     */
    public static RtpInfoResult getRtpInfo(String host, String secret, String streamId) {
        Map<String, String> params = new HashMap<>();
        params.put("stream_id", streamId);
        String s = doApi(host, secret, ApiConstants.GET_RTP_INFO, params);
        return JSON.parseObject(s, new TypeReference<RtpInfoResult>() {
        });
    }

    public static OpenRtpServerResult openRtpServer(String host, String secret, OpenRtpServerReq req) {
        return openRtpServer(host, secret, req.toMap());
    }

    /**
     * 创建RTP服务器
     */
    public static OpenRtpServerResult openRtpServer(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.OPEN_RTP_SERVER, params);
        return JSON.parseObject(s, new TypeReference<OpenRtpServerResult>() {
        });
    }

    public static OpenRtpServerResult openRtpServerMultiplex(String host, String secret, OpenRtpServerReq req) {
        return openRtpServerMultiplex(host, secret, req.toMap());
    }

    /**
     * 创建多路复用RTP服务器
     */
    public static OpenRtpServerResult openRtpServerMultiplex(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.OPEN_RTP_SERVER_MULTIPLEX, params);
        return JSON.parseObject(s, new TypeReference<OpenRtpServerResult>() {
        });
    }

    public static OpenRtpServerResult connectRtpServer(String host, String secret, ConnectRtpServerReq req) {
        return connectRtpServer(host, secret, req.toMap());
    }

    /**
     * 连接RTP服务器
     */
    public static OpenRtpServerResult connectRtpServer(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.CONNECT_RTP_SERVER, params);
        return JSON.parseObject(s, new TypeReference<OpenRtpServerResult>() {
        });
    }

    /**
     * 关闭RTP服务器
     */
    public static CloseRtpServerResult closeRtpServer(String host, String secret, String streamId) {
        Map<String, String> params = new HashMap<>();
        params.put("stream_id", streamId);
        String s = doApi(host, secret, ApiConstants.CLOSE_RTP_SERVER, params);
        return JSON.parseObject(s, new TypeReference<CloseRtpServerResult>() {
        });
    }

    /**
     * 更新RTP服务器过滤SSRC
     */
    public static ServerResponse<String> updateRtpServerSSRC(String host, String secret, String streamId, String ssrc) {
        Map<String, String> params = new HashMap<>();
        params.put("stream_id", streamId);
        params.put("ssrc", ssrc);
        String s = doApi(host, secret, ApiConstants.UPDATE_RTP_SERVER_SSRC, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse<String>>() {
        });
    }

    /**
     * 暂停RTP超时检查
     */
    public static ServerResponse<String> pauseRtpCheck(String host, String secret, String streamId) {
        Map<String, String> params = new HashMap<>();
        params.put("stream_id", streamId);
        String s = doApi(host, secret, ApiConstants.PAUSE_RTP_CHECK, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse<String>>() {
        });
    }

    /**
     * 恢复RTP超时检查
     */
    public static ServerResponse<String> resumeRtpCheck(String host, String secret, String streamId) {
        Map<String, String> params = new HashMap<>();
        params.put("stream_id", streamId);
        String s = doApi(host, secret, ApiConstants.RESUME_RTP_CHECK, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse<String>>() {
        });
    }

    /**
     * 获取RTP服务器列表
     */
    public static ServerResponse<List<RtpServer>> listRtpServer(String host, String secret) {
        Map<String, String> params = new HashMap<>();
        String s = doApi(host, secret, ApiConstants.LIST_RTP_SERVER, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse<List<RtpServer>>>() {
        });
    }

    public static StartSendRtpResult startSendRtp(String host, String secret, StartSendRtpReq req) {
        return startSendRtp(host, secret, req.toMap());
    }

    /**
     * 开始发送rtp
     */
    public static StartSendRtpResult startSendRtp(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.START_SEND_RTP, params);
        return JSON.parseObject(s, new TypeReference<StartSendRtpResult>() {
        });
    }

    public static StartSendRtpResult startSendRtpPassive(String host, String secret, StartSendRtpReq req) {
        return startSendRtpPassive(host, secret, req.getPassiveMap());
    }

    /**
     * 开始tcp passive被动发送rtp
     */
    public static StartSendRtpResult startSendRtpPassive(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.START_SEND_RTP_PASSIVE, params);
        return JSON.parseObject(s, new TypeReference<StartSendRtpResult>() {
        });
    }

    public static ServerResponse<String> stopSendRtp(String host, String secret, CloseSendRtpReq req) {
        return stopSendRtp(host, secret, req.getMap());
    }

    /**
     * 停止 发送rtp
     */
    public static ServerResponse<String> stopSendRtp(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.STOP_SEND_RTP, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse<String>>() {
        });
    }

    /**
     * 获取拉流代理信息
     */
    public static ServerResponse getProxyInfo(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.GET_PROXY_INFO, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse>() {
        });
    }

    /**
     * 获取推流代理信息
     */
    public static ServerResponse getProxyPusherInfo(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.GET_PROXY_PUSHER_INFO, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse>() {
        });
    }

    /**
     * 多文件推流
     */
    public static ServerResponse startMultiMp4Publish(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.START_MULTI_MP4_PUBLISH, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse>() {
        });
    }

    /**
     * 获取存储信息
     */
    public static ServerResponse getStorageSpace(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.GET_STORAGE_SPACE, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse>() {
        });
    }

    /**
     * 关闭多文件推流
     */
    public static ServerResponse stopMultiMp4Publish(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.STOP_MULTI_MP4_PUBLISH, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse>() {
        });
    }

    /**
     * 点播mp4文件
     */
    public static ServerResponse loadMP4File(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.LOAD_MP4_FILE, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse>() {
        });
    }

    public static String doApi(String host, String secret, String path, Map<String, String> params) {
        Assert.notNull(host, "host is null");
        Assert.notNull(path, "api is null");
        Assert.notNull(secret, "secret is null");

        params = Optional.ofNullable(params).orElse(new HashMap<>());
        params.put("secret", secret);
        return HttpUtils.doPostHander(host, path, new HashMap<>(), params, StringUtils.EMPTY);
    }

    public static String doApiImg(String host, String secret, String path, Map<String, String> params, File file) {
        Assert.notNull(host, "host is null");
        Assert.notNull(path, "api is null");
        Assert.notNull(secret, "secret is null");

        params = Optional.ofNullable(params).orElse(new HashMap<>());
        params.put("secret", secret);
        ClassicHttpResponse classicHttpResponse = HttpUtils.doPost(host, path, new HashMap<>(), params, StringUtils.EMPTY);
        if (classicHttpResponse == null) {
            return null;
        }
        byte[] bytes = HttpUtils.checkResponseStreamAndGetResult(classicHttpResponse);
        if (bytes == null) {
            return null;
        }
        FileTools.write(bytes, file.getAbsolutePath());
        return file.getAbsolutePath();
    }
}
