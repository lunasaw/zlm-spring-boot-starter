package io.github.lunasaw.zlm.config;

import io.github.lunasaw.zlm.hook.service.ZlmHookService;
import io.github.lunasaw.zlm.hook.service.impl.DefaultZlmHookServiceImpl;
import io.github.lunasaw.zlm.node.LoadBalancer;
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
    public LoadBalancer loadBalancer() {
        switch (zlmProperties.getBalance()) {
            case RANDOM:
                return new RandomLoadBalancer();
            case ROUND_ROBIN:
                return new RoundRobinLoadBalancer();
            case CONSISTENT_HASHING:
                return new ConsistentHashingLoadBalancer();
            case WEIGHT_RANDOM:
                return new WeightRandomLoadBalancer();
            case WEIGHT_ROUND_ROBIN:
                return new WeightRoundRobinLoadBalancer();
            default:
                throw new RuntimeException("未找到负载均衡器");
        }
    }
}
