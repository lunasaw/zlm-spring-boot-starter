---
title: WD-ZLM v1.0.0
language_tabs:
  - shell: Shell
  - http: HTTP
  - javascript: JavaScript
  - ruby: Ruby
  - python: Python
  - php: PHP
  - java: Java
  - go: Go
toc_footers: [ ]
includes: [ ]
search: true
code_clipboard: true
highlight_theme: darkula
headingLevel: 2
generator: "@tarslib/widdershins v4.0.17"

---

# WD-ZLM

> v1.0.0

Base URLs:

* <a href="http://127.0.0.1:9092">开发环境: http://127.0.0.1:9092</a>

# Default

## GET 获取服务器api列表(getApiList)

GET /index/api/getApiList

### 请求参数

| 名称     | 位置    | 类型     | 必选 | 说明              |
|--------|-------|--------|----|-----------------|
| secret | query | string | 是  | api操作密钥(配置文件配置) |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 获取网络线程负载(getThreadsLoad)

GET /index/api/getThreadsLoad

### 请求参数

| 名称     | 位置    | 类型     | 必选 | 说明              |
|--------|-------|--------|----|-----------------|
| secret | query | string | 是  | api操作密钥(配置文件配置) |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 获取主要对象个数(getStatistic)

GET /index/api/getStatistic

### 请求参数

| 名称     | 位置    | 类型     | 必选 | 说明              |
|--------|-------|--------|----|-----------------|
| secret | query | string | 是  | api操作密钥(配置文件配置) |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 获取后台线程负载(getWorkThreadsLoad)

GET /index/api/getWorkThreadsLoad

### 请求参数

| 名称     | 位置    | 类型     | 必选 | 说明              |
|--------|-------|--------|----|-----------------|
| secret | query | string | 是  | api操作密钥(配置文件配置) |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 获取服务器配置(getServerConfig)

GET /index/api/getServerConfig

### 请求参数

| 名称     | 位置    | 类型     | 必选 | 说明              |
|--------|-------|--------|----|-----------------|
| secret | query | string | 是  | api操作密钥(配置文件配置) |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 设置服务器配置(setServerConfig)

GET /index/api/setServerConfig

### 请求参数

| 名称           | 位置    | 类型     | 必选 | 说明              |
|--------------|-------|--------|----|-----------------|
| secret       | query | string | 是  | api操作密钥(配置文件配置) |
| api.apiDebug | query | string | 是  | 配置键与配置项值        |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 重启服务器(restartServer)

GET /index/api/restartServer

### 请求参数

| 名称     | 位置    | 类型     | 必选 | 说明              |
|--------|-------|--------|----|-----------------|
| secret | query | string | 是  | api操作密钥(配置文件配置) |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 获取流列表(getMediaList)

GET /index/api/getMediaList

### 请求参数

| 名称     | 位置    | 类型     | 必选 | 说明              |
|--------|-------|--------|----|-----------------|
| secret | query | string | 是  | api操作密钥(配置文件配置) |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 关断单个流(close_stream)

GET /index/api/close_stream

### 请求参数

| 名称     | 位置    | 类型     | 必选 | 说明                      |
|--------|-------|--------|----|-------------------------|
| secret | query | string | 是  | api操作密钥(配置文件配置)         |
| schema | query | string | 是  | 协议，例如 rtsp或rtmp         |
| vhost  | query | string | 是  | 虚拟主机，例如__defaultVhost__ |
| app    | query | string | 是  | 应用名，例如 live             |
| stream | query | string | 是  | 流id，例如 test             |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 批量关断流(close_streams)

GET /index/api/close_streams

### 请求参数

| 名称     | 位置    | 类型     | 必选 | 说明                      |
|--------|-------|--------|----|-------------------------|
| secret | query | string | 是  | api操作密钥(配置文件配置)         |
| schema | query | string | 是  | 协议，例如 rtsp或rtmp         |
| vhost  | query | string | 是  | 虚拟主机，例如__defaultVhost__ |
| app    | query | string | 是  | 应用名，例如 live             |
| stream | query | string | 是  | 流id，例如 test             |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 获取Session列表(getAllSession)

