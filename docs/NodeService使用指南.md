# NodeService 使用指南

## 概述

`NodeService` 是 ZLM Spring Boot Starter 提供的节点管理服务，用于统一管理 ZLM 节点的选择、查询和负载均衡功能。该服务将
`LoadBalancer` 和 `NodeSupplier` 的功能进行了整合，为外部项目提供了简洁易用的节点管理接口。

## 功能特性

- **节点选择**: 支持指定节点key获取特定节点，或使用负载均衡策略自动选择节点
- **负载均衡**: 集成多种负载均衡策略（轮询、随机、权重等）
- **节点查询**: 提供节点列表查询、节点存在性检查等功能
- **异常处理**: 完善的异常处理机制，提供清晰的错误信息

## 接口方法

### 1. 获取指定节点

```java
ZlmNode getNode(String nodeKey)
```

根据节点key获取指定的ZLM节点。

**参数:**

- `nodeKey`: 节点标识符

**返回值:**

- `ZlmNode`: 指定的节点对象

**异常:**

- `IllegalArgumentException`: 当节点key为空或节点不存在时抛出

**示例:**

```java

@Autowired
private NodeService nodeService;

ZlmNode node = nodeService.getNode("node1");
```

### 2. 负载均衡选择节点

```java
ZlmNode selectNode(String key)

ZlmNode selectNode()  // 使用默认key "default"
```

使用负载均衡策略选择节点。

**参数:**

- `key`: 负载均衡选择key，通常用于标识业务类型

**返回值:**

- `ZlmNode`: 选中的节点对象

**异常:**

- `IllegalStateException`: 当没有可用节点时抛出

**示例:**

```java
// 使用指定key选择节点
ZlmNode node1 = nodeService.selectNode("live-stream");

// 使用默认key选择节点
ZlmNode node2 = nodeService.selectNode();
```

### 3. 获取所有节点

```java
List<ZlmNode> getAllNodes()
```

获取当前所有可用的ZLM节点列表。

**返回值:**

- `List<ZlmNode>`: 节点列表

**示例:**

```java
List<ZlmNode> nodes = nodeService.getAllNodes();
for(
ZlmNode node :nodes){
        System.out.

println("节点ID: "+node.getServerId());
        System.out.

println("节点地址: "+node.getHost());
        }
```

### 4. 检查节点是否存在

```java
boolean hasNode(String nodeKey)
```

检查指定节点是否存在。

**参数:**

- `nodeKey`: 节点标识符

**返回值:**

- `boolean`: 如果节点存在返回true，否则返回false

**示例:**

```java
if(nodeService.hasNode("node1")){
        System.out.

println("节点存在");
}else{
        System.out.

println("节点不存在");
}
```

### 5. 获取节点数量

```java
int getNodeCount()
```

获取当前可用节点的数量。

**返回值:**

- `int`: 节点数量

**示例:**

```java
int count = nodeService.getNodeCount();
System.out.

println("当前可用节点数量: "+count);
```

## 使用场景

### 1. 在Controller中使用

```java

@RestController
@RequestMapping("/api")
public class MediaController {

    @Autowired
    private NodeService nodeService;

    @GetMapping("/version")
    public ServerResponse<Version> getVersion(@RequestParam(required = false) String nodeKey) {
        ZlmNode node;
        if (nodeKey != null && !nodeKey.trim().isEmpty()) {
            // 使用指定节点
            node = nodeService.getNode(nodeKey);
        } else {
            // 使用负载均衡选择节点
            node = nodeService.selectNode();
        }

        return ZlmRestService.getVersion(node.getHost(), node.getSecret());
    }

    @GetMapping("/nodes")
    public List<ZlmNode> getAllNodes() {
        return nodeService.getAllNodes();
    }
}
```

### 2. 在Service中使用

```java

@Service
public class MediaService {

    @Autowired
    private NodeService nodeService;

    public void processMediaStream(String streamId, String nodeKey) {
        // 检查指定节点是否存在
        if (!nodeService.hasNode(nodeKey)) {
            throw new IllegalArgumentException("指定的节点不存在: " + nodeKey);
        }

        // 获取节点信息
        ZlmNode node = nodeService.getNode(nodeKey);

        // 处理媒体流逻辑
        processStream(node, streamId);
    }

    public void broadcastToAllNodes(String message) {
        // 获取所有节点
        List<ZlmNode> nodes = nodeService.getAllNodes();

        // 向所有节点广播消息
        for (ZlmNode node : nodes) {
            broadcastMessage(node, message);
        }
    }
}
```

### 3. 负载均衡场景

```java

@Component
public class LoadBalancedMediaService {

    @Autowired
    private NodeService nodeService;

    public void handleLiveStream(String streamId) {
        // 使用负载均衡选择节点处理直播流
        ZlmNode node = nodeService.selectNode("live-stream");
        processLiveStream(node, streamId);
    }

    public void handleVodStream(String streamId) {
        // 使用负载均衡选择节点处理点播流
        ZlmNode node = nodeService.selectNode("vod-stream");
        processVodStream(node, streamId);
    }
}
```

## 配置说明

NodeService 会自动注册为 Spring Bean，无需额外配置。它依赖于以下组件：

- `LoadBalancer`: 负载均衡器实现
- `NodeSupplier`: 节点提供器实现

这些组件会在 ZLM Starter 自动配置中自动注册。

## 异常处理

NodeService 提供了完善的异常处理机制：

1. **节点不存在异常**: 当指定的节点key不存在时，会抛出 `IllegalArgumentException`
2. **无可用节点异常**: 当负载均衡器无法选择到可用节点时，会抛出 `IllegalStateException`
3. **参数验证异常**: 当传入的参数为空或无效时，会抛出 `IllegalArgumentException`

建议在使用时进行适当的异常处理：

```java
try{
ZlmNode node = nodeService.getNode("node1");
// 处理业务逻辑
}catch(
IllegalArgumentException e){
        // 处理节点不存在的情况
        log.

error("节点不存在: {}",e.getMessage());
        }catch(
IllegalStateException e){
        // 处理无可用节点的情况
        log.

error("无可用节点: {}",e.getMessage());
        }
```

## 最佳实践

1. **节点选择策略**: 根据业务需求选择合适的节点选择策略
    - 对于需要指定节点的场景，使用 `getNode(nodeKey)`
    - 对于需要负载均衡的场景，使用 `selectNode(key)`

2. **异常处理**: 始终进行适当的异常处理，确保系统的健壮性

3. **性能考虑**: NodeService 的方法都是轻量级的，可以频繁调用

4. **监控**: 建议监控节点数量和可用性，及时发现节点问题

5. **日志记录**: 在关键操作处添加日志记录，便于问题排查