package io.github.lunasaw.zlm.api;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.google.common.collect.Maps;
import com.luna.common.check.Assert;
import com.luna.common.file.FileTools;
import com.luna.common.net.HttpUtils;
import io.github.lunasaw.zlm.constant.ApiConstants;
import io.github.lunasaw.zlm.entity.*;
import io.github.lunasaw.zlm.entity.req.MediaReq;
import io.github.lunasaw.zlm.entity.req.RecordReq;
import io.github.lunasaw.zlm.entity.req.SnapshotReq;
import io.github.lunasaw.zlm.entity.rtp.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.hc.core5.http.ClassicHttpResponse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author luna
 * @version 1.0
 * @date 2023/12/2
 * @description: zlm rest api
 */
public class ZlmRestService {

    /**
     * 通用API调用处理方法
     *
     * @param host         服务器地址
     * @param secret       密钥
     * @param apiPath      API路径
     * @param params       请求参数
     * @param typeRef      返回类型引用
     * @param <T>          返回类型
     * @return API响应结果
     */
    private static <T> T executeApiCall(String host, String secret, String apiPath, Map<String, String> params, TypeReference<T> typeRef) {
        String response = doApi(host, secret, apiPath, params);
        return JSON.parseObject(response, typeRef);
    }

    /**
     * 通用API调用处理方法 - 支持请求对象
     *
     * @param host         服务器地址
     * @param secret       密钥
     * @param apiPath      API路径
     * @param requestObj   请求对象（需要有toMap方法）
     * @param typeRef      返回类型引用
     * @param mapExtractor 提取Map的函数
     * @param <T>          返回类型
     * @param <R>          请求对象类型
     * @return API响应结果
     */
    private static <T, R> T executeApiCall(String host, String secret, String apiPath, R requestObj, TypeReference<T> typeRef, Function<R, Map<String, String>> mapExtractor) {
        Map<String, String> params = mapExtractor.apply(requestObj);
        return executeApiCall(host, secret, apiPath, params, typeRef);
    }

    /**
     * 简化的单参数API调用
     *
     * @param host      服务器地址
     * @param secret    密钥
     * @param apiPath   API路径
     * @param paramKey  参数key
     * @param paramValue 参数值
     * @param typeRef   返回类型引用
     * @param <T>       返回类型
     * @return API响应结果
     */
    private static <T> T executeApiCallWithSingleParam(String host, String secret, String apiPath, String paramKey, String paramValue, TypeReference<T> typeRef) {
        Map<String, String> params = new HashMap<>();
        params.put(paramKey, paramValue);
        return executeApiCall(host, secret, apiPath, params, typeRef);
    }

    /**
     * 获取版本信息
     */
    public static ServerResponse<Version> getVersion(String host, String secret) {
        return executeApiCall(host, secret, ApiConstants.GET_VERSION, new HashMap<>(), new TypeReference<ServerResponse<Version>>() {});
    }

    public static ServerResponse<List<String>> getApiList(String host, String secret) {
        return getApiList(host, secret, new HashMap<>());
    }

    /**
     * 获取流媒体服务器列表
     */
    public static ServerResponse<List<String>> getApiList(String host, String secret, Map<String, String> params) {
        return executeApiCall(host, secret, ApiConstants.GET_API_LIST, params, new TypeReference<ServerResponse<List<String>>>() {});
    }

    /**
     * 获取网络线程负载
     */
    public static ServerResponse<List<ThreadLoad>> getThreadsLoad(String host, String secret) {
        return executeApiCall(host, secret, ApiConstants.GET_THREADS_LOAD, new HashMap<>(), new TypeReference<ServerResponse<List<ThreadLoad>>>() {});
    }

    /**
     * 获取主要对象个数
     */
    public static ServerResponse<ImportantObjectNum> getStatistic(String host, String secret) {
        return executeApiCall(host, secret, ApiConstants.GET_STATISTIC, new HashMap<>(), new TypeReference<ServerResponse<ImportantObjectNum>>() {});
    }