GET /index/api/getAllSession

### 请求参数

| 名称     | 位置    | 类型     | 必选 | 说明              |
|--------|-------|--------|----|-----------------|
| secret | query | string | 是  | api操作密钥(配置文件配置) |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 断开tcp连接(kick_session)

GET /index/api/kick_session

### 请求参数

| 名称     | 位置    | 类型     | 必选 | 说明                            |
|--------|-------|--------|----|-------------------------------|
| secret | query | string | 是  | api操作密钥(配置文件配置)               |
| id     | query | string | 是  | 客户端唯一id，可以通过getAllSession接口获取 |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 批量断开tcp连接(kick_sessions)

GET /index/api/kick_sessions

### 请求参数

| 名称     | 位置    | 类型     | 必选 | 说明              |
|--------|-------|--------|----|-----------------|
| secret | query | string | 是  | api操作密钥(配置文件配置) |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 添加rtsp/rtmp/hls拉流代理(addStreamProxy)

GET /index/api/addStreamProxy

### 请求参数

| 名称     | 位置    | 类型     | 必选 | 说明                                              |
|--------|-------|--------|----|-------------------------------------------------|
| secret | query | string | 是  | api操作密钥(配置文件配置)                                 |
| vhost  | query | string | 是  | 添加的流的虚拟主机，例如__defaultVhost__                    |
| app    | query | string | 是  | 添加的流的应用名，例如live                                 |
| stream | query | string | 是  | 添加的流的id名，例如test                                 |
| url    | query | string | 是  | 拉流地址，例如rtmp://live.hkstv.hk.lxdns.com/live/hks2 |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 关闭拉流代理(delStreamProxy)

GET /index/api/delStreamProxy

### 请求参数

| 名称     | 位置    | 类型     | 必选 | 说明                     |
|--------|-------|--------|----|------------------------|
| secret | query | string | 是  | api操作密钥(配置文件配置)        |
| key    | query | string | 是  | addStreamProxy接口返回的key |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 添加rtsp/rtmp推流(addStreamPusherProxy)

GET /index/api/addStreamPusherProxy

### 请求参数

| 名称      | 位置    | 类型     | 必选 | 说明                            |
|---------|-------|--------|----|-------------------------------|
| secret  | query | string | 是  | api操作密钥(配置文件配置)               |
| schema  | query | string | 是  | 推流协议，支持rtsp、rtmp，大小写敏感        |
| vhost   | query | string | 是  | 已注册流的虚拟主机，一般为__defaultVhost__ |
| app     | query | string | 是  | 已注册流的应用名，例如live               |
| stream  | query | string | 是  | 已注册流的id名，例如test               |
| dst_url | query | string | 是  | 推流地址，需要与schema字段协议一致          |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 关闭推流(delStreamPusherProxy)

GET /index/api/delStreamPusherProxy

### 请求参数

| 名称     | 位置    | 类型     | 必选 | 说明                           |
|--------|-------|--------|----|------------------------------|
| secret | query | string | 是  | api操作密钥(配置文件配置)              |
| key    | query | string | 是  | addStreamPusherProxy接口返回的key |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 添加FFmpeg拉流代理(addFFmpegSource)

GET /index/api/addFFmpegSource

### 请求参数

| 名称         | 位置    | 类型     | 必选 | 说明                                                                  |
|------------|-------|--------|----|---------------------------------------------------------------------|
| secret     | query | string | 是  | api操作密钥(配置文件配置)                                                     |
| src_url    | query | string | 是  | FFmpeg拉流地址,支持任意协议或格式(只要FFmpeg支持即可)                                  |
| dst_url    | query | string | 是  | FFmpeg rtmp推流地址，一般都是推给自己，例如rtmp://127.0.0.1/live/stream_form_ffmpeg |
| timeout_ms | query | string | 是  | FFmpeg推流成功超时时间,单位毫秒                                                 |
| enable_hls | query | string | 是  | 是否开启hls录制                                                           |
| enable_mp4 | query | string | 是  | 是否开启mp4录制                                                           |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 关闭FFmpeg拉流代理(delFFmpegSource)

