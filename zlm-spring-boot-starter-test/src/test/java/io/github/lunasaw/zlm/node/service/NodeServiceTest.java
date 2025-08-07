package io.github.lunasaw.zlm.node.service;

import io.github.lunasaw.zlm.config.ZlmNode;
import io.github.lunasaw.zlm.node.LoadBalancer;
import io.github.lunasaw.zlm.node.NodeSupplier;
import io.github.lunasaw.zlm.node.service.impl.NodeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * NodeService 单元测试
 *
 * @author luna
 * @date 2025/01/23
 */
@ExtendWith(MockitoExtension.class)
class NodeServiceTest {

    @Mock
    private LoadBalancer loadBalancer;

    @Mock
    private NodeSupplier nodeSupplier;

    @InjectMocks
    private NodeServiceImpl nodeService;

    private ZlmNode testNode1;
    private ZlmNode testNode2;
    private List<ZlmNode> testNodes;

    @BeforeEach
    void setUp() {
        testNode1 = new ZlmNode();
        testNode1.setServerId("node1");
        testNode1.setHost("192.168.1.100");
        testNode1.setSecret("test-secret-1");

        testNode2 = new ZlmNode();
        testNode2.setServerId("node2");
        testNode2.setHost("192.168.1.101");
        testNode2.setSecret("test-secret-2");

        testNodes = Arrays.asList(testNode1, testNode2);
    }

    @Test
    void testGetNode_Success() {
        // Given
        when(nodeSupplier.getNode("node1")).thenReturn(testNode1);

        // When
        ZlmNode result = nodeService.getNode("node1");

        // Then
        assertNotNull(result);
        assertEquals("node1", result.getServerId());
        assertEquals("192.168.1.100", result.getHost());
    }

    @Test
    void testGetNode_NodeNotFound() {
        // Given
        when(nodeSupplier.getNode("nonexistent")).thenReturn(null);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            nodeService.getNode("nonexistent");
        });
    }

    @Test
    void testGetNode_NullNodeKey() {
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            nodeService.getNode(null);
        });
    }

    @Test
    void testGetNode_EmptyNodeKey() {
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            nodeService.getNode("");
        });
    }

    @Test
    void testSelectNode_Success() {
        // Given
        when(loadBalancer.selectNode("test-key")).thenReturn(testNode1);

        // When
        ZlmNode result = nodeService.selectNode("test-key");

        // Then
        assertNotNull(result);
        assertEquals("node1", result.getServerId());
    }

    @Test
    void testSelectNode_NoAvailableNodes() {
        // Given
        when(loadBalancer.selectNode("test-key")).thenReturn(null);

        // When & Then
        assertThrows(IllegalStateException.class, () -> {
            nodeService.selectNode("test-key");
        });
    }

    @Test
    void testSelectNode_DefaultKey() {
        // Given
        when(loadBalancer.selectNode("default")).thenReturn(testNode2);

        // When
        ZlmNode result = nodeService.selectNode();

        // Then
        assertNotNull(result);
        assertEquals("node2", result.getServerId());
    }

    @Test
    void testGetAllNodes() {
        // Given
        when(nodeSupplier.getNodes()).thenReturn(testNodes);

        // When
        List<ZlmNode> result = nodeService.getAllNodes();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("node1", result.get(0).getServerId());
        assertEquals("node2", result.get(1).getServerId());
    }

    @Test
    void testHasNode_NodeExists() {
        // Given
        when(nodeSupplier.getNode("node1")).thenReturn(testNode1);

        // When
        boolean result = nodeService.hasNode("node1");

        // Then
        assertTrue(result);
    }

    @Test
    void testHasNode_NodeNotExists() {
        // Given
        when(nodeSupplier.getNode("nonexistent")).thenReturn(null);

        // When
        boolean result = nodeService.hasNode("nonexistent");

        // Then
        assertFalse(result);
    }

    @Test
    void testHasNode_NullNodeKey() {
        // When
        boolean result = nodeService.hasNode(null);

        // Then
        assertFalse(result);
    }

    @Test
    void testHasNode_EmptyNodeKey() {
        // When
        boolean result = nodeService.hasNode("");

        // Then
        assertFalse(result);
    }

    @Test
    void testGetNodeCount() {
        // Given
        when(nodeSupplier.getNodes()).thenReturn(testNodes);

        // When
        int result = nodeService.getNodeCount();

        // Then
        assertEquals(2, result);
    }

    @Test
    void testGetNodeCount_EmptyList() {
        // Given
        when(nodeSupplier.getNodes()).thenReturn(Arrays.asList());

        // When
        int result = nodeService.getNodeCount();

        // Then
        assertEquals(0, result);
    }

    @Test
    void testGetNodeCount_NullList() {
        // Given
        when(nodeSupplier.getNodes()).thenReturn(null);

        // When
        int result = nodeService.getNodeCount();

        // Then
        assertEquals(0, result);
    }
}