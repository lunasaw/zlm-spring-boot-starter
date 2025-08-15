package io.github.lunasaw.zlm;

import io.github.lunasaw.zlm.config.ZlmNode;
import io.github.lunasaw.zlm.config.ZlmProperties;
import io.github.lunasaw.zlm.hook.service.ZlmHookService;
import io.github.lunasaw.zlm.hook.service.impl.DefaultZlmHookServiceImpl;
import io.github.lunasaw.zlm.node.LoadBalancer;
import io.github.lunasaw.zlm.node.NodeSupplier;
import io.github.lunasaw.zlm.node.impl.DefaultNodeSupplier;
import io.github.lunasaw.zlm.node.impl.RoundRobinLoadBalancer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ZLM服务基础单元测试
 * 测试ZLM核心组件的基本功能，不依赖外部服务
 *
 * @author luna
 * @date 2024/1/5
 */
public class ZlmServiceUnitTest {

    @Test
    void testZlmNodeBasicFunctionality() {
        // 测试ZLM节点的基本功能
        ZlmNode node = new ZlmNode();

        // 测试基本属性设置
        node.setServerId("test-node");
        node.setHost("http://localhost:9092");
        node.setSecret("test-secret");
        node.setEnabled(true);
        node.setHookEnabled(true);

        // 验证属性
        assertEquals("test-node", node.getServerId());
        assertEquals("http://localhost:9092", node.getHost());
        assertEquals("test-secret", node.getSecret());
        assertTrue(node.isEnabled());
        assertTrue(node.isHookEnabled());
    }

    @Test
    void testZlmPropertiesBasicCreation() {
        // 测试ZLM配置属性基本创建
        ZlmProperties properties = new ZlmProperties();

        assertNotNull(properties);
        properties.setEnable(true);
        assertTrue(properties.isEnable());
    }

    @Test
    void testNodeSupplierBasicFunctionality() {
        // 测试节点提供器基本功能
        NodeSupplier nodeSupplier = new DefaultNodeSupplier();
        assertNotNull(nodeSupplier);
        assertEquals("DefaultNodeSupplier", nodeSupplier.getName());
    }

    @Test
    void testLoadBalancerBasicFunctionality() {
        // 测试负载均衡器基本功能
        LoadBalancer loadBalancer = new RoundRobinLoadBalancer();
        assertNotNull(loadBalancer);
        assertEquals("RoundRobin", loadBalancer.getType());

        // 测试设置NodeSupplier
        NodeSupplier nodeSupplier = new DefaultNodeSupplier();
        loadBalancer.setNodeSupplier(nodeSupplier);

        // 验证设置成功（通过不报错验证）
        assertDoesNotThrow(() -> {
            loadBalancer.toString();
        });
    }

    @Test
    void testHookServiceCreation() {
        // 测试Hook服务创建
        ZlmHookService hookService = new DefaultZlmHookServiceImpl();
        assertNotNull(hookService);
        assertTrue(hookService instanceof DefaultZlmHookServiceImpl);

        // 测试基本的Hook处理方法存在
        assertDoesNotThrow(() -> {
            hookService.toString(); // 基本的Object方法
        });
    }

    @Test
    void testZlmNodeEquality() {
        // 测试ZLM节点对象相等性
        ZlmNode node1 = new ZlmNode();
        node1.setServerId("test-id");
        node1.setHost("http://localhost:9092");
        node1.setSecret("secret123");

        ZlmNode node2 = new ZlmNode();
        node2.setServerId("test-id");
        node2.setHost("http://localhost:9092");
        node2.setSecret("secret123");

        assertEquals(node1, node2);
        assertEquals(node1.hashCode(), node2.hashCode());

        // 测试不相等情况
        node2.setServerId("different-id");
        assertNotEquals(node1, node2);
    }

    @Test
    void testZlmNodeDefaultValues() {
        // 测试ZLM节点默认值
        ZlmNode node = new ZlmNode();

        // 测试默认值
        assertFalse(node.isEnabled());
        assertFalse(node.isHookEnabled());

        // 测试设置值
        node.setEnabled(true);
        node.setHookEnabled(true);
        node.setServerId("validation-test");
        node.setHost("http://test.example.com:9092");
        node.setSecret("validation-secret");

        assertTrue(node.isEnabled());
        assertTrue(node.isHookEnabled());
        assertEquals("validation-test", node.getServerId());
        assertEquals("http://test.example.com:9092", node.getHost());
        assertEquals("validation-secret", node.getSecret());
    }

    @Test
    void testServiceComponentsInitialization() {
        // 测试服务组件初始化过程
        assertDoesNotThrow(() -> {
            // 模拟基本的Spring Boot组件创建过程
            ZlmProperties properties = new ZlmProperties();
            properties.setEnable(true);
            assertNotNull(properties);

            NodeSupplier supplier = new DefaultNodeSupplier();
            assertNotNull(supplier);

            LoadBalancer balancer = new RoundRobinLoadBalancer();
            balancer.setNodeSupplier(supplier);
            assertNotNull(balancer);

            ZlmHookService service = new DefaultZlmHookServiceImpl();
            assertNotNull(service);
        });
    }
}