GET /index/api/delFFmpegSource

### 请求参数

| 名称     | 位置    | 类型     | 必选 | 说明              |
|--------|-------|--------|----|-----------------|
| secret | query | string | 是  | api操作密钥(配置文件配置) |
| key    | query | string | 是  | none            |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 流是否在线(isMediaOnline)

GET /index/api/isMediaOnline

### 请求参数

| 名称     | 位置    | 类型     | 必选 | 说明                      |
|--------|-------|--------|----|-------------------------|
| secret | query | string | 是  | api操作密钥(配置文件配置)         |
| schema | query | string | 是  | 协议，例如 rtsp或rtmp         |
| vhost  | query | string | 是  | 虚拟主机，例如__defaultVhost__ |
| app    | query | string | 是  | 应用名，例如 live             |
| stream | query | string | 是  | 流id，例如 test             |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 获取媒体流播放器列表(getMediaPlayerList)

GET /index/api/getMediaPlayerList

### 请求参数

| 名称     | 位置    | 类型     | 必选 | 说明                      |
|--------|-------|--------|----|-------------------------|
| secret | query | string | 是  | api操作密钥(配置文件配置)         |
| schema | query | string | 是  | 协议，例如 rtsp或rtmp         |
| vhost  | query | string | 是  | 虚拟主机，例如__defaultVhost__ |
| app    | query | string | 是  | 应用名，例如 live             |
| stream | query | string | 是  | 流id，例如 test             |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 广播webrtc datachannel消息(broadcastMessage)

GET /index/api/broadcastMessage

### 请求参数

| 名称     | 位置    | 类型     | 必选 | 说明                          |
|--------|-------|--------|----|-----------------------------|
| secret | query | string | 是  | api操作密钥(配置文件配置)             |
| schema | query | string | 是  | 协议，例如 rtsp或rtmp，目前仅支持rtsp协议 |
| vhost  | query | string | 是  | 虚拟主机，例如__defaultVhost__     |
| app    | query | string | 是  | 应用名，例如 live                 |
| stream | query | string | 是  | 流id，例如 test                 |
| msg    | query | string | 是  | none                        |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 获取流信息(getMediaInfo)

GET /index/api/getMediaInfo

### 请求参数

| 名称     | 位置    | 类型     | 必选 | 说明                      |
|--------|-------|--------|----|-------------------------|
| secret | query | string | 是  | api操作密钥(配置文件配置)         |
| schema | query | string | 是  | 协议，例如 rtsp或rtmp         |
| vhost  | query | string | 是  | 虚拟主机，例如__defaultVhost__ |
| app    | query | string | 是  | 应用名，例如 live             |
| stream | query | string | 是  | 流id，例如 test             |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 获取流信息(getMp4RecordFile)

GET /index/api/getMp4RecordFile

### 请求参数

| 名称              | 位置    | 类型     | 必选 | 说明                                                            |
|-----------------|-------|--------|----|---------------------------------------------------------------|
| secret          | query | string | 是  | api操作密钥(配置文件配置)                                               |
| vhost           | query | string | 是  | 虚拟主机，例如__defaultVhost__                                       |
| app             | query | string | 是  | 应用名，例如 live                                                   |
| stream          | query | string | 是  | 流id，例如 test                                                   |
| customized_path | query | string | 是  | 录像文件保存自定义根目录，为空则采用配置文件设置                                      |
| period          | query | string | 是  | 流的录像日期，格式为2020-02-01,如果不是完整的日期，那么是搜索录像文件夹列表，否则搜索对应日期下的mp4文件列表 |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 删除录像文件夹(deleteRecordDirectory)

GET /index/api/deleteRecordDirectory

### 请求参数

| 名称     | 位置    | 类型     | 必选 | 说明                                     |
|--------|-------|--------|----|----------------------------------------|
| secret | query | string | 是  | api操作密钥(配置文件配置)                        |
| vhost  | query | string | 是  | 虚拟主机，例如__defaultVhost__                |
| app    | query | string | 是  | 应用名，例如 live                            |
| stream | query | string | 是  | 流id，例如 test                            |
| period | query | string | 是  | 流的录像日期，格式为2020-01-01,如果不是完整的日期，那么会删除失败 |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 开始录制(startRecord)

