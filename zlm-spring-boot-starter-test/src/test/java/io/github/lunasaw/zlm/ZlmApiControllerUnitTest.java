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
 * ZlmApiController单元测试类
 * 测试所有API方法的功能和异常处理
 *
 * @author luna
 * @version 1.0
 * @date 2025/01/25
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ZlmApiControllerUnitTest {

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
    private ServerResponse<Version> mockVersionResponse;
    private ServerResponse<List<String>> mockStringListResponse;
    private ServerResponse<List<ThreadLoad>> mockThreadLoadResponse;
    private ServerResponse<ImportantObjectNum> mockStatisticResponse;
    private ServerResponse<List<ServerNodeConfig>> mockServerConfigResponse;
    private ServerResponse<String> mockStringResponse;
    private ServerResponse<Object> mockObjectResponse;
    private ServerResponse<List<MediaData>> mockMediaListResponse;
    private MediaOnlineStatus mockMediaOnlineStatus;
    private ServerResponse<MediaPlayer> mockMediaPlayerResponse;
    private ServerResponse<MediaInfo> mockMediaInfoResponse;
    private ServerResponse<PlayUrl> mockPlayUrlResponse;
    private ServerResponse<StreamKey> mockStreamKeyResponse;
    private ServerResponse<Mp4RecordFile> mockMp4RecordResponse;
    private DeleteRecordDirectory mockDeleteRecordResponse;
    private RtpInfoResult mockRtpInfoResult;
    private OpenRtpServerResult mockOpenRtpServerResult;
    private CloseRtpServerResult mockCloseRtpServerResult;
    private StartSendRtpResult mockStartSendRtpResult;
    private ServerResponse<List<RtpServer>> mockRtpServerListResponse;
    private ServerResponse<List<TcpLink>> mockTcpLinkResponse;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // 初始化测试节点
        testNode = new ZlmNode();
        testNode.setServerId("test-node");
        testNode.setHost("http://localhost:9092");
        testNode.setSecret("test-secret");
        testNode.setEnabled(true);

        // 初始化Mock响应对象
        initMockResponses();
    }

    private void initMockResponses() {
        // Version响应
        mockVersionResponse = new ServerResponse<>();
        mockVersionResponse.setCode(1);
        Version version = new Version();
        version.setBuildTime("2024-01-01 12:00:00");
        version.setBranchName("master");
        version.setCommitHash("abc123");
        mockVersionResponse.setData(version);

        // String列表响应
        mockStringListResponse = new ServerResponse<>();
        mockStringListResponse.setCode(1);
        mockStringListResponse.setData(Arrays.asList("api1", "api2"));

        // ThreadLoad响应
        mockThreadLoadResponse = new ServerResponse<>();
        mockThreadLoadResponse.setCode(1);
        List<ThreadLoad> threadLoads = new ArrayList<>();
        ThreadLoad threadLoad = new ThreadLoad();
        threadLoad.setLoad("50");
        threadLoad.setDelay("10");
        threadLoads.add(threadLoad);
        mockThreadLoadResponse.setData(threadLoads);

        // 统计信息响应
        mockStatisticResponse = new ServerResponse<>();
        mockStatisticResponse.setCode(1);
        ImportantObjectNum statistic = new ImportantObjectNum();
        statistic.setMediaSource(10);
        mockStatisticResponse.setData(statistic);

        // 服务器配置响应
        mockServerConfigResponse = new ServerResponse<>();
        mockServerConfigResponse.setCode(1);
        List<ServerNodeConfig> configs = new ArrayList<>();
        ServerNodeConfig config = new ServerNodeConfig();
        // ServerNodeConfig 可能有不同的属性结构
        configs.add(config);
        mockServerConfigResponse.setData(configs);

        // String响应
        mockStringResponse = new ServerResponse<>();
        mockStringResponse.setCode(1);
        mockStringResponse.setData("success");

        // Object响应
        mockObjectResponse = new ServerResponse<>();
        mockObjectResponse.setCode(1);
        mockObjectResponse.setData(new Object());

        // MediaData列表响应
        mockMediaListResponse = new ServerResponse<>();
        mockMediaListResponse.setCode(1);
        List<MediaData> mediaList = new ArrayList<>();
        MediaData mediaData = new MediaData();
        mediaData.setApp("live");
        mediaData.setStream("test");
        mediaList.add(mediaData);
        mockMediaListResponse.setData(mediaList);

        // MediaOnlineStatus响应
        mockMediaOnlineStatus = new MediaOnlineStatus();
        mockMediaOnlineStatus.setOnline(true);

        // MediaPlayer响应
        mockMediaPlayerResponse = new ServerResponse<>();
        mockMediaPlayerResponse.setCode(1);
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setIdentifier("test-id");
        mediaPlayer.setLocalPort(8080);
        mockMediaPlayerResponse.setData(mediaPlayer);

        // MediaInfo响应
        mockMediaInfoResponse = new ServerResponse<>();
        mockMediaInfoResponse.setCode(1);
        MediaInfo mediaInfo = new MediaInfo();
        mediaInfo.setDuration(3600);
        mockMediaInfoResponse.setData(mediaInfo);

        // PlayUrl响应
        mockPlayUrlResponse = new ServerResponse<>();
        mockPlayUrlResponse.setCode(1);
        PlayUrl playUrl = new PlayUrl();
        playUrl.setRtmp("rtmp://test/live/stream");
        mockPlayUrlResponse.setData(playUrl);

        // StreamKey响应
        mockStreamKeyResponse = new ServerResponse<>();
        mockStreamKeyResponse.setCode(1);
        StreamKey streamKey = new StreamKey();
        streamKey.setKey("test-key");
        mockStreamKeyResponse.setData(streamKey);

        // Mp4Record响应
        mockMp4RecordResponse = new ServerResponse<>();
        mockMp4RecordResponse.setCode(1);
        Mp4RecordFile mp4Record = new Mp4RecordFile();
        mp4Record.setCount(1);
        mockMp4RecordResponse.setData(mp4Record);

        // DeleteRecord响应
        mockDeleteRecordResponse = new DeleteRecordDirectory();
        mockDeleteRecordResponse.setDeleted(true);

        // RtpInfo响应
        mockRtpInfoResult = new RtpInfoResult();
        mockRtpInfoResult.setExist(true);

        // OpenRtpServer响应
        mockOpenRtpServerResult = new OpenRtpServerResult();
        mockOpenRtpServerResult.setPort(10000);

        // CloseRtpServer响应
        mockCloseRtpServerResult = new CloseRtpServerResult();
        mockCloseRtpServerResult.setHit(1);

        // StartSendRtp响应
        mockStartSendRtpResult = new StartSendRtpResult();
        mockStartSendRtpResult.setLocal_port(20000);

        // RtpServer列表响应
        mockRtpServerListResponse = new ServerResponse<>();
        mockRtpServerListResponse.setCode(1);
        List<RtpServer> rtpServers = new ArrayList<>();
        RtpServer rtpServer = new RtpServer();
        rtpServer.setPort(10000);
        rtpServers.add(rtpServer);
        mockRtpServerListResponse.setData(rtpServers);

        // TcpLink响应
        mockTcpLinkResponse = new ServerResponse<>();
        mockTcpLinkResponse.setCode(1);
        List<TcpLink> tcpLinks = new ArrayList<>();
        TcpLink tcpLink = new TcpLink();
        tcpLink.setIdentifier("test-id");
        tcpLinks.add(tcpLink);
        mockTcpLinkResponse.setData(tcpLinks);
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

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.getVersion(testNode.getHost(), testNode.getSecret()))
                    .thenReturn(mockVersionResponse);

            ServerResponse<Version> result = zlmApiController.getVersion();

            assertNotNull(result);
            assertEquals(1, result.getCode());
            assertEquals("1.0.0", result.getData().getVersion());
            verify(nodeService).selectNode();
        }
    }

    @Test
    public void testGetApiList() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        Map<String, String> params = new HashMap<>();
        params.put("test", "value");

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.getApiList(testNode.getHost(), testNode.getSecret(), params))
                    .thenReturn(mockStringListResponse);

            ServerResponse<List<String>> result = zlmApiController.getApiList(params);

            assertNotNull(result);
            assertEquals(1, result.getCode());
            assertEquals(2, result.getData().size());
            verify(nodeService).selectNode();
        }
    }

    @Test
    public void testGetThreadsLoad() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.getThreadsLoad(testNode.getHost(), testNode.getSecret()))
                    .thenReturn(mockThreadLoadResponse);

            ServerResponse<List<ThreadLoad>> result = zlmApiController.getThreadsLoad();

            assertNotNull(result);
            assertEquals(1, result.getCode());
            assertEquals(1, result.getData().size());
            assertEquals(50, result.getData().get(0).getLoad());
            verify(nodeService).selectNode();
        }
    }

    @Test
    public void testGetStatistic() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.getStatistic(testNode.getHost(), testNode.getSecret()))
                    .thenReturn(mockStatisticResponse);

            ServerResponse<ImportantObjectNum> result = zlmApiController.getStatistic();

            assertNotNull(result);
            assertEquals(1, result.getCode());
            assertEquals(10, result.getData().getMediaSourceSize());
            verify(nodeService).selectNode();
        }
    }

    @Test
    public void testGetWorkThreadsLoad() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.getWorkThreadsLoad(testNode.getHost(), testNode.getSecret()))
                    .thenReturn(mockThreadLoadResponse);

            ServerResponse<List<ThreadLoad>> result = zlmApiController.getWorkThreadsLoad();

            assertNotNull(result);
            assertEquals(1, result.getCode());
            assertEquals(1, result.getData().size());
            verify(nodeService).selectNode();
        }
    }

    @Test
    public void testGetServerConfig() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.getServerConfig(testNode.getHost(), testNode.getSecret()))
                    .thenReturn(mockServerConfigResponse);

            ServerResponse<List<ServerNodeConfig>> result = zlmApiController.getServerConfig();

            assertNotNull(result);
            assertEquals(1, result.getCode());
            assertEquals(1, result.getData().size());
            assertEquals("test.key", result.getData().get(0).getKey());
            verify(nodeService).selectNode();
        }
    }

    @Test
    public void testSetServerConfig() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        Map<String, String> params = new HashMap<>();
        params.put("key", "value");

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.setServerConfig(testNode.getHost(), testNode.getSecret(), params))
                    .thenReturn(mockStringResponse);

            ServerResponse<String> result = zlmApiController.setServerConfig(params);

            assertNotNull(result);
            assertEquals(1, result.getCode());
            assertEquals("success", result.getData());
            verify(nodeService).selectNode();
        }
    }

    @Test
    public void testRestartServer() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        Map<String, String> params = new HashMap<>();
        params.put("restart", "true");

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.restartServer(testNode.getHost(), testNode.getSecret(), params))
                    .thenReturn(mockObjectResponse);

            ServerResponse<Object> result = zlmApiController.restartServer(params);

            assertNotNull(result);
            assertEquals(1, result.getCode());
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

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.getMediaList(testNode.getHost(), testNode.getSecret(), mediaReq))
                    .thenReturn(mockMediaListResponse);

            ServerResponse<List<MediaData>> result = zlmApiController.getMediaList(mediaReq);

            assertNotNull(result);
            assertEquals(1, result.getCode());
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

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.closeStream(testNode.getHost(), testNode.getSecret(), mediaReq))
                    .thenReturn(mockStringResponse);

            ServerResponse<String> result = zlmApiController.closeStream(mediaReq);

            assertNotNull(result);
            assertEquals(1, result.getCode());
            assertEquals("success", result.getData());
            verify(nodeService).selectNode();
        }
    }

    @Test
    public void testCloseStreams() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        CloseStreamsReq closeStreamsReq = new CloseStreamsReq();

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.closeStreams(eq(testNode.getHost()), eq(testNode.getSecret()), any(Map.class)))
                    .thenReturn(mockObjectResponse);

            ServerResponse result = zlmApiController.closeStreams(closeStreamsReq);

            assertNotNull(result);
            assertEquals(1, result.getCode());
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

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.isMediaOnline(testNode.getHost(), testNode.getSecret(), mediaReq))
                    .thenReturn(mockMediaOnlineStatus);

            MediaOnlineStatus result = zlmApiController.isMediaOnline(mediaReq);

            assertNotNull(result);
            assertTrue(result.isOnline());
            verify(nodeService).selectNode();
        }
    }

    @Test
    public void testGetMediaPlayerList() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        MediaReq mediaReq = new MediaReq();
        mediaReq.setApp("live");
        mediaReq.setStream("test");

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.getMediaPlayerList(testNode.getHost(), testNode.getSecret(), mediaReq))
                    .thenReturn(mockMediaPlayerResponse);

            ServerResponse<MediaPlayer> result = zlmApiController.getMediaPlayerList(mediaReq);

            assertNotNull(result);
            assertEquals(1, result.getCode());
            assertEquals(1, result.getData().getCount());
            verify(nodeService).selectNode();
        }
    }

    @Test
    public void testGetMediaInfo() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        MediaReq mediaReq = new MediaReq();
        mediaReq.setApp("live");
        mediaReq.setStream("test");

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.getMediaInfo(testNode.getHost(), testNode.getSecret(), mediaReq))
                    .thenReturn(mockMediaInfoResponse);

            ServerResponse<MediaInfo> result = zlmApiController.getMediaInfo(mediaReq);

            assertNotNull(result);
            assertEquals(1, result.getCode());
            assertEquals(3600, result.getData().getDuration());
            verify(nodeService).selectNode();
        }
    }

    @Test
    public void testGetPlaybackUrls() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        MediaReq mediaReq = new MediaReq();
        mediaReq.setApp("live");
        mediaReq.setStream("test");

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.getPlaybackUrls(testNode.getHost(), testNode.getSecret(), mediaReq))
                    .thenReturn(mockPlayUrlResponse);

            ServerResponse<PlayUrl> result = zlmApiController.getPlaybackUrls(mediaReq);

            assertNotNull(result);
            assertEquals(1, result.getCode());
            assertEquals("rtmp://test/live/stream", result.getData().getRtmp());
            verify(nodeService).selectNode();
        }
    }

    @Test
    public void testBroadcastMessage() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        Map<String, String> params = new HashMap<>();
        params.put("message", "test message");

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.broadcastMessage(testNode.getHost(), testNode.getSecret(), params))
                    .thenReturn(mockObjectResponse);

            ServerResponse result = zlmApiController.broadcastMessage(params);

            assertNotNull(result);
            assertEquals(1, result.getCode());
            verify(nodeService).selectNode();
        }
    }

    // ==================== 测试TCP会话管理接口 ====================

    @Test
    public void testGetAllSession() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        String localPort = "8080";
        String peerIp = "192.168.1.1";

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.getAllSession(testNode.getHost(), testNode.getSecret(), localPort, peerIp))
                    .thenReturn(mockTcpLinkResponse);

            ServerResponse<List<TcpLink>> result = zlmApiController.getAllSession(localPort, peerIp);

            assertNotNull(result);
            assertEquals(1, result.getCode());
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

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.kickSession(testNode.getHost(), testNode.getSecret(), sessionId))
                    .thenReturn(mockStringResponse);

            ServerResponse<String> result = zlmApiController.kickSession(sessionId);

            assertNotNull(result);
            assertEquals(1, result.getCode());
            assertEquals("success", result.getData());
            verify(nodeService).selectNode();
        }
    }

    @Test
    public void testKickSessions() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        Map<String, String> params = new HashMap<>();
        params.put("localPort", "8080");

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.kickSessions(testNode.getHost(), testNode.getSecret(), params))
                    .thenReturn(mockStringResponse);

            ServerResponse<String> result = zlmApiController.kickSessions(params);

            assertNotNull(result);
            assertEquals(1, result.getCode());
            assertEquals("success", result.getData());
            verify(nodeService).selectNode();
        }
    }

    // ==================== 测试异常处理 ====================

    @Test
    public void testHandleIllegalArgumentException() {
        IllegalArgumentException exception = new IllegalArgumentException("Test error message");

        ServerResponse<String> result = zlmApiController.handleIllegalArgumentException(exception);

        assertNotNull(result);
        assertEquals(0, result.getCode());
        assertEquals("Test error message", result.getMsg());
    }

    // ==================== 测试指定节点操作接口 ====================

    @Test
    public void testGetVersionByNode() {
        String nodeId = "test-node-id";
        when(nodeService.getAvailableNode(nodeId)).thenReturn(testNode);

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.getVersion(testNode.getHost(), testNode.getSecret()))
                    .thenReturn(mockVersionResponse);

            ServerResponse<Version> result = zlmApiController.getVersionByNode(nodeId);

            assertNotNull(result);
            assertEquals(1, result.getCode());
            assertEquals("1.0.0", result.getData().getVersion());
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

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.getMediaList(testNode.getHost(), testNode.getSecret(), mediaReq))
                    .thenReturn(mockMediaListResponse);

            ServerResponse<List<MediaData>> result = zlmApiController.getMediaListByNode(nodeId, mediaReq);

            assertNotNull(result);
            assertEquals(1, result.getCode());
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
        verify(nodeSupplier).getNodes();
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

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.addStreamProxy(testNode.getHost(), testNode.getSecret(), streamProxyItem))
                    .thenReturn(mockStreamKeyResponse);

            ServerResponse<StreamKey> result = zlmApiController.addStreamProxy(streamProxyItem);

            assertNotNull(result);
            assertEquals(1, result.getCode());
            assertEquals("test-key", result.getData().getKey());
            verify(nodeService).selectNode();
            verify(zlmHookService).onProxyAdded(eq(streamProxyItem), eq(mockStreamKeyResponse.getData()), eq(request));
        }
    }

    @Test
    public void testDelStreamProxy() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        String key = "proxy-key-123";

        ServerResponse<StreamKey.StringDelFlag> mockDelResponse = new ServerResponse<>();
        mockDelResponse.setCode(1);
        StreamKey.StringDelFlag delFlag = new StreamKey.StringDelFlag();
        delFlag.setFlag(true);
        mockDelResponse.setData(delFlag);

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.delStreamProxy(testNode.getHost(), testNode.getSecret(), key))
                    .thenReturn(mockDelResponse);

            ServerResponse<StreamKey.StringDelFlag> result = zlmApiController.delStreamProxy(key);

            assertNotNull(result);
            assertEquals(1, result.getCode());
            assertTrue(result.getData().isFlag());
            verify(nodeService).selectNode();
        }
    }

    @Test
    public void testGetProxyInfo() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        Map<String, String> params = new HashMap<>();
        params.put("key", "proxy-key");

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.getProxyInfo(testNode.getHost(), testNode.getSecret(), params))
                    .thenReturn(mockObjectResponse);

            ServerResponse result = zlmApiController.getProxyInfo(params);

            assertNotNull(result);
            assertEquals(1, result.getCode());
            verify(nodeService).selectNode();
        }
    }

    @Test
    public void testAddStreamPusherProxy() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        StreamPusherItem streamPusherItem = new StreamPusherItem();
        streamPusherItem.setApp("live");
        streamPusherItem.setStream("test");
        streamPusherItem.setDst_url("rtmp://push.example.com/live/stream");

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.addStreamPusherProxy(testNode.getHost(), testNode.getSecret(), streamPusherItem))
                    .thenReturn(mockStreamKeyResponse);

            ServerResponse<StreamKey> result = zlmApiController.addStreamPusherProxy(streamPusherItem);

            assertNotNull(result);
            assertEquals(1, result.getCode());
            assertEquals("test-key", result.getData().getKey());
            verify(nodeService).selectNode();
        }
    }

    @Test
    public void testDelStreamPusherProxy() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        String key = "pusher-key-123";

        ServerResponse<StreamKey.StringDelFlag> mockDelResponse = new ServerResponse<>();
        mockDelResponse.setCode(1);
        StreamKey.StringDelFlag delFlag = new StreamKey.StringDelFlag();
        delFlag.setFlag(true);
        mockDelResponse.setData(delFlag);

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.delStreamPusherProxy(testNode.getHost(), testNode.getSecret(), key))
                    .thenReturn(mockDelResponse);

            ServerResponse<StreamKey.StringDelFlag> result = zlmApiController.delStreamPusherProxy(key);

            assertNotNull(result);
            assertEquals(1, result.getCode());
            assertTrue(result.getData().isFlag());
            verify(nodeService).selectNode();
        }
    }

    @Test
    public void testGetProxyPusherInfo() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        Map<String, String> params = new HashMap<>();
        params.put("key", "pusher-key");

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.getProxyPusherInfo(testNode.getHost(), testNode.getSecret(), params))
                    .thenReturn(mockObjectResponse);

            ServerResponse result = zlmApiController.getProxyPusherInfo(params);

            assertNotNull(result);
            assertEquals(1, result.getCode());
            verify(nodeService).selectNode();
        }
    }

    // ==================== 测试FFmpeg管理接口 ====================

    @Test
    public void testAddFFmpegSource() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        StreamFfmpegItem streamFfmpegItem = new StreamFfmpegItem();
        streamFfmpegItem.setSrc_url("rtmp://source.com/live/stream");
        streamFfmpegItem.setDst_url("rtmp://localhost/live/test");

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.addFFmpegSource(testNode.getHost(), testNode.getSecret(), streamFfmpegItem))
                    .thenReturn(mockStreamKeyResponse);

            ServerResponse<StreamKey> result = zlmApiController.addFFmpegSource(streamFfmpegItem);

            assertNotNull(result);
            assertEquals(1, result.getCode());
            assertEquals("test-key", result.getData().getKey());
            verify(nodeService).selectNode();
        }
    }

    @Test
    public void testDelFFmpegSource() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        String key = "ffmpeg-key-123";

        ServerResponse<StreamKey.StringDelFlag> mockDelResponse = new ServerResponse<>();
        mockDelResponse.setCode(1);
        StreamKey.StringDelFlag delFlag = new StreamKey.StringDelFlag();
        delFlag.setFlag(true);
        mockDelResponse.setData(delFlag);

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.delFFmpegSource(testNode.getHost(), testNode.getSecret(), key))
                    .thenReturn(mockDelResponse);

            ServerResponse<StreamKey.StringDelFlag> result = zlmApiController.delFFmpegSource(key);

            assertNotNull(result);
            assertEquals(1, result.getCode());
            assertTrue(result.getData().isFlag());
            verify(nodeService).selectNode();
        }
    }

    // ==================== 测试录制管理接口 ====================

    @Test
    public void testGetMp4RecordFile() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        RecordReq recordReq = new RecordReq();
        recordReq.setApp("live");
        recordReq.setStream("test");

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.getMp4RecordFile(testNode.getHost(), testNode.getSecret(), recordReq))
                    .thenReturn(mockMp4RecordResponse);

            ServerResponse<Mp4RecordFile> result = zlmApiController.getMp4RecordFile(recordReq);

            assertNotNull(result);
            assertEquals(1, result.getCode());
            assertEquals(1, result.getData().getCount());
            verify(nodeService).selectNode();
        }
    }

    @Test
    public void testDeleteRecordDirectory() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        Map<String, String> params = new HashMap<>();
        params.put("path", "/record/path");

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.deleteRecordDirectory(testNode.getHost(), testNode.getSecret(), params))
                    .thenReturn(mockDeleteRecordResponse);

            DeleteRecordDirectory result = zlmApiController.deleteRecordDirectory(params);

            assertNotNull(result);
            assertTrue(result.isDeleted());
            verify(nodeService).selectNode();
        }
    }

    @Test
    public void testStartRecord() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        RecordReq recordReq = new RecordReq();
        recordReq.setApp("live");
        recordReq.setStream("test");
        recordReq.setType(1); // mp4

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.startRecord(testNode.getHost(), testNode.getSecret(), recordReq))
                    .thenReturn(mockStringResponse);

            ServerResponse<String> result = zlmApiController.startRecord(recordReq);

            assertNotNull(result);
            assertEquals(1, result.getCode());
            assertEquals("success", result.getData());
            verify(nodeService).selectNode();
        }
    }

    @Test
    public void testSetRecordSpeed() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        RecordReq recordReq = new RecordReq();
        recordReq.setApp("live");
        recordReq.setStream("test");
        recordReq.setSpeed(2.0f); // 2x speed

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.setRecordSpeed(testNode.getHost(), testNode.getSecret(), recordReq))
                    .thenReturn(mockStringResponse);

            ServerResponse<String> result = zlmApiController.setRecordSpeed(recordReq);

            assertNotNull(result);
            assertEquals(1, result.getCode());
            assertEquals("success", result.getData());
            verify(nodeService).selectNode();
        }
    }

    @Test
    public void testSeekRecordStamp() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        RecordReq recordReq = new RecordReq();
        recordReq.setApp("live");
        recordReq.setStream("test");
        recordReq.setStamp(3600); // seek to 1 hour

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.seekRecordStamp(testNode.getHost(), testNode.getSecret(), recordReq))
                    .thenReturn(mockStringResponse);

            ServerResponse<String> result = zlmApiController.seekRecordStamp(recordReq);

            assertNotNull(result);
            assertEquals(1, result.getCode());
            assertEquals("success", result.getData());
            verify(nodeService).selectNode();
        }
    }

    @Test
    public void testStopRecord() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        RecordReq recordReq = new RecordReq();
        recordReq.setApp("live");
        recordReq.setStream("test");

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.stopRecord(testNode.getHost(), testNode.getSecret(), recordReq))
                    .thenReturn(mockStringResponse);

            ServerResponse<String> result = zlmApiController.stopRecord(recordReq);

            assertNotNull(result);
            assertEquals(1, result.getCode());
            assertEquals("success", result.getData());
            verify(nodeService).selectNode();
        }
    }

    @Test
    public void testIsRecording() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        RecordReq recordReq = new RecordReq();
        recordReq.setApp("live");
        recordReq.setStream("test");

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.isRecording(testNode.getHost(), testNode.getSecret(), recordReq))
                    .thenReturn(mockStringResponse);

            ServerResponse<String> result = zlmApiController.isRecording(recordReq);

            assertNotNull(result);
            assertEquals(1, result.getCode());
            assertEquals("success", result.getData());
            verify(nodeService).selectNode();
        }
    }

    @Test
    public void testGetMp4RecordSummary() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        Map<String, String> params = new HashMap<>();
        params.put("app", "live");
        params.put("stream", "test");

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.getMp4RecordSummary(testNode.getHost(), testNode.getSecret(), params))
                    .thenReturn(mockStringResponse);

            ServerResponse<String> result = zlmApiController.getMp4RecordSummary(params);

            assertNotNull(result);
            assertEquals(1, result.getCode());
            assertEquals("success", result.getData());
            verify(nodeService).selectNode();
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

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.getRtpInfo(testNode.getHost(), testNode.getSecret(), streamId))
                    .thenReturn(mockRtpInfoResult);

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
        req.setStream_id("test-stream");
        req.setPort(10000);

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.openRtpServer(testNode.getHost(), testNode.getSecret(), req))
                    .thenReturn(mockOpenRtpServerResult);

            OpenRtpServerResult result = zlmApiController.openRtpServer(req);

            assertNotNull(result);
            assertEquals(10000, result.getPort());
            verify(nodeService).selectNode();
        }
    }

    @Test
    public void testOpenRtpServerMultiplex() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        OpenRtpServerReq req = new OpenRtpServerReq();
        req.setStream_id("test-stream");
        req.setPort(10000);

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.openRtpServerMultiplex(testNode.getHost(), testNode.getSecret(), req))
                    .thenReturn(mockOpenRtpServerResult);

            OpenRtpServerResult result = zlmApiController.openRtpServerMultiplex(req);

            assertNotNull(result);
            assertEquals(10000, result.getPort());
            verify(nodeService).selectNode();
        }
    }

    @Test
    public void testConnectRtpServer() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        ConnectRtpServerReq req = new ConnectRtpServerReq();
        req.setStream_id("test-stream");
        req.setDst_url("127.0.0.1");
        req.setDst_port(10000);

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.connectRtpServer(testNode.getHost(), testNode.getSecret(), req))
                    .thenReturn(mockOpenRtpServerResult);

            OpenRtpServerResult result = zlmApiController.connectRtpServer(req);

            assertNotNull(result);
            assertEquals(10000, result.getPort());
            verify(nodeService).selectNode();
        }
    }

    @Test
    public void testCloseRtpServer() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        String streamId = "test-stream-id";

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.closeRtpServer(testNode.getHost(), testNode.getSecret(), streamId))
                    .thenReturn(mockCloseRtpServerResult);

            CloseRtpServerResult result = zlmApiController.closeRtpServer(streamId);

            assertNotNull(result);
            assertEquals(1, result.getHit());
            verify(nodeService).selectNode();
        }
    }

    @Test
    public void testUpdateRtpServerSSRC() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        String streamId = "test-stream-id";
        String ssrc = "123456";

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.updateRtpServerSSRC(testNode.getHost(), testNode.getSecret(), streamId, ssrc))
                    .thenReturn(mockStringResponse);

            ServerResponse<String> result = zlmApiController.updateRtpServerSSRC(streamId, ssrc);

            assertNotNull(result);
            assertEquals(1, result.getCode());
            assertEquals("success", result.getData());
            verify(nodeService).selectNode();
        }
    }

    @Test
    public void testPauseRtpCheck() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        String streamId = "test-stream-id";

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.pauseRtpCheck(testNode.getHost(), testNode.getSecret(), streamId))
                    .thenReturn(mockStringResponse);

            ServerResponse<String> result = zlmApiController.pauseRtpCheck(streamId);

            assertNotNull(result);
            assertEquals(1, result.getCode());
            assertEquals("success", result.getData());
            verify(nodeService).selectNode();
        }
    }

    @Test
    public void testResumeRtpCheck() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        String streamId = "test-stream-id";

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.resumeRtpCheck(testNode.getHost(), testNode.getSecret(), streamId))
                    .thenReturn(mockStringResponse);

            ServerResponse<String> result = zlmApiController.resumeRtpCheck(streamId);

            assertNotNull(result);
            assertEquals(1, result.getCode());
            assertEquals("success", result.getData());
            verify(nodeService).selectNode();
        }
    }

    @Test
    public void testListRtpServer() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.listRtpServer(testNode.getHost(), testNode.getSecret()))
                    .thenReturn(mockRtpServerListResponse);

            ServerResponse<List<RtpServer>> result = zlmApiController.listRtpServer();

            assertNotNull(result);
            assertEquals(1, result.getCode());
            assertEquals(1, result.getData().size());
            assertEquals(10000, result.getData().get(0).getPort());
            verify(nodeService).selectNode();
        }
    }

    // ==================== 测试RTP发送管理接口 ====================

    @Test
    public void testStartSendRtp() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        StartSendRtpReq req = new StartSendRtpReq();
        req.setApp("live");
        req.setStream("test");
        req.setDst_url("192.168.1.100");
        req.setDst_port(10000);

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.startSendRtp(testNode.getHost(), testNode.getSecret(), req))
                    .thenReturn(mockStartSendRtpResult);

            StartSendRtpResult result = zlmApiController.startSendRtp(req);

            assertNotNull(result);
            assertEquals(20000, result.getLocal_port());
            verify(nodeService).selectNode();
        }
    }

    @Test
    public void testStartSendRtpPassive() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        StartSendRtpReq req = new StartSendRtpReq();
        req.setApp("live");
        req.setStream("test");

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.startSendRtpPassive(testNode.getHost(), testNode.getSecret(), req))
                    .thenReturn(mockStartSendRtpResult);

            StartSendRtpResult result = zlmApiController.startSendRtpPassive(req);

            assertNotNull(result);
            assertEquals(20000, result.getLocal_port());
            verify(nodeService).selectNode();
        }
    }

    @Test
    public void testStopSendRtp() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        CloseSendRtpReq req = new CloseSendRtpReq();
        req.setApp("live");
        req.setStream("test");

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.stopSendRtp(testNode.getHost(), testNode.getSecret(), req))
                    .thenReturn(mockStringResponse);

            ServerResponse<String> result = zlmApiController.stopSendRtp(req);

            assertNotNull(result);
            assertEquals(1, result.getCode());
            assertEquals("success", result.getData());
            verify(nodeService).selectNode();
        }
    }

    // ==================== 测试MP4文件管理接口 ====================

    @Test
    public void testStartMultiMp4Publish() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        Map<String, String> params = new HashMap<>();
        params.put("file_path", "/path/to/video.mp4");

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.startMultiMp4Publish(testNode.getHost(), testNode.getSecret(), params))
                    .thenReturn(mockObjectResponse);

            ServerResponse result = zlmApiController.startMultiMp4Publish(params);

            assertNotNull(result);
            assertEquals(1, result.getCode());
            verify(nodeService).selectNode();
        }
    }

    @Test
    public void testStopMultiMp4Publish() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        Map<String, String> params = new HashMap<>();
        params.put("app", "live");
        params.put("stream", "test");

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.stopMultiMp4Publish(testNode.getHost(), testNode.getSecret(), params))
                    .thenReturn(mockObjectResponse);

            ServerResponse result = zlmApiController.stopMultiMp4Publish(params);

            assertNotNull(result);
            assertEquals(1, result.getCode());
            verify(nodeService).selectNode();
        }
    }

    @Test
    public void testLoadMP4File() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        Map<String, String> params = new HashMap<>();
        params.put("file_path", "/path/to/video.mp4");

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.loadMP4File(testNode.getHost(), testNode.getSecret(), params))
                    .thenReturn(mockObjectResponse);

            ServerResponse result = zlmApiController.loadMP4File(params);

            assertNotNull(result);
            assertEquals(1, result.getCode());
            verify(nodeService).selectNode();
        }
    }

    // ==================== 测试存储管理接口 ====================

    @Test
    public void testGetStorageSpace() {
        when(request.getHeader("X-Node-Key")).thenReturn(null);
        when(nodeService.selectNode()).thenReturn(testNode);
        Map<String, String> params = new HashMap<>();
        params.put("path", "/storage");

        try (MockedStatic<ZlmRestService> mockedStatic = mockStatic(ZlmRestService.class)) {
            mockedStatic.when(() -> ZlmRestService.getStorageSpace(testNode.getHost(), testNode.getSecret(), params))
                    .thenReturn(mockStringResponse);

            ServerResponse<String> result = zlmApiController.getStorageSpace(params);

            assertNotNull(result);
            assertEquals(1, result.getCode());
            assertEquals("success", result.getData());
            verify(nodeService).selectNode();
        }
    }
}