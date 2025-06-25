package io.github.lunasaw.zlm;

import com.luna.common.thread.AsyncEngineUtils;
import io.github.lunasaw.zlm.config.ZlmAutoConfiguration;
import io.github.lunasaw.zlm.config.ZlmNode;
import io.github.lunasaw.zlm.config.ZlmProperties;
import io.github.lunasaw.zlm.node.NodeService;
import io.github.lunasaw.zlm.node.impl.WeightRandomLoadBalancer;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author luna
 * @version 1.0
 * @date 2023/12/3
 * @description:
 */
@SpringBootTest(classes = ZlmAutoConfiguration.class)
@RunWith(SpringRunner.class)
public class ZlmZApiTest {

    @Autowired
    private ZlmProperties zlmProperties;

    @Autowired
    private NodeService nodeService;

    @SneakyThrows
    private static void extracted() {
        AtomicInteger k = new AtomicInteger(0);
        WeightRandomLoadBalancer weightRoundRobinLoadBalancer = new WeightRandomLoadBalancer();

        AsyncEngineUtils.execute(() -> {
            for (int i = 0; i < 100; i++) {
                ZlmNode node = weightRoundRobinLoadBalancer.selectNode("12312" + i);
                if (Objects.equals(node.getServerId(), "1234")) {
                    k.getAndIncrement();
                }
            }
        });
        for (int i = 0; i < 100; i++) {
            ZlmNode node = weightRoundRobinLoadBalancer.selectNode("12312" + i);
            if (Objects.equals(node.getServerId(), "1234")) {
                k.getAndIncrement();
            }
        }
        System.out.println(k.get());
    }

    @Test
    public void btest() {
        ZlmNode node1 = new ZlmNode();
        node1.setServerId("123");
        node1.setWeight(2);
        nodeService.addNode(node1);

        ZlmNode node2 = new ZlmNode();
        node2.setServerId("1234");
        node2.setWeight(1);
        nodeService.addNode(node2);

        ZlmNode node3 = new ZlmNode();
        node3.setServerId("12345");
        node3.setWeight(1);
        nodeService.addNode(node3);

        AtomicInteger k = new AtomicInteger();

        long l = System.currentTimeMillis();
        extracted();
    }

    @Test
    public void atest() {
        System.out.println(zlmProperties.getBalance());
    }
}