GET /index/api/startRecord

### 请求参数

| 名称     | 位置    | 类型     | 必选 | 说明                      |
|--------|-------|--------|----|-------------------------|
| secret | query | string | 是  | api操作密钥(配置文件配置)         |
| type   | query | string | 是  | 0为hls，1为mp4             |
| vhost  | query | string | 是  | 虚拟主机，例如__defaultVhost__ |
| app    | query | string | 是  | 应用名，例如 live             |
| stream | query | string | 是  | 流id，例如 obs              |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 设置录像速度(setRecordSpeed)

GET /index/api/setRecordSpeed

### 请求参数

| 名称     | 位置    | 类型     | 必选 | 说明                      |
|--------|-------|--------|----|-------------------------|
| secret | query | string | 是  | api操作密钥(配置文件配置)         |
| vhost  | query | string | 是  | 虚拟主机，例如__defaultVhost__ |
| app    | query | string | 是  | 应用名，例如 live             |
| stream | query | string | 是  | 流id，例如 obs              |
| speed  | query | string | 是  | 要设置的录像倍速                |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 设置录像流播放位置(seekRecordStamp)

GET /index/api/seekRecordStamp

### 请求参数

| 名称     | 位置    | 类型     | 必选 | 说明                      |
|--------|-------|--------|----|-------------------------|
| secret | query | string | 是  | api操作密钥(配置文件配置)         |
| vhost  | query | string | 是  | 虚拟主机，例如__defaultVhost__ |
| app    | query | string | 是  | 应用名，例如 live             |
| stream | query | string | 是  | 流id，例如 obs              |
| stamp  | query | string | 是  | 要设置的录像播放位置              |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 停止录制(stopRecord)

GET /index/api/stopRecord

### 请求参数

| 名称     | 位置    | 类型     | 必选 | 说明                      |
|--------|-------|--------|----|-------------------------|
| secret | query | string | 是  | api操作密钥(配置文件配置)         |
| type   | query | string | 是  | 0为hls，1为mp4             |
| vhost  | query | string | 是  | 虚拟主机，例如__defaultVhost__ |
| app    | query | string | 是  | 应用名，例如 live             |
| stream | query | string | 是  | 流id，例如 obs              |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 是否正在录制(isRecording)

GET /index/api/isRecording

### 请求参数

| 名称     | 位置    | 类型     | 必选 | 说明                      |
|--------|-------|--------|----|-------------------------|
| secret | query | string | 是  | api操作密钥(配置文件配置)         |
| type   | query | string | 是  | 0为hls，1为mp4             |
| vhost  | query | string | 是  | 虚拟主机，例如__defaultVhost__ |
| app    | query | string | 是  | 应用名，例如 live             |
| stream | query | string | 是  | 流id，例如 obs              |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 获取截图(getSnap)

GET /index/api/getSnap

### 请求参数

| 名称          | 位置    | 类型     | 必选 | 说明                        |
|-------------|-------|--------|----|---------------------------|
| secret      | query | string | 是  | api操作密钥(配置文件配置)           |
| url         | query | string | 是  | 需要截图的url，可以是本机的，也可以是远程主机的 |
| timeout_sec | query | string | 是  | 截图失败超时时间，防止FFmpeg一直等待截图   |
| expire_sec  | query | string | 是  | 截图的过期时间，该时间内产生的截图都会作为缓存返回 |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 获取rtp推流信息(getRtpInfo)

GET /index/api/getRtpInfo

### 请求参数

| 名称        | 位置    | 类型     | 必选 | 说明              |
|-----------|-------|--------|----|-----------------|
| secret    | query | string | 是  | api操作密钥(配置文件配置) |
| stream_id | query | string | 是  | 流id             |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 创建多路复用RTP服务器(openRtpServerMultiplex)

GET /index/api/openRtpServer

### 请求参数

