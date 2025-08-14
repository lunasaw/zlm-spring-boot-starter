package io.github.lunasaw.zlm.config;

import io.github.lunasaw.zlm.hook.service.ZlmHookService;
import io.github.lunasaw.zlm.hook.service.impl.DefaultZlmHookServiceImpl;
import io.github.lunasaw.zlm.node.LoadBalancer;
import io.github.lunasaw.zlm.node.NodeSupplier;
import io.github.lunasaw.zlm.node.impl.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author luna
 * @version 1.0
 * @date 2023/12/2
 * @description:
 */
@Slf4j
@AutoConfiguration
@ConfigurationPropertiesScan
@EnableConfigurationProperties(ZlmProperties.class)
@ComponentScan(basePackages = "io.github.lunasaw.zlm")
@ConditionalOnProperty(prefix = "zlm", name = "enable", havingValue = "true", matchIfMissing = true)
public class ZlmAutoConfiguration {

    @Autowired
    ZlmProperties zlmProperties;

    @Bean
    @ConditionalOnMissingBean(ZlmHookService.class)
    public ZlmHookService zlmHookService() {
        return new DefaultZlmHookServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean(NodeSupplier.class)
    public NodeSupplier nodeSupplier() {
        log.info("创建默认NodeSupplier实例，节点配置数量: {}",
                zlmProperties.getNodes() != null ? zlmProperties.getNodes().size() : 0);
        return new DefaultNodeSupplier(zlmProperties);
    }

    @Bean
    @ConditionalOnMissingBean(LoadBalancer.class)
    public LoadBalancer loadBalancer(NodeSupplier nodeSupplier) {
        log.info("创建LoadBalancer实例，算法: {}，NodeSupplier: {}",
                zlmProperties.getBalance(), nodeSupplier.getName());

        LoadBalancer balancer = switch (zlmProperties.getBalance()) {
            case RANDOM -> new RandomLoadBalancer();
            case ROUND_ROBIN -> new RoundRobinLoadBalancer();
            case CONSISTENT_HASHING -> new ConsistentHashingLoadBalancer();
            case WEIGHT_RANDOM -> new WeightRandomLoadBalancer();
            case WEIGHT_ROUND_ROBIN -> new WeightRoundRobinLoadBalancer();
            default -> throw new RuntimeException("未找到负载均衡器: " + zlmProperties.getBalance());
        };

        // 设置NodeSupplier到LoadBalancer中
        balancer.setNodeSupplier(nodeSupplier);
        log.info("LoadBalancer配置完成，类型: {}", balancer.getType());

        return balancer;
    }
}
