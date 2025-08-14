package io.github.lunasaw.zlm;

import io.github.lunasaw.zlm.config.ZlmAutoConfiguration;
import io.github.lunasaw.zlm.config.ZlmNode;
import io.github.lunasaw.zlm.config.ZlmProperties;
import io.github.lunasaw.zlm.node.service.NodeService;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * NodeService测试类
 * 测试节点负载均衡服务的各项功能
 *
 * @author luna
 * @version 1.0
 * @date 2025/01/23
 */
@SpringBootTest(classes = ZlmAutoConfiguration.class)
@RunWith(SpringRunner.class)
public class NodeServiceTest {

    @Autowired
    private ZlmProperties zlmProperties;

    @Autowired
    private NodeService nodeService;

    /**
     * 测试根据节点key获取指定节点
     */
    @Test
    public void testGetAvailableNode() {
        // 准备测试数据
        setupTestNodes();

        // 测试获取指定节点
        ZlmNode node1 = nodeService.getAvailableNode("123");
        assert node1 != null;
        assert Objects.equals(node1.getServerId(), "123");

        ZlmNode node2 = nodeService.getAvailableNode("1234");
        assert node2 != null;
        assert Objects.equals(node2.getServerId(), "1234");

        System.out.println("getAvailableNode测试通过");
    }

    /**
     * 测试使用负载均衡策略选择节点
     */
    @Test
    public void testSelectNodeWithKey() {
        // 准备测试数据
        setupTestNodes();

        // 测试使用key选择节点
        ZlmNode selectedNode = nodeService.selectNode("test-key");
        assert selectedNode != null;
        assert selectedNode.isEnabled();

        System.out.println("selectNode with key测试通过，选中节点: " + selectedNode.getServerId());
    }

    /**
     * 测试使用默认负载均衡策略选择节点
     */
    @Test
    public void testSelectNodeDefault() {
        // 准备测试数据
        setupTestNodes();

        // 测试默认选择节点
        ZlmNode selectedNode = nodeService.selectNode();
        assert selectedNode != null;
        assert selectedNode.isEnabled();

        System.out.println("selectNode default测试通过，选中节点: " + selectedNode.getServerId());
    }

    /**
     * 测试负载均衡的分布情况
     */
    @Test
    public void testLoadBalancingDistribution() {
        // 准备测试数据
        setupTestNodes();

        AtomicInteger node1Count = new AtomicInteger(0);
        AtomicInteger node2Count = new AtomicInteger(0);
        AtomicInteger node3Count = new AtomicInteger(0);

        // 执行多次选择，统计分布
        for (int i = 0; i < 1000; i++) {
            ZlmNode selectedNode = nodeService.selectNode("distribution-test-" + i);
            if (Objects.equals(selectedNode.getServerId(), "123")) {
                node1Count.getAndIncrement();
            } else if (Objects.equals(selectedNode.getServerId(), "1234")) {
                node2Count.getAndIncrement();
            } else if (Objects.equals(selectedNode.getServerId(), "12345")) {
                node3Count.getAndIncrement();
            }
        }

        System.out.println("负载均衡分布测试结果:");
        System.out.println("节点123被选中次数: " + node1Count.get());
        System.out.println("节点1234被选中次数: " + node2Count.get());
        System.out.println("节点12345被选中次数: " + node3Count.get());

        // 验证所有节点都被选中过
        assert node1Count.get() > 0;
        assert node2Count.get() > 0;
        assert node3Count.get() > 0;
    }

    /**
     * 测试异常情况 - 节点不存在
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetAvailableNodeWithInvalidKey() {
        // 准备测试数据
        setupTestNodes();

        // 测试获取不存在的节点
        nodeService.getAvailableNode("");
    }

    /**
     * 测试异常情况 - 空key选择节点
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSelectNodeWithEmptyKey() {
        // 准备测试数据
        setupTestNodes();

        // 测试使用空key选择节点
        nodeService.selectNode("");
    }

    /**
     * 设置测试节点数据
     */
    private void setupTestNodes() {
        // 清空现有节点
        zlmProperties.getNodes().clear();
        zlmProperties.getNodeMap().clear();

        // 创建测试节点1
        ZlmNode node1 = new ZlmNode();
        node1.setServerId("123");
        node1.setWeight(2);
        node1.setEnabled(true);
        zlmProperties.getNodes().add(node1);
        zlmProperties.getNodeMap().put(node1.getServerId(), node1);

        // 创建测试节点2
        ZlmNode node2 = new ZlmNode();
        node2.setServerId("1234");
        node2.setWeight(1);
        node2.setEnabled(true);
        zlmProperties.getNodes().add(node2);
        zlmProperties.getNodeMap().put(node2.getServerId(), node2);

        // 创建测试节点3
        ZlmNode node3 = new ZlmNode();
        node3.setServerId("12345");
        node3.setWeight(1);
        node3.setEnabled(true);
        zlmProperties.getNodes().add(node3);
        zlmProperties.getNodeMap().put(node3.getServerId(), node3);
    }

    /**
     * 测试配置属性
     */
    @Test
    public void testZlmProperties() {
        System.out.println("负载均衡策略: " + zlmProperties.getBalance());
        System.out.println("节点数量: " + zlmProperties.getNodes().size());
    }
}