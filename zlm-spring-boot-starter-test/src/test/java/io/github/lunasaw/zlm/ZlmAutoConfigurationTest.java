package io.github.lunasaw.zlm;

import io.github.lunasaw.zlm.config.ZlmAutoConfiguration;
import io.github.lunasaw.zlm.config.ZlmProperties;
import io.github.lunasaw.zlm.node.LoadBalancer;
import io.github.lunasaw.zlm.node.NodeSupplier;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ZlmAutoConfiguration测试类
 *
 * @author luna
 * @date 2024/1/5
 */
@SpringBootTest(classes = {ZlmAutoConfiguration.class, ZlmAutoConfigurationTest.TestConfig.class})
@TestPropertySource(properties = {
        "zlm.enable=true",
        "zlm.balance=round_robin",
        "zlm.nodes[0].server-id=test-node-1",
        "zlm.nodes[0].host=http://127.0.0.1:9092",
        "zlm.nodes[0].secret=test-secret",
        "zlm.nodes[0].enabled=true",
        "zlm.nodes[0].hook-enabled=true"
})
public class ZlmAutoConfigurationTest {

    @TestConfiguration
    static class TestConfig {
        @Bean
        public ZlmProperties zlmProperties() {
            ZlmProperties properties = new ZlmProperties();
            // Spring Boot会自动绑定属性，这里主要用于测试
            return properties;
        }
    }

    @Test
    void testNodeSupplierBeanCreation() {
        // 这个测试主要验证NodeSupplier Bean能够正常创建
        assertDoesNotThrow(() -> {
            NodeSupplier supplier = new io.github.lunasaw.zlm.node.impl.DefaultNodeSupplier();
            assertNotNull(supplier);
            assertEquals("DefaultNodeSupplier", supplier.getName());
        });
    }

    @Test
    void testLoadBalancerConfiguration() {
        // 验证LoadBalancer配置逻辑
        assertDoesNotThrow(() -> {
            NodeSupplier nodeSupplier = new io.github.lunasaw.zlm.node.impl.DefaultNodeSupplier();

            // 模拟配置过程
            LoadBalancer balancer = new io.github.lunasaw.zlm.node.impl.RoundRobinLoadBalancer();
            balancer.setNodeSupplier(nodeSupplier);

            assertNotNull(balancer);
            assertEquals("RoundRobin", balancer.getType());
        });
    }
}