| 名称        | 位置    | 类型     | 必选 | 说明                           |
|-----------|-------|--------|----|------------------------------|
| secret    | query | string | 是  | api操作密钥(配置文件配置)              |
| port      | query | string | 是  | 绑定的端口，0时为随机端口                |
| tcp_mode  | query | string | 是  | tcp模式，0时为不启用tcp监听，1时为启用tcp监听 |
| stream_id | query | string | 是  | 该端口绑定的流id                    |

#### 详细说明

**stream_id**: 该端口绑定的流id

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 连接RTP服务器(connectRtpServer)

GET /index/api/connectRtpServer

### 请求参数

| 名称        | 位置    | 类型     | 必选 | 说明                   |
|-----------|-------|--------|----|----------------------|
| secret    | query | string | 是  | api操作密钥(配置文件配置)      |
| dst_url   | query | string | 是  | tcp主动模式时服务端地址        |
| dst_port  | query | string | 是  | tcp主动模式时服务端端口        |
| stream_id | query | string | 是  | OpenRtpServer时绑定的流id |

#### 详细说明

**stream_id**: OpenRtpServer时绑定的流id

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 关闭RTP服务器(closeRtpServer)

GET /index/api/closeRtpServer

### 请求参数

| 名称        | 位置    | 类型     | 必选 | 说明              |
|-----------|-------|--------|----|-----------------|
| secret    | query | string | 是  | api操作密钥(配置文件配置) |
| stream_id | query | string | 是  | 该端口绑定的流id       |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 更新RTP服务器过滤SSRC(updateRtpServerSSRC)

GET /index/api/updateRtpServerSSRC

### 请求参数

| 名称        | 位置    | 类型     | 必选 | 说明              |
|-----------|-------|--------|----|-----------------|
| secret    | query | string | 是  | api操作密钥(配置文件配置) |
| stream_id | query | string | 是  | 该端口绑定的流id       |
| ssrc      | query | string | 是  | 十进制ssrc         |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 暂停RTP超时检查(pauseRtpCheck)

GET /index/api/pauseRtpCheck

### 请求参数

| 名称        | 位置    | 类型     | 必选 | 说明              |
|-----------|-------|--------|----|-----------------|
| secret    | query | string | 是  | api操作密钥(配置文件配置) |
| stream_id | query | string | 是  | 该端口绑定的流id       |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 恢复RTP超时检查(resumeRtpCheck)

GET /index/api/resumeRtpCheck

### 请求参数

| 名称        | 位置    | 类型     | 必选 | 说明              |
|-----------|-------|--------|----|-----------------|
| secret    | query | string | 是  | api操作密钥(配置文件配置) |
| stream_id | query | string | 是  | 该端口绑定的流id       |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 获取RTP服务器列表(listRtpServer)

GET /index/api/listRtpServer

### 请求参数

| 名称     | 位置    | 类型     | 必选 | 说明              |
|--------|-------|--------|----|-----------------|
| secret | query | string | 是  | api操作密钥(配置文件配置) |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 开始发送rtp(startSendRtp)

GET /index/api/startSendRtp

### 请求参数

| 名称       | 位置    | 类型     | 必选 | 说明                      |
|----------|-------|--------|----|-------------------------|
| secret   | query | string | 是  | api操作密钥(配置文件配置)         |
| vhost    | query | string | 是  | 虚拟主机，例如__defaultVhost__ |
| app      | query | string | 是  | 应用名，例如 live             |
| stream   | query | string | 是  | 流id，例如 obs              |
| ssrc     | query | string | 是  | rtp推流的ssrc              |
| dst_url  | query | string | 是  | 目标ip或域名                 |
| dst_port | query | string | 是  | 目标端口                    |
| is_udp   | query | string | 是  | 是否为udp模式,否则为tcp模式       |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 开始tcp passive被动发送rtp(startSendRtpPassive)

GET /index/api/startSendRtpPassive

### 请求参数