    /**
     * 获取后台线程负载
     */
    public static ServerResponse<List<ThreadLoad>> getWorkThreadsLoad(String host, String secret) {
        return executeApiCall(host, secret, ApiConstants.GET_WORK_THREADS_LOAD, new HashMap<>(), new TypeReference<>() {
        });
    }

    /**
     * 获取服务器配置
     */
    public static ServerResponse<List<ServerNodeConfig>> getServerConfig(String host, String secret) {
        return executeApiCall(host, secret, ApiConstants.GET_SERVER_CONFIG, new HashMap<>(), new TypeReference<>() {
        });
    }

    /**
     * 设置服务器配置
     */
    public static ServerResponse<String> setServerConfig(String host, String secret, Map<String, String> params) {
        return executeApiCall(host, secret, ApiConstants.SET_SERVER_CONFIG, params, new TypeReference<>() {
        });
    }

    /**
     * 重启服务器
     * 重启服务器,只有Daemon方式才能重启，否则是直接关闭！
     */
    public static ServerResponse<Object> restartServer(String host, String secret, Map<String, String> params) {
        return executeApiCall(host, secret, ApiConstants.RESTART_SERVER, params, new TypeReference<ServerResponse<Object>>() {});
    }

    public static ServerResponse<List<MediaData>> getMediaList(String host, String secret, MediaReq mediaReq) {
        return executeApiCall(host, secret, ApiConstants.GET_MEDIA_LIST, mediaReq, new TypeReference<ServerResponse<List<MediaData>>>() {}, MediaReq::toMap);
    }

    /**
     * 获取流列表
     */
    public static ServerResponse<List<MediaData>> getMediaList(String host, String secret, Map<String, String> params) {
        return executeApiCall(host, secret, ApiConstants.GET_MEDIA_LIST, params, new TypeReference<ServerResponse<List<MediaData>>>() {});
    }

    public static ServerResponse<String> closeStream(String host, String secret, MediaReq mediaReq) {
        return executeApiCall(host, secret, ApiConstants.CLOSE_STREAM, mediaReq, new TypeReference<ServerResponse<String>>() {}, MediaReq::toMap);
    }

    /**
     * 关断单个流
     */
    public static ServerResponse<String> closeStream(String host, String secret, Map<String, String> params) {
        return executeApiCall(host, secret, ApiConstants.CLOSE_STREAM, params, new TypeReference<ServerResponse<String>>() {});
    }

    /**
     * 批量关断流
     */
    public static ServerResponse closeStreams(String host, String secret, Map<String, String> params) {
        return executeApiCall(host, secret, ApiConstants.CLOSE_STREAMS, params, new TypeReference<ServerResponse>() {});
    }

    /**
     * 获取所有TcpSession列表(获取所有tcp客户端相关信息)
     *
     * @param host
     * @param secret
     * @param localPort 筛选本机端口，例如筛选rtsp链接：554
     * @param peerIp    筛选客户端ip
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
        return executeApiCall(host, secret, ApiConstants.GET_ALL_SESSION, params, new TypeReference<ServerResponse<List<TcpLink>>>() {});
    }

    /**
     * 断开tcp连接
     */
    public static ServerResponse<String> kickSession(String host, String secret, String sessionId) {
        return executeApiCallWithSingleParam(host, secret, ApiConstants.KICK_SESSION, "id", sessionId, new TypeReference<ServerResponse<String>>() {});
    }

    /**
     * 批量断开tcp连接
     */
    public static ServerResponse<String> kickSessions(String host, String secret, Map<String, String> params) {
        return executeApiCall(host, secret, ApiConstants.KICK_SESSIONS, params, new TypeReference<ServerResponse<String>>() {});
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
        return executeApiCall(host, secret, ApiConstants.ADD_STREAM_PROXY, streamProxyItem, new TypeReference<ServerResponse<StreamKey>>() {}, StreamProxyItem::toMap);
    }

    /**
     * 添加rtsp/rtmp/hls拉流代理
     */
    public static ServerResponse<StreamKey> addStreamProxy(String host, String secret, Map<String, String> params) {
        return executeApiCall(host, secret, ApiConstants.ADD_STREAM_PROXY, params, new TypeReference<ServerResponse<StreamKey>>() {});
    }

