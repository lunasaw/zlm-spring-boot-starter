package io.github.lunasaw.zlm.node;

import io.github.lunasaw.zlm.config.ZlmProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

/**
 * @author luna
 * @date 2023/12/4
 */
@Service
@ConditionalOnBean(ZlmProperties.class)
public class NodeService {


    public void method() {

    }
}