| 名称     | 位置    | 类型     | 必选 | 说明                              |
|--------|-------|--------|----|---------------------------------|
| secret | query | string | 是  | api操作密钥(配置文件配置)                 |
| vhost  | query | string | 是  | 虚拟主机，例如__defaultVhost__         |
| app    | query | string | 是  | 应用名，例如 live                     |
| stream | query | string | 是  | 流id，例如 obs                      |
| ssrc   | query | string | 是  | rtp推流的ssrc，ssrc不同时，可以推流到多个上级服务器 |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 停止 发送rtp(stopSendRtp)

GET /index/api/stopSendRtp

### 请求参数

| 名称     | 位置    | 类型     | 必选 | 说明                      |
|--------|-------|--------|----|-------------------------|
| secret | query | string | 是  | api操作密钥(配置文件配置)         |
| vhost  | query | string | 是  | 虚拟主机，例如__defaultVhost__ |
| app    | query | string | 是  | 应用名，例如 live             |
| stream | query | string | 是  | 流id，例如 obs              |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 获取版本信息

GET /index/api/version

### 请求参数

| 名称     | 位置    | 类型     | 必选 | 说明              |
|--------|-------|--------|----|-----------------|
| secret | query | string | 是  | api操作密钥(配置文件配置) |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 获取拉流代理信息(getProxyInfo)

GET /index/api/getProxyInfo

### 请求参数

| 名称     | 位置    | 类型     | 必选 | 说明   |
|--------|-------|--------|----|------|
| secret | query | string | 是  | none |
| key    | query | string | 是  | none |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 获取推流代理信息(getProxyPusherInfo)

GET /index/api/getProxyPusherInfo

### 请求参数

| 名称     | 位置    | 类型     | 必选 | 说明   |
|--------|-------|--------|----|------|
| secret | query | string | 是  | none |
| key    | query | string | 是  | none |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 多文件推流(startMultiMp4Publish)

GET /index/api/startMultiMp4Publish

### 请求参数

| 名称            | 位置    | 类型     | 必选 | 说明               |
|---------------|-------|--------|----|------------------|
| secret        | query | string | 是  | none             |
| app           | query | string | 否  | 添加的流的应用名，例如live  |
| stream        | query | string | 否  | 添加的流的id名，例如test	 |
| startTime     | query | string | 否  | 开始时间             |
| endTime       | query | string | 否  | 结束时间             |
| speed         | query | string | 否  | 倍速               |
| remoteAddress | query | string | 否  | 推流地址             |
| callId        | query | string | 否  | 全局唯一ID	          |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 关闭多文件推流(startMultiMp4Publish)

GET /index/api/stopMultiMp4Publish

### 请求参数

| 名称     | 位置    | 类型     | 必选 | 说明      |
|--------|-------|--------|----|---------|
| secret | query | string | 是  | none    |
| key    | query | string | 是  | none    |
| callId | query | string | 否  | 全局唯一ID	 |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 获取存储信息(getStorageSpace)

GET /index/api/getStorageSpace

### 请求参数

| 名称     | 位置    | 类型     | 必选 | 说明              |
|--------|-------|--------|----|-----------------|
| secret | query | string | 是  | api操作密钥(配置文件配置) |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 查询文件概览 getMp4RecordSummary

GET /index/api/getMp4RecordSummary

### 请求参数

| 名称     | 位置    | 类型     | 必选 | 说明              |
|--------|-------|--------|----|-----------------|
| secret | query | string | 是  | api操作密钥(配置文件配置) |
| app    | query | string | 否  | none            |
| vhost  | query | string | 否  | none            |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

## GET 点播mp4文件(loadMP4File)

GET /index/api/loadMP4File

### 请求参数

| 名称        | 位置    | 类型     | 必选 | 说明                           |
|-----------|-------|--------|----|------------------------------|
| secret    | query | string | 是  | api操作密钥(配置文件配置)              |
| vhost     | query | string | 是  | 添加的流的虚拟主机，例如__defaultVhost__ |
| app       | query | string | 是  | 添加的流的应用名，例如live              |
| stream    | query | string | 是  | 添加的流的id名，例如test              |
| file_path | query | string | 是  | mp4文件绝对路径                    |

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

| 状态码 | 状态码含义                                                   | 说明 | 数据模型   |
|-----|---------------------------------------------------------|----|--------|
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |

### 返回数据结构

# 数据模型