    /**
     * 关闭拉流代理
     */
    public static ServerResponse<StreamKey.StringDelFlag> delStreamProxy(String host, String secret, String key) {
        return executeApiCallWithSingleParam(host, secret, ApiConstants.DEL_STREAM_PROXY, "key", key, new TypeReference<ServerResponse<StreamKey.StringDelFlag>>() {});
    }

    public static ServerResponse<StreamKey> addStreamPusherProxy(String host, String secret, StreamPusherItem streamPusherItem) {
        return executeApiCall(host, secret, ApiConstants.ADD_STREAM_PUSHER_PROXY, streamPusherItem, new TypeReference<ServerResponse<StreamKey>>() {}, StreamPusherItem::toMap);
    }

    /**
     * 添加rtsp/rtmp推流
     */
    public static ServerResponse<StreamKey> addStreamPusherProxy(String host, String secret, Map<String, String> params) {
        return executeApiCall(host, secret, ApiConstants.ADD_STREAM_PUSHER_PROXY, params, new TypeReference<ServerResponse<StreamKey>>() {});
    }

    /**
     * 关闭推流
     */
    public static ServerResponse<StreamKey.StringDelFlag> delStreamPusherProxy(String host, String secret, String key) {
        return executeApiCallWithSingleParam(host, secret, ApiConstants.DEL_STREAM_PUSHER_PROXY, "key", key, new TypeReference<ServerResponse<StreamKey.StringDelFlag>>() {});
    }

    public static ServerResponse<StreamKey> addFFmpegSource(String host, String secret, StreamFfmpegItem streamFfmpegItem) {
        return executeApiCall(host, secret, ApiConstants.ADD_FFMPEG_SOURCE, streamFfmpegItem, new TypeReference<ServerResponse<StreamKey>>() {}, StreamFfmpegItem::toMap);
    }

    /**
     * 添加FFmpeg拉流代理
     */
    public static ServerResponse<StreamKey> addFFmpegSource(String host, String secret, Map<String, String> params) {
        return executeApiCall(host, secret, ApiConstants.ADD_FFMPEG_SOURCE, params, new TypeReference<ServerResponse<StreamKey>>() {});
    }

    /**
     * 关闭FFmpeg拉流代理
     */
    public static ServerResponse<StreamKey.StringDelFlag> delFFmpegSource(String host, String secret, String key) {
        return executeApiCallWithSingleParam(host, secret, ApiConstants.DEL_FFMPEG_SOURCE, "key", key, new TypeReference<ServerResponse<StreamKey.StringDelFlag>>() {});
    }

    public static MediaOnlineStatus isMediaOnline(String host, String secret, MediaReq mediaReq) {
        return executeApiCall(host, secret, ApiConstants.IS_MEDIA_ONLINE, mediaReq, new TypeReference<MediaOnlineStatus>() {}, MediaReq::toMap);
    }

    /**
     * 流是否在线
     */
    public static MediaOnlineStatus isMediaOnline(String host, String secret, Map<String, String> params) {
        return executeApiCall(host, secret, ApiConstants.IS_MEDIA_ONLINE, params, new TypeReference<MediaOnlineStatus>() {});
    }

    public static ServerResponse<MediaPlayer> getMediaPlayerList(String host, String secret, MediaReq mediaReq) {
        return executeApiCall(host, secret, ApiConstants.GET_MEDIA_PLAYER_LIST, mediaReq, new TypeReference<ServerResponse<MediaPlayer>>() {}, MediaReq::toMap);
    }

    /**
     * 获取媒体流播放器列表
     */
    public static ServerResponse<MediaPlayer> getMediaPlayerList(String host, String secret, Map<String, String> params) {
        return executeApiCall(host, secret, ApiConstants.GET_MEDIA_PLAYER_LIST, params, new TypeReference<ServerResponse<MediaPlayer>>() {});
    }

    /**
     * 广播webrtc datachannel消息
     */
    public static ServerResponse<String> broadcastMessage(String host, String secret, Map<String, String> params) {
        return executeApiCall(host, secret, ApiConstants.BROADCAST_MESSAGE, params, new TypeReference<ServerResponse>() {});
    }

