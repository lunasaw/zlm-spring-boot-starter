package io.github.lunasaw.zlm.config;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author luna
 * @version 1.0
 * @date 2023/12/2
 * @description:
 */
@ConfigurationProperties(prefix = "zlm")
@Data
public class ZlmProperties implements InitializingBean {

    /**
     * 对外NodeMap
     */
    private Map<String, ZlmNodeConfig> nodeMap = new HashMap<>();

    private List<ZlmNodeConfig> nodes = new ArrayList<>();

    private boolean enable = true;

    @Override
    public void afterPropertiesSet() {
        nodeMap = nodes.stream().filter(ZlmNodeConfig::isEnabled).collect(Collectors.toMap(ZlmNodeConfig::getServerId, node -> node));
    }
}
