package io.github.lunasaw.zlm;

import io.github.lunasaw.zlm.config.ZlmAutoConfiguration;
import io.github.lunasaw.zlm.config.ZlmProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author weidian
 * @version 1.0
 * @date 2023/12/3
 * @description:
 */
@SpringBootTest(classes = ZlmAutoConfiguration.class)
@RunWith(SpringRunner.class)
public class ZlmZApiTest {

    @Autowired
    private ZlmProperties zlmProperties;

    @Test
    public void atest() {
        System.out.println(zlmProperties.getNodeMap());
    }
}
