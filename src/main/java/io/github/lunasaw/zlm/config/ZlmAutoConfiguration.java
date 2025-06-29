package io.github.lunasaw.zlm.config;

import io.github.lunasaw.zlm.hook.service.ZlmHookService;
import io.github.lunasaw.zlm.hook.service.impl.DefaultZlmHookServiceImpl;
import io.github.lunasaw.zlm.node.LoadBalancer;
import io.github.lunasaw.zlm.node.NodeSupplier;
import io.github.lunasaw.zlm.node.impl.*;
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
@AutoConfiguration
@ConfigurationPropertiesScan
@EnableConfigurationProperties(ZlmProperties.class)
@ComponentScan(basePackages = "io.github.lunasaw.zlm")
@ConditionalOnProperty(prefix = "zlm", name = "enable", havingValue = "true", matchIfMissing = true)
public class ZlmAutoConfiguration {

    @Autowired
    ZlmProperties zlmProperties;

    @Bean
    @ConditionalOnMissingBean
    public ZlmHookService zlmHookService() {
        return new DefaultZlmHookServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public NodeSupplier nodeSupplier() {
        return new DefaultNodeSupplier();
    }

    @Bean
    @ConditionalOnMissingBean
    public LoadBalancer loadBalancer(NodeSupplier nodeSupplier) {
        LoadBalancer balancer;
        switch (zlmProperties.getBalance()) {
            case RANDOM:
                balancer = new RandomLoadBalancer();
                break;
            case ROUND_ROBIN:
                balancer = new RoundRobinLoadBalancer();
                break;
            case CONSISTENT_HASHING:
                balancer = new ConsistentHashingLoadBalancer();
                break;
            case WEIGHT_RANDOM:
                balancer = new WeightRandomLoadBalancer();
                break;
            case WEIGHT_ROUND_ROBIN:
                balancer = new WeightRoundRobinLoadBalancer();
                break;
            default:
                throw new RuntimeException("未找到负载均衡器");
        }

        // 设置NodeSupplier到LoadBalancer中
        balancer.setNodeSupplier(nodeSupplier);

        return balancer;
    }
}