    public static ServerResponse<MediaInfo> getMediaInfo(String host, String secret, MediaReq mediaReq) {
        MediaInfo mediaInfo = executeApiCall(host, secret, ApiConstants.GET_MEDIA_INFO, mediaReq, new TypeReference<MediaInfo>() {
        }, MediaReq::toMap);
        return ServerResponse.success(mediaInfo);
    }


    public static ServerResponse<Mp4RecordFile> getMp4RecordFile(String host, String secret, RecordReq recordReq) {
        return executeApiCall(host, secret, ApiConstants.GET_MP4_RECORD_FILE, recordReq, new TypeReference<ServerResponse<Mp4RecordFile>>() {}, RecordReq::toMap);
    }

    /**
     * 获取流信息
     */
    public static ServerResponse<Mp4RecordFile> getMp4RecordFile(String host, String secret, Map<String, String> params) {
        return executeApiCall(host, secret, ApiConstants.GET_MP4_RECORD_FILE, params, new TypeReference<ServerResponse<Mp4RecordFile>>() {});
    }

    /**
     * 删除录像文件夹
     */
    public static DeleteRecordDirectory deleteRecordDirectory(String host, String secret, Map<String, String> params) {
        return executeApiCall(host, secret, ApiConstants.DELETE_RECORD_DIRECTORY, params, new TypeReference<DeleteRecordDirectory>() {});
    }

    public static ServerResponse<String> startRecord(String host, String secret, RecordReq recordReq) {
        return executeApiCall(host, secret, ApiConstants.START_RECORD, recordReq, new TypeReference<ServerResponse<String>>() {}, RecordReq::getSaveMp4Map);
    }

    /**
     * 开始录制
     */
    public static ServerResponse<String> startRecord(String host, String secret, Map<String, String> params) {
        return executeApiCall(host, secret, ApiConstants.START_RECORD, params, new TypeReference<ServerResponse<String>>() {});
    }

    public static ServerResponse<String> setRecordSpeed(String host, String secret, RecordReq recordReq) {
        return executeApiCall(host, secret, ApiConstants.SET_RECORD_SPEED, recordReq, new TypeReference<ServerResponse<String>>() {}, RecordReq::getRecordSpeedMap);
    }

    /**
     * 设置录像速度
     */
    public static ServerResponse<String> setRecordSpeed(String host, String secret, Map<String, String> params) {
        return executeApiCall(host, secret, ApiConstants.SET_RECORD_SPEED, params, new TypeReference<ServerResponse<String>>() {});
    }

    public static ServerResponse<String> seekRecordStamp(String host, String secret, RecordReq recordReq) {
        return executeApiCall(host, secret, ApiConstants.SEEK_RECORD_STAMP, recordReq, new TypeReference<ServerResponse<String>>() {}, RecordReq::getSeekRecordStampMap);
    }

    /**
     * 设置录像流播放位置
     */
    public static ServerResponse<String> seekRecordStamp(String host, String secret, Map<String, String> params) {
        return executeApiCall(host, secret, ApiConstants.SEEK_RECORD_STAMP, params, new TypeReference<ServerResponse<String>>() {});
    }

    public static ServerResponse<String> stopRecord(String host, String secret, RecordReq recordReq) {
        return executeApiCall(host, secret, ApiConstants.STOP_RECORD, recordReq, new TypeReference<ServerResponse<String>>() {}, RecordReq::getStopRecordMap);
    }

    /**
     * 停止录制
     */
    public static ServerResponse<String> stopRecord(String host, String secret, Map<String, String> params) {
        return executeApiCall(host, secret, ApiConstants.STOP_RECORD, params, new TypeReference<ServerResponse<String>>() {});
    }

    public static ServerResponse<String> isRecording(String host, String secret, RecordReq recordReq) {
        return executeApiCall(host, secret, ApiConstants.IS_RECORDING, recordReq, new TypeReference<ServerResponse<String>>() {}, RecordReq::getIsRecordingMap);
    }

