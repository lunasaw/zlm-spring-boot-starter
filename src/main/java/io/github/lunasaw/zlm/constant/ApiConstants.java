package io.github.lunasaw.zlm.constant;

/**
 * @author weidian
 * @version 1.0
 * @date 2023/12/2
 * @description:
 */
public class ApiConstants {

    /*API基础路径*/
    public static final String API_INDEX = "/index/api";

    /*获取服务器api列表*/
    public static final String GET_API_LIST = API_INDEX + "/getApiList";

    /*获取网络线程负载*/
    public static final String GET_THREADS_LOAD = API_INDEX + "/getThreadsLoad";

    /*获取主要对象个数*/
    public static final String GET_STATISTIC = API_INDEX + "/getStatistic";

    /*获取后台线程负载*/
    public static final String GET_WORK_THREADS_LOAD = API_INDEX + "/getWorkThreadsLoad";

    /*获取服务器配置*/
    public static final String GET_SERVER_CONFIG = API_INDEX + "/getServerConfig";

    /*设置服务器配置*/
    public static final String SET_SERVER_CONFIG = API_INDEX + "/setServerConfig";

    /*重启服务器*/
    public static final String RESTART_SERVER = API_INDEX + "/restartServer";

    /*获取流列表*/
    public static final String GET_MEDIA_LIST = API_INDEX + "/getMediaList";

    /*关断单个流*/
    public static final String CLOSE_STREAM = API_INDEX + "/close_stream";

    /*批量关断流*/
    public static final String CLOSE_STREAMS = API_INDEX + "/close_streams";

    /*获取Session列表*/
    public static final String GET_ALL_SESSION = API_INDEX + "/getAllSession";

    /*断开tcp连接*/
    public static final String KICK_SESSION = API_INDEX + "/kick_session";

    /*批量断开tcp连接*/
    public static final String KICK_SESSIONS = API_INDEX + "/kick_sessions";

    /*添加rtsp/rtmp/hls拉流代理*/
    public static final String ADD_STREAM_PROXY = API_INDEX + "/addStreamProxy";

    /*关闭拉流代理*/
    public static final String DEL_STREAM_PROXY = API_INDEX + "/delStreamProxy";

    /*添加rtsp/rtmp推流*/
    public static final String ADD_STREAM_PUSHER_PROXY = API_INDEX + "/addStreamPusherProxy";

    /*关闭推流*/
    public static final String DEL_STREAM_PUSHER_PROXY = API_INDEX + "/delStreamPusherProxy";

    /*添加FFmpeg拉流代理*/
    public static final String ADD_FFMPEG_SOURCE = API_INDEX + "/addFFmpegSource";

    /*关闭FFmpeg拉流代理*/
    public static final String DEL_FFMPEG_SOURCE = API_INDEX + "/delFFmpegSource";

    /*流是否在线*/
    public static final String IS_MEDIA_ONLINE = API_INDEX + "/isMediaOnline";

    /*获取媒体流播放器列表*/
    public static final String GET_MEDIA_PLAYER_LIST = API_INDEX + "/getMediaPlayerList";

    /*广播webrtc datachannel消息*/
    public static final String BROADCAST_MESSAGE = API_INDEX + "/broadcastMessage";

    /*获取流信息*/
    public static final String GET_MEDIA_INFO = API_INDEX + "/getMediaInfo";

    /*获取流信息*/
    public static final String GET_MP4_RECORD_FILE = API_INDEX + "/getMp4RecordFile";

    /*删除录像文件夹*/
    public static final String DELETE_RECORD_DIRECTORY = API_INDEX + "/deleteRecordDirectory";

    /*开始录制*/
    public static final String START_RECORD = API_INDEX + "/startRecord";

    /*设置录像速度*/
    public static final String SET_RECORD_SPEED = API_INDEX + "/setRecordSpeed";

    /*设置录像流播放位置*/
    public static final String SEEK_RECORD_STAMP = API_INDEX + "/seekRecordStamp";

    /*停止录制*/
    public static final String STOP_RECORD = API_INDEX + "/stopRecord";

    /*是否正在录制*/
    public static final String IS_RECORDING = API_INDEX + "/isRecording";

    /*获取截图*/
    public static final String GET_SNAP = API_INDEX + "/getSnap";

    /*获取版本信息*/
    public static final String GET_VERSION = API_INDEX + "/version";

    /*查询文件概览*/
    public static final String GET_MP4_RECORD_SUMMARY = API_INDEX + "/getMp4RecordSummary";

    /*获取rtp推流信息*/
    public static final String GET_RTP_INFO = API_INDEX + "/getRtpInfo";

    /*创建RTP服务器*/
    public static final String OPEN_RTP_SERVER = API_INDEX + "/openRtpServer";

    /*创建多路复用RTP服务器*/
    public static final String OPEN_RTP_SERVER_MULTIPLEX = API_INDEX + "/openRtpServerMultiplex";

    /*连接RTP服务器*/
    public static final String CONNECT_RTP_SERVER = API_INDEX + "/connectRtpServer";

    /*关闭RTP服务器*/
    public static final String CLOSE_RTP_SERVER = API_INDEX + "/closeRtpServer";

    /*更新RTP服务器过滤SSRC*/
    public static final String UPDATE_RTP_SERVER_SSRC = API_INDEX + "/updateRtpServerSSRC";

    /*暂停RTP超时检查*/
    public static final String PAUSE_RTP_CHECK = API_INDEX + "/pauseRtpCheck";

    /*恢复RTP超时检查*/
    public static final String RESUME_RTP_CHECK = API_INDEX + "/resumeRtpCheck";

    /*获取RTP服务器列表*/
    public static final String LIST_RTP_SERVER = API_INDEX + "/listRtpServer";

    /*开始发送rtp*/
    public static final String START_SEND_RTP = API_INDEX + "/startSendRtp";

    /*开始tcp passive被动发送rtp*/
    public static final String START_SEND_RTP_PASSIVE = API_INDEX + "/startSendRtpPassive";

    /*停止 发送rtp*/
    public static final String STOP_SEND_RTP = API_INDEX + "/stopSendRtp";

    /*获取版本信息*/
    public static final String GET_VERSION = API_INDEX + "/version";

    /*获取拉流代理信息*/
    public static final String GET_PROXY_INFO = API_INDEX + "/getProxyInfo";

    /*获取推流代理信息*/
    public static final String GET_PROXY_PUSHER_INFO = API_INDEX + "/getProxyPusherInfo";

    /*多文件推流*/
    public static final String START_MULTI_MP4_PUBLISH = API_INDEX + "/startMultiMp4Publish";

    /*获取存储信息*/
    public static final String GET_STORAGE_SPACE = API_INDEX + "/getStorageSpace";

    /*关闭多文件推流*/
    public static final String STOP_MULTI_MP4_PUBLISH = API_INDEX + "/stopMultiMp4Publish";

    /*点播mp4文件*/
    public static final String LOAD_MP4_FILE = API_INDEX + "/loadMP4File";

}
