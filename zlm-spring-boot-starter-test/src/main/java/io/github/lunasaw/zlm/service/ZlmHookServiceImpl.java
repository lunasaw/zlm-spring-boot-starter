package io.github.lunasaw.zlm.service;

import io.github.lunasaw.zlm.hook.param.OnServerKeepaliveHookParam;
import io.github.lunasaw.zlm.hook.service.ZlmHookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author weidian
 * @version 1.0
 * @date 2023/12/3
 * @description:
 */
@Slf4j
@Service
public class ZlmHookServiceImpl implements ZlmHookService {
    @Override
    public void onServerKeepLive(OnServerKeepaliveHookParam param) {
        log.info("onServerKeepLive::param = {}", param);
    }
}
