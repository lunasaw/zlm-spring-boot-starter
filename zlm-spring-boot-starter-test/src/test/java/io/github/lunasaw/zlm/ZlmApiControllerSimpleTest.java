package io.github.lunasaw.zlm;

import io.github.lunasaw.zlm.api.ZlmRestService;
import io.github.lunasaw.zlm.api.controller.ZlmApiController;
import io.github.lunasaw.zlm.config.ZlmNode;
import io.github.lunasaw.zlm.entity.*;
import io.github.lunasaw.zlm.entity.req.MediaReq;
import io.github.lunasaw.zlm.entity.req.SnapshotReq;
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
 * ZlmApiController简化单元测试类
 * 测试主要API方法的功能和Mock组件交互
 *
 * @author claude
 * @version 1.0
 * @date 2025/01/25
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ZlmApiControllerSimpleTest {

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

    @Test
    public void testGetAvailableNodeWithEmptyNodeKey() {
        // 模拟请求头有空的X-Node-Key
        when(request.getHeader("X-Node-Key")).thenReturn("  ");
        when(nodeService.selectNode()).thenReturn(testNode);

        // 通过反射调用私有方法
        ZlmNode result = (ZlmNode) ReflectionTestUtils.invokeMethod(zlmApiController, "getAvailableNode");

        assertNotNull(result);
        assertEquals(testNode.getServerId(), result.getServerId());
        verify(nodeService).selectNode();
        verify(nodeService, never()).getAvailableNode(anyString());
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
            assertNotNull(result.getData());
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
            assertTrue(result.getData().contains("api1"));
            assertTrue(result.getData().contains("api2"));
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
        statistic.setBuffer(20);
        mockResponse.setData(statistic);

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.getStatistic(testNode.getHost(), testNode.getSecret()))
                    .thenReturn(mockResponse);

            ServerResponse<ImportantObjectNum> result = zlmApiController.getStatistic();

            assertNotNull(result);
            assertEquals(Integer.valueOf(1), result.getCode());
            assertNotNull(result.getData());
            assertEquals(Integer.valueOf(10), result.getData().getMediaSource());
            assertEquals(Integer.valueOf(20), result.getData().getBuffer());
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
            assertEquals("test", result.getData().get(0).getStream());
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
        mockStatus.setCode(1);

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.isMediaOnline(testNode.getHost(), testNode.getSecret(), mediaReq))
                    .thenReturn(mockStatus);

            MediaOnlineStatus result = zlmApiController.isMediaOnline(mediaReq);

            assertNotNull(result);
            assertEquals(Boolean.TRUE, result.getOnline());
            assertEquals(Integer.valueOf(1), result.getCode());
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
            assertNotNull(result.getData());
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
        snapshotReq.setUrl("rtmp://localhost/live/test");
        snapshotReq.setTimeoutSec(30);

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.getSnap(testNode.getHost(), testNode.getSecret(), snapshotReq))
                    .thenReturn("snapshot-url");

            String result = zlmApiController.getSnap(snapshotReq);

            assertNotNull(result);
            assertEquals("snapshot-url", result);
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
        version.setBranchName("develop");
        mockResponse.setData(version);

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.getVersion(testNode.getHost(), testNode.getSecret()))
                    .thenReturn(mockResponse);

            ServerResponse<Version> result = zlmApiController.getVersionByNode(nodeId);

            assertNotNull(result);
            assertEquals(Integer.valueOf(1), result.getCode());
            assertNotNull(result.getData());
            assertEquals("2024-01-01 12:00:00", result.getData().getBuildTime());
            assertEquals("develop", result.getData().getBranchName());
            verify(nodeService).getAvailableNode(nodeId);
        }
    }

    @Test
    public void testGetMediaListByNode() {
        String nodeId = "test-node-id";
        MediaReq mediaReq = new MediaReq();
        mediaReq.setApp("live");
        mediaReq.setStream("test");
        when(nodeService.getAvailableNode(nodeId)).thenReturn(testNode);

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

            ServerResponse<List<MediaData>> result = zlmApiController.getMediaListByNode(nodeId, mediaReq);

            assertNotNull(result);
            assertEquals(Integer.valueOf(1), result.getCode());
            assertEquals(1, result.getData().size());
            assertEquals("live", result.getData().get(0).getApp());
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
        assertEquals(testNode.getHost(), result.get(0).getHost());
        assertEquals(testNode.getSecret(), result.get(0).getSecret());
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

    // ==================== 测试NodeSupplier和NodeService Mock ====================

    @Test
    public void testNodeSupplierMock() {
        // 测试NodeSupplier Mock是否正常工作
        List<ZlmNode> mockNodes = Arrays.asList(testNode);
        when(nodeSupplier.getNodes()).thenReturn(mockNodes);

        List<ZlmNode> result = nodeSupplier.getNodes();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testNode.getServerId(), result.get(0).getServerId());
        verify(nodeSupplier).getNodes();
    }

    @Test
    public void testNodeServiceMock() {
        // 测试NodeService Mock是否正常工作
        when(nodeService.selectNode()).thenReturn(testNode);
        when(nodeService.getAvailableNode("test-id")).thenReturn(testNode);

        ZlmNode result1 = nodeService.selectNode();
        ZlmNode result2 = nodeService.getAvailableNode("test-id");

        assertNotNull(result1);
        assertNotNull(result2);
        assertEquals(testNode.getServerId(), result1.getServerId());
        assertEquals(testNode.getServerId(), result2.getServerId());
        verify(nodeService).selectNode();
        verify(nodeService).getAvailableNode("test-id");
    }

    @Test
    public void testZlmHookServiceMock() {
        // 测试ZlmHookService Mock是否正常工作
        StreamProxyItem proxyItem = new StreamProxyItem();
        StreamKey streamKey = new StreamKey();
        streamKey.setKey("test-key");

        // 调用Hook服务方法（无返回值）
        zlmHookService.onProxyAdded(proxyItem, streamKey, request);

        // 验证方法被调用
        verify(zlmHookService).onProxyAdded(eq(proxyItem), eq(streamKey), eq(request));
    }

    @Test
    public void testHttpServletRequestMock() {
        // 测试HttpServletRequest Mock是否正常工作
        when(request.getHeader("X-Node-Key")).thenReturn("test-node");
        when(request.getHeader("User-Agent")).thenReturn("Test-Agent");

        String nodeKey = request.getHeader("X-Node-Key");
        String userAgent = request.getHeader("User-Agent");

        assertEquals("test-node", nodeKey);
        assertEquals("Test-Agent", userAgent);
        verify(request).getHeader("X-Node-Key");
        verify(request).getHeader("User-Agent");
    }
}