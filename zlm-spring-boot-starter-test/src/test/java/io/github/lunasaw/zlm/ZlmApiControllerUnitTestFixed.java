package io.github.lunasaw.zlm;

import io.github.lunasaw.zlm.api.ZlmRestService;
import io.github.lunasaw.zlm.api.controller.ZlmApiController;
import io.github.lunasaw.zlm.config.ZlmNode;
import io.github.lunasaw.zlm.entity.*;
import io.github.lunasaw.zlm.entity.req.CloseStreamsReq;
import io.github.lunasaw.zlm.entity.req.MediaReq;
import io.github.lunasaw.zlm.entity.req.RecordReq;
import io.github.lunasaw.zlm.entity.req.SnapshotReq;
import io.github.lunasaw.zlm.entity.rtp.*;
import io.github.lunasaw.zlm.hook.service.ZlmHookService;
import io.github.lunasaw.zlm.node.NodeSupplier;
import io.github.lunasaw.zlm.node.service.NodeService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * ZlmApiController单元测试类（修复版）
 * 测试所有API方法的功能和异常处理
 *
 * @author luna
 * @version 1.0
 * @date 2025/01/25
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ZlmApiControllerUnitTestFixed {

    @InjectMocks
    private ZlmApiController zlmApiController;

    @Mock
    private NodeSupplier nodeSupplier;

    @Mock
    private NodeService nodeService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private ZlmHookService zlmHookService;

    private ZlmNode testNode;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // 初始化测试节点
        testNode = new ZlmNode();
        testNode.setServerId("test-node");
        testNode.setHost("http://localhost:9092");
        testNode.setSecret("test-secret");
        testNode.setEnabled(true);
    }

    // ==================== 测试私有方法 getAvailableNode ====================

    @Test
    public void testGetAvailableNodeWithoutNodeKey() {
        // 模拟请求头没有X-Node-Key
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);

        // 通过反射调用私有方法
        ZlmNode result = (ZlmNode) ReflectionTestUtils.invokeMethod(zlmApiController, "getAvailableNode");

        assertNotNull(result);
        assertEquals(testNode.getServerId(), result.getServerId());
        verify(nodeService).selectNode();
        verify(nodeService, never()).getAvailableNode(anyString());
    }

    @Test
    public void testGetAvailableNodeWithNodeKey() {
        // 模拟请求头有X-Node-Key
        String nodeKey = "test-node-key";
        when(request.getHeader("X-Node-Key")).thenReturn(nodeKey);
        when(nodeService.getAvailableNode(nodeKey)).thenReturn(testNode);

        // 通过反射调用私有方法
        ZlmNode result = (ZlmNode) ReflectionTestUtils.invokeMethod(zlmApiController, "getAvailableNode");

        assertNotNull(result);
        assertEquals(testNode.getServerId(), result.getServerId());
        verify(nodeService).getAvailableNode(nodeKey);
        verify(nodeService, never()).selectNode();
    }

    // ==================== 测试系统信息接口 ====================

    @Test
    public void testGetVersion() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);

        ServerResponse<Version> mockResponse = new ServerResponse<>();
        mockResponse.setCode(1);
        Version version = new Version();
        version.setBuildTime("2024-01-01 12:00:00");
        version.setBranchName("master");
        version.setCommitHash("abc123");
        mockResponse.setData(version);

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.getVersion(testNode.getHost(), testNode.getSecret()))
                    .thenReturn(mockResponse);

            ServerResponse<Version> result = zlmApiController.getVersion();

            assertNotNull(result);
            assertEquals(Integer.valueOf(1), result.getCode());
            assertEquals("2024-01-01 12:00:00", result.getData().getBuildTime());
            verify(nodeService).selectNode();
        }
    }

    @Test
    public void testGetApiList() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        Map<String, String> params = new HashMap<>();
        params.put("test", "value");

        ServerResponse<List<String>> mockResponse = new ServerResponse<>();
        mockResponse.setCode(1);
        mockResponse.setData(Arrays.asList("api1", "api2"));

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.getApiList(testNode.getHost(), testNode.getSecret(), params))
                    .thenReturn(mockResponse);

            ServerResponse<List<String>> result = zlmApiController.getApiList(params);

            assertNotNull(result);
            assertEquals(Integer.valueOf(1), result.getCode());
            assertEquals(2, result.getData().size());
            verify(nodeService).selectNode();
        }
    }

    @Test
    public void testGetThreadsLoad() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);

        ServerResponse<List<ThreadLoad>> mockResponse = new ServerResponse<>();
        mockResponse.setCode(1);
        List<ThreadLoad> threadLoads = new ArrayList<>();
        ThreadLoad threadLoad = new ThreadLoad();
        threadLoad.setLoad("50");
        threadLoad.setDelay("10");
        threadLoads.add(threadLoad);
        mockResponse.setData(threadLoads);

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.getThreadsLoad(testNode.getHost(), testNode.getSecret()))
                    .thenReturn(mockResponse);

            ServerResponse<List<ThreadLoad>> result = zlmApiController.getThreadsLoad();

            assertNotNull(result);
            assertEquals(Integer.valueOf(1), result.getCode());
            assertEquals(1, result.getData().size());
            assertEquals("50", result.getData().get(0).getLoad());
            verify(nodeService).selectNode();
        }
    }

    @Test
    public void testGetStatistic() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);

        ServerResponse<ImportantObjectNum> mockResponse = new ServerResponse<>();
        mockResponse.setCode(1);
        ImportantObjectNum statistic = new ImportantObjectNum();
        statistic.setMediaSource(10);
        mockResponse.setData(statistic);

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.getStatistic(testNode.getHost(), testNode.getSecret()))
                    .thenReturn(mockResponse);

            ServerResponse<ImportantObjectNum> result = zlmApiController.getStatistic();

            assertNotNull(result);
            assertEquals(Integer.valueOf(1), result.getCode());
            assertEquals(Integer.valueOf(10), result.getData().getMediaSource());
            verify(nodeService).selectNode();
        }
    }

    // ==================== 测试媒体流管理接口 ====================

    @Test
    public void testGetMediaList() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        MediaReq mediaReq = new MediaReq();
        mediaReq.setApp("live");
        mediaReq.setStream("test");

        ServerResponse<List<MediaData>> mockResponse = new ServerResponse<>();
        mockResponse.setCode(1);
        List<MediaData> mediaList = new ArrayList<>();
        MediaData mediaData = new MediaData();
        mediaData.setApp("live");
        mediaData.setStream("test");
        mediaList.add(mediaData);
        mockResponse.setData(mediaList);

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.getMediaList(testNode.getHost(), testNode.getSecret(), mediaReq))
                    .thenReturn(mockResponse);

            ServerResponse<List<MediaData>> result = zlmApiController.getMediaList(mediaReq);

            assertNotNull(result);
            assertEquals(Integer.valueOf(1), result.getCode());
            assertEquals(1, result.getData().size());
            assertEquals("live", result.getData().get(0).getApp());
            verify(nodeService).selectNode();
        }
    }

    @Test
    public void testCloseStream() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        MediaReq mediaReq = new MediaReq();
        mediaReq.setApp("live");
        mediaReq.setStream("test");

        ServerResponse<String> mockResponse = new ServerResponse<>();
        mockResponse.setCode(1);
        mockResponse.setData("success");

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.closeStream(testNode.getHost(), testNode.getSecret(), mediaReq))
                    .thenReturn(mockResponse);

            ServerResponse<String> result = zlmApiController.closeStream(mediaReq);

            assertNotNull(result);
            assertEquals(Integer.valueOf(1), result.getCode());
            assertEquals("success", result.getData());
            verify(nodeService).selectNode();
        }
    }

    @Test
    public void testIsMediaOnline() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        MediaReq mediaReq = new MediaReq();
        mediaReq.setApp("live");
        mediaReq.setStream("test");

        MediaOnlineStatus mockStatus = new MediaOnlineStatus();
        mockStatus.setOnline(true);

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.isMediaOnline(testNode.getHost(), testNode.getSecret(), mediaReq))
                    .thenReturn(mockStatus);

            MediaOnlineStatus result = zlmApiController.isMediaOnline(mediaReq);

            assertNotNull(result);
            assertTrue(result.isOnline());
            verify(nodeService).selectNode();
        }
    }

    // ==================== 测试代理流管理接口 ====================

    @Test
    public void testAddStreamProxy() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        StreamProxyItem streamProxyItem = new StreamProxyItem();
        streamProxyItem.setApp("live");
        streamProxyItem.setStream("test");
        streamProxyItem.setUrl("rtmp://source.com/live/stream");

        ServerResponse<StreamKey> mockResponse = new ServerResponse<>();
        mockResponse.setCode(1);
        StreamKey streamKey = new StreamKey();
        streamKey.setKey("test-key");
        mockResponse.setData(streamKey);

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.addStreamProxy(testNode.getHost(), testNode.getSecret(), streamProxyItem))
                    .thenReturn(mockResponse);

            ServerResponse<StreamKey> result = zlmApiController.addStreamProxy(streamProxyItem);

            assertNotNull(result);
            assertEquals(Integer.valueOf(1), result.getCode());
            assertEquals("test-key", result.getData().getKey());
            verify(nodeService).selectNode();
            verify(zlmHookService).onProxyAdded(eq(streamProxyItem), eq(mockResponse.getData()), eq(request));
        }
    }

    // ==================== 测试截图接口 ====================

    @Test
    public void testGetSnap() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        SnapshotReq snapshotReq = new SnapshotReq();
        snapshotReq.setApp("live");
        snapshotReq.setStream("test");

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.getSnap(testNode.getHost(), testNode.getSecret(), snapshotReq))
                    .thenReturn("snapshot-url");

            String result = zlmApiController.getSnap(snapshotReq);

            assertNotNull(result);
            assertEquals("snapshot-url", result);
            verify(nodeService).selectNode();
        }
    }

    // ==================== 测试RTP服务器管理接口 ====================

    @Test
    public void testGetRtpInfo() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        String streamId = "test-stream-id";

        RtpInfoResult mockResult = new RtpInfoResult();
        mockResult.setExist(true);

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.getRtpInfo(testNode.getHost(), testNode.getSecret(), streamId))
                    .thenReturn(mockResult);

            RtpInfoResult result = zlmApiController.getRtpInfo(streamId);

            assertNotNull(result);
            assertTrue(result.isExist());
            verify(nodeService).selectNode();
        }
    }

    @Test
    public void testOpenRtpServer() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        OpenRtpServerReq req = new OpenRtpServerReq();
        req.setStreamId("test-stream");
        req.setPort(10000);

        OpenRtpServerResult mockResult = new OpenRtpServerResult();
        mockResult.setPort(10000);

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.openRtpServer(testNode.getHost(), testNode.getSecret(), req))
                    .thenReturn(mockResult);

            OpenRtpServerResult result = zlmApiController.openRtpServer(req);

            assertNotNull(result);
            assertEquals(Integer.valueOf(10000), result.getPort());
            verify(nodeService).selectNode();
        }
    }

    @Test
    public void testCloseRtpServer() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        String streamId = "test-stream-id";

        CloseRtpServerResult mockResult = new CloseRtpServerResult();
        mockResult.setHit(1);

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.closeRtpServer(testNode.getHost(), testNode.getSecret(), streamId))
                    .thenReturn(mockResult);

            CloseRtpServerResult result = zlmApiController.closeRtpServer(streamId);

            assertNotNull(result);
            assertEquals(Integer.valueOf(1), result.getHit());
            verify(nodeService).selectNode();
        }
    }

    // ==================== 测试指定节点操作接口 ====================

    @Test
    public void testGetVersionByNode() {
        String nodeId = "test-node-id";
        when(nodeService.getAvailableNode(nodeId)).thenReturn(testNode);

        ServerResponse<Version> mockResponse = new ServerResponse<>();
        mockResponse.setCode(1);
        Version version = new Version();
        version.setBuildTime("2024-01-01 12:00:00");
        mockResponse.setData(version);

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.getVersion(testNode.getHost(), testNode.getSecret()))
                    .thenReturn(mockResponse);

            ServerResponse<Version> result = zlmApiController.getVersionByNode(nodeId);

            assertNotNull(result);
            assertEquals(Integer.valueOf(1), result.getCode());
            assertEquals("2024-01-01 12:00:00", result.getData().getBuildTime());
            verify(nodeService).getAvailableNode(nodeId);
        }
    }

    @Test
    public void testGetAllNodes() {
        List<ZlmNode> nodes = Arrays.asList(testNode);
        when(nodeSupplier.getNodes()).thenReturn(nodes);

        List<ZlmNode> result = zlmApiController.getAllNodes();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testNode.getServerId(), result.get(0).getServerId());
        verify(nodeSupplier).getNodes();
    }

    // ==================== 测试异常处理 ====================

    @Test
    public void testHandleIllegalArgumentException() {
        IllegalArgumentException exception = new IllegalArgumentException("Test error message");

        ServerResponse<String> result = zlmApiController.handleIllegalArgumentException(exception);

        assertNotNull(result);
        assertEquals(Integer.valueOf(0), result.getCode());
        assertEquals("Test error message", result.getMsg());
    }

    // ==================== 测试TCP会话管理接口 ====================

    @Test
    public void testGetAllSession() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        String localPort = "8080";
        String peerIp = "192.168.1.1";

        ServerResponse<List<TcpLink>> mockResponse = new ServerResponse<>();
        mockResponse.setCode(1);
        List<TcpLink> tcpLinks = new ArrayList<>();
        TcpLink tcpLink = new TcpLink();
        tcpLink.setIdentifier("test-id");
        tcpLinks.add(tcpLink);
        mockResponse.setData(tcpLinks);

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.getAllSession(testNode.getHost(), testNode.getSecret(), localPort, peerIp))
                    .thenReturn(mockResponse);

            ServerResponse<List<TcpLink>> result = zlmApiController.getAllSession(localPort, peerIp);

            assertNotNull(result);
            assertEquals(Integer.valueOf(1), result.getCode());
            assertEquals(1, result.getData().size());
            assertEquals("test-id", result.getData().get(0).getIdentifier());
            verify(nodeService).selectNode();
        }
    }

    @Test
    public void testKickSession() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        String sessionId = "session-123";

        ServerResponse<String> mockResponse = new ServerResponse<>();
        mockResponse.setCode(1);
        mockResponse.setData("success");

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.kickSession(testNode.getHost(), testNode.getSecret(), sessionId))
                    .thenReturn(mockResponse);

            ServerResponse<String> result = zlmApiController.kickSession(sessionId);

            assertNotNull(result);
            assertEquals(Integer.valueOf(1), result.getCode());
            assertEquals("success", result.getData());
            verify(nodeService).selectNode();
        }
    }
}