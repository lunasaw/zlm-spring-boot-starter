---
title: Voglander
language_tabs:
  - shell: Shell
  - http: HTTP
  - javascript: JavaScript
  - ruby: Ruby
  - python: Python
  - php: PHP
  - java: Java
  - go: Go
toc_footers: []
includes: []
search: true
code_clipboard: true
highlight_theme: darkula
headingLevel: 2
generator: "@tarslib/widdershins v4.0.30"

---

# Voglander

Base URLs:

# Authentication

# ZlmApiController

## GET 获取版本信息

GET /zlm/api/version

获取版本信息
获取版本信息
获取ZLMediaKit服务器的版本信息

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "data": {
    "buildTime": "",
    "branchName": "",
    "commitHash": ""
  },
  "msg": "",
  "result": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                                  |
|-----|---------------------------------------------------------|------|-------------------------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [ServerResponseVersion](#schemaserverresponseversion) |

## GET 获取API列表

GET /zlm/api/api/list

获取API列表
获取API列表
获取ZLMediaKit服务器支持的所有API接口列表

### 请求参数

| 名称  | 位置    | 类型  | 必选 | 说明   |
|-----|-------|-----|----|------|
| key | query | any | 否  | none |

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "data": [
    ""
  ],
  "msg": "",
  "result": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                                        |
|-----|---------------------------------------------------------|------|-------------------------------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [ServerResponseListString](#schemaserverresponseliststring) |

## GET 获取网络线程负载

GET /zlm/api/threads/load

获取网络线程负载
获取网络线程负载
获取ZLMediaKit服务器网络线程的负载情况

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "data": [
    {
      "delay": "",
      "load": ""
    }
  ],
  "msg": "",
  "result": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                                                |
|-----|---------------------------------------------------------|------|---------------------------------------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [ServerResponseListThreadLoad](#schemaserverresponselistthreadload) |

## GET 获取统计信息

GET /zlm/api/statistic

获取主要对象个数
获取统计信息
获取ZLMediaKit服务器主要对象的统计数量

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "data": {
    "Buffer": 0,
    "RtpPacket": 0,
    "Frame": 0,
    "RtmpPacket": 0,
    "TcpSession": 0,
    "UdpServer": 0,
    "TcpServer": 0,
    "FrameImp": 0,
    "BufferList": 0,
    "BufferRaw": 0,
    "MediaSource": 0,
    "MultiMediaSourceMuxer": 0,
    "TcpClient": 0,
    "BufferLikeString": 0,
    "Socket": 0,
    "UdpSession": 0
  },
  "msg": "",
  "result": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                                                        |
|-----|---------------------------------------------------------|------|-----------------------------------------------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [ServerResponseImportantObjectNum](#schemaserverresponseimportantobjectnum) |

## GET 获取后台线程负载

GET /zlm/api/work-threads/load

获取后台线程负载
获取后台线程负载
获取ZLMediaKit服务器后台工作线程的负载情况

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "data": [
    {
      "delay": "",
      "load": ""
    }
  ],
  "msg": "",
  "result": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                                                |
|-----|---------------------------------------------------------|------|---------------------------------------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [ServerResponseListThreadLoad](#schemaserverresponselistthreadload) |

## GET 获取服务器配置

GET /zlm/api/server/config

获取服务器配置
获取服务器配置
获取ZLMediaKit服务器的配置信息

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "data": {
    "rtp.rtpMaxSize": "",
    "protocol.hls_demand": "",
    "rtp_proxy.opus_pt": "",
    "rtp_proxy.timeoutSec": "",
    "rtmp.port": "",
    "hook.on_ip_not_found": "",
    "record.fileRepeat": "",
    "general.flowThreshold": "",
    "rtsp.rtpTransportType": "",
    "hook.retry_delay": "",
    "http.rootPath": "",
    "rtsp.keepAliveSecond": "",
    "hook.on_server_started": "",
    "api.defaultSnap": "",
    "cluster.origin_url": "",
    "http.port": "",
    "http.virtualPath": "",
    "http.keepAliveSecond": "",
    "ffmpeg.log": "",
    "hook.on_flow_report": "",
    "http.dirMenu": "",
    "rtsp.directProxy": "",
    "ffmpeg.cmd": "",
    "rtp.lowLatency": "",
    "protocol.enable_rtsp": "",
    "rtsp.port": "",
    "rtmp.sslport": "",
    "protocol.hls_save_path": "",
    "http.charSet": "",
    "http.sendBufSize": "",
    "hls.broadcastRecordTs": "",
    "api.apiDebug": "",
    "general.mergeWriteMS": "",
    "http.forbidCacheSuffix": "",
    "http.notFound": "",
    "hook.retry": "",
    "record.appName": "",
    "hls.fileBufSize": "",
    "hook.timeoutSec": "",
    "rtsp.sslport": "",
    "hls.deleteDelaySec": "",
    "hook.on_rtp_server_timeout": "",
    "hook.on_send_rtp_stopped": "",
    "hook.on_record_mp4": "",
    "hook.alive_interval": "",
    "rtmp.handshakeSecond": "",
    "hook.stream_changed_schemas": "",
    "rtc.externIP": "",
    "rtc.rembBitRate": "",
    "general.streamNoneReaderDelayMS": "",
    "protocol.mp4_max_second": "",
    "hook.on_publish": "",
    "rtp_proxy.port": "",
    "http.sslport": "",
    "rtp.audioMtuSize": "",
    "general.check_nvidia_dev": "",
    "record.fastStart": "",
    "hook.on_stream_not_found": "",
    "rtp_proxy.port_range": "",
    "protocol.enable_rtmp": "",
    "srt.timeoutSec": "",
    "rtsp.handshakeSecond": "",
    "hls.segDur": "",
    "protocol.mp4_as_player": "",
    "api.secret": "",
    "hls.segRetain": "",
    "protocol.rtsp_demand": "",
    "srt.port": "",
    "srt.pktBufSize": "",
    "rtp_proxy.gop_cache": "",
    "shell.maxReqSize": "",
    "ffmpeg.snap": "",
    "general.maxStreamWaitMS": "",
    "multicast.addrMax": "",
    "general.wait_add_track_ms": "",
    "http.allow_cross_domains": "",
    "protocol.modify_stamp": "",
    "rtp.videoMtuSize": "",
    "api.snapRoot": "",
    "protocol.enable_audio": "",
    "hook.on_server_keepalive": "",
    "multicast.addrMin": "",
    "protocol.ts_demand": "",
    "protocol.enable_fmp4": "",
    "rtsp.lowLatency": "",
    "http.allow_ip_range": "",
    "hook.on_rtsp_realm": "",
    "hook.on_stream_changed": "",
    "http.forwarded_ip_header": "",
    "rtp_proxy.h265_pt": "",
    "hook.on_del_mp4": "",
    "protocol.enable_hls": "",
    "protocol.enable_mp4": "",
    "rtc.port": "",
    "protocol.fmp4_demand": "",
    "record.sampleMS": "",
    "shell.port": "",
    "hook.on_shell_login": "",
    "cluster.retry_count": "",
    "general.enableVhost": "",
    "general.unready_frame_cache": "",
    "rtc.preferredCodecV": "",
    "rtp_proxy.h264_pt": "",
    "protocol.auto_close": "",
    "srt.latencyMul": "",
    "hook.on_server_exited": "",
    "general.resetWhenRePlay": "",
    "protocol.mp4_save_path": "",
    "protocol.continue_push_ms": "",
    "rtp_proxy.dumpDir": "",
    "rtp_proxy.ps_pt": "",
    "hook.enable": "",
    "rtc.timeoutSec": "",
    "rtc.preferredCodecA": "",
    "hls.segKeep": "",
    "multicast.udpTTL": "",
    "rtp.h264_stap_a": "",
    "hook.on_stream_none_reader": "",
    "hook.on_record_ts": "",
    "ffmpeg.bin": "",
    "protocol.enable_ts": "",
    "protocol.enable_hls_fmp4": "",
    "hls.segNum": "",
    "http.maxReqSize": "",
    "rtc.tcpPort": "",
    "cluster.timeout_sec": "",
    "general.enable_ffmpeg_log": "",
    "general.mediaServerId": "",
    "hook.on_http_access": "",
    "general.wait_track_ready_ms": "",
    "rtsp.authBasic": "",
    "hook.on_rtsp_auth": "",
    "protocol.rtmp_demand": "",
    "protocol.add_mute_audio": "",
    "record.fileBufSize": "",
    "rtmp.keepAliveSecond": "",
    "hook.on_play": ""
  },
  "msg": "",
  "result": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                                                            |
|-----|---------------------------------------------------------|------|---------------------------------------------------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [ServerResponseListServerNodeConfig](#schemaserverresponselistservernodeconfig) |

## POST 设置服务器配置

POST /zlm/api/server/config

设置服务器配置
设置服务器配置
修改ZLMediaKit服务器的配置参数

> Body 请求参数

```json
{
  "key": "string"
}
```

### 请求参数

| 名称   | 位置   | 类型                            | 必选 | 说明   |
|------|------|-------------------------------|----|------|
| body | body | [MapString](#schemamapstring) | 否  | none |

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "data": "",
  "msg": "",
  "result": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                                |
|-----|---------------------------------------------------------|------|-----------------------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [ServerResponseString](#schemaserverresponsestring) |

## POST 重启服务器

POST /zlm/api/server/restart

重启服务器
重启服务器
重启ZLMediaKit服务器

> Body 请求参数

```json
{
  "key": "string"
}
```

### 请求参数

| 名称   | 位置   | 类型                            | 必选 | 说明   |
|------|------|-------------------------------|----|------|
| body | body | [MapString](#schemamapstring) | 否  | none |

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "data": {},
  "msg": "",
  "result": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                                |
|-----|---------------------------------------------------------|------|-----------------------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [ServerResponseObject](#schemaserverresponseobject) |

## POST 获取流列表

POST /zlm/api/media/list

获取流列表
获取流列表
获取ZLMediaKit服务器中的媒体流列表

> Body 请求参数

```json
{
  "schema": "rtsp",
  "vhost": "__defaultVhost__",
  "app": "string",
  "stream": "string"
}
```

### 请求参数

| 名称   | 位置   | 类型                          | 必选 | 说明   |
|------|------|-----------------------------|----|------|
| body | body | [MediaReq](#schemamediareq) | 否  | none |

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "data": [
    {
      "app": "",
      "readerCount": 0,
      "totalReaderCount": 0,
      "schema": "",
      "stream": "",
      "originSock": {
        "identifier": "",
        "local_ip": "",
        "local_port": 0,
        "peer_ip": "",
        "peer_port": 0,
        "typeid": ""
      },
      "originType": 0,
      "originTypeStr": "",
      "originUrl": "",
      "createStamp": 0,
      "aliveSecond": 0,
      "bytesSpeed": 0,
      "tracks": [
        {
          "channels": 0,
          "codec_id": 0,
          "codec_id_name": "",
          "codec_type": 0,
          "fps": 0,
          "height": 0,
          "ready": false,
          "width": 0,
          "frames": 0,
          "sample_bit": 0,
          "sample_rate": 0,
          "gop_interval_ms": 0,
          "gop_size": 0,
          "key_frames": 0
        }
      ],
      "vhost": ""
    }
  ],
  "msg": "",
  "result": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                                              |
|-----|---------------------------------------------------------|------|-------------------------------------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [ServerResponseListMediaData](#schemaserverresponselistmediadata) |

## POST 关断单个流

POST /zlm/api/media/close

关断单个流
关断单个流
关闭指定的媒体流

> Body 请求参数

```json
{
  "schema": "rtsp",
  "vhost": "__defaultVhost__",
  "app": "string",
  "stream": "string"
}
```

### 请求参数

| 名称   | 位置   | 类型                          | 必选 | 说明   |
|------|------|-----------------------------|----|------|
| body | body | [MediaReq](#schemamediareq) | 否  | none |

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "data": "",
  "msg": "",
  "result": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                                |
|-----|---------------------------------------------------------|------|-----------------------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [ServerResponseString](#schemaserverresponsestring) |

## POST 批量关断流

POST /zlm/api/media/close-batch

批量关断流
批量关断流
批量关闭多个媒体流

> Body 请求参数

```json
{
  "schema": "rtsp",
  "vhost": "__defaultVhost__",
  "app": "string",
  "stream": "string",
  "force": 0
}
```

### 请求参数

| 名称   | 位置   | 类型                                        | 必选 | 说明   |
|------|------|-------------------------------------------|----|------|
| body | body | [CloseStreamsReq](#schemaclosestreamsreq) | 否  | none |

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "data": {},
  "msg": "",
  "result": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                    |
|-----|---------------------------------------------------------|------|-----------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [ServerResponse](#schemaserverresponse) |

## POST 检查流是否在线

POST /zlm/api/media/online

流是否在线
检查流是否在线
检查指定媒体流是否在线

> Body 请求参数

```json
{
  "schema": "rtsp",
  "vhost": "__defaultVhost__",
  "app": "string",
  "stream": "string"
}
```

### 请求参数

| 名称   | 位置   | 类型                          | 必选 | 说明   |
|------|------|-----------------------------|----|------|
| body | body | [MediaReq](#schemamediareq) | 否  | none |

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "data": "",
  "msg": "",
  "result": "",
  "online": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                          |
|-----|---------------------------------------------------------|------|-----------------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [MediaOnlineStatus](#schemamediaonlinestatus) |

## POST 获取媒体流播放器列表

POST /zlm/api/media/player/list

> Body 请求参数

```json
{
  "schema": "rtsp",
  "vhost": "__defaultVhost__",
  "app": "string",
  "stream": "string"
}
```

### 请求参数

| 名称   | 位置   | 类型                          | 必选 | 说明   |
|------|------|-----------------------------|----|------|
| body | body | [MediaReq](#schemamediareq) | 否  | none |

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "data": {
    "identifier": "",
    "local_ip": "",
    "local_port": 0,
    "peer_ip": "",
    "peer_port": 0,
    "typeid": ""
  },
  "msg": "",
  "result": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                                          |
|-----|---------------------------------------------------------|------|---------------------------------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [ServerResponseMediaPlayer](#schemaserverresponsemediaplayer) |

## POST 获取流信息

POST /zlm/api/media/info

> Body 请求参数

```json
{
  "schema": "rtsp",
  "vhost": "__defaultVhost__",
  "app": "string",
  "stream": "string"
}
```

### 请求参数

| 名称   | 位置   | 类型                          | 必选 | 说明   |
|------|------|-----------------------------|----|------|
| body | body | [MediaReq](#schemamediareq) | 否  | none |

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "data": {
    "code": 0,
    "online": false,
    "readerCount": 0,
    "totalReaderCount": 0,
    "tracks": [
      {
        "channels": 0,
        "codec_id": 0,
        "codec_id_name": "",
        "codec_type": 0,
        "fps": 0,
        "height": 0,
        "ready": false,
        "width": 0,
        "frames": 0,
        "sample_bit": 0,
        "sample_rate": 0,
        "gop_interval_ms": 0,
        "gop_size": 0,
        "key_frames": 0
      }
    ]
  },
  "msg": "",
  "result": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                                      |
|-----|---------------------------------------------------------|------|-----------------------------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [ServerResponseMediaInfo](#schemaserverresponsemediainfo) |

## POST 广播webrtc datachannel消息

POST /zlm/api/broadcast/message

> Body 请求参数

```json
{
  "key": "string"
}
```

### 请求参数

| 名称   | 位置   | 类型                            | 必选 | 说明   |
|------|------|-------------------------------|----|------|
| body | body | [MapString](#schemamapstring) | 否  | none |

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "data": "",
  "msg": "",
  "result": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                    |
|-----|---------------------------------------------------------|------|-----------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [ServerResponse](#schemaserverresponse) |

## GET 获取TCP会话列表

GET /zlm/api/session/list

获取所有TcpSession列表
获取TCP会话列表
获取ZLMediaKit服务器中所有TCP连接会话的列表

### 请求参数

| 名称        | 位置    | 类型     | 必选 | 说明   |
|-----------|-------|--------|----|------|
| localPort | query | string | 否  | none |
| peerIp    | query | string | 否  | none |

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "data": [
    {
      "id": "",
      "local_ip": "",
      "local_port": 0,
      "peer_ip": "",
      "peer_port": 0,
      "typeid": ""
    }
  ],
  "msg": "",
  "result": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                                          |
|-----|---------------------------------------------------------|------|---------------------------------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [ServerResponseListTcpLink](#schemaserverresponselisttcplink) |

## DELETE 断开TCP连接

DELETE /zlm/api/session/{sessionId}

断开tcp连接
断开TCP连接
根据会话ID断开指定的TCP连接

### 请求参数

| 名称        | 位置   | 类型     | 必选 | 说明   |
|-----------|------|--------|----|------|
| sessionId | path | string | 是  | none |

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "data": "",
  "msg": "",
  "result": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                                |
|-----|---------------------------------------------------------|------|-----------------------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [ServerResponseString](#schemaserverresponsestring) |

## POST 批量断开tcp连接

POST /zlm/api/session/kick-batch

> Body 请求参数

```json
{
  "key": "string"
}
```

### 请求参数

| 名称   | 位置   | 类型                            | 必选 | 说明   |
|------|------|-------------------------------|----|------|
| body | body | [MapString](#schemamapstring) | 否  | none |

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "data": "",
  "msg": "",
  "result": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                                |
|-----|---------------------------------------------------------|------|-----------------------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [ServerResponseString](#schemaserverresponsestring) |

## POST 添加代理拉流

POST /zlm/api/proxy/add

添加代理拉流
添加代理拉流
添加一个拉流代理，用于从外部拉取媒体流

> Body 请求参数

```json
{
  "vhost": "string",
  "app": "string",
  "stream": "string",
  "url": "string",
  "retry_count": 0,
  "rtp_type": 0,
  "timeout_sec": 0,
  "enable_hls": true,
  "enable_hls_fmp4": true,
  "enable_mp4": true,
  "enable_rtsp": true,
  "enable_rtmp": true,
  "enable_ts": true,
  "enable_fmp4": true,
  "hls_demand": true,
  "rtsp_demand": true,
  "rtmp_demand": true,
  "ts_demand": true,
  "fmp4_demand": true,
  "enable_audio": true,
  "add_mute_audio": true,
  "mp4_save_path": "string",
  "mp4_max_second": 0,
  "mp4_as_player": true,
  "hls_save_path": "string",
  "modify_stamp": 0,
  "auto_close": true
}
```

### 请求参数

| 名称   | 位置   | 类型                                        | 必选 | 说明   |
|------|------|-------------------------------------------|----|------|
| body | body | [StreamProxyItem](#schemastreamproxyitem) | 否  | none |

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "data": {
    "key": ""
  },
  "msg": "",
  "result": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                                      |
|-----|---------------------------------------------------------|------|-----------------------------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [ServerResponseStreamKey](#schemaserverresponsestreamkey) |

## DELETE 关闭拉流代理

DELETE /zlm/api/proxy/{key}

关闭拉流代理
关闭拉流代理
根据代理key关闭指定的拉流代理

### 请求参数

| 名称  | 位置   | 类型     | 必选 | 说明   |
|-----|------|--------|----|------|
| key | path | string | 是  | none |

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "data": {
    "flag": ""
  },
  "msg": "",
  "result": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                                              |
|-----|---------------------------------------------------------|------|-------------------------------------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [ServerResponseStringDelFlag](#schemaserverresponsestringdelflag) |

## POST 获取拉流代理信息

POST /zlm/api/proxy/info

> Body 请求参数

```json
{
  "key": "string"
}
```

### 请求参数

| 名称   | 位置   | 类型                            | 必选 | 说明   |
|------|------|-------------------------------|----|------|
| body | body | [MapString](#schemamapstring) | 否  | none |

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "data": "",
  "msg": "",
  "result": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                    |
|-----|---------------------------------------------------------|------|-----------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [ServerResponse](#schemaserverresponse) |

## POST 添加推流代理

POST /zlm/api/pusher/add

> Body 请求参数

```json
{
  "vhost": "string",
  "schema": "string",
  "app": "string",
  "stream": "string",
  "dst_url": "string",
  "retry_count": 0,
  "rtp_type": 0,
  "timeout_sec": 0
}
```

### 请求参数

| 名称   | 位置   | 类型                                          | 必选 | 说明   |
|------|------|---------------------------------------------|----|------|
| body | body | [StreamPusherItem](#schemastreampusheritem) | 否  | none |

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "data": {
    "key": ""
  },
  "msg": "",
  "result": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                                      |
|-----|---------------------------------------------------------|------|-----------------------------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [ServerResponseStreamKey](#schemaserverresponsestreamkey) |

## DELETE 关闭推流代理

DELETE /zlm/api/pusher/{key}

### 请求参数

| 名称  | 位置   | 类型     | 必选 | 说明   |
|-----|------|--------|----|------|
| key | path | string | 是  | none |

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "data": {
    "flag": ""
  },
  "msg": "",
  "result": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                                              |
|-----|---------------------------------------------------------|------|-------------------------------------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [ServerResponseStringDelFlag](#schemaserverresponsestringdelflag) |

## POST 获取推流代理信息

POST /zlm/api/pusher/info

> Body 请求参数

```json
{
  "key": "string"
}
```

### 请求参数

| 名称   | 位置   | 类型                            | 必选 | 说明   |
|------|------|-------------------------------|----|------|
| body | body | [MapString](#schemamapstring) | 否  | none |

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "data": "",
  "msg": "",
  "result": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                    |
|-----|---------------------------------------------------------|------|-----------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [ServerResponse](#schemaserverresponse) |

## POST 添加FFmpeg拉流代理

POST /zlm/api/ffmpeg/add

> Body 请求参数

```json
{
  "src_url": "string",
  "dst_url": "string",
  "timeout_ms": 0,
  "enable_hls": true,
  "enable_mp4": true,
  "ffmpeg_cmd_key": "string"
}
```

### 请求参数

| 名称   | 位置   | 类型                                          | 必选 | 说明   |
|------|------|---------------------------------------------|----|------|
| body | body | [StreamFfmpegItem](#schemastreamffmpegitem) | 否  | none |

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "data": {
    "key": ""
  },
  "msg": "",
  "result": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                                      |
|-----|---------------------------------------------------------|------|-----------------------------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [ServerResponseStreamKey](#schemaserverresponsestreamkey) |

## DELETE 关闭FFmpeg拉流代理

DELETE /zlm/api/ffmpeg/{key}

### 请求参数

| 名称  | 位置   | 类型     | 必选 | 说明   |
|-----|------|--------|----|------|
| key | path | string | 是  | none |

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "data": {
    "flag": ""
  },
  "msg": "",
  "result": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                                              |
|-----|---------------------------------------------------------|------|-------------------------------------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [ServerResponseStringDelFlag](#schemaserverresponsestringdelflag) |

## POST 获取录制文件列表

POST /zlm/api/record/files

> Body 请求参数

```json
{
  "schema": "rtsp",
  "vhost": "__defaultVhost__",
  "app": "string",
  "stream": "string",
  "period": "string",
  "customized_path": "string",
  "max_seconds": "string",
  "type": 0,
  "speed": "string",
  "stamp": "string"
}
```

### 请求参数

| 名称   | 位置   | 类型                            | 必选 | 说明   |
|------|------|-------------------------------|----|------|
| body | body | [RecordReq](#schemarecordreq) | 否  | none |

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "data": {
    "paths": [
      ""
    ],
    "rootPath": ""
  },
  "msg": "",
  "result": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                                              |
|-----|---------------------------------------------------------|------|-------------------------------------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [ServerResponseMp4RecordFile](#schemaserverresponsemp4recordfile) |

## POST 删除录像文件夹

POST /zlm/api/record/delete-directory

> Body 请求参数

```json
{
  "key": "string"
}
```

### 请求参数

| 名称   | 位置   | 类型                            | 必选 | 说明   |
|------|------|-------------------------------|----|------|
| body | body | [MapString](#schemamapstring) | 否  | none |

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "data": "",
  "msg": "",
  "result": "",
  "path": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                                  |
|-----|---------------------------------------------------------|------|-------------------------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [DeleteRecordDirectory](#schemadeleterecorddirectory) |

## POST 开始录制

POST /zlm/api/record/start

开始录制
开始录制
开始录制指定的媒体流

> Body 请求参数

```json
{
  "schema": "rtsp",
  "vhost": "__defaultVhost__",
  "app": "string",
  "stream": "string",
  "period": "string",
  "customized_path": "string",
  "max_seconds": "string",
  "type": 0,
  "speed": "string",
  "stamp": "string"
}
```

### 请求参数

| 名称   | 位置   | 类型                            | 必选 | 说明   |
|------|------|-------------------------------|----|------|
| body | body | [RecordReq](#schemarecordreq) | 否  | none |

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "data": "",
  "msg": "",
  "result": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                                |
|-----|---------------------------------------------------------|------|-----------------------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [ServerResponseString](#schemaserverresponsestring) |

## POST 设置录像速度

POST /zlm/api/record/speed

> Body 请求参数

```json
{
  "schema": "rtsp",
  "vhost": "__defaultVhost__",
  "app": "string",
  "stream": "string",
  "period": "string",
  "customized_path": "string",
  "max_seconds": "string",
  "type": 0,
  "speed": "string",
  "stamp": "string"
}
```

### 请求参数

| 名称   | 位置   | 类型                            | 必选 | 说明   |
|------|------|-------------------------------|----|------|
| body | body | [RecordReq](#schemarecordreq) | 否  | none |

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "data": "",
  "msg": "",
  "result": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                                |
|-----|---------------------------------------------------------|------|-----------------------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [ServerResponseString](#schemaserverresponsestring) |

## POST 设置录像流播放位置

POST /zlm/api/record/seek

> Body 请求参数

```json
{
  "schema": "rtsp",
  "vhost": "__defaultVhost__",
  "app": "string",
  "stream": "string",
  "period": "string",
  "customized_path": "string",
  "max_seconds": "string",
  "type": 0,
  "speed": "string",
  "stamp": "string"
}
```

### 请求参数

| 名称   | 位置   | 类型                            | 必选 | 说明   |
|------|------|-------------------------------|----|------|
| body | body | [RecordReq](#schemarecordreq) | 否  | none |

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "data": "",
  "msg": "",
  "result": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                                |
|-----|---------------------------------------------------------|------|-----------------------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [ServerResponseString](#schemaserverresponsestring) |

## POST 停止录制

POST /zlm/api/record/stop

停止录制
停止录制
停止录制指定的媒体流

> Body 请求参数

```json
{
  "schema": "rtsp",
  "vhost": "__defaultVhost__",
  "app": "string",
  "stream": "string",
  "period": "string",
  "customized_path": "string",
  "max_seconds": "string",
  "type": 0,
  "speed": "string",
  "stamp": "string"
}
```

### 请求参数

| 名称   | 位置   | 类型                            | 必选 | 说明   |
|------|------|-------------------------------|----|------|
| body | body | [RecordReq](#schemarecordreq) | 否  | none |

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "data": "",
  "msg": "",
  "result": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                                |
|-----|---------------------------------------------------------|------|-----------------------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [ServerResponseString](#schemaserverresponsestring) |

## POST 是否正在录制

POST /zlm/api/record/status

> Body 请求参数

```json
{
  "schema": "rtsp",
  "vhost": "__defaultVhost__",
  "app": "string",
  "stream": "string",
  "period": "string",
  "customized_path": "string",
  "max_seconds": "string",
  "type": 0,
  "speed": "string",
  "stamp": "string"
}
```

### 请求参数

| 名称   | 位置   | 类型                            | 必选 | 说明   |
|------|------|-------------------------------|----|------|
| body | body | [RecordReq](#schemarecordreq) | 否  | none |

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "data": "",
  "msg": "",
  "result": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                                |
|-----|---------------------------------------------------------|------|-----------------------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [ServerResponseString](#schemaserverresponsestring) |

## POST 查询文件概览

POST /zlm/api/record/summary

> Body 请求参数

```json
{
  "key": "string"
}
```

### 请求参数

| 名称   | 位置   | 类型                            | 必选 | 说明   |
|------|------|-------------------------------|----|------|
| body | body | [MapString](#schemamapstring) | 否  | none |

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "data": "",
  "msg": "",
  "result": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                                |
|-----|---------------------------------------------------------|------|-----------------------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [ServerResponseString](#schemaserverresponsestring) |

## POST 获取截图

POST /zlm/api/snapshot

获取截图
获取截图
获取指定媒体流的截图

> Body 请求参数

```json
{
  "url": "string",
  "timeout_sec": 30,
  "expire_sec": 5
}
```

### 请求参数

| 名称   | 位置   | 类型                                | 必选 | 说明   |
|------|------|-----------------------------------|----|------|
| body | body | [SnapshotReq](#schemasnapshotreq) | 否  | none |

> 返回示例

> 200 Response

```json
"string"
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型   |
|-----|---------------------------------------------------------|------|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | string |

## GET 获取RTP推流信息

GET /zlm/api/rtp/info/{streamId}

获取rtp推流信息
获取RTP推流信息
根据流ID获取RTP推流的详细信息

### 请求参数

| 名称       | 位置   | 类型     | 必选 | 说明   |
|----------|------|--------|----|------|
| streamId | path | string | 是  | none |

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "exist": false,
  "peer_ip": "",
  "peer_port": 0,
  "local_ip": "",
  "local_port": 0
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                  |
|-----|---------------------------------------------------------|------|---------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [RtpInfoResult](#schemartpinforesult) |

## POST 创建RTP服务器

POST /zlm/api/rtp/server/open

创建RTP服务器
创建RTP服务器
创建一个RTP服务器用于接收RTP推流

> Body 请求参数

```json
{
  "port": 0,
  "tcp_mode": 0,
  "stream_id": "string"
}
```

### 请求参数

| 名称   | 位置   | 类型                                          | 必选 | 说明   |
|------|------|---------------------------------------------|----|------|
| body | body | [OpenRtpServerReq](#schemaopenrtpserverreq) | 否  | none |

> 返回示例

> 200 Response

```json
{
  "port": "",
  "code": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                              |
|-----|---------------------------------------------------------|------|---------------------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [OpenRtpServerResult](#schemaopenrtpserverresult) |

## POST 创建多路复用RTP服务器

POST /zlm/api/rtp/server/open-multiplex

> Body 请求参数

```json
{
  "port": 0,
  "tcp_mode": 0,
  "stream_id": "string"
}
```

### 请求参数

| 名称   | 位置   | 类型                                          | 必选 | 说明   |
|------|------|---------------------------------------------|----|------|
| body | body | [OpenRtpServerReq](#schemaopenrtpserverreq) | 否  | none |

> 返回示例

> 200 Response

```json
{
  "port": "",
  "code": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                              |
|-----|---------------------------------------------------------|------|---------------------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [OpenRtpServerResult](#schemaopenrtpserverresult) |

## POST 连接RTP服务器

POST /zlm/api/rtp/server/connect

> Body 请求参数

```json
{
  "dst_port": 0,
  "dst_url": 0,
  "stream_id": "string"
}
```

### 请求参数

| 名称   | 位置   | 类型                                                | 必选 | 说明   |
|------|------|---------------------------------------------------|----|------|
| body | body | [ConnectRtpServerReq](#schemaconnectrtpserverreq) | 否  | none |

> 返回示例

> 200 Response

```json
{
  "port": "",
  "code": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                              |
|-----|---------------------------------------------------------|------|---------------------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [OpenRtpServerResult](#schemaopenrtpserverresult) |

## DELETE 关闭RTP服务器

DELETE /zlm/api/rtp/server/{streamId}

关闭RTP服务器
关闭RTP服务器
根据流ID关闭指定的RTP服务器

### 请求参数

| 名称       | 位置   | 类型     | 必选 | 说明   |
|----------|------|--------|----|------|
| streamId | path | string | 是  | none |

> 返回示例

> 200 Response

```json
{
  "hit": "",
  "code": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                                |
|-----|---------------------------------------------------------|------|-----------------------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [CloseRtpServerResult](#schemaclosertpserverresult) |

## PUT 更新RTP服务器过滤SSRC

PUT /zlm/api/rtp/server/{streamId}/ssrc/{ssrc}

### 请求参数

| 名称       | 位置   | 类型     | 必选 | 说明   |
|----------|------|--------|----|------|
| streamId | path | string | 是  | none |
| ssrc     | path | string | 是  | none |

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "data": "",
  "msg": "",
  "result": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                                |
|-----|---------------------------------------------------------|------|-----------------------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [ServerResponseString](#schemaserverresponsestring) |

## POST 暂停RTP超时检查

POST /zlm/api/rtp/server/{streamId}/pause-check

### 请求参数

| 名称       | 位置   | 类型     | 必选 | 说明   |
|----------|------|--------|----|------|
| streamId | path | string | 是  | none |

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "data": "",
  "msg": "",
  "result": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                                |
|-----|---------------------------------------------------------|------|-----------------------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [ServerResponseString](#schemaserverresponsestring) |

## POST 恢复RTP超时检查

POST /zlm/api/rtp/server/{streamId}/resume-check

### 请求参数

| 名称       | 位置   | 类型     | 必选 | 说明   |
|----------|------|--------|----|------|
| streamId | path | string | 是  | none |

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "data": "",
  "msg": "",
  "result": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                                |
|-----|---------------------------------------------------------|------|-----------------------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [ServerResponseString](#schemaserverresponsestring) |

## GET 获取RTP服务器列表

GET /zlm/api/rtp/server/list

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "data": [
    {
      "port": "",
      "streamId": ""
    }
  ],
  "msg": "",
  "result": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                                              |
|-----|---------------------------------------------------------|------|-------------------------------------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [ServerResponseListRtpServer](#schemaserverresponselistrtpserver) |

## POST 开始发送rtp

POST /zlm/api/rtp/send/start

> Body 请求参数

```json
{
  "schema": "rtsp",
  "vhost": "__defaultVhost__",
  "app": "string",
  "stream": "string",
  "ssrc": 0,
  "dst_url": "string",
  "dst_port": 0,
  "is_udp": true,
  "src_port": 0,
  "pt": 0,
  "use_ps": 0,
  "only_audio": true
}
```

### 请求参数

| 名称   | 位置   | 类型                                        | 必选 | 说明   |
|------|------|-------------------------------------------|----|------|
| body | body | [StartSendRtpReq](#schemastartsendrtpreq) | 否  | none |

> 返回示例

> 200 Response

```json
{
  "code": "",
  "local_port": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                            |
|-----|---------------------------------------------------------|------|-------------------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [StartSendRtpResult](#schemastartsendrtpresult) |

## POST 开始tcp passive被动发送rtp

POST /zlm/api/rtp/send/start-passive

> Body 请求参数

```json
{
  "schema": "rtsp",
  "vhost": "__defaultVhost__",
  "app": "string",
  "stream": "string",
  "ssrc": 0,
  "dst_url": "string",
  "dst_port": 0,
  "is_udp": true,
  "src_port": 0,
  "pt": 0,
  "use_ps": 0,
  "only_audio": true
}
```

### 请求参数

| 名称   | 位置   | 类型                                        | 必选 | 说明   |
|------|------|-------------------------------------------|----|------|
| body | body | [StartSendRtpReq](#schemastartsendrtpreq) | 否  | none |

> 返回示例

> 200 Response

```json
{
  "code": "",
  "local_port": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                            |
|-----|---------------------------------------------------------|------|-------------------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [StartSendRtpResult](#schemastartsendrtpresult) |

## POST 停止发送rtp

POST /zlm/api/rtp/send/stop

> Body 请求参数

```json
{
  "schema": "rtsp",
  "vhost": "__defaultVhost__",
  "app": "string",
  "stream": "string",
  "ssrc": "string"
}
```

### 请求参数

| 名称   | 位置   | 类型                                        | 必选 | 说明   |
|------|------|-------------------------------------------|----|------|
| body | body | [CloseSendRtpReq](#schemaclosesendrtpreq) | 否  | none |

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "data": "",
  "msg": "",
  "result": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                                |
|-----|---------------------------------------------------------|------|-----------------------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [ServerResponseString](#schemaserverresponsestring) |

## POST 多文件推流

POST /zlm/api/mp4/publish/start

> Body 请求参数

```json
{
  "key": "string"
}
```

### 请求参数

| 名称   | 位置   | 类型                            | 必选 | 说明   |
|------|------|-------------------------------|----|------|
| body | body | [MapString](#schemamapstring) | 否  | none |

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "data": "",
  "msg": "",
  "result": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                    |
|-----|---------------------------------------------------------|------|-----------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [ServerResponse](#schemaserverresponse) |

## POST 关闭多文件推流

POST /zlm/api/mp4/publish/stop

> Body 请求参数

```json
{
  "key": "string"
}
```

### 请求参数

| 名称   | 位置   | 类型                            | 必选 | 说明   |
|------|------|-------------------------------|----|------|
| body | body | [MapString](#schemamapstring) | 否  | none |

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "data": "",
  "msg": "",
  "result": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                    |
|-----|---------------------------------------------------------|------|-----------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [ServerResponse](#schemaserverresponse) |

## POST 点播mp4文件

POST /zlm/api/mp4/load

> Body 请求参数

```json
{
  "key": "string"
}
```

### 请求参数

| 名称   | 位置   | 类型                            | 必选 | 说明   |
|------|------|-------------------------------|----|------|
| body | body | [MapString](#schemamapstring) | 否  | none |

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "data": "",
  "msg": "",
  "result": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                    |
|-----|---------------------------------------------------------|------|-----------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [ServerResponse](#schemaserverresponse) |

## POST 获取存储信息

POST /zlm/api/storage/space

> Body 请求参数

```json
{
  "key": "string"
}
```

### 请求参数

| 名称   | 位置   | 类型                            | 必选 | 说明   |
|------|------|-------------------------------|----|------|
| body | body | [MapString](#schemamapstring) | 否  | none |

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "data": "",
  "msg": "",
  "result": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                    |
|-----|---------------------------------------------------------|------|-----------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [ServerResponse](#schemaserverresponse) |

## GET 指定节点获取版本信息

GET /zlm/api/node/{nodeId}/version

指定节点获取版本信息
指定节点获取版本信息
获取指定ZLM节点的版本信息

### 请求参数

| 名称     | 位置   | 类型     | 必选 | 说明   |
|--------|------|--------|----|------|
| nodeId | path | string | 是  | none |

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "data": {
    "buildTime": "",
    "branchName": "",
    "commitHash": ""
  },
  "msg": "",
  "result": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                                  |
|-----|---------------------------------------------------------|------|-------------------------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [ServerResponseVersion](#schemaserverresponseversion) |

## POST 指定节点获取流列表

POST /zlm/api/node/{nodeId}/media/list

指定节点获取流列表
指定节点获取流列表
获取指定ZLM节点中的媒体流列表

> Body 请求参数

```json
{
  "schema": "rtsp",
  "vhost": "__defaultVhost__",
  "app": "string",
  "stream": "string"
}
```

### 请求参数

| 名称     | 位置   | 类型                          | 必选 | 说明   |
|--------|------|-----------------------------|----|------|
| nodeId | path | string                      | 是  | none |
| body   | body | [MediaReq](#schemamediareq) | 否  | none |

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "data": [
    {
      "app": "",
      "readerCount": 0,
      "totalReaderCount": 0,
      "schema": "",
      "stream": "",
      "originSock": {
        "identifier": "",
        "local_ip": "",
        "local_port": 0,
        "peer_ip": "",
        "peer_port": 0,
        "typeid": ""
      },
      "originType": 0,
      "originTypeStr": "",
      "originUrl": "",
      "createStamp": 0,
      "aliveSecond": 0,
      "bytesSpeed": 0,
      "tracks": [
        {
          "channels": 0,
          "codec_id": 0,
          "codec_id_name": "",
          "codec_type": 0,
          "fps": 0,
          "height": 0,
          "ready": false,
          "width": 0,
          "frames": 0,
          "sample_bit": 0,
          "sample_rate": 0,
          "gop_interval_ms": 0,
          "gop_size": 0,
          "key_frames": 0
        }
      ],
      "vhost": ""
    }
  ],
  "msg": "",
  "result": ""
}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型                                                              |
|-----|---------------------------------------------------------|------|-------------------------------------------------------------------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | [ServerResponseListMediaData](#schemaserverresponselistmediadata) |

## GET 获取所有节点列表

GET /zlm/api/nodes

获取所有节点列表
获取所有节点列表
获取当前配置的所有ZLM节点信息

> 返回示例

> 200 Response

```json
[
  {
    "serverId": "",
    "host": "",
    "secret": "",
    "enabled": false,
    "hookEnabled": false,
    "weight": 0,
    "keepalive": 0
  }
]
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明   | 数据模型   |
|-----|---------------------------------------------------------|------|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none | Inline |

### 返回数据结构

状态码 **200**

*获取成功*

| 名称            | 类型                          | 必选    | 约束   | 中文名 | 说明                                                                  |
|---------------|-----------------------------|-------|------|-----|---------------------------------------------------------------------|
| *anonymous*   | [[ZlmNode](#schemazlmnode)] | false | none |     | 获取成功                                                                |
| » serverId    | string                      | false | none |     | The id of this node.                                                |
| » host        | string                      | false | none |     | The host of this node. eg: <a href="http://127.0.0.1:9092">node</a> |
| » secret      | string                      | false | none |     | The secret of this host.                                            |
| » enabled     | boolean                     | false | none |     | Whether enable this host.                                           |
| » hookEnabled | boolean                     | false | none |     | Whether enable hook.                                                |
| » weight      | integer                     | false | none |     | The weight of this host.                                            |

# 数据模型

<h2 id="tocS_Version">Version</h2>

<a id="schemaversion"></a>
<a id="schema_Version"></a>
<a id="tocSversion"></a>
<a id="tocsversion"></a>

```json
{
  "buildTime": "string",
  "branchName": "string",
  "commitHash": "string"
}

```

### 属性

| 名称         | 类型     | 必选    | 约束   | 中文名 | 说明   |
|------------|--------|-------|------|-----|------|
| buildTime  | string | false | none |     | none |
| branchName | string | false | none |     | none |
| commitHash | string | false | none |     | none |

<h2 id="tocS_ServerResponseVersion">ServerResponseVersion</h2>

<a id="schemaserverresponseversion"></a>
<a id="schema_ServerResponseVersion"></a>
<a id="tocSserverresponseversion"></a>
<a id="tocsserverresponseversion"></a>

```json
{
  "code": 0,
  "data": {
    "buildTime": "string",
    "branchName": "string",
    "commitHash": "string"
  },
  "msg": "success",
  "result": "success"
}

```

### 属性

| 名称     | 类型                        | 必选    | 约束   | 中文名 | 说明   |
|--------|---------------------------|-------|------|-----|------|
| code   | integer                   | false | none |     | none |
| data   | [Version](#schemaversion) | false | none |     | none |
| msg    | string                    | false | none |     | none |
| result | string                    | false | none |     | none |

<h2 id="tocS_ServerResponseListString">ServerResponseListString</h2>

<a id="schemaserverresponseliststring"></a>
<a id="schema_ServerResponseListString"></a>
<a id="tocSserverresponseliststring"></a>
<a id="tocsserverresponseliststring"></a>

```json
{
  "code": 0,
  "data": [
    "string"
  ],
  "msg": "success",
  "result": "success"
}

```

### 属性

| 名称     | 类型       | 必选    | 约束   | 中文名 | 说明   |
|--------|----------|-------|------|-----|------|
| code   | integer  | false | none |     | none |
| data   | [string] | false | none |     | none |
| msg    | string   | false | none |     | none |
| result | string   | false | none |     | none |

<h2 id="tocS_ThreadLoad">ThreadLoad</h2>

<a id="schemathreadload"></a>
<a id="schema_ThreadLoad"></a>
<a id="tocSthreadload"></a>
<a id="tocsthreadload"></a>

```json
{
  "delay": "string",
  "load": "string"
}

```

### 属性

| 名称    | 类型     | 必选    | 约束   | 中文名 | 说明            |
|-------|--------|-------|------|-----|---------------|
| delay | string | false | none |     | 该线程延时         |
| load  | string | false | none |     | 该线程负载，0 ~ 100 |

<h2 id="tocS_ServerResponseListThreadLoad">ServerResponseListThreadLoad</h2>

<a id="schemaserverresponselistthreadload"></a>
<a id="schema_ServerResponseListThreadLoad"></a>
<a id="tocSserverresponselistthreadload"></a>
<a id="tocsserverresponselistthreadload"></a>

```json
{
  "code": 0,
  "data": [
    {
      "delay": "string",
      "load": "string"
    }
  ],
  "msg": "success",
  "result": "success"
}

```

### 属性

| 名称     | 类型                                | 必选    | 约束   | 中文名 | 说明   |
|--------|-----------------------------------|-------|------|-----|------|
| code   | integer                           | false | none |     | none |
| data   | [[ThreadLoad](#schemathreadload)] | false | none |     | none |
| msg    | string                            | false | none |     | none |
| result | string                            | false | none |     | none |

<h2 id="tocS_ServerResponseImportantObjectNum">ServerResponseImportantObjectNum</h2>

<a id="schemaserverresponseimportantobjectnum"></a>
<a id="schema_ServerResponseImportantObjectNum"></a>
<a id="tocSserverresponseimportantobjectnum"></a>
<a id="tocsserverresponseimportantobjectnum"></a>

```json
{
  "code": 0,
  "data": {
    "Buffer": 0,
    "RtpPacket": 0,
    "Frame": 0,
    "RtmpPacket": 0,
    "TcpSession": 0,
    "UdpServer": 0,
    "TcpServer": 0,
    "FrameImp": 0,
    "BufferList": 0,
    "BufferRaw": 0,
    "MediaSource": 0,
    "MultiMediaSourceMuxer": 0,
    "TcpClient": 0,
    "BufferLikeString": 0,
    "Socket": 0,
    "UdpSession": 0
  },
  "msg": "success",
  "result": "success"
}

```

### 属性

| 名称     | 类型                                              | 必选    | 约束   | 中文名 | 说明   |
|--------|-------------------------------------------------|-------|------|-----|------|
| code   | integer                                         | false | none |     | none |
| data   | [ImportantObjectNum](#schemaimportantobjectnum) | false | none |     | none |
| msg    | string                                          | false | none |     | none |
| result | string                                          | false | none |     | none |

<h2 id="tocS_ImportantObjectNum">ImportantObjectNum</h2>

<a id="schemaimportantobjectnum"></a>
<a id="schema_ImportantObjectNum"></a>
<a id="tocSimportantobjectnum"></a>
<a id="tocsimportantobjectnum"></a>

```json
{
  "Buffer": 0,
  "RtpPacket": 0,
  "Frame": 0,
  "RtmpPacket": 0,
  "TcpSession": 0,
  "UdpServer": 0,
  "TcpServer": 0,
  "FrameImp": 0,
  "BufferList": 0,
  "BufferRaw": 0,
  "MediaSource": 0,
  "MultiMediaSourceMuxer": 0,
  "TcpClient": 0,
  "BufferLikeString": 0,
  "Socket": 0,
  "UdpSession": 0
}

```

### 属性

| 名称                    | 类型      | 必选    | 约束   | 中文名 | 说明   |
|-----------------------|---------|-------|------|-----|------|
| Buffer                | integer | false | none |     | none |
| RtpPacket             | integer | false | none |     | none |
| Frame                 | integer | false | none |     | none |
| RtmpPacket            | integer | false | none |     | none |
| TcpSession            | integer | false | none |     | none |
| UdpServer             | integer | false | none |     | none |
| TcpServer             | integer | false | none |     | none |
| FrameImp              | integer | false | none |     | none |
| BufferList            | integer | false | none |     | none |
| BufferRaw             | integer | false | none |     | none |
| MediaSource           | integer | false | none |     | none |
| MultiMediaSourceMuxer | integer | false | none |     | none |
| TcpClient             | integer | false | none |     | none |
| BufferLikeString      | integer | false | none |     | none |
| Socket                | integer | false | none |     | none |
| UdpSession            | integer | false | none |     | none |

<h2 id="tocS_ServerResponseListServerNodeConfig">ServerResponseListServerNodeConfig</h2>

<a id="schemaserverresponselistservernodeconfig"></a>
<a id="schema_ServerResponseListServerNodeConfig"></a>
<a id="tocSserverresponselistservernodeconfig"></a>
<a id="tocsserverresponselistservernodeconfig"></a>

```json
{
  "code": 0,
  "data": [
    {
      "rtp.rtpMaxSize": "string",
      "protocol.hls_demand": "string",
      "rtp_proxy.opus_pt": "string",
      "rtp_proxy.timeoutSec": "string",
      "rtmp.port": "string",
      "hook.on_ip_not_found": "string",
      "record.fileRepeat": "string",
      "general.flowThreshold": "string",
      "rtsp.rtpTransportType": "string",
      "hook.retry_delay": "string",
      "http.rootPath": "string",
      "rtsp.keepAliveSecond": "string",
      "hook.on_server_started": "string",
      "api.defaultSnap": "string",
      "cluster.origin_url": "string",
      "http.port": "string",
      "http.virtualPath": "string",
      "http.keepAliveSecond": "string",
      "ffmpeg.log": "string",
      "hook.on_flow_report": "string",
      "http.dirMenu": "string",
      "rtsp.directProxy": "string",
      "ffmpeg.cmd": "string",
      "rtp.lowLatency": "string",
      "protocol.enable_rtsp": "string",
      "rtsp.port": "string",
      "rtmp.sslport": "string",
      "protocol.hls_save_path": "string",
      "http.charSet": "string",
      "http.sendBufSize": "string",
      "hls.broadcastRecordTs": "string",
      "api.apiDebug": "string",
      "general.mergeWriteMS": "string",
      "http.forbidCacheSuffix": "string",
      "http.notFound": "string",
      "hook.retry": "string",
      "record.appName": "string",
      "hls.fileBufSize": "string",
      "hook.timeoutSec": "string",
      "rtsp.sslport": "string",
      "hls.deleteDelaySec": "string",
      "hook.on_rtp_server_timeout": "string",
      "hook.on_send_rtp_stopped": "string",
      "hook.on_record_mp4": "string",
      "hook.alive_interval": "string",
      "rtmp.handshakeSecond": "string",
      "hook.stream_changed_schemas": "string",
      "rtc.externIP": "string",
      "rtc.rembBitRate": "string",
      "general.streamNoneReaderDelayMS": "string",
      "protocol.mp4_max_second": "string",
      "hook.on_publish": "string",
      "rtp_proxy.port": "string",
      "http.sslport": "string",
      "rtp.audioMtuSize": "string",
      "general.check_nvidia_dev": "string",
      "record.fastStart": "string",
      "hook.on_stream_not_found": "string",
      "rtp_proxy.port_range": "string",
      "protocol.enable_rtmp": "string",
      "srt.timeoutSec": "string",
      "rtsp.handshakeSecond": "string",
      "hls.segDur": "string",
      "protocol.mp4_as_player": "string",
      "api.secret": "string",
      "hls.segRetain": "string",
      "protocol.rtsp_demand": "string",
      "srt.port": "string",
      "srt.pktBufSize": "string",
      "rtp_proxy.gop_cache": "string",
      "shell.maxReqSize": "string",
      "ffmpeg.snap": "string",
      "general.maxStreamWaitMS": "string",
      "multicast.addrMax": "string",
      "general.wait_add_track_ms": "string",
      "http.allow_cross_domains": "string",
      "protocol.modify_stamp": "string",
      "rtp.videoMtuSize": "string",
      "api.snapRoot": "string",
      "protocol.enable_audio": "string",
      "hook.on_server_keepalive": "string",
      "multicast.addrMin": "string",
      "protocol.ts_demand": "string",
      "protocol.enable_fmp4": "string",
      "rtsp.lowLatency": "string",
      "http.allow_ip_range": "string",
      "hook.on_rtsp_realm": "string",
      "hook.on_stream_changed": "string",
      "http.forwarded_ip_header": "string",
      "rtp_proxy.h265_pt": "string",
      "hook.on_del_mp4": "string",
      "protocol.enable_hls": "string",
      "protocol.enable_mp4": "string",
      "rtc.port": "string",
      "protocol.fmp4_demand": "string",
      "record.sampleMS": "string",
      "shell.port": "string",
      "hook.on_shell_login": "string",
      "cluster.retry_count": "string",
      "general.enableVhost": "string",
      "general.unready_frame_cache": "string",
      "rtc.preferredCodecV": "string",
      "rtp_proxy.h264_pt": "string",
      "protocol.auto_close": "string",
      "srt.latencyMul": "string",
      "hook.on_server_exited": "string",
      "general.resetWhenRePlay": "string",
      "protocol.mp4_save_path": "string",
      "protocol.continue_push_ms": "string",
      "rtp_proxy.dumpDir": "string",
      "rtp_proxy.ps_pt": "string",
      "hook.enable": "string",
      "rtc.timeoutSec": "string",
      "rtc.preferredCodecA": "string",
      "hls.segKeep": "string",
      "multicast.udpTTL": "string",
      "rtp.h264_stap_a": "string",
      "hook.on_stream_none_reader": "string",
      "hook.on_record_ts": "string",
      "ffmpeg.bin": "string",
      "protocol.enable_ts": "string",
      "protocol.enable_hls_fmp4": "string",
      "hls.segNum": "string",
      "http.maxReqSize": "string",
      "rtc.tcpPort": "string",
      "cluster.timeout_sec": "string",
      "general.enable_ffmpeg_log": "string",
      "general.mediaServerId": "string",
      "hook.on_http_access": "string",
      "general.wait_track_ready_ms": "string",
      "rtsp.authBasic": "string",
      "hook.on_rtsp_auth": "string",
      "protocol.rtmp_demand": "string",
      "protocol.add_mute_audio": "string",
      "record.fileBufSize": "string",
      "rtmp.keepAliveSecond": "string",
      "hook.on_play": "string"
    }
  ],
  "msg": "success",
  "result": "success"
}

```

### 属性

| 名称     | 类型                                            | 必选    | 约束   | 中文名 | 说明   |
|--------|-----------------------------------------------|-------|------|-----|------|
| code   | integer                                       | false | none |     | none |
| data   | [[ServerNodeConfig](#schemaservernodeconfig)] | false | none |     | none |
| msg    | string                                        | false | none |     | none |
| result | string                                        | false | none |     | none |

<h2 id="tocS_ServerNodeConfig">ServerNodeConfig</h2>

<a id="schemaservernodeconfig"></a>
<a id="schema_ServerNodeConfig"></a>
<a id="tocSservernodeconfig"></a>
<a id="tocsservernodeconfig"></a>

```json
{
  "rtp.rtpMaxSize": "string",
  "protocol.hls_demand": "string",
  "rtp_proxy.opus_pt": "string",
  "rtp_proxy.timeoutSec": "string",
  "rtmp.port": "string",
  "hook.on_ip_not_found": "string",
  "record.fileRepeat": "string",
  "general.flowThreshold": "string",
  "rtsp.rtpTransportType": "string",
  "hook.retry_delay": "string",
  "http.rootPath": "string",
  "rtsp.keepAliveSecond": "string",
  "hook.on_server_started": "string",
  "api.defaultSnap": "string",
  "cluster.origin_url": "string",
  "http.port": "string",
  "http.virtualPath": "string",
  "http.keepAliveSecond": "string",
  "ffmpeg.log": "string",
  "hook.on_flow_report": "string",
  "http.dirMenu": "string",
  "rtsp.directProxy": "string",
  "ffmpeg.cmd": "string",
  "rtp.lowLatency": "string",
  "protocol.enable_rtsp": "string",
  "rtsp.port": "string",
  "rtmp.sslport": "string",
  "protocol.hls_save_path": "string",
  "http.charSet": "string",
  "http.sendBufSize": "string",
  "hls.broadcastRecordTs": "string",
  "api.apiDebug": "string",
  "general.mergeWriteMS": "string",
  "http.forbidCacheSuffix": "string",
  "http.notFound": "string",
  "hook.retry": "string",
  "record.appName": "string",
  "hls.fileBufSize": "string",
  "hook.timeoutSec": "string",
  "rtsp.sslport": "string",
  "hls.deleteDelaySec": "string",
  "hook.on_rtp_server_timeout": "string",
  "hook.on_send_rtp_stopped": "string",
  "hook.on_record_mp4": "string",
  "hook.alive_interval": "string",
  "rtmp.handshakeSecond": "string",
  "hook.stream_changed_schemas": "string",
  "rtc.externIP": "string",
  "rtc.rembBitRate": "string",
  "general.streamNoneReaderDelayMS": "string",
  "protocol.mp4_max_second": "string",
  "hook.on_publish": "string",
  "rtp_proxy.port": "string",
  "http.sslport": "string",
  "rtp.audioMtuSize": "string",
  "general.check_nvidia_dev": "string",
  "record.fastStart": "string",
  "hook.on_stream_not_found": "string",
  "rtp_proxy.port_range": "string",
  "protocol.enable_rtmp": "string",
  "srt.timeoutSec": "string",
  "rtsp.handshakeSecond": "string",
  "hls.segDur": "string",
  "protocol.mp4_as_player": "string",
  "api.secret": "string",
  "hls.segRetain": "string",
  "protocol.rtsp_demand": "string",
  "srt.port": "string",
  "srt.pktBufSize": "string",
  "rtp_proxy.gop_cache": "string",
  "shell.maxReqSize": "string",
  "ffmpeg.snap": "string",
  "general.maxStreamWaitMS": "string",
  "multicast.addrMax": "string",
  "general.wait_add_track_ms": "string",
  "http.allow_cross_domains": "string",
  "protocol.modify_stamp": "string",
  "rtp.videoMtuSize": "string",
  "api.snapRoot": "string",
  "protocol.enable_audio": "string",
  "hook.on_server_keepalive": "string",
  "multicast.addrMin": "string",
  "protocol.ts_demand": "string",
  "protocol.enable_fmp4": "string",
  "rtsp.lowLatency": "string",
  "http.allow_ip_range": "string",
  "hook.on_rtsp_realm": "string",
  "hook.on_stream_changed": "string",
  "http.forwarded_ip_header": "string",
  "rtp_proxy.h265_pt": "string",
  "hook.on_del_mp4": "string",
  "protocol.enable_hls": "string",
  "protocol.enable_mp4": "string",
  "rtc.port": "string",
  "protocol.fmp4_demand": "string",
  "record.sampleMS": "string",
  "shell.port": "string",
  "hook.on_shell_login": "string",
  "cluster.retry_count": "string",
  "general.enableVhost": "string",
  "general.unready_frame_cache": "string",
  "rtc.preferredCodecV": "string",
  "rtp_proxy.h264_pt": "string",
  "protocol.auto_close": "string",
  "srt.latencyMul": "string",
  "hook.on_server_exited": "string",
  "general.resetWhenRePlay": "string",
  "protocol.mp4_save_path": "string",
  "protocol.continue_push_ms": "string",
  "rtp_proxy.dumpDir": "string",
  "rtp_proxy.ps_pt": "string",
  "hook.enable": "string",
  "rtc.timeoutSec": "string",
  "rtc.preferredCodecA": "string",
  "hls.segKeep": "string",
  "multicast.udpTTL": "string",
  "rtp.h264_stap_a": "string",
  "hook.on_stream_none_reader": "string",
  "hook.on_record_ts": "string",
  "ffmpeg.bin": "string",
  "protocol.enable_ts": "string",
  "protocol.enable_hls_fmp4": "string",
  "hls.segNum": "string",
  "http.maxReqSize": "string",
  "rtc.tcpPort": "string",
  "cluster.timeout_sec": "string",
  "general.enable_ffmpeg_log": "string",
  "general.mediaServerId": "string",
  "hook.on_http_access": "string",
  "general.wait_track_ready_ms": "string",
  "rtsp.authBasic": "string",
  "hook.on_rtsp_auth": "string",
  "protocol.rtmp_demand": "string",
  "protocol.add_mute_audio": "string",
  "record.fileBufSize": "string",
  "rtmp.keepAliveSecond": "string",
  "hook.on_play": "string"
}

```

### 属性

| 名称                              | 类型     | 必选    | 约束   | 中文名 | 说明                                                                     |
|---------------------------------|--------|-------|------|-----|------------------------------------------------------------------------|
| rtp.rtpMaxSize                  | string | false | none |     | The maximum size of RTP packets.                                       |
| protocol.hls_demand             | string | false | none |     | Whether to enable HLS demand.                                          |
| rtp_proxy.opus_pt               | string | false | none |     | The Opus payload type used by the RTP proxy.                           |
| rtp_proxy.timeoutSec            | string | false | none |     | The timeout value for the RTP proxy in seconds.                        |
| rtmp.port                       | string | false | none |     | The port used for RTMP.                                                |
| hook.on_ip_not_found            | string | false | none |     | The action to take when the IP address is not found.                   |
| record.fileRepeat               | string | false | none |     | Whether to allow file repetition during recording.                     |
| general.flowThreshold           | string | false | none |     | The threshold for the maximum flow.                                    |
| rtsp.rtpTransportType           | string | false | none |     | The transport type used for RTP in RTSP.                               |
| hook.retry_delay                | string | false | none |     | The delay time for retrying hooks in seconds.                          |
| http.rootPath                   | string | false | none |     | The root path for HTTP requests.                                       |
| rtsp.keepAliveSecond            | string | false | none |     | The time in seconds to keep the RTSP connection alive.                 |
| hook.on_server_started          | string | false | none |     | The action to take when the server is started.                         |
| api.defaultSnap                 | string | false | none |     | The default snapshot for the API.                                      |
| cluster.origin_url              | string | false | none |     | The origin URL for the cluster.                                        |
| http.port                       | string | false | none |     | The port used for HTTP.                                                |
| http.virtualPath                | string | false | none |     | The virtual path for HTTP requests.                                    |
| http.keepAliveSecond            | string | false | none |     | The time in seconds to keep the HTTP connection alive.                 |
| ffmpeg.log                      | string | false | none |     | The log file for FFmpeg.                                               |
| hook.on_flow_report             | string | false | none |     | The action to take when a flow report is received.                     |
| http.dirMenu                    | string | false | none |     | Whether to enable directory listing for HTTP requests.                 |
| rtsp.directProxy                | string | false | none |     | Whether to use direct proxy for RTSP.                                  |
| ffmpeg.cmd                      | string | false | none |     | The command used for FFmpeg.                                           |
| rtp.lowLatency                  | string | false | none |     | Whether to enable low latency for RTP.                                 |
| protocol.enable_rtsp            | string | false | none |     | Whether to enable RTSP.                                                |
| rtsp.port                       | string | false | none |     | The port used for RTSP.                                                |
| rtmp.sslport                    | string | false | none |     | The SSL port used for RTMP.                                            |
| protocol.hls_save_path          | string | false | none |     | The save path for HLS.                                                 |
| http.charSet                    | string | false | none |     | The character set used for HTTP requests.                              |
| http.sendBufSize                | string | false | none |     | The size of the send buffer for HTTP requests.                         |
| hls.broadcastRecordTs           | string | false | none |     | Whether to broadcast recorded TS files.                                |
| api.apiDebug                    | string | false | none |     | Whether to enable API debugging.                                       |
| general.mergeWriteMS            | string | false | none |     | The time in milliseconds for merging writes.                           |
| http.forbidCacheSuffix          | string | false | none |     | The suffixes to forbid caching for HTTP requests.                      |
| http.notFound                   | string | false | none |     | The action to take when a resource is not found for HTTP requests.     |
| hook.retry                      | string | false | none |     | The number of times to retry hooks.                                    |
| record.appName                  | string | false | none |     | The application name for recording.                                    |
| hls.fileBufSize                 | string | false | none |     | The buffer size for HLS files.                                         |
| hook.timeoutSec                 | string | false | none |     | The timeout value for hooks in seconds.                                |
| rtsp.sslport                    | string | false | none |     | The SSL port used for RTSP.                                            |
| hls.deleteDelaySec              | string | false | none |     | The delay time for deleting HLS files in seconds.                      |
| hook.on_rtp_server_timeout      | string | false | none |     | The action to take when the RTP server times out.                      |
| hook.on_send_rtp_stopped        | string | false | none |     | The action to take when sending RTP is stopped.                        |
| hook.on_record_mp4              | string | false | none |     | The action to take when recording MP4 files.                           |
| hook.alive_interval             | string | false | none |     | The interval time for sending keepalive messages for hooks in seconds. |
| rtmp.handshakeSecond            | string | false | none |     | The time in seconds for the RTMP handshake.                            |
| hook.stream_changed_schemas     | string | false | none |     | The schemas to use for stream changes in hooks.                        |
| rtc.externIP                    | string | false | none |     | The external IP address for RTC.                                       |
| rtc.rembBitRate                 | string | false | none |     | The bit rate for REMB in RTC.                                          |
| general.streamNoneReaderDelayMS | string | false | none |     | The time in milliseconds for waiting for a stream reader.              |
| protocol.mp4_max_second         | string | false | none |     | The maximum duration for MP4 files in seconds.                         |
| hook.on_publish                 | string | false | none |     | The action to take when publishing a stream.                           |
| rtp_proxy.port                  | string | false | none |     | The port used for the RTP proxy.                                       |
| http.sslport                    | string | false | none |     | The SSL port used for HTTP.                                            |
| rtp.audioMtuSize                | string | false | none |     | The MTU size for audio packets in RTP.                                 |
| general.check_nvidia_dev        | string | false | none |     | Whether to check for NVIDIA devices.                                   |
| record.fastStart                | string | false | none |     | Whether to enable fast start for recording.                            |
| hook.on_stream_not_found        | string | false | none |     | The action to take when a stream is not found in hooks.                |
| rtp_proxy.port_range            | string | false | none |     | The port range used for the RTP proxy.                                 |
| protocol.enable_rtmp            | string | false | none |     | Whether to enable RTMP.                                                |
| srt.timeoutSec                  | string | false | none |     | The timeout value for SRT in seconds.                                  |
| rtsp.handshakeSecond            | string | false | none |     | The time in seconds for the RTSP handshake.                            |
| hls.segDur                      | string | false | none |     | The duration for each HLS segment in seconds.                          |
| protocol.mp4_as_player          | string | false | none |     | Whether to use MP4 as a player for the protocol.                       |
| api.secret                      | string | false | none |     | The secret key for the API.                                            |
| hls.segRetain                   | string | false | none |     | The number of HLS segments to retain.                                  |
| protocol.rtsp_demand            | string | false | none |     | Whether to enable demand for RTSP.                                     |
| srt.port                        | string | false | none |     | The port used for SRT.                                                 |
| srt.pktBufSize                  | string | false | none |     | The packet buffer size for SRT.                                        |
| rtp_proxy.gop_cache             | string | false | none |     | Whether to enable GOP caching for the RTP proxy.                       |
| shell.maxReqSize                | string | false | none |     | The maximum size of requests for the shell.                            |
| ffmpeg.snap                     | string | false | none |     | Whether to enable snapshots for FFmpeg.                                |
| general.maxStreamWaitMS         | string | false | none |     | The maximum time in milliseconds to wait for a stream reader.          |
| multicast.addrMax               | string | false | none |     | The maximum multicast address.                                         |
| general.wait_add_track_ms       | string | false | none |     | The time in milliseconds to wait for adding a track.                   |
| http.allow_cross_domains        | string | false | none |     | Whether to allow cross-domain requests for HTTP.                       |
| protocol.modify_stamp           | string | false | none |     | Whether to modify the timestamp for the protocol.                      |
| rtp.videoMtuSize                | string | false | none |     | The MTU size for video packets in RTP.                                 |
| api.snapRoot                    | string | false | none |     | The root directory for snapshots in the API.                           |
| protocol.enable_audio           | string | false | none |     | Whether to enable audio for the protocol.                              |
| hook.on_server_keepalive        | string | false | none |     | The action to take when the server keeps alive.                        |
| multicast.addrMin               | string | false | none |     | The minimum multicast address.                                         |
| protocol.ts_demand              | string | false | none |     | Whether to enable demand for TS.                                       |
| protocol.enable_fmp4            | string | false | none |     | Whether to enable FMP4 for the protocol.                               |
| rtsp.lowLatency                 | string | false | none |     | Whether to enable low latency for RTSP.                                |
| http.allow_ip_range             | string | false | none |     | The IP range to allow for HTTP requests.                               |
| hook.on_rtsp_realm              | string | false | none |     | The action to take when the RTSP realm is accessed in hooks.           |
| hook.on_stream_changed          | string | false | none |     | The action to take when a stream is changed in hooks.                  |
| http.forwarded_ip_header        | string | false | none |     | The header to use for forwarded IP addresses in HTTP requests.         |
| rtp_proxy.h265_pt               | string | false | none |     | The H.265 payload type used by the RTP proxy.                          |
| hook.on_del_mp4                 | string | false | none |     | The action to take when an MP4 file is deleted in hooks.               |
| protocol.enable_hls             | string | false | none |     | Whether to enable HLS for the protocol.                                |
| protocol.enable_mp4             | string | false | none |     | Whether to enable MP4 for the protocol.                                |
| rtc.port                        | string | false | none |     | The port used for RTC.                                                 |
| protocol.fmp4_demand            | string | false | none |     | Whether to enable demand for FMP4.                                     |
| record.sampleMS                 | string | false | none |     | The time in milliseconds for each sample during recording.             |
| shell.port                      | string | false | none |     | The port used for the shell.                                           |
| hook.on_shell_login             | string | false | none |     | The action to take when logging in to the shell.                       |
| cluster.retry_count             | string | false | none |     | The number of times to retry connecting to the cluster.                |
| general.enableVhost             | string | false | none |     | Whether to enable virtual hosts.                                       |
| general.unready_frame_cache     | string | false | none |     | The size of the unready frame cache.                                   |
| rtc.preferredCodecV             | string | false | none |     | The preferred video codec for RTC.                                     |
| rtp_proxy.h264_pt               | string | false | none |     | The H.264 payload type used by the RTP proxy.                          |
| protocol.auto_close             | string | false | none |     | Whether to automatically close connections for the protocol.           |
| srt.latencyMul                  | string | false | none |     | The latency multiplier for SRT.                                        |
| hook.on_server_exited           | string | false | none |     | The action to take when the server is exited.                          |
| general.resetWhenRePlay         | string | false | none |     | Whether to reset when replaying.                                       |
| protocol.mp4_save_path          | string | false | none |     | The save path for MP4 files in the protocol.                           |
| protocol.continue_push_ms       | string | false | none |     | The time in milliseconds for continuing to push data for the protocol. |
| rtp_proxy.dumpDir               | string | false | none |     | The dump directory for the RTP proxy.                                  |
| rtp_proxy.ps_pt                 | string | false | none |     | The payload type used for PS in the RTP proxy.                         |
| hook.enable                     | string | false | none |     | Whether to enable hooks.                                               |
| rtc.timeoutSec                  | string | false | none |     | The timeout value for RTC in seconds.                                  |
| rtc.preferredCodecA             | string | false | none |     | The preferred audio codec for RTC.                                     |
| hls.segKeep                     | string | false | none |     | The number of HLS segments to keep.                                    |
| multicast.udpTTL                | string | false | none |     | The TTL value for UDP multicast.                                       |
| rtp.h264_stap_a                 | string | false | none |     | Whether to enable STAP-A for H.264 in RTP.                             |
| hook.on_stream_none_reader      | string | false | none |     | The action to take when there are no stream readers in hooks.          |
| hook.on_record_ts               | string | false | none |     | The action to take when recording TS files in hooks.                   |
| ffmpeg.bin                      | string | false | none |     | The path to the FFmpeg binary.                                         |
| protocol.enable_ts              | string | false | none |     | Whether to enable demand for TS in the protocol.                       |
| protocol.enable_hls_fmp4        | string | false | none |     | Whether to enable HLS FMP4 in the protocol.                            |
| hls.segNum                      | string | false | none |     | The number of HLS segments.                                            |
| http.maxReqSize                 | string | false | none |     | The maximum size of requests for HTTP.                                 |
| rtc.tcpPort                     | string | false | none |     | The TCP port used for RTC.                                             |
| cluster.timeout_sec             | string | false | none |     | The timeout value for the cluster in seconds.                          |
| general.enable_ffmpeg_log       | string | false | none |     | Whether to enable FFmpeg logging.                                      |
| general.mediaServerId           | string | false | none |     | The ID of the media server.                                            |
| hook.on_http_access             | string | false | none |     | The action to take when accessing HTTP in hooks.                       |
| general.wait_track_ready_ms     | string | false | none |     | The time in milliseconds to wait for a track to be ready.              |
| rtsp.authBasic                  | string | false | none |     | Whether to enable basic authentication for RTSP.                       |
| hook.on_rtsp_auth               | string | false | none |     | The action to take when authenticating RTSP in hooks.                  |
| protocol.rtmp_demand            | string | false | none |     | Whether to enable demand for RTMP in the protocol.                     |
| protocol.add_mute_audio         | string | false | none |     | Whether to add mute audio for the protocol.                            |
| record.fileBufSize              | string | false | none |     | The buffer size for recording files.                                   |
| rtmp.keepAliveSecond            | string | false | none |     | The time in seconds to keep the RTMP connection alive.                 |
| hook.on_play                    | string | false | none |     | The action to take when playing a stream in hooks.                     |

<h2 id="tocS_MapString">MapString</h2>

<a id="schemamapstring"></a>
<a id="schema_MapString"></a>
<a id="tocSmapstring"></a>
<a id="tocsmapstring"></a>

```json
{
  "key": "string"
}

```

### 属性

| 名称  | 类型     | 必选    | 约束   | 中文名 | 说明   |
|-----|--------|-------|------|-----|------|
| key | string | false | none |     | none |

<h2 id="tocS_ServerResponseString">ServerResponseString</h2>

<a id="schemaserverresponsestring"></a>
<a id="schema_ServerResponseString"></a>
<a id="tocSserverresponsestring"></a>
<a id="tocsserverresponsestring"></a>

```json
{
  "code": 0,
  "data": "string",
  "msg": "success",
  "result": "success"
}

```

### 属性

| 名称     | 类型      | 必选    | 约束   | 中文名 | 说明   |
|--------|---------|-------|------|-----|------|
| code   | integer | false | none |     | none |
| data   | string  | false | none |     | none |
| msg    | string  | false | none |     | none |
| result | string  | false | none |     | none |

<h2 id="tocS_ServerResponseObject">ServerResponseObject</h2>

<a id="schemaserverresponseobject"></a>
<a id="schema_ServerResponseObject"></a>
<a id="tocSserverresponseobject"></a>
<a id="tocsserverresponseobject"></a>

```json
{
  "code": 0,
  "data": {},
  "msg": "success",
  "result": "success"
}

```

### 属性

| 名称     | 类型      | 必选    | 约束   | 中文名 | 说明   |
|--------|---------|-------|------|-----|------|
| code   | integer | false | none |     | none |
| data   | object  | false | none |     | none |
| msg    | string  | false | none |     | none |
| result | string  | false | none |     | none |

<h2 id="tocS_MediaReq">MediaReq</h2>

<a id="schemamediareq"></a>
<a id="schema_MediaReq"></a>
<a id="tocSmediareq"></a>
<a id="tocsmediareq"></a>

```json
{
  "schema": "rtsp",
  "vhost": "__defaultVhost__",
  "app": "string",
  "stream": "string"
}

```

### 属性

| 名称     | 类型     | 必选    | 约束   | 中文名 | 说明                        |
|--------|--------|-------|------|-----|---------------------------|
| schema | string | false | none |     | 筛选协议，例如 rtsp或rtmp         |
| vhost  | string | false | none |     | 筛选虚拟主机，例如__defaultVhost__ |
| app    | string | false | none |     | 筛选应用名，例如 live             |
| stream | string | false | none |     | 筛选流id，例如 test             |

<h2 id="tocS_ServerResponseListMediaData">ServerResponseListMediaData</h2>

<a id="schemaserverresponselistmediadata"></a>
<a id="schema_ServerResponseListMediaData"></a>
<a id="tocSserverresponselistmediadata"></a>
<a id="tocsserverresponselistmediadata"></a>

```json
{
  "code": 0,
  "data": [
    {
      "app": "string",
      "readerCount": 0,
      "totalReaderCount": 0,
      "schema": "string",
      "stream": "string",
      "originSock": {
        "identifier": "string",
        "local_ip": "string",
        "local_port": 0,
        "peer_ip": "string",
        "peer_port": 0,
        "typeid": "string"
      },
      "originType": 0,
      "originTypeStr": "string",
      "originUrl": "string",
      "createStamp": 0,
      "aliveSecond": 0,
      "bytesSpeed": 0,
      "tracks": [
        {
          "channels": 0,
          "codec_id": 0,
          "codec_id_name": "string",
          "codec_type": 0,
          "fps": 0,
          "height": 0,
          "ready": true,
          "width": 0,
          "frames": 0,
          "sample_bit": 0,
          "sample_rate": 0,
          "gop_interval_ms": 0,
          "gop_size": 0,
          "key_frames": 0
        }
      ],
      "vhost": "string"
    }
  ],
  "msg": "success",
  "result": "success"
}

```

### 属性

| 名称     | 类型                              | 必选    | 约束   | 中文名 | 说明   |
|--------|---------------------------------|-------|------|-----|------|
| code   | integer                         | false | none |     | none |
| data   | [[MediaData](#schemamediadata)] | false | none |     | none |
| msg    | string                          | false | none |     | none |
| result | string                          | false | none |     | none |

<h2 id="tocS_MediaData">MediaData</h2>

<a id="schemamediadata"></a>
<a id="schema_MediaData"></a>
<a id="tocSmediadata"></a>
<a id="tocsmediadata"></a>

```json
{
  "app": "string",
  "readerCount": 0,
  "totalReaderCount": 0,
  "schema": "string",
  "stream": "string",
  "originSock": {
    "identifier": "string",
    "local_ip": "string",
    "local_port": 0,
    "peer_ip": "string",
    "peer_port": 0,
    "typeid": "string"
  },
  "originType": 0,
  "originTypeStr": "string",
  "originUrl": "string",
  "createStamp": 0,
  "aliveSecond": 0,
  "bytesSpeed": 0,
  "tracks": [
    {
      "channels": 0,
      "codec_id": 0,
      "codec_id_name": "string",
      "codec_type": 0,
      "fps": 0,
      "height": 0,
      "ready": true,
      "width": 0,
      "frames": 0,
      "sample_bit": 0,
      "sample_rate": 0,
      "gop_interval_ms": 0,
      "gop_size": 0,
      "key_frames": 0
    }
  ],
  "vhost": "string"
}

```

### 属性

| 名称               | 类型                                | 必选    | 约束   | 中文名 | 说明                                                                                                  |
|------------------|-----------------------------------|-------|------|-----|-----------------------------------------------------------------------------------------------------|
| app              | string                            | false | none |     | 应用名                                                                                                 |
| readerCount      | integer                           | false | none |     | 本协议观看人数                                                                                             |
| totalReaderCount | integer                           | false | none |     | 观看总人数，包括hls/rtsp/rtmp/http-flv/ws-flv                                                               |
| schema           | string                            | false | none |     | 协议                                                                                                  |
| stream           | string                            | false | none |     | 流id                                                                                                 |
| originSock       | [MediaPlayer](#schemamediaplayer) | false | none |     | 客户端和服务器网络信息，可能为null类型                                                                               |
| originType       | integer                           | false | none |     | 产生源类型，包括 unknown = 0,rtmp_push=1,rtsp_push=2,rtp_push=3,pull=4,ffmpeg_pull=5,mp4_vod=6,device_chn=7 |
| originTypeStr    | string                            | false | none |     | none                                                                                                |
| originUrl        | string                            | false | none |     | 产生源的url                                                                                             |
| createStamp      | integer(int64)                    | false | none |     | GMT unix系统时间戳，单位秒                                                                                   |
| aliveSecond      | integer                           | false | none |     | 存活时间，单位秒                                                                                            |
| bytesSpeed       | integer                           | false | none |     | 数据产生速度，单位byte/s                                                                                     |
| tracks           | [[Track](#schematrack)]           | false | none |     | 音视频轨道                                                                                               |
| vhost            | string                            | false | none |     | 虚拟主机名                                                                                               |

<h2 id="tocS_Track">Track</h2>

<a id="schematrack"></a>
<a id="schema_Track"></a>
<a id="tocStrack"></a>
<a id="tocstrack"></a>

```json
{
  "channels": 0,
  "codec_id": 0,
  "codec_id_name": "string",
  "codec_type": 0,
  "fps": 0,
  "height": 0,
  "ready": true,
  "width": 0,
  "frames": 0,
  "sample_bit": 0,
  "sample_rate": 0,
  "gop_interval_ms": 0,
  "gop_size": 0,
  "key_frames": 0
}

```

### 属性

| 名称              | 类型      | 必选    | 约束   | 中文名 | 说明                                                      |
|-----------------|---------|-------|------|-----|---------------------------------------------------------|
| channels        | integer | false | none |     | 音频通道数。                                                  |
| codec_id        | integer | false | none |     | 编码ID。H264 = 0, H265 = 1, AAC = 2, G711A = 3, G711U = 4。 |
| codec_id_name   | string  | false | none |     | 编码类型的名称。                                                |
| codec_type      | integer | false | none |     | 类型。视频 = 0, 音频 = 1。                                      |
| fps             | integer | false | none |     | 视频的帧率。                                                  |
| height          | integer | false | none |     | 视频的高度。                                                  |
| ready           | boolean | false | none |     | 轨道是否准备就绪。                                               |
| width           | integer | false | none |     | 视频的宽度。                                                  |
| frames          | integer | false | none |     | 累计接收帧数                                                  |
| sample_bit      | integer | false | none |     | 音频采样位数                                                  |
| sample_rate     | integer | false | none |     | 音频采样率                                                   |
| gop_interval_ms | integer | false | none |     | gop间隔时间，单位毫秒                                            |
| gop_size        | integer | false | none |     | gop大小，单位帧数                                              |
| key_frames      | integer | false | none |     | 累计接收关键帧数                                                |

<h2 id="tocS_MediaPlayer">MediaPlayer</h2>

<a id="schemamediaplayer"></a>
<a id="schema_MediaPlayer"></a>
<a id="tocSmediaplayer"></a>
<a id="tocsmediaplayer"></a>

```json
{
  "identifier": "string",
  "local_ip": "string",
  "local_port": 0,
  "peer_ip": "string",
  "peer_port": 0,
  "typeid": "string"
}

```

### 属性

| 名称         | 类型      | 必选    | 约束   | 中文名 | 说明                                                                                           |
|------------|---------|-------|------|-----|----------------------------------------------------------------------------------------------|
| identifier | string  | false | none |     | The unique identifier for the session, e.g., "3-309".                                        |
| local_ip   | string  | false | none |     | The local IP address. "::" is a shorthand in IPv6 for representing multiple groups of zeros. |
| local_port | integer | false | none |     | The local port number.                                                                       |
| peer_ip    | string  | false | none |     | The IP address of the peer in the session.                                                   |
| peer_port  | integer | false | none |     | The port number of the peer.                                                                 |
| typeid     | string  | false | none |     | The type identifier for the session, indicating it's a WebRTC session from MediaKit.         |

<h2 id="tocS_CloseStreamsReq">CloseStreamsReq</h2>

<a id="schemaclosestreamsreq"></a>
<a id="schema_CloseStreamsReq"></a>
<a id="tocSclosestreamsreq"></a>
<a id="tocsclosestreamsreq"></a>

```json
{
  "schema": "rtsp",
  "vhost": "__defaultVhost__",
  "app": "string",
  "stream": "string",
  "force": 0
}

```

### 属性

| 名称     | 类型      | 必选    | 约束   | 中文名 | 说明                        |
|--------|---------|-------|------|-----|---------------------------|
| schema | string  | false | none |     | 筛选协议，例如 rtsp或rtmp         |
| vhost  | string  | false | none |     | 筛选虚拟主机，例如__defaultVhost__ |
| app    | string  | false | none |     | 筛选应用名，例如 live             |
| stream | string  | false | none |     | 筛选流id，例如 test             |
| force  | integer | false | none |     | none                      |

<h2 id="tocS_ServerResponse">ServerResponse</h2>

<a id="schemaserverresponse"></a>
<a id="schema_ServerResponse"></a>
<a id="tocSserverresponse"></a>
<a id="tocsserverresponse"></a>

```json
{
  "code": 0,
  "data": "string",
  "msg": "success",
  "result": "success"
}

```

### 属性

| 名称     | 类型      | 必选    | 约束   | 中文名 | 说明   |
|--------|---------|-------|------|-----|------|
| code   | integer | false | none |     | none |
| data   | string  | false | none |     | none |
| msg    | string  | false | none |     | none |
| result | string  | false | none |     | none |

<h2 id="tocS_MediaOnlineStatus">MediaOnlineStatus</h2>

<a id="schemamediaonlinestatus"></a>
<a id="schema_MediaOnlineStatus"></a>
<a id="tocSmediaonlinestatus"></a>
<a id="tocsmediaonlinestatus"></a>

```json
{
  "code": 0,
  "data": "string",
  "msg": "success",
  "result": "success",
  "online": "string"
}

```

### 属性

| 名称     | 类型      | 必选    | 约束   | 中文名 | 说明   |
|--------|---------|-------|------|-----|------|
| code   | integer | false | none |     | none |
| data   | string  | false | none |     | none |
| msg    | string  | false | none |     | none |
| result | string  | false | none |     | none |
| online | string  | false | none |     | none |

<h2 id="tocS_ServerResponseMediaPlayer">ServerResponseMediaPlayer</h2>

<a id="schemaserverresponsemediaplayer"></a>
<a id="schema_ServerResponseMediaPlayer"></a>
<a id="tocSserverresponsemediaplayer"></a>
<a id="tocsserverresponsemediaplayer"></a>

```json
{
  "code": 0,
  "data": {
    "identifier": "string",
    "local_ip": "string",
    "local_port": 0,
    "peer_ip": "string",
    "peer_port": 0,
    "typeid": "string"
  },
  "msg": "success",
  "result": "success"
}

```

### 属性

| 名称     | 类型                                | 必选    | 约束   | 中文名 | 说明   |
|--------|-----------------------------------|-------|------|-----|------|
| code   | integer                           | false | none |     | none |
| data   | [MediaPlayer](#schemamediaplayer) | false | none |     | none |
| msg    | string                            | false | none |     | none |
| result | string                            | false | none |     | none |

<h2 id="tocS_ServerResponseMediaInfo">ServerResponseMediaInfo</h2>

<a id="schemaserverresponsemediainfo"></a>
<a id="schema_ServerResponseMediaInfo"></a>
<a id="tocSserverresponsemediainfo"></a>
<a id="tocsserverresponsemediainfo"></a>

```json
{
  "code": 0,
  "data": {
    "code": 0,
    "online": true,
    "readerCount": 0,
    "totalReaderCount": 0,
    "tracks": [
      {
        "channels": 0,
        "codec_id": 0,
        "codec_id_name": "string",
        "codec_type": 0,
        "fps": 0,
        "height": 0,
        "ready": true,
        "width": 0,
        "frames": 0,
        "sample_bit": 0,
        "sample_rate": 0,
        "gop_interval_ms": 0,
        "gop_size": 0,
        "key_frames": 0
      }
    ]
  },
  "msg": "success",
  "result": "success"
}

```

### 属性

| 名称     | 类型                            | 必选    | 约束   | 中文名 | 说明   |
|--------|-------------------------------|-------|------|-----|------|
| code   | integer                       | false | none |     | none |
| data   | [MediaInfo](#schemamediainfo) | false | none |     | none |
| msg    | string                        | false | none |     | none |
| result | string                        | false | none |     | none |

<h2 id="tocS_MediaInfo">MediaInfo</h2>

<a id="schemamediainfo"></a>
<a id="schema_MediaInfo"></a>
<a id="tocSmediainfo"></a>
<a id="tocsmediainfo"></a>

```json
{
  "code": 0,
  "online": true,
  "readerCount": 0,
  "totalReaderCount": 0,
  "tracks": [
    {
      "channels": 0,
      "codec_id": 0,
      "codec_id_name": "string",
      "codec_type": 0,
      "fps": 0,
      "height": 0,
      "ready": true,
      "width": 0,
      "frames": 0,
      "sample_bit": 0,
      "sample_rate": 0,
      "gop_interval_ms": 0,
      "gop_size": 0,
      "key_frames": 0
    }
  ]
}

```

### 属性

| 名称               | 类型                        | 必选    | 约束   | 中文名 | 说明                                     |
|------------------|---------------------------|-------|------|-----|----------------------------------------|
| code             | integer                   | false | none |     | 状态码。                                   |
| online           | boolean                   | false | none |     | 会话是否在线。                                |
| readerCount      | integer                   | false | none |     | 本协议的观看人数。                              |
| totalReaderCount | integer                   | false | none |     | 观看总人数，包括hls/rtsp/rtmp/http-flv/ws-flv。 |
| tracks           | [[Track1](#schematrack1)] | false | none |     | 轨道列表。                                  |

<h2 id="tocS_Track1">Track1</h2>

<a id="schematrack1"></a>
<a id="schema_Track1"></a>
<a id="tocStrack1"></a>
<a id="tocstrack1"></a>

```json
{
  "channels": 0,
  "codec_id": 0,
  "codec_id_name": "string",
  "codec_type": 0,
  "fps": 0,
  "height": 0,
  "ready": true,
  "width": 0,
  "frames": 0,
  "sample_bit": 0,
  "sample_rate": 0,
  "gop_interval_ms": 0,
  "gop_size": 0,
  "key_frames": 0
}

```

### 属性

| 名称              | 类型      | 必选    | 约束   | 中文名 | 说明                                                      |
|-----------------|---------|-------|------|-----|---------------------------------------------------------|
| channels        | integer | false | none |     | 音频通道数。                                                  |
| codec_id        | integer | false | none |     | 编码ID。H264 = 0, H265 = 1, AAC = 2, G711A = 3, G711U = 4。 |
| codec_id_name   | string  | false | none |     | 编码类型的名称。                                                |
| codec_type      | integer | false | none |     | 类型。视频 = 0, 音频 = 1。                                      |
| fps             | integer | false | none |     | 视频的帧率。                                                  |
| height          | integer | false | none |     | 视频的高度。                                                  |
| ready           | boolean | false | none |     | 轨道是否准备就绪。                                               |
| width           | integer | false | none |     | 视频的宽度。                                                  |
| frames          | integer | false | none |     | 累计接收帧数                                                  |
| sample_bit      | integer | false | none |     | 音频采样位数                                                  |
| sample_rate     | integer | false | none |     | 音频采样率                                                   |
| gop_interval_ms | integer | false | none |     | gop间隔时间，单位毫秒                                            |
| gop_size        | integer | false | none |     | gop大小，单位帧数                                              |
| key_frames      | integer | false | none |     | 累计接收关键帧数                                                |

<h2 id="tocS_ServerResponseListTcpLink">ServerResponseListTcpLink</h2>

<a id="schemaserverresponselisttcplink"></a>
<a id="schema_ServerResponseListTcpLink"></a>
<a id="tocSserverresponselisttcplink"></a>
<a id="tocsserverresponselisttcplink"></a>

```json
{
  "code": 0,
  "data": [
    {
      "id": "string",
      "local_ip": "string",
      "local_port": 0,
      "peer_ip": "string",
      "peer_port": 0,
      "typeid": "string"
    }
  ],
  "msg": "success",
  "result": "success"
}

```

### 属性

| 名称     | 类型                          | 必选    | 约束   | 中文名 | 说明   |
|--------|-----------------------------|-------|------|-----|------|
| code   | integer                     | false | none |     | none |
| data   | [[TcpLink](#schematcplink)] | false | none |     | none |
| msg    | string                      | false | none |     | none |
| result | string                      | false | none |     | none |

<h2 id="tocS_TcpLink">TcpLink</h2>

<a id="schematcplink"></a>
<a id="schema_TcpLink"></a>
<a id="tocStcplink"></a>
<a id="tocstcplink"></a>

```json
{
  "id": "string",
  "local_ip": "string",
  "local_port": 0,
  "peer_ip": "string",
  "peer_port": 0,
  "typeid": "string"
}

```

### 属性

| 名称         | 类型      | 必选    | 约束   | 中文名 | 说明                     |
|------------|---------|-------|------|-----|------------------------|
| id         | string  | false | none |     | 该tcp链接唯一id             |
| local_ip   | string  | false | none |     | 本机网卡ip                 |
| local_port | integer | false | none |     | 本机端口号 (这是个rtmp播放器或推流器) |
| peer_ip    | string  | false | none |     | 客户端ip                  |
| peer_port  | integer | false | none |     | 客户端端口号                 |
| typeid     | string  | false | none |     | 客户端TCPSession typeid   |

<h2 id="tocS_StreamProxyItem">StreamProxyItem</h2>

<a id="schemastreamproxyitem"></a>
<a id="schema_StreamProxyItem"></a>
<a id="tocSstreamproxyitem"></a>
<a id="tocsstreamproxyitem"></a>

```json
{
  "vhost": "string",
  "app": "string",
  "stream": "string",
  "url": "string",
  "retry_count": 0,
  "rtp_type": 0,
  "timeout_sec": 0,
  "enable_hls": true,
  "enable_hls_fmp4": true,
  "enable_mp4": true,
  "enable_rtsp": true,
  "enable_rtmp": true,
  "enable_ts": true,
  "enable_fmp4": true,
  "hls_demand": true,
  "rtsp_demand": true,
  "rtmp_demand": true,
  "ts_demand": true,
  "fmp4_demand": true,
  "enable_audio": true,
  "add_mute_audio": true,
  "mp4_save_path": "string",
  "mp4_max_second": 0,
  "mp4_as_player": true,
  "hls_save_path": "string",
  "modify_stamp": 0,
  "auto_close": true
}

```

### 属性

| 名称              | 类型      | 必选    | 约束   | 中文名 | 说明                                              |
|-----------------|---------|-------|------|-----|-------------------------------------------------|
| vhost           | string  | false | none |     | 添加的流的虚拟主机，例如__defaultVhost__                    |
| app             | string  | false | none |     | 添加的流的应用名，例如live                                 |
| stream          | string  | false | none |     | 添加的流的id名，例如test                                 |
| url             | string  | false | none |     | 拉流地址，例如rtmp://live.hkstv.hk.lxdns.com/live/hks2 |
| retry_count     | integer | false | none |     | 拉流重试次数，默认为-1无限重试                                |
| rtp_type        | integer | false | none |     | rtsp拉流时，拉流方式，0：tcp，1：udp，2：组播                   |
| timeout_sec     | integer | false | none |     | 拉流超时时间，单位秒，float类型                              |
| enable_hls      | boolean | false | none |     | 是否转换成hls-mpegts协议                               |
| enable_hls_fmp4 | boolean | false | none |     | 是否转换成hls-fmp4协议                                 |
| enable_mp4      | boolean | false | none |     | 是否允许mp4录制                                       |
| enable_rtsp     | boolean | false | none |     | 是否转rtsp协议                                       |
| enable_rtmp     | boolean | false | none |     | 是否转rtmp/flv协议                                   |
| enable_ts       | boolean | false | none |     | 是否转http-ts/ws-ts协议                              |
| enable_fmp4     | boolean | false | none |     | 是否转http-fmp4/ws-fmp4协议                          |
| hls_demand      | boolean | false | none |     | 该协议是否有人观看才生成                                    |
| rtsp_demand     | boolean | false | none |     | 该协议是否有人观看才生成                                    |
| rtmp_demand     | boolean | false | none |     | 该协议是否有人观看才生成                                    |
| ts_demand       | boolean | false | none |     | 该协议是否有人观看才生成                                    |
| fmp4_demand     | boolean | false | none |     | 该协议是否有人观看才生成                                    |
| enable_audio    | boolean | false | none |     | 转协议时是否开启音频                                      |
| add_mute_audio  | boolean | false | none |     | 转协议时，无音频是否添加静音aac音频                             |
| mp4_save_path   | string  | false | none |     | mp4录制文件保存根目录，置空使用默认                             |
| mp4_max_second  | integer | false | none |     | mp4录制切片大小，单位秒                                   |
| mp4_as_player   | boolean | false | none |     | MP4录制是否当作观看者参与播放人数计数                            |
| hls_save_path   | string  | false | none |     | hls文件保存保存根目录，置空使用默认                             |
| modify_stamp    | integer | false | none |     | 该流是否开启时间戳覆盖(0:绝对时间戳/1:系统时间戳/2:相对时间戳)            |
| auto_close      | boolean | false | none |     | 无人观看是否自动关闭流(不触发无人观看hook)                        |

<h2 id="tocS_ServerResponseStreamKey">ServerResponseStreamKey</h2>

<a id="schemaserverresponsestreamkey"></a>
<a id="schema_ServerResponseStreamKey"></a>
<a id="tocSserverresponsestreamkey"></a>
<a id="tocsserverresponsestreamkey"></a>

```json
{
  "code": 0,
  "data": {
    "key": "string"
  },
  "msg": "success",
  "result": "success"
}

```

### 属性

| 名称     | 类型                            | 必选    | 约束   | 中文名 | 说明   |
|--------|-------------------------------|-------|------|-----|------|
| code   | integer                       | false | none |     | none |
| data   | [StreamKey](#schemastreamkey) | false | none |     | none |
| msg    | string                        | false | none |     | none |
| result | string                        | false | none |     | none |

<h2 id="tocS_StreamKey">StreamKey</h2>

<a id="schemastreamkey"></a>
<a id="schema_StreamKey"></a>
<a id="tocSstreamkey"></a>
<a id="tocsstreamkey"></a>

```json
{
  "key": "string"
}

```

### 属性

| 名称  | 类型     | 必选    | 约束   | 中文名 | 说明   |
|-----|--------|-------|------|-----|------|
| key | string | false | none |     | none |

<h2 id="tocS_ServerResponseStringDelFlag">ServerResponseStringDelFlag</h2>

<a id="schemaserverresponsestringdelflag"></a>
<a id="schema_ServerResponseStringDelFlag"></a>
<a id="tocSserverresponsestringdelflag"></a>
<a id="tocsserverresponsestringdelflag"></a>

```json
{
  "code": 0,
  "data": {
    "flag": "string"
  },
  "msg": "success",
  "result": "success"
}

```

### 属性

| 名称     | 类型                                    | 必选    | 约束   | 中文名 | 说明   |
|--------|---------------------------------------|-------|------|-----|------|
| code   | integer                               | false | none |     | none |
| data   | [StringDelFlag](#schemastringdelflag) | false | none |     | none |
| msg    | string                                | false | none |     | none |
| result | string                                | false | none |     | none |

<h2 id="tocS_StringDelFlag">StringDelFlag</h2>

<a id="schemastringdelflag"></a>
<a id="schema_StringDelFlag"></a>
<a id="tocSstringdelflag"></a>
<a id="tocsstringdelflag"></a>

```json
{
  "flag": "string"
}

```

### 属性

| 名称   | 类型     | 必选    | 约束   | 中文名 | 说明   |
|------|--------|-------|------|-----|------|
| flag | string | false | none |     | none |

<h2 id="tocS_StreamPusherItem">StreamPusherItem</h2>

<a id="schemastreampusheritem"></a>
<a id="schema_StreamPusherItem"></a>
<a id="tocSstreampusheritem"></a>
<a id="tocsstreampusheritem"></a>

```json
{
  "vhost": "string",
  "schema": "string",
  "app": "string",
  "stream": "string",
  "dst_url": "string",
  "retry_count": 0,
  "rtp_type": 0,
  "timeout_sec": 0
}

```

### 属性

| 名称          | 类型      | 必选    | 约束   | 中文名 | 说明                           |
|-------------|---------|-------|------|-----|------------------------------|
| vhost       | string  | false | none |     | 添加的流的虚拟主机，例如__defaultVhost__ |
| schema      | string  | false | none |     | 协议，例如 rtsp或rtmp              |
| app         | string  | false | none |     | 添加的流的应用名，例如live              |
| stream      | string  | false | none |     | 需要转推的流id                     |
| dst_url     | string  | false | none |     | 目标转推url，带参数需要自行url转义         |
| retry_count | integer | false | none |     | 转推失败重试次数，默认无限重试              |
| rtp_type    | integer | false | none |     | rtsp推流时，推流方式，0：tcp，1：udp     |
| timeout_sec | integer | false | none |     | 推流超时时间，单位秒，float类型           |

<h2 id="tocS_StreamFfmpegItem">StreamFfmpegItem</h2>

<a id="schemastreamffmpegitem"></a>
<a id="schema_StreamFfmpegItem"></a>
<a id="tocSstreamffmpegitem"></a>
<a id="tocsstreamffmpegitem"></a>

```json
{
  "src_url": "string",
  "dst_url": "string",
  "timeout_ms": 0,
  "enable_hls": true,
  "enable_mp4": true,
  "ffmpeg_cmd_key": "string"
}

```

### 属性

| 名称             | 类型      | 必选    | 约束   | 中文名 | 说明                                                                  |
|----------------|---------|-------|------|-----|---------------------------------------------------------------------|
| src_url        | string  | false | none |     | FFmpeg拉流地址,支持任意协议或格式(只要FFmpeg支持即可)                                  |
| dst_url        | string  | false | none |     | FFmpeg rtmp推流地址，一般都是推给自己，例如rtmp://127.0.0.1/live/stream_form_ffmpeg |
| timeout_ms     | integer | false | none |     | FFmpeg推流成功超时时间                                                      |
| enable_hls     | boolean | false | none |     | 是否开启hls录制                                                           |
| enable_mp4     | boolean | false | none |     | 是否开启mp4录制                                                           |
| ffmpeg_cmd_key | string  | false | none |     | 配置文件中FFmpeg命令参数模板key(非内容)，置空则采用默认模板:ffmpeg.cmd                      |

<h2 id="tocS_RecordReq">RecordReq</h2>

<a id="schemarecordreq"></a>
<a id="schema_RecordReq"></a>
<a id="tocSrecordreq"></a>
<a id="tocsrecordreq"></a>

```json
{
  "schema": "rtsp",
  "vhost": "__defaultVhost__",
  "app": "string",
  "stream": "string",
  "period": "string",
  "customized_path": "string",
  "max_seconds": "string",
  "type": 0,
  "speed": "string",
  "stamp": "string"
}

```

### 属性

| 名称              | 类型      | 必选    | 约束   | 中文名 | 说明                                                            |
|-----------------|---------|-------|------|-----|---------------------------------------------------------------|
| schema          | string  | false | none |     | 筛选协议，例如 rtsp或rtmp                                             |
| vhost           | string  | false | none |     | 筛选虚拟主机，例如__defaultVhost__                                     |
| app             | string  | false | none |     | 筛选应用名，例如 live                                                 |
| stream          | string  | false | none |     | 筛选流id，例如 test                                                 |
| period          | string  | false | none |     | 流的录像日期，格式为2020-02-01,如果不是完整的日期，那么是搜索录像文件夹列表，否则搜索对应日期下的mp4文件列表 |
| customized_path | string  | false | none |     | 自定义搜索路径，与startRecord方法中的customized_path一样，默认为配置文件的路径          |
| max_seconds     | string  | false | none |     | mp4录像切片时间大小,单位秒，置0则采用配置项                                      |
| type            | integer | false | none |     | 0为hls，1为mp4                                                   |
| speed           | string  | false | none |     | 要设置的录像倍速 eg.2.0                                               |
| stamp           | string  | false | none |     | 要设置的录像播放位置                                                    |

<h2 id="tocS_ServerResponseMp4RecordFile">ServerResponseMp4RecordFile</h2>

<a id="schemaserverresponsemp4recordfile"></a>
<a id="schema_ServerResponseMp4RecordFile"></a>
<a id="tocSserverresponsemp4recordfile"></a>
<a id="tocsserverresponsemp4recordfile"></a>

```json
{
  "code": 0,
  "data": {
    "paths": [
      "string"
    ],
    "rootPath": "string"
  },
  "msg": "success",
  "result": "success"
}

```

### 属性

| 名称     | 类型                                    | 必选    | 约束   | 中文名 | 说明   |
|--------|---------------------------------------|-------|------|-----|------|
| code   | integer                               | false | none |     | none |
| data   | [Mp4RecordFile](#schemamp4recordfile) | false | none |     | none |
| msg    | string                                | false | none |     | none |
| result | string                                | false | none |     | none |

<h2 id="tocS_Mp4RecordFile">Mp4RecordFile</h2>

<a id="schemamp4recordfile"></a>
<a id="schema_Mp4RecordFile"></a>
<a id="tocSmp4recordfile"></a>
<a id="tocsmp4recordfile"></a>

```json
{
  "paths": [
    "string"
  ],
  "rootPath": "string"
}

```

### 属性

| 名称       | 类型       | 必选    | 约束   | 中文名 | 说明   |
|----------|----------|-------|------|-----|------|
| paths    | [string] | false | none |     | none |
| rootPath | string   | false | none |     | none |

<h2 id="tocS_DeleteRecordDirectory">DeleteRecordDirectory</h2>

<a id="schemadeleterecorddirectory"></a>
<a id="schema_DeleteRecordDirectory"></a>
<a id="tocSdeleterecorddirectory"></a>
<a id="tocsdeleterecorddirectory"></a>

```json
{
  "code": 0,
  "data": "string",
  "msg": "success",
  "result": "success",
  "path": "string"
}

```

### 属性

| 名称     | 类型      | 必选    | 约束   | 中文名 | 说明   |
|--------|---------|-------|------|-----|------|
| code   | integer | false | none |     | none |
| data   | string  | false | none |     | none |
| msg    | string  | false | none |     | none |
| result | string  | false | none |     | none |
| path   | string  | false | none |     | none |

<h2 id="tocS_SnapshotReq">SnapshotReq</h2>

<a id="schemasnapshotreq"></a>
<a id="schema_SnapshotReq"></a>
<a id="tocSsnapshotreq"></a>
<a id="tocssnapshotreq"></a>

```json
{
  "url": "string",
  "timeout_sec": 30,
  "expire_sec": 5
}

```

### 属性

| 名称          | 类型      | 必选    | 约束   | 中文名 | 说明                         |
|-------------|---------|-------|------|-----|----------------------------|
| url         | string  | false | none |     | 需要截图的url，可以是本机的，也可以是远程主机的。 |
| timeout_sec | integer | false | none |     | 截图失败超时时间，防止FFmpeg一直等待截图。   |
| expire_sec  | integer | false | none |     | 截图的过期时间，该时间内产生的截图都会作为缓存返回。 |

<h2 id="tocS_RtpInfoResult">RtpInfoResult</h2>

<a id="schemartpinforesult"></a>
<a id="schema_RtpInfoResult"></a>
<a id="tocSrtpinforesult"></a>
<a id="tocsrtpinforesult"></a>

```json
{
  "code": 0,
  "exist": true,
  "peer_ip": "string",
  "peer_port": 0,
  "local_ip": "string",
  "local_port": 0
}

```

### 属性

| 名称         | 类型      | 必选    | 约束   | 中文名 | 说明         |
|------------|---------|-------|------|-----|------------|
| code       | integer | false | none |     | 状态码。       |
| exist      | boolean | false | none |     | 会话是否存在。    |
| peer_ip    | string  | false | none |     | 推流客户端ip。   |
| peer_port  | integer | false | none |     | 客户端端口号。    |
| local_ip   | string  | false | none |     | 本地监听的网卡ip。 |
| local_port | integer | false | none |     | 本地端口号。     |

<h2 id="tocS_OpenRtpServerReq">OpenRtpServerReq</h2>

<a id="schemaopenrtpserverreq"></a>
<a id="schema_OpenRtpServerReq"></a>
<a id="tocSopenrtpserverreq"></a>
<a id="tocsopenrtpserverreq"></a>

```json
{
  "port": 0,
  "tcp_mode": 0,
  "stream_id": "string"
}

```

### 属性

| 名称        | 类型      | 必选    | 约束   | 中文名 | 说明                                                    |
|-----------|---------|-------|------|-----|-------------------------------------------------------|
| port      | integer | false | none |     | 接收端口，0则为随机端口。                                         |
| tcp_mode  | integer | false | none |     | 0 udp 模式，1 tcp 被动模式, 2 tcp 主动模式。 (兼容enable_tcp 为0/1)。 |
| stream_id | string  | false | none |     | 该端口绑定的流ID，该端口只能创建这一个流(而不是根据ssrc创建多个)。                 |

<h2 id="tocS_OpenRtpServerResult">OpenRtpServerResult</h2>

<a id="schemaopenrtpserverresult"></a>
<a id="schema_OpenRtpServerResult"></a>
<a id="tocSopenrtpserverresult"></a>
<a id="tocsopenrtpserverresult"></a>

```json
{
  "port": "string",
  "code": "string"
}

```

### 属性

| 名称   | 类型     | 必选    | 约束   | 中文名 | 说明   |
|------|--------|-------|------|-----|------|
| port | string | false | none |     | none |
| code | string | false | none |     | none |

<h2 id="tocS_ConnectRtpServerReq">ConnectRtpServerReq</h2>

<a id="schemaconnectrtpserverreq"></a>
<a id="schema_ConnectRtpServerReq"></a>
<a id="tocSconnectrtpserverreq"></a>
<a id="tocsconnectrtpserverreq"></a>

```json
{
  "dst_port": 0,
  "dst_url": 0,
  "stream_id": "string"
}

```

### 属性

| 名称        | 类型      | 必选    | 约束   | 中文名 | 说明                                    |
|-----------|---------|-------|------|-----|---------------------------------------|
| dst_port  | integer | false | none |     | tcp主动模式时服务端端口                         |
| dst_url   | integer | false | none |     | tcp主动模式时服务端地址                         |
| stream_id | string  | false | none |     | 该端口绑定的流ID，该端口只能创建这一个流(而不是根据ssrc创建多个)。 |

<h2 id="tocS_CloseRtpServerResult">CloseRtpServerResult</h2>

<a id="schemaclosertpserverresult"></a>
<a id="schema_CloseRtpServerResult"></a>
<a id="tocSclosertpserverresult"></a>
<a id="tocsclosertpserverresult"></a>

```json
{
  "hit": "string",
  "code": "string"
}

```

### 属性

| 名称   | 类型     | 必选    | 约束   | 中文名 | 说明        |
|------|--------|-------|------|-----|-----------|
| hit  | string | false | none |     | 是否找到记录并关闭 |
| code | string | false | none |     | none      |

<h2 id="tocS_RtpServer">RtpServer</h2>

<a id="schemartpserver"></a>
<a id="schema_RtpServer"></a>
<a id="tocSrtpserver"></a>
<a id="tocsrtpserver"></a>

```json
{
  "port": "string",
  "streamId": "string"
}

```

### 属性

| 名称       | 类型     | 必选    | 约束   | 中文名 | 说明   |
|----------|--------|-------|------|-----|------|
| port     | string | false | none |     | none |
| streamId | string | false | none |     | none |

<h2 id="tocS_ServerResponseListRtpServer">ServerResponseListRtpServer</h2>

<a id="schemaserverresponselistrtpserver"></a>
<a id="schema_ServerResponseListRtpServer"></a>
<a id="tocSserverresponselistrtpserver"></a>
<a id="tocsserverresponselistrtpserver"></a>

```json
{
  "code": 0,
  "data": [
    {
      "port": "string",
      "streamId": "string"
    }
  ],
  "msg": "success",
  "result": "success"
}

```

### 属性

| 名称     | 类型                              | 必选    | 约束   | 中文名 | 说明   |
|--------|---------------------------------|-------|------|-----|------|
| code   | integer                         | false | none |     | none |
| data   | [[RtpServer](#schemartpserver)] | false | none |     | none |
| msg    | string                          | false | none |     | none |
| result | string                          | false | none |     | none |

<h2 id="tocS_StartSendRtpReq">StartSendRtpReq</h2>

<a id="schemastartsendrtpreq"></a>
<a id="schema_StartSendRtpReq"></a>
<a id="tocSstartsendrtpreq"></a>
<a id="tocsstartsendrtpreq"></a>

```json
{
  "schema": "rtsp",
  "vhost": "__defaultVhost__",
  "app": "string",
  "stream": "string",
  "ssrc": 0,
  "dst_url": "string",
  "dst_port": 0,
  "is_udp": true,
  "src_port": 0,
  "pt": 0,
  "use_ps": 0,
  "only_audio": true
}

```

### 属性

| 名称         | 类型      | 必选    | 约束   | 中文名 | 说明                                        |
|------------|---------|-------|------|-----|-------------------------------------------|
| schema     | string  | false | none |     | 筛选协议，例如 rtsp或rtmp                         |
| vhost      | string  | false | none |     | 筛选虚拟主机，例如__defaultVhost__                 |
| app        | string  | false | none |     | 筛选应用名，例如 live                             |
| stream     | string  | false | none |     | 筛选流id，例如 test                             |
| ssrc       | integer | false | none |     | 推流的rtp的ssrc,指定不同的ssrc可以同时推流到多个服务器。        |
| dst_url    | string  | false | none |     | 目标ip或域名。                                  |
| dst_port   | integer | false | none |     | 目标端口。                                     |
| is_udp     | boolean | false | none |     | 是否为udp模式,否则为tcp模式。                        |
| src_port   | integer | false | none |     | 使用的本机端口，为0或不传时默认为随机端口。                    |
| pt         | integer | false | none |     | 发送时，rtp的pt（uint8_t）,不传时默认为96。             |
| use_ps     | integer | false | none |     | 发送时，rtp的负载类型。为1时，负载为ps；为0时，为es；不传时默认为1。   |
| only_audio | boolean | false | none |     | 当use_ps 为0时，有效。为1时，发送音频；为0时，发送视频；不传时默认为0。 |

<h2 id="tocS_StartSendRtpResult">StartSendRtpResult</h2>

<a id="schemastartsendrtpresult"></a>
<a id="schema_StartSendRtpResult"></a>
<a id="tocSstartsendrtpresult"></a>
<a id="tocsstartsendrtpresult"></a>

```json
{
  "code": "string",
  "local_port": "string"
}

```

### 属性

| 名称         | 类型     | 必选    | 约束   | 中文名 | 说明   |
|------------|--------|-------|------|-----|------|
| code       | string | false | none |     | none |
| local_port | string | false | none |     | none |

<h2 id="tocS_CloseSendRtpReq">CloseSendRtpReq</h2>

<a id="schemaclosesendrtpreq"></a>
<a id="schema_CloseSendRtpReq"></a>
<a id="tocSclosesendrtpreq"></a>
<a id="tocsclosesendrtpreq"></a>

```json
{
  "schema": "rtsp",
  "vhost": "__defaultVhost__",
  "app": "string",
  "stream": "string",
  "ssrc": "string"
}

```

### 属性

| 名称     | 类型     | 必选    | 约束   | 中文名 | 说明                        |
|--------|--------|-------|------|-----|---------------------------|
| schema | string | false | none |     | 筛选协议，例如 rtsp或rtmp         |
| vhost  | string | false | none |     | 筛选虚拟主机，例如__defaultVhost__ |
| app    | string | false | none |     | 筛选应用名，例如 live             |
| stream | string | false | none |     | 筛选流id，例如 test             |
| ssrc   | string | false | none |     | 停止GB28181 ps-rtp推流        |

<h2 id="tocS_ZlmNode">ZlmNode</h2>

<a id="schemazlmnode"></a>
<a id="schema_ZlmNode"></a>
<a id="tocSzlmnode"></a>
<a id="tocszlmnode"></a>

```json
{
  "serverId": "zlm",
  "host": "http://127.0.0.1:9092",
  "secret": "string",
  "enabled": true,
  "hookEnabled": true,
  "weight": 100
}

```

### 属性

| 名称          | 类型      | 必选    | 约束   | 中文名 | 说明                                                                  |
|-------------|---------|-------|------|-----|---------------------------------------------------------------------|
| serverId    | string  | false | none |     | The id of this node.                                                |
| host        | string  | false | none |     | The host of this node. eg: <a href="http://127.0.0.1:9092">node</a> |
| secret      | string  | false | none |     | The secret of this host.                                            |
| enabled     | boolean | false | none |     | Whether enable this host.                                           |
| hookEnabled | boolean | false | none |     | Whether enable hook.                                                |
| weight      | integer | false | none |     | The weight of this host.                                            |

