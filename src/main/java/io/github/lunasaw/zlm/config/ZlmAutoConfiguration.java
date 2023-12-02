package io.github.lunasaw.zlm.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author weidian
 * @version 1.0
 * @date 2023/12/2
 * @description:
 */
@AutoConfiguration
@EnableConfigurationProperties(ZlmProperties.class)
public class ZlmAutoConfiguration {
}