    /**
     * 是否正在录制
     */
    public static ServerResponse<String> isRecording(String host, String secret, Map<String, String> params) {
        return executeApiCall(host, secret, ApiConstants.IS_RECORDING, params, new TypeReference<ServerResponse<String>>() {});
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
    public static ServerResponse<String> getMp4RecordSummary(String host, String secret, Map<String, String> params) {
        return executeApiCall(host, secret, ApiConstants.GET_MP4_RECORD_SUMMARY, params, new TypeReference<ServerResponse<String>>() {});
    }

    /**
     * 获取rtp推流信息
     *
     * @param streamId RTP的ssrc，16进制字符串或者是流的id(openRtpServer接口指定)
     */
    public static RtpInfoResult getRtpInfo(String host, String secret, String streamId) {
        return executeApiCallWithSingleParam(host, secret, ApiConstants.GET_RTP_INFO, "stream_id", streamId, new TypeReference<RtpInfoResult>() {});
    }

    public static OpenRtpServerResult openRtpServer(String host, String secret, OpenRtpServerReq req) {
        return executeApiCall(host, secret, ApiConstants.OPEN_RTP_SERVER, req, new TypeReference<OpenRtpServerResult>() {}, OpenRtpServerReq::toMap);
    }

    /**
     * 创建RTP服务器
     */
    public static OpenRtpServerResult openRtpServer(String host, String secret, Map<String, String> params) {
        return executeApiCall(host, secret, ApiConstants.OPEN_RTP_SERVER, params, new TypeReference<OpenRtpServerResult>() {});
    }

    public static OpenRtpServerResult openRtpServerMultiplex(String host, String secret, OpenRtpServerReq req) {
        return executeApiCall(host, secret, ApiConstants.OPEN_RTP_SERVER_MULTIPLEX, req, new TypeReference<OpenRtpServerResult>() {}, OpenRtpServerReq::toMap);
    }

    /**
     * 创建多路复用RTP服务器
     */
    public static OpenRtpServerResult openRtpServerMultiplex(String host, String secret, Map<String, String> params) {
        return executeApiCall(host, secret, ApiConstants.OPEN_RTP_SERVER_MULTIPLEX, params, new TypeReference<OpenRtpServerResult>() {});
    }

    public static OpenRtpServerResult connectRtpServer(String host, String secret, ConnectRtpServerReq req) {
        return executeApiCall(host, secret, ApiConstants.CONNECT_RTP_SERVER, req, new TypeReference<OpenRtpServerResult>() {}, ConnectRtpServerReq::toMap);
    }

    /**
     * 连接RTP服务器
     */
    public static OpenRtpServerResult connectRtpServer(String host, String secret, Map<String, String> params) {
        return executeApiCall(host, secret, ApiConstants.CONNECT_RTP_SERVER, params, new TypeReference<OpenRtpServerResult>() {});
    }

    /**
     * 关闭RTP服务器
     */
    public static CloseRtpServerResult closeRtpServer(String host, String secret, String streamId) {
        return executeApiCallWithSingleParam(host, secret, ApiConstants.CLOSE_RTP_SERVER, "stream_id", streamId, new TypeReference<CloseRtpServerResult>() {});
    }

    /**
     * 更新RTP服务器过滤SSRC
     */
    public static ServerResponse<String> updateRtpServerSSRC(String host, String secret, String streamId, String ssrc) {
        Map<String, String> params = new HashMap<>();
        params.put("stream_id", streamId);
        params.put("ssrc", ssrc);
        return executeApiCall(host, secret, ApiConstants.UPDATE_RTP_SERVER_SSRC, params, new TypeReference<ServerResponse<String>>() {});
    }

    /**
     * 暂停RTP超时检查
     */
    public static ServerResponse<String> pauseRtpCheck(String host, String secret, String streamId) {
        return executeApiCallWithSingleParam(host, secret, ApiConstants.PAUSE_RTP_CHECK, "stream_id", streamId, new TypeReference<ServerResponse<String>>() {});
    }

    /**
     * 恢复RTP超时检查
     */
    public static ServerResponse<String> resumeRtpCheck(String host, String secret, String streamId) {
        return executeApiCallWithSingleParam(host, secret, ApiConstants.RESUME_RTP_CHECK, "stream_id", streamId, new TypeReference<ServerResponse<String>>() {});
    }

    /**
     * 获取RTP服务器列表
     */
    public static ServerResponse<List<RtpServer>> listRtpServer(String host, String secret) {
        return executeApiCall(host, secret, ApiConstants.LIST_RTP_SERVER, new HashMap<>(), new TypeReference<ServerResponse<List<RtpServer>>>() {});
    }

    public static StartSendRtpResult startSendRtp(String host, String secret, StartSendRtpReq req) {
        return executeApiCall(host, secret, ApiConstants.START_SEND_RTP, req, new TypeReference<StartSendRtpResult>() {}, StartSendRtpReq::toMap);
    }

    /**
     * 开始发送rtp
     */
    public static StartSendRtpResult startSendRtp(String host, String secret, Map<String, String> params) {
        return executeApiCall(host, secret, ApiConstants.START_SEND_RTP, params, new TypeReference<StartSendRtpResult>() {});
    }

    public static StartSendRtpResult startSendRtpPassive(String host, String secret, StartSendRtpReq req) {
        return executeApiCall(host, secret, ApiConstants.START_SEND_RTP_PASSIVE, req, new TypeReference<StartSendRtpResult>() {}, StartSendRtpReq::getPassiveMap);
    }

    /**
     * 开始tcp passive被动发送rtp
     */
    public static StartSendRtpResult startSendRtpPassive(String host, String secret, Map<String, String> params) {
        return executeApiCall(host, secret, ApiConstants.START_SEND_RTP_PASSIVE, params, new TypeReference<StartSendRtpResult>() {});
    }

    public static ServerResponse<String> stopSendRtp(String host, String secret, CloseSendRtpReq req) {
        return executeApiCall(host, secret, ApiConstants.STOP_SEND_RTP, req, new TypeReference<ServerResponse<String>>() {}, CloseSendRtpReq::getMap);
    }

    /**
     * 停止 发送rtp
     */
    public static ServerResponse<String> stopSendRtp(String host, String secret, Map<String, String> params) {
        return executeApiCall(host, secret, ApiConstants.STOP_SEND_RTP, params, new TypeReference<ServerResponse<String>>() {});
    }

    /**
     * 获取拉流代理信息
     */
    public static ServerResponse<String> getProxyInfo(String host, String secret, Map<String, String> params) {
        return executeApiCall(host, secret, ApiConstants.GET_PROXY_INFO, params, new TypeReference<ServerResponse<String>>() {});
    }

    /**
     * 获取推流代理信息
     */
    public static ServerResponse<String> getProxyPusherInfo(String host, String secret, Map<String, String> params) {
        return executeApiCall(host, secret, ApiConstants.GET_PROXY_PUSHER_INFO, params, new TypeReference<ServerResponse<String>>() {});
    }

    /**
     * 多文件推流
     */
    public static ServerResponse<String> startMultiMp4Publish(String host, String secret, Map<String, String> params) {
        return executeApiCall(host, secret, ApiConstants.START_MULTI_MP4_PUBLISH, params, new TypeReference<ServerResponse<String>>() {});
    }

    /**
     * 获取存储信息
     */
    public static ServerResponse<String> getStorageSpace(String host, String secret, Map<String, String> params) {
        return executeApiCall(host, secret, ApiConstants.GET_STORAGE_SPACE, params, new TypeReference<ServerResponse<String>>() {});
    }

    /**
     * 关闭多文件推流
     */
    public static ServerResponse<String> stopMultiMp4Publish(String host, String secret, Map<String, String> params) {
        return executeApiCall(host, secret, ApiConstants.STOP_MULTI_MP4_PUBLISH, params, new TypeReference<ServerResponse<String>>() {});
    }

    /**
     * 点播mp4文件
     */
    public static ServerResponse<String> loadMP4File(String host, String secret, Map<String, String> params) {
        return executeApiCall(host, secret, ApiConstants.LOAD_MP4_FILE, params, new TypeReference<ServerResponse<String>>() {});
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
