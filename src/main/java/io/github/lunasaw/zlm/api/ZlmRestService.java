package io.github.lunasaw.zlm.api;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.luna.common.check.Assert;
import com.luna.common.net.HttpUtils;
import io.github.lunasaw.zlm.config.ZlmProperties;
import io.github.lunasaw.zlm.constant.ApiConstants;
import io.github.lunasaw.zlm.entity.*;
import io.github.lunasaw.zlm.entity.req.MediaReq;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author luna
 * @version 1.0
 * @date 2023/12/2
 * @description: zlm rest api
 */
@Service
public class ZlmRestService {

    private static final String URL = "http://127.0.0.1:9092";
    private static final String SECRET = "zlm";
    @Autowired
    private ZlmProperties zlmProperties;

    /**
     * 获取版本信息
     */
    public static ServerResponse<Version> version(String host, String secret) {
        String s = doApi(host, secret, ApiConstants.VERSION, new HashMap<>());
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
     * 获取Session列表
     */
    public static ServerResponse getAllSession(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.GET_ALL_SESSION, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse>() {
        });
    }

    /**
     * 断开tcp连接
     */
    public static ServerResponse kickSession(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.KICK_SESSION, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse>() {
        });
    }

    /**
     * 批量断开tcp连接
     */
    public static ServerResponse kickSessions(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.KICK_SESSIONS, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse>() {
        });
    }

    /**
     * 添加rtsp/rtmp/hls拉流代理
     */
    public static ServerResponse addStreamProxy(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.ADD_STREAM_PROXY, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse>() {
        });
    }

    /**
     * 关闭拉流代理
     */
    public static ServerResponse delStreamProxy(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.DEL_STREAM_PROXY, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse>() {
        });
    }

    /**
     * 添加rtsp/rtmp推流
     */
    public static ServerResponse addStreamPusherProxy(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.ADD_STREAM_PUSHER_PROXY, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse>() {
        });
    }

    /**
     * 关闭推流
     */
    public static ServerResponse delStreamPusherProxy(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.DEL_STREAM_PUSHER_PROXY, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse>() {
        });
    }

    /**
     * 添加FFmpeg拉流代理
     */
    public static ServerResponse addFFmpegSource(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.ADD_FFMPEG_SOURCE, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse>() {
        });
    }

    /**
     * 关闭FFmpeg拉流代理
     */
    public static ServerResponse delFFmpegSource(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.DEL_FFMPEG_SOURCE, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse>() {
        });
    }

    /**
     * 流是否在线
     */
    public static ServerResponse isMediaOnline(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.IS_MEDIA_ONLINE, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse>() {
        });
    }

    /**
     * 获取媒体流播放器列表
     */
    public static ServerResponse getMediaPlayerList(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.GET_MEDIA_PLAYER_LIST, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse>() {
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

    /**
     * 获取流信息
     */
    public static ServerResponse getMediaInfo(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.GET_MEDIA_INFO, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse>() {
        });
    }

    /**
     * 获取流信息
     */
    public static ServerResponse getMp4RecordFile(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.GET_MP4_RECORD_FILE, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse>() {
        });
    }

    /**
     * 删除录像文件夹
     */
    public static ServerResponse deleteRecordDirectory(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.DELETE_RECORD_DIRECTORY, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse>() {
        });
    }

    /**
     * 开始录制
     */
    public static ServerResponse startRecord(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.START_RECORD, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse>() {
        });
    }

    /**
     * 设置录像速度
     */
    public static ServerResponse setRecordSpeed(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.SET_RECORD_SPEED, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse>() {
        });
    }

    /**
     * 设置录像流播放位置
     */
    public static ServerResponse seekRecordStamp(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.SEEK_RECORD_STAMP, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse>() {
        });
    }

    /**
     * 停止录制
     */
    public static ServerResponse stopRecord(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.STOP_RECORD, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse>() {
        });
    }

    /**
     * 是否正在录制
     */
    public static ServerResponse isRecording(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.IS_RECORDING, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse>() {
        });
    }

    /**
     * 获取截图
     */
    public static ServerResponse getSnap(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.GET_SNAP, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse>() {
        });
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
     */
    public static ServerResponse getRtpInfo(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.GET_RTP_INFO, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse>() {
        });
    }

    /**
     * 创建RTP服务器
     */
    public static ServerResponse openRtpServer(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.OPEN_RTP_SERVER, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse>() {
        });
    }

    /**
     * 创建多路复用RTP服务器
     */
    public static ServerResponse openRtpServerMultiplex(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.OPEN_RTP_SERVER_MULTIPLEX, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse>() {
        });
    }

    /**
     * 连接RTP服务器
     */
    public static ServerResponse connectRtpServer(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.CONNECT_RTP_SERVER, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse>() {
        });
    }

    /**
     * 关闭RTP服务器
     */
    public static ServerResponse closeRtpServer(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.CLOSE_RTP_SERVER, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse>() {
        });
    }

    /**
     * 更新RTP服务器过滤SSRC
     */
    public static ServerResponse updateRtpServerSSRC(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.UPDATE_RTP_SERVER_SSRC, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse>() {
        });
    }

    /**
     * 暂停RTP超时检查
     */
    public static ServerResponse pauseRtpCheck(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.PAUSE_RTP_CHECK, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse>() {
        });
    }

    /**
     * 恢复RTP超时检查
     */
    public static ServerResponse resumeRtpCheck(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.RESUME_RTP_CHECK, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse>() {
        });
    }

    /**
     * 获取RTP服务器列表
     */
    public static ServerResponse listRtpServer(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.LIST_RTP_SERVER, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse>() {
        });
    }

    /**
     * 开始发送rtp
     */
    public static ServerResponse startSendRtp(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.START_SEND_RTP, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse>() {
        });
    }

    /**
     * 开始tcp passive被动发送rtp
     */
    public static ServerResponse startSendRtpPassive(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.START_SEND_RTP_PASSIVE, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse>() {
        });
    }

    /**
     * 停止 发送rtp
     */
    public static ServerResponse stopSendRtp(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.STOP_SEND_RTP, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse>() {
        });
    }

    /**
     * 获取版本信息
     */
    public static ServerResponse getVersion(String host, String secret, Map<String, String> params) {
        String s = doApi(host, secret, ApiConstants.GET_VERSION, params);
        return JSON.parseObject(s, new TypeReference<ServerResponse>() {
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


    public static void main(String[] args) {
//        ServerResponse zlm = getServerConfig(URL, SECRET);
//        System.out.println(zlm);
        ServerResponse<List<String>> version = getApiList(URL, SECRET, new HashMap<>());
        System.out.println(version);
    }

    public static String doApi(String host, String secret, String path, Map<String, String> params) {
        Assert.notNull(host, "host is null");
        Assert.notNull(path, "api is null");
        Assert.notNull(secret, "secret is null");

        params = Optional.ofNullable(params).orElse(new HashMap<>());
        params.put("secret", secret);
        return HttpUtils.doPostHander(host, path, new HashMap<>(), params, StringUtils.EMPTY);
    }

}
