# zlm-spring-boot-starter

[![Maven Central](https://img.shields.io/maven-central/v/io.github.lunasaw/zlm-spring-boot-starter)](https://mvnrepository.com/artifact/io.github.lunasaw/zlm-spring-boot-starter)
[![GitHub license](https://img.shields.io/badge/MIT_License-blue.svg)](https://raw.githubusercontent.com/lunasaw/zlm-spring-boot-starter/master/LICENSE)

[www.isluna.ml](http://lunasaw.github.io)

## 项目简介

ZLMediaKit的Spring Boot Starter，是一个针对[ZLMediaKit](https://github.com/ZLMediaKit/ZLMediaKit)
流媒体服务器的Java集成组件。本项目对ZLMediaKit的REST
API进行了完整封装，并提供了Hook事件处理机制，支持集群化管理和多种负载均衡策略，让Java开发者能够轻松集成和管理ZLMediaKit流媒体服务器。

[文档链接](https://github.com/lunasaw/zlm-spring-boot-starter/blob/master/zlm-api.md) | [API文档](https://lunasaw.github.io/zlm-spring-boot-starter/)

## 功能特性

- 🚀 **简单易用**: 基于Spring Boot自动配置，开箱即用
- 🔄 **完整API封装**: 封装了ZLMediaKit的所有REST API接口
- 🎯 **Hook事件处理**: 支持ZLMediaKit的所有Hook事件回调
- ⚖️ **负载均衡**: 内置5种负载均衡算法，支持集群化部署
- 🔧 **灵活配置**: 支持多节点配置，可独立启用/禁用节点和Hook功能
- 📊 **监控支持**: 提供流媒体状态监控和统计信息获取
- 🎬 **流媒体管理**: 支持流的推拉、录制、截图等完整功能
- 🔐 **安全认证**: 支持RTSP认证和HTTP访问控制

## 系统要求

- Java 17+
- Spring Boot 3.5.3+
- ZLMediaKit服务器

## 快速开始

### 1. 添加依赖

```xml

<dependency>
    <groupId>io.github.lunasaw</groupId>
    <artifactId>zlm-spring-boot-starter</artifactId>
    <version>${last.version}</version>
</dependency>
```

### 2. 配置文件

在 `application.yml` 中添加ZLMediaKit配置：

```yaml
zlm:
  enable: true # 是否启用，未启用不会加载
  balance: round_robin # 节点负载均衡算法，默认round_robin
  nodes: # zlm节点列表，每个节点配置如下
    - server-id: zlm-node-1 # 节点ID，可自定义
      host: "http://127.0.0.1:9092" # 节点地址
      secret: zlm # 节点密钥
      enabled: true # 节点是否启用
      hook-enabled: true # 节点是否启用hook接口，启用hook会注入hook接口，默认true，需要注意拦截器放通
    - server-id: zlm-node-2 # 可配置多个节点
      host: "http://127.0.0.1:9093"
      secret: zlm
      enabled: true
      hook-enabled: true
```

### 3. 使用REST API

直接调用静态方法使用REST API：

```java
import io.github.lunasaw.zlm.api.ZlmRestService;
import io.github.lunasaw.zlm.entity.ServerResponse;
import io.github.lunasaw.zlm.entity.Version;

// 获取服务器版本信息
ServerResponse<Version> versionResponse = ZlmRestService.getVersion("http://127.0.0.1:9092", "zlm");
System.out.

        println("ZLMediaKit版本: "+versionResponse.getData().

        getVersion());

        // 获取流列表
        ServerResponse<List<MediaData>> mediaList = ZlmRestService.getMediaList("http://127.0.0.1:9092", "zlm", new HashMap<>());
mediaList.

        getData().

        forEach(media ->{
        System.out.

        println("流ID: "+media.getApp() +"/"+media.

        getStream());
        });
```

### 4. 实现Hook服务

创建Hook服务实现类来处理ZLMediaKit的事件回调：

```java
import io.github.lunasaw.zlm.hook.service.AbstractZlmHookService;
import org.springframework.stereotype.Service;

@Service
public class CustomZlmHookService extends AbstractZlmHookService {

    @Override
    public HookResult onPlay(OnPlayHookParam param) {
        // 播放鉴权逻辑
        log.info("播放请求: {}:{}", param.getApp(), param.getStream());
        if (isValidUser(param.getParams())) {
            return HookResult.SUCCESS();
        }
        return HookResult.FAILED("无权限播放该流");
    }

    @Override
    public HookResultForOnPublish onPublish(OnPublishHookParam param) {
        // 推流鉴权逻辑
        log.info("推流请求: {}:{}", param.getApp(), param.getStream());
        return HookResultForOnPublish.SUCCESS();
    }

    @Override
    public void onStreamChanged(OnStreamChangedHookParam param) {
        // 流状态变化处理
        log.info("流状态变化: {} - {}", param.getStream(), param.isRegist());
    }

    private boolean isValidUser(String params) {
        // 实现用户验证逻辑
        return true;
    }
}
```

## 详细配置说明

### 负载均衡算法

支持以下5种负载均衡算法：

| 算法    | 配置值                  | 说明         |
|-------|----------------------|------------|
| 随机    | `random`             | 随机选择节点     |
| 轮询    | `round_robin`        | 轮询选择节点（默认） |
| 一致性哈希 | `consistent_hashing` | 基于一致性哈希算法  |
| 加权轮询  | `weight_round_robin` | 基于权重的轮询    |
| 加权随机  | `weight_random`      | 基于权重的随机选择  |

### 配置参数详解

```yaml
zlm:
  enable: true                    # 是否启用ZLM功能
  balance: round_robin           # 负载均衡算法
  nodes: # 节点配置列表
    - server-id: unique-id       # 节点唯一标识
      host: "http://ip:port"     # 节点地址
      secret: "secret-key"       # API密钥
      enabled: true              # 是否启用该节点
      hook-enabled: true         # 是否启用Hook功能
      weight: 1                  # 节点权重（仅加权算法有效）
```

## API功能详解

### 1. 服务器管理

```java
// 获取服务器版本
ServerResponse<Version> version = ZlmRestService.getVersion(host, secret);

// 获取服务器配置
ServerResponse<ServerNodeConfig> config = ZlmRestService.getServerConfig(host, secret);

// 获取API列表
ServerResponse<List<String>> apiList = ZlmRestService.getApiList(host, secret);

// 获取服务器统计信息
ServerResponse<ImportantObjectNum> statistics = ZlmRestService.getStatistic(host, secret);
```

### 2. 流媒体管理

```java
// 获取流列表
MediaReq mediaReq = new MediaReq();
mediaReq.

setApp("live");

ServerResponse<List<MediaData>> mediaList = ZlmRestService.getMediaList(host, secret, mediaReq);

// 关闭指定流
ZlmRestService.

closeStream(host, secret, mediaReq);

// 检查流是否在线
MediaOnlineStatus status = ZlmRestService.isMediaOnline(host, secret, mediaReq);

// 获取流详细信息
ServerResponse<MediaInfo> mediaInfo = ZlmRestService.getMediaInfo(host, secret, mediaReq);
```

### 3. 代理拉流

```java
// 添加拉流代理
StreamProxyItem proxyItem = new StreamProxyItem();
proxyItem.

setVhost("__defaultVhost__");
proxyItem.

setApp("live");
proxyItem.

setStream("test");
proxyItem.

setUrl("rtmp://example.com/live/stream");

ServerResponse<StreamKey> result = ZlmRestService.addStreamProxy(host, secret, proxyItem);

// 删除拉流代理
ZlmRestService.

delStreamProxy(host, secret, result.getData().

getKey());
```

### 4. 推流管理

```java
// 添加推流
StreamPusherItem pusherItem = new StreamPusherItem();
pusherItem.

setSchema("rtmp");
pusherItem.

setVhost("__defaultVhost__");
pusherItem.

setApp("live");
pusherItem.

setStream("test");
pusherItem.

setDst_url("rtmp://push.example.com/live/stream");

ServerResponse<StreamKey> pushResult = ZlmRestService.addStreamPusherProxy(host, secret, pusherItem);
```

### 5. 录制功能

```java
// 开始录制
RecordReq recordReq = new RecordReq();
recordReq.

setType(0); // 0-hls, 1-mp4
recordReq.

setVhost("__defaultVhost__");
recordReq.

setApp("live");
recordReq.

setStream("test");
ZlmRestService.

startRecord(host, secret, recordReq);

// 停止录制
ZlmRestService.

stopRecord(host, secret, recordReq);

// 获取录制文件
ServerResponse<Mp4RecordFile> recordFiles = ZlmRestService.getMp4RecordFile(host, secret, recordReq);
```

### 6. 截图功能

```java
// 获取流截图
SnapshotReq snapshotReq = new SnapshotReq();
snapshotReq.

setVhost("__defaultVhost__");
snapshotReq.

setApp("live");
snapshotReq.

setStream("test");
snapshotReq.

setSavePath("/path/to/snapshot.jpg");

String result = ZlmRestService.getSnap(host, secret, snapshotReq);
```

### 7. RTP服务

```java
// 创建RTP服务器
OpenRtpServerReq rtpReq = new OpenRtpServerReq();
rtpReq.

setStream_id("test_rtp");
rtpReq.

setPort(10000);

OpenRtpServerResult rtpResult = ZlmRestService.openRtpServer(host, secret, rtpReq);

// 关闭RTP服务器
ZlmRestService.

closeRtpServer(host, secret, "test_rtp");
```

## Hook事件详解

### Hook接口说明

实现 `ZlmHookService` 接口或继承 `AbstractZlmHookService` 类来处理各种Hook事件：

```java
public interface ZlmHookService {
    // 服务器保活事件
    void onServerKeepLive(OnServerKeepaliveHookParam param);

    // 播放鉴权
    HookResult onPlay(OnPlayHookParam param);

    // 推流鉴权
    HookResultForOnPublish onPublish(OnPublishHookParam param);

    // 流状态变化
    void onStreamChanged(OnStreamChangedHookParam param);

    // 流无人观看
    HookResultForStreamNoneReader onStreamNoneReader(OnStreamNoneReaderHookParam param);

    // 流未找到
    void onStreamNotFound(OnStreamNotFoundHookParam param);

    // 服务器启动
    void onServerStarted(ServerNodeConfig param);

    // RTP推流停止
    void onSendRtpStopped(OnSendRtpStoppedHookParam param);

    // RTP服务器超时
    void onRtpServerTimeout(OnRtpServerTimeoutHookParam param);

    // HTTP访问鉴权
    HookResultForOnHttpAccess onHttpAccess(OnHttpAccessParam param);

    // RTSP Realm鉴权
    HookResultForOnRtspRealm onRtspRealm(OnRtspRealmHookParam param);

    // RTSP用户密码鉴权
    HookResultForOnRtspAuth onRtspAuth(OnRtspAuthHookParam param);

    // 流量统计
    void onFlowReport(OnFlowReportHookParam param);

    // 服务器退出
    void onServerExited(HookParam param);

    // MP4录制完成
    void onRecordMp4(OnRecordMp4HookParam param);
}
```

### Hook返回值说明

不同的Hook事件需要返回不同的结果：

```java
// 基础Hook结果 - 用于播放鉴权等
HookResult.SUCCESS();           // 允许
HookResult.

FAILED("原因");      // 拒绝

// 推流鉴权结果
HookResultForOnPublish.

SUCCESS();                    // 允许推流
HookResultForOnPublish.

FAILED("推流被拒绝");         // 拒绝推流

// HTTP访问鉴权结果
HookResultForOnHttpAccess.

SUCCESS();                 // 允许访问
HookResultForOnHttpAccess.

FAILED(401,"未授权");    // 拒绝访问
```

## 高级用法

### 集群部署示例

```yaml
zlm:
  enable: true
  balance: consistent_hashing
  nodes:
    - server-id: zlm-beijing-1
      host: "http://10.0.1.10:9092"
      secret: "beijing-secret"
      enabled: true
      hook-enabled: true
      weight: 3
    - server-id: zlm-beijing-2
      host: "http://10.0.1.11:9092"
      secret: "beijing-secret"
      enabled: true
      hook-enabled: true
      weight: 2
    - server-id: zlm-shanghai-1
      host: "http://10.0.2.10:9092"
      secret: "shanghai-secret"
      enabled: true
      hook-enabled: false  # 上海节点不处理Hook
      weight: 1
```

### 自定义负载均衡器

```java

@Component
public class CustomLoadBalancer implements LoadBalancer {
    @Override
    public ZlmNode selectNode(List<ZlmNode> nodes, String key) {
        // 实现自定义负载均衡逻辑
        return nodes.get(0);
    }
}
```

### 条件化Hook处理

```java

@Service
public class ConditionalZlmHookService extends AbstractZlmHookService {

    @Override
    public HookResult onPlay(OnPlayHookParam param) {
        // 根据不同应用进行不同处理
        switch (param.getApp()) {
            case "live":
                return handleLivePlay(param);
            case "vod":
                return handleVodPlay(param);
            default:
                return HookResult.SUCCESS();
        }
    }

    private HookResult handleLivePlay(OnPlayHookParam param) {
        // 直播流播放逻辑
        return HookResult.SUCCESS();
    }

    private HookResult handleVodPlay(OnPlayHookParam param) {
        // 点播流播放逻辑
        return HookResult.SUCCESS();
    }
}
```

## 常见问题

### Q: Hook接口无法接收到回调？

A: 请检查以下几点：

1. 确保 `hook-enabled: true`
2. 检查Spring Boot的拦截器是否放通了Hook接口路径
3. 确认ZLMediaKit配置中Hook地址是否正确
4. 检查网络连通性

### Q: 多节点负载均衡不生效？

A: 请确认：

1. 多个节点的 `enabled: true`
2. 负载均衡算法配置正确
3. 节点权重配置（如使用加权算法）

### Q: API调用超时？

A: 建议：

1. 检查网络连接
2. 调整HTTP客户端超时时间
3. 确认ZLMediaKit服务状态

### Q: 录制文件找不到？

A: 请检查：

1. ZLMediaKit的录制路径配置
2. 文件系统权限
3. 磁盘空间

## 注意事项

1. **版本兼容性**: 请确保ZLMediaKit版本与starter版本兼容
2. **Hook接口安全**: 生产环境需要对Hook接口进行适当的安全防护
3. **性能考虑**: 大量并发时建议合理配置连接池和超时时间
4. **日志监控**: 建议开启详细日志以便问题排查

## 代码规范

- 后端使用同一份代码格式化模板ali-code-style.xml，eclipse直接导入使用，idea使用Eclipse Code Formatter插件配置xml后使用。
- 前端代码使用vs插件的Beautify格式化，缩进使用TAB
- 后端代码非特殊情况遵守P3C插件规范
- 注释要尽可能完整明晰，提交的代码必须要先格式化
- xml文件和前端一样，使用TAB缩进

## 贡献指南

欢迎提交Issue和Pull Request！

1. Fork本项目
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 开启Pull Request

## 许可证

本项目使用 [Apache 2.0](LICENSE) 许可证。

## 联系方式

- 作者: luna
- 邮箱: iszychen@gmail.com
- 项目主页: https://github.com/lunasaw/zlm-spring-boot-starter
