package io.github.lunasaw.zlm;

import io.github.lunasaw.zlm.api.controller.ZlmApiController;
import io.github.lunasaw.zlm.config.ZlmNode;
import io.github.lunasaw.zlm.entity.ServerResponse;
import io.github.lunasaw.zlm.entity.Version;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author luna
 * @version 1.0
 * @date 2024/1/15
 * @description: ZLM API 集成测试 - 真正的外部调用测试
 * <p>
 * 注意：此测试需要真实的 ZLM 服务器运行在 127.0.0.1:9092
 * 如果没有可用的 ZLM 服务器，测试可能会失败
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZlmApplicationTest.class)
@ActiveProfiles("dev")
public class ZlmApiIntegrationTest {

    @Autowired
    private ZlmApiController zlmApiController;

    /**
     * 测试获取版本信息接口
     * 这是一个真正的集成测试，会调用实际的 ZLM 服务器
     */
    @Test
    public void testGetVersionIntegration() {
        try {
            // 直接调用 controller 方法
            ServerResponse<Version> response = zlmApiController.getVersion();

            // 验证响应体
            assertNotNull("响应体不应为空", response);

            // 验证业务响应码
            assertEquals("业务响应码应该为0", Integer.valueOf(0), response.getCode());

            // 验证版本信息
            Version version = response.getData();
            assertNotNull("版本信息不应为空", version);

            System.out.println("=== 版本信息 ===");
            System.out.println("响应码: " + response.getCode());
            System.out.println("版本数据: " + version);
            System.out.println("测试通过：成功获取到ZLM服务器版本信息");

        } catch (Exception e) {
            // 如果是连接异常，给出友好的提示
            if (e.getMessage() != null && (e.getMessage().contains("Connection refused") ||
                    e.getMessage().contains("ConnectException") || e.getMessage().contains("connect timed out"))) {
                System.err.println("=== 连接失败 ===");
                System.err.println("无法连接到ZLM服务器 (127.0.0.1:9092)");
                System.err.println("请确保ZLM服务器正在运行，然后重新执行测试");
                System.err.println("如果没有可用的ZLM服务器，可以跳过此集成测试");

                // 在CI环境中，我们可以选择跳过这个测试
                // 但在本地开发环境中，我们希望知道连接失败
                fail("无法连接到ZLM服务器，请确保服务器正在运行。错误信息: " + e.getMessage());
            } else {
                // 其他异常直接抛出
                fail("测试执行失败: " + e.getMessage());
            }
        }
    }

    /**
     * 测试使用指定节点获取版本信息
     */
    @Test
    public void testGetVersionWithSpecificNode() {
        try {
            // 直接调用 controller 方法，使用指定的节点ID
            ServerResponse<Version> response = zlmApiController.getVersionByNode("zlm");

            // 验证响应体
            assertNotNull("响应体不应为空", response);

            // 验证业务响应码
            assertEquals("业务响应码应该为0", Integer.valueOf(0), response.getCode());

            // 验证版本信息
            Version version = response.getData();
            assertNotNull("版本信息不应为空", version);

            System.out.println("=== 指定节点版本信息 ===");
            System.out.println("节点ID: zlm");
            System.out.println("响应码: " + response.getCode());
            System.out.println("版本数据: " + version);
            System.out.println("测试通过：成功通过指定节点获取到ZLM服务器版本信息");

        } catch (Exception e) {
            if (e.getMessage() != null && (e.getMessage().contains("Connection refused") ||
                    e.getMessage().contains("ConnectException") || e.getMessage().contains("connect timed out"))) {
                System.err.println("=== 连接失败 ===");
                System.err.println("无法连接到ZLM服务器 (127.0.0.1:9092)");
                System.err.println("请确保ZLM服务器正在运行，然后重新执行测试");
                fail("无法连接到ZLM服务器，请确保服务器正在运行。错误信息: " + e.getMessage());
            } else {
                fail("测试执行失败: " + e.getMessage());
            }
        }
    }

    /**
     * 测试不存在的节点应该返回错误
     */
    @Test
    public void testGetVersionWithNonExistentNode() {
        try {
            // 直接调用 controller 方法，使用不存在的节点ID
            zlmApiController.getVersionByNode("non-existent-node");
            fail("应该抛出异常，因为节点不存在");
        } catch (IllegalArgumentException e) {
            // 验证错误信息
            assertNotNull("错误信息不应为空", e.getMessage());
            assertTrue("错误信息应该包含节点不存在的提示", e.getMessage().contains("节点不存在"));

            System.out.println("=== 不存在节点测试 ===");
            System.out.println("错误信息: " + e.getMessage());
            System.out.println("测试通过：正确处理了不存在的节点请求");
        } catch (Exception e) {
            fail("应该抛出 IllegalArgumentException，但抛出了: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    /**
     * 测试获取所有节点列表
     */
    @Test
    public void testGetAllNodes() {
        try {
            // 直接调用 controller 方法
            List<ZlmNode> nodes = zlmApiController.getAllNodes();

            // 验证响应体
            assertNotNull("节点列表不应为空", nodes);
            assertTrue("至少应该有一个节点", nodes.size() > 0);

            // 验证节点信息
            ZlmNode firstNode = nodes.get(0);
            assertNotNull("节点对象不应为空", firstNode);
            assertEquals("节点ID应该是zlm", "zlm", firstNode.getServerId());

            System.out.println("=== 所有节点列表 ===");
            System.out.println("节点数量: " + nodes.size());
            for (ZlmNode node : nodes) {
                System.out.println("节点ID: " + node.getServerId() + ", 地址: " + node.getHost());
            }
            System.out.println("测试通过：成功获取到所有节点列表");

        } catch (Exception e) {
            fail("测试执行失败: " + e.getMessage());
        }
    }
}