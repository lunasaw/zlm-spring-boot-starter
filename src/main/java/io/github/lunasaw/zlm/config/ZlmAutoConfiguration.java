package io.github.lunasaw.zlm.config;

import io.github.lunasaw.zlm.hook.service.impl.DefaultZlmHookServiceImpl;
import io.github.lunasaw.zlm.hook.service.ZlmHookService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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
@EnableConfigurationProperties(ZlmProperties.class)
@ComponentScan(basePackages = "io.github.lunasaw.zlm")
public class ZlmAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ZlmHookService zlmHookService() {
        return new DefaultZlmHookServiceImpl();
    }
}
