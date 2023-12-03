package io.github.lunasaw.zlm.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
}
