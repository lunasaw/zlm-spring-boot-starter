package io.github.lunasaw.zlm;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.lunasaw.zlm.api.ZlmRestService;
import io.github.lunasaw.zlm.api.controller.ZlmApiController;
import io.github.lunasaw.zlm.config.ZlmNode;
import io.github.lunasaw.zlm.config.ZlmProperties;
import io.github.lunasaw.zlm.entity.*;
import io.github.lunasaw.zlm.entity.req.CloseStreamsReq;
import io.github.lunasaw.zlm.entity.req.MediaReq;
import io.github.lunasaw.zlm.entity.req.RecordReq;
import io.github.lunasaw.zlm.entity.req.SnapshotReq;
import io.github.lunasaw.zlm.entity.rtp.*;
import io.github.lunasaw.zlm.node.LoadBalancer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author luna
 * @version 1.0
 * @date 2023/12/3
 * @description: ZlmApiController 单元测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZlmApplicationTest.class)
@AutoConfigureMockMvc
public class ZlmApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ZlmProperties zlmProperties;

    @MockBean
    private LoadBalancer loadBalancer;

    @Autowired
    private ObjectMapper objectMapper;

    private MockedStatic<ZlmRestService> zlmRestServiceMock;
    private ZlmNode testNode;

    @Before
    public void setUp() {
        // 初始化测试节点
        testNode = new ZlmNode();
        testNode.setServerId("test-node-1");
        testNode.setHost("http://127.0.0.1:9092");
        testNode.setSecret("weidian");
        testNode.setWeight(1);

        // Mock LoadBalancer
        when(loadBalancer.selectNode("default")).thenReturn(testNode);

        // Mock ZlmProperties
        Map<String, ZlmNode> nodeMap = new HashMap<>();
        nodeMap.put("test-node-1", testNode);
        when(zlmProperties.getNodeMap()).thenReturn(nodeMap);
        when(zlmProperties.getNodes()).thenReturn(Collections.singletonList(testNode));

        // Mock ZlmRestService 静态方法
        zlmRestServiceMock = Mockito.mockStatic(ZlmRestService.class);
    }

    @After
    public void tearDown() {
        if (zlmRestServiceMock != null) {
            zlmRestServiceMock.close();
        }
    }

    // ==================== 系统信息接口测试 ====================

    @Test
    public void testGetVersion() throws Exception {
        // 准备测试数据
        Version version = new Version();
        ServerResponse<Version> response = new ServerResponse<>();
        response.setCode(0);
        response.setData(version);

        // Mock 静态方法调用
        zlmRestServiceMock.when(() -> ZlmRestService.getVersion(
                eq(testNode.getHost()), eq(testNode.getSecret())
        )).thenReturn(response);

        // 执行测试
        mockMvc.perform(get("/zlm/api/version"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));
    }

    @Test
    public void testGetApiList() throws Exception {
        // 准备测试数据
        List<String> apiList = Arrays.asList("api1", "api2", "api3");
        ServerResponse<List<String>> response = new ServerResponse<>();
        response.setCode(0);
        response.setData(apiList);

        // Mock 静态方法调用
        zlmRestServiceMock.when(() -> ZlmRestService.getApiList(
                eq(testNode.getHost()), eq(testNode.getSecret()), any()
        )).thenReturn(response);

        // 执行测试
        mockMvc.perform(get("/zlm/api/api/list")
                        .param("test", "value"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    public void testGetThreadsLoad() throws Exception {
        // 准备测试数据
        List<ThreadLoad> threadLoads = new ArrayList<>();
        ThreadLoad threadLoad = new ThreadLoad();
        threadLoads.add(threadLoad);
        ServerResponse<List<ThreadLoad>> response = new ServerResponse<>();
        response.setCode(0);
        response.setData(threadLoads);

        // Mock 静态方法调用
        zlmRestServiceMock.when(() -> ZlmRestService.getThreadsLoad(
                eq(testNode.getHost()), eq(testNode.getSecret())
        )).thenReturn(response);

        // 执行测试
        mockMvc.perform(get("/zlm/api/threads/load"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));
    }

    @Test
    public void testGetStatistic() throws Exception {
        // 准备测试数据
        ImportantObjectNum statistic = new ImportantObjectNum();
        ServerResponse<ImportantObjectNum> response = new ServerResponse<>();
        response.setCode(0);
        response.setData(statistic);

        // Mock 静态方法调用
        zlmRestServiceMock.when(() -> ZlmRestService.getStatistic(
                eq(testNode.getHost()), eq(testNode.getSecret())
        )).thenReturn(response);

        // 执行测试
        mockMvc.perform(get("/zlm/api/statistic"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));
    }

    @Test
    public void testGetServerConfig() throws Exception {
        // 准备测试数据
        ServerNodeConfig config = new ServerNodeConfig();
        ServerResponse<ServerNodeConfig> response = new ServerResponse<>();
        response.setCode(0);
        response.setData(config);

        // Mock 静态方法调用
        zlmRestServiceMock.when(() -> ZlmRestService.getServerConfig(
                eq(testNode.getHost()), eq(testNode.getSecret())
        )).thenReturn(response);

        // 执行测试
        mockMvc.perform(get("/zlm/api/server/config"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));
    }

    @Test
    public void testSetServerConfig() throws Exception {
        // 准备测试数据
        Map<String, String> params = new HashMap<>();
        params.put("key", "value");
        ServerResponse<String> response = new ServerResponse<>();
        response.setCode(0);
        response.setData("success");

        // Mock 静态方法调用
        zlmRestServiceMock.when(() -> ZlmRestService.setServerConfig(
                eq(testNode.getHost()), eq(testNode.getSecret()), any(Map.class)
        )).thenReturn(response);

        // 执行测试
        mockMvc.perform(post("/zlm/api/server/config")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(params)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));
    }

    // ==================== 媒体流管理接口测试 ====================

    @Test
    public void testGetMediaList() throws Exception {
        // 准备测试数据
        MediaReq mediaReq = new MediaReq();
        mediaReq.setApp("live");
        mediaReq.setStream("test");

        List<MediaData> mediaList = new ArrayList<>();
        MediaData mediaData = new MediaData();
        mediaList.add(mediaData);

        ServerResponse<List<MediaData>> response = new ServerResponse<>();
        response.setCode(0);
        response.setData(mediaList);

        // Mock 静态方法调用
        zlmRestServiceMock.when(() -> ZlmRestService.getMediaList(
                eq(testNode.getHost()), eq(testNode.getSecret()), any(MediaReq.class)
        )).thenReturn(response);

        // 执行测试
        mockMvc.perform(post("/zlm/api/media/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mediaReq)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));
    }

    @Test
    public void testCloseStream() throws Exception {
        // 准备测试数据
        MediaReq mediaReq = new MediaReq();
        mediaReq.setApp("live");
        mediaReq.setStream("test");

        ServerResponse<String> response = new ServerResponse<>();
        response.setCode(0);
        response.setData("success");

        // Mock 静态方法调用
        zlmRestServiceMock.when(() -> ZlmRestService.closeStream(
                eq(testNode.getHost()), eq(testNode.getSecret()), any(MediaReq.class)
        )).thenReturn(response);

        // 执行测试
        mockMvc.perform(post("/zlm/api/media/close")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mediaReq)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));
    }

    @Test
    public void testCloseStreams() throws Exception {
        // 准备测试数据
        CloseStreamsReq closeStreamsReq = new CloseStreamsReq();

        ServerResponse response = new ServerResponse<>();
        response.setCode(0);

        // Mock 静态方法调用
        zlmRestServiceMock.when(() -> ZlmRestService.closeStreams(
                eq(testNode.getHost()), eq(testNode.getSecret()), any(Map.class)
        )).thenReturn(response);

        // 执行测试
        mockMvc.perform(post("/zlm/api/media/close-batch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(closeStreamsReq)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));
    }

    @Test
    public void testIsMediaOnline() throws Exception {
        // 准备测试数据
        MediaReq mediaReq = new MediaReq();
        mediaReq.setApp("live");
        mediaReq.setStream("test");

        MediaOnlineStatus status = new MediaOnlineStatus();

        // Mock 静态方法调用
        zlmRestServiceMock.when(() -> ZlmRestService.isMediaOnline(
                eq(testNode.getHost()), eq(testNode.getSecret()), any(MediaReq.class)
        )).thenReturn(status);

        // 执行测试
        mockMvc.perform(post("/zlm/api/media/online")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mediaReq)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // ==================== RTP服务器管理接口测试 ====================

    @Test
    public void testGetRtpInfo() throws Exception {
        // 准备测试数据
        String streamId = "test-stream";
        RtpInfoResult rtpInfo = new RtpInfoResult();

        // Mock 静态方法调用
        zlmRestServiceMock.when(() -> ZlmRestService.getRtpInfo(
                eq(testNode.getHost()), eq(testNode.getSecret()), eq(streamId)
        )).thenReturn(rtpInfo);

        // 执行测试
        mockMvc.perform(get("/zlm/api/rtp/info/{streamId}", streamId))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testOpenRtpServer() throws Exception {
        // 准备测试数据
        OpenRtpServerReq req = new OpenRtpServerReq();
        req.setStreamId("test-stream");
        req.setPort(10000);

        OpenRtpServerResult result = new OpenRtpServerResult();

        // Mock 静态方法调用
        zlmRestServiceMock.when(() -> ZlmRestService.openRtpServer(
                eq(testNode.getHost()), eq(testNode.getSecret()), any(OpenRtpServerReq.class)
        )).thenReturn(result);

        // 执行测试
        mockMvc.perform(post("/zlm/api/rtp/server/open")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testCloseRtpServer() throws Exception {
        // 准备测试数据
        String streamId = "test-stream";
        CloseRtpServerResult result = new CloseRtpServerResult();

        // Mock 静态方法调用
        zlmRestServiceMock.when(() -> ZlmRestService.closeRtpServer(
                eq(testNode.getHost()), eq(testNode.getSecret()), eq(streamId)
        )).thenReturn(result);

        // 执行测试
        mockMvc.perform(delete("/zlm/api/rtp/server/{streamId}", streamId))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testStartSendRtp() throws Exception {
        // 准备测试数据
        StartSendRtpReq req = new StartSendRtpReq();
        req.setApp("live");
        req.setStream("test");
        req.setDstUrl("192.168.1.100");
        req.setDstPort(5004);

        StartSendRtpResult result = new StartSendRtpResult();

        // Mock 静态方法调用
        zlmRestServiceMock.when(() -> ZlmRestService.startSendRtp(
                eq(testNode.getHost()), eq(testNode.getSecret()), any(StartSendRtpReq.class)
        )).thenReturn(result);

        // 执行测试
        mockMvc.perform(post("/zlm/api/rtp/send/start")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // ==================== 录制管理接口测试 ====================

    @Test
    public void testGetMp4RecordFile() throws Exception {
        // 准备测试数据
        RecordReq recordReq = new RecordReq();
        recordReq.setApp("live");
        recordReq.setStream("test");

        Mp4RecordFile recordFile = new Mp4RecordFile();
        ServerResponse<Mp4RecordFile> response = new ServerResponse<>();
        response.setCode(0);
        response.setData(recordFile);

        // Mock 静态方法调用
        zlmRestServiceMock.when(() -> ZlmRestService.getMp4RecordFile(
                eq(testNode.getHost()), eq(testNode.getSecret()), any(RecordReq.class)
        )).thenReturn(response);

        // 执行测试
        mockMvc.perform(post("/zlm/api/record/files")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(recordReq)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));
    }

    @Test
    public void testStartRecord() throws Exception {
        // 准备测试数据
        RecordReq recordReq = new RecordReq();
        recordReq.setApp("live");
        recordReq.setStream("test");
        recordReq.setType(0);

        ServerResponse<String> response = new ServerResponse<>();
        response.setCode(0);
        response.setData("success");

        // Mock 静态方法调用
        zlmRestServiceMock.when(() -> ZlmRestService.startRecord(
                eq(testNode.getHost()), eq(testNode.getSecret()), any(RecordReq.class)
        )).thenReturn(response);

        // 执行测试
        mockMvc.perform(post("/zlm/api/record/start")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(recordReq)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));
    }

    @Test
    public void testStopRecord() throws Exception {
        // 准备测试数据
        RecordReq recordReq = new RecordReq();
        recordReq.setApp("live");
        recordReq.setStream("test");
        recordReq.setType(0);

        ServerResponse<String> response = new ServerResponse<>();
        response.setCode(0);
        response.setData("success");

        // Mock 静态方法调用
        zlmRestServiceMock.when(() -> ZlmRestService.stopRecord(
                eq(testNode.getHost()), eq(testNode.getSecret()), any(RecordReq.class)
        )).thenReturn(response);

        // 执行测试
        mockMvc.perform(post("/zlm/api/record/stop")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(recordReq)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));
    }

    // ==================== 截图接口测试 ====================

    @Test
    public void testGetSnap() throws Exception {
        // 准备测试数据
        SnapshotReq snapshotReq = new SnapshotReq();

        String snapResult = "data:image/jpeg;base64,test-image-data";

        // Mock 静态方法调用
        zlmRestServiceMock.when(() -> ZlmRestService.getSnap(
                eq(testNode.getHost()), eq(testNode.getSecret()), any(SnapshotReq.class)
        )).thenReturn(snapResult);

        // 执行测试
        mockMvc.perform(post("/zlm/api/snapshot")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(snapshotReq)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(snapResult));
    }

    // ==================== 指定节点操作接口测试 ====================

    @Test
    public void testGetVersionByNode() throws Exception {
        // 准备测试数据
        String nodeId = "test-node-1";
        Version version = new Version();
        ServerResponse<Version> response = new ServerResponse<>();
        response.setCode(0);
        response.setData(version);

        // Mock 静态方法调用
        zlmRestServiceMock.when(() -> ZlmRestService.getVersion(
                eq(testNode.getHost()), eq(testNode.getSecret())
        )).thenReturn(response);

        // 执行测试
        mockMvc.perform(get("/zlm/api/node/{nodeId}/version", nodeId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));
    }

    @Test
    public void testGetVersionByNodeNotFound() throws Exception {
        // 测试节点不存在的情况
        String nonExistentNodeId = "non-existent-node";

        // 执行测试
        mockMvc.perform(get("/zlm/api/node/{nodeId}/version", nonExistentNodeId))
                .andDo(print())
                .andExpect(status().isExpectationFailed())
                .andExpect(content().string("节点不存在: " + nonExistentNodeId));
    }

    @Test
    public void testGetMediaListByNode() throws Exception {
        // 准备测试数据
        String nodeId = "test-node-1";
        MediaReq mediaReq = new MediaReq();
        mediaReq.setApp("live");
        mediaReq.setStream("test");

        List<MediaData> mediaList = new ArrayList<>();
        MediaData mediaData = new MediaData();
        mediaList.add(mediaData);

        ServerResponse<List<MediaData>> response = new ServerResponse<>();
        response.setCode(0);
        response.setData(mediaList);

        // Mock 静态方法调用
        zlmRestServiceMock.when(() -> ZlmRestService.getMediaList(
                eq(testNode.getHost()), eq(testNode.getSecret()), any(MediaReq.class)
        )).thenReturn(response);

        // 执行测试
        mockMvc.perform(post("/zlm/api/node/{nodeId}/media/list", nodeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mediaReq)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));
    }

    @Test
    public void testGetAllNodes() throws Exception {
        // 执行测试
        mockMvc.perform(get("/zlm/api/nodes"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].serverId").value("test-node-1"));
    }

    // ==================== 异常情况测试 ====================

    @Test
    public void testLoadBalancerReturnsNull() throws Exception {
        // Mock LoadBalancer 返回 null
        when(loadBalancer.selectNode("default")).thenReturn(null);

        // 执行测试，应该抛出异常
        mockMvc.perform(get("/zlm/api/version"))
                .andDo(print())
                .andExpect(status().isExpectationFailed());
    }

    // ==================== X-Node-Key 请求头测试 ====================

    @Test
    public void testGetVersionWithNodeKeyHeader() throws Exception {
        // 准备测试数据
        Version version = new Version();
        ServerResponse<Version> response = new ServerResponse<>();
        response.setCode(0);
        response.setData(version);

        // Mock 静态方法调用
        zlmRestServiceMock.when(() -> ZlmRestService.getVersion(
                eq(testNode.getHost()), eq(testNode.getSecret())
        )).thenReturn(response);

        // 执行测试 - 使用 X-Node-Key 请求头指定节点
        mockMvc.perform(get("/zlm/api/version")
                        .header("X-Node-Key", "test-node-1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));
    }

    @Test
    public void testGetVersionWithInvalidNodeKey() throws Exception {
        // 执行测试 - 使用不存在的节点key
        mockMvc.perform(get("/zlm/api/version")
                        .header("X-Node-Key", "invalid-node"))
                .andDo(print())
                .andExpect(status().isExpectationFailed());
    }

    @Test
    public void testGetVersionWithEmptyNodeKey() throws Exception {
        // 准备测试数据
        Version version = new Version();
        ServerResponse<Version> response = new ServerResponse<>();
        response.setCode(0);
        response.setData(version);

        // Mock 静态方法调用
        zlmRestServiceMock.when(() -> ZlmRestService.getVersion(
                eq(testNode.getHost()), eq(testNode.getSecret())
        )).thenReturn(response);

        // 执行测试 - 使用空的 X-Node-Key 请求头，应该回退到负载均衡
        mockMvc.perform(get("/zlm/api/version")
                        .header("X-Node-Key", ""))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));
    }

    @Test
    public void testGetMediaListWithNodeKeyHeader() throws Exception {
        // 准备测试数据
        MediaReq mediaReq = new MediaReq();
        mediaReq.setApp("live");
        mediaReq.setStream("test");

        List<MediaData> mediaList = new ArrayList<>();
        MediaData mediaData = new MediaData();
        mediaList.add(mediaData);

        ServerResponse<List<MediaData>> response = new ServerResponse<>();
        response.setCode(0);
        response.setData(mediaList);

        // Mock 静态方法调用
        zlmRestServiceMock.when(() -> ZlmRestService.getMediaList(
                eq(testNode.getHost()), eq(testNode.getSecret()), any(MediaReq.class)
        )).thenReturn(response);

        // 执行测试 - 使用 X-Node-Key 请求头指定节点
        mockMvc.perform(post("/zlm/api/media/list")
                        .header("X-Node-Key", "test-node-1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mediaReq)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));
    }

    @Test
    public void testOpenRtpServerWithNodeKeyHeader() throws Exception {
        // 准备测试数据
        OpenRtpServerReq req = new OpenRtpServerReq();
        req.setStreamId("test-stream");
        req.setPort(10000);

        OpenRtpServerResult result = new OpenRtpServerResult();

        // Mock 静态方法调用
        zlmRestServiceMock.when(() -> ZlmRestService.openRtpServer(
                eq(testNode.getHost()), eq(testNode.getSecret()), any(OpenRtpServerReq.class)
        )).thenReturn(result);

        // 执行测试 - 使用 X-Node-Key 请求头指定节点
        mockMvc.perform(post("/zlm/api/rtp/server/open")
                        .header("X-Node-Key", "test-node-1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testGetRtpInfoWithNodeKeyHeader() throws Exception {
        // 准备测试数据
        String streamId = "test-stream";
        RtpInfoResult rtpInfo = new RtpInfoResult();

        // Mock 静态方法调用
        zlmRestServiceMock.when(() -> ZlmRestService.getRtpInfo(
                eq(testNode.getHost()), eq(testNode.getSecret()), eq(streamId)
        )).thenReturn(rtpInfo);

        // 执行测试 - 使用 X-Node-Key 请求头指定节点
        mockMvc.perform(get("/zlm/api/rtp/info/{streamId}", streamId)
                        .header("X-Node-Key", "test-node-1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // ==================== 指定节点操作异常情况测试 ====================

    @Test
    public void testGetMediaListByNodeNotFound() throws Exception {
        // 测试指定节点获取媒体列表 - 节点不存在
        String nonExistentNodeId = "non-existent-node";
        MediaReq mediaReq = new MediaReq();
        mediaReq.setApp("live");
        mediaReq.setStream("test");

        // 执行测试
        mockMvc.perform(post("/zlm/api/node/{nodeId}/media/list", nonExistentNodeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mediaReq)))
                .andDo(print())
                .andExpect(status().isExpectationFailed())
                .andExpect(content().string("节点不存在: " + nonExistentNodeId));
    }

    @Test
    public void testXNodeKeyHeaderWithNullValue() throws Exception {
        // 测试 X-Node-Key 请求头为 null 时，回退到负载均衡
        Version version = new Version();
        ServerResponse<Version> response = new ServerResponse<>();
        response.setCode(0);
        response.setData(version);

        // Mock 静态方法调用
        zlmRestServiceMock.when(() -> ZlmRestService.getVersion(
                eq(testNode.getHost()), eq(testNode.getSecret())
        )).thenReturn(response);

        // 执行测试 - 不传 X-Node-Key 请求头
        mockMvc.perform(get("/zlm/api/version"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));
    }

    @Test
    public void testXNodeKeyHeaderWithWhitespaceValue() throws Exception {
        // 测试 X-Node-Key 请求头为空白字符时，回退到负载均衡
        Version version = new Version();
        ServerResponse<Version> response = new ServerResponse<>();
        response.setCode(0);
        response.setData(version);

        // Mock 静态方法调用
        zlmRestServiceMock.when(() -> ZlmRestService.getVersion(
                eq(testNode.getHost()), eq(testNode.getSecret())
        )).thenReturn(response);

        // 执行测试 - 使用空白字符的 X-Node-Key 请求头
        mockMvc.perform(get("/zlm/api/version")
                        .header("X-Node-Key", "   "))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));
    }

    @Test
    public void testGetVersionWithInvalidNodeKeyMultipleScenarios() throws Exception {
        // 测试多种无效节点key的情况
        String[] invalidNodeKeys = {"", " ", "null", "undefined", "invalid-node-123"};

        for (String invalidKey : invalidNodeKeys) {
            if ("".equals(invalidKey) || " ".equals(invalidKey)) {
                // 空字符串和空白字符应该回退到负载均衡
                Version version = new Version();
                ServerResponse<Version> response = new ServerResponse<>();
                response.setCode(0);
                response.setData(version);

                zlmRestServiceMock.when(() -> ZlmRestService.getVersion(
                        eq(testNode.getHost()), eq(testNode.getSecret())
                )).thenReturn(response);

                mockMvc.perform(get("/zlm/api/version")
                                .header("X-Node-Key", invalidKey))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.code").value(0));
            } else {
                // 其他无效key应该返回417错误
                mockMvc.perform(get("/zlm/api/version")
                                .header("X-Node-Key", invalidKey))
                        .andDo(print())
                        .andExpect(status().isExpectationFailed())
                        .andExpect(content().string("指定的节点不存在: " + invalidKey));
            }
        }
    }
}