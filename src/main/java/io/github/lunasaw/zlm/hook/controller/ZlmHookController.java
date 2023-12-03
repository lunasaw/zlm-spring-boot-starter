package io.github.lunasaw.zlm.hook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.lunasaw.zlm.hook.param.HookResult;
import io.github.lunasaw.zlm.hook.param.OnServerKeepaliveHookParam;
import io.github.lunasaw.zlm.hook.service.ZlmHookService;

/**
 * @author weidian
 * @version 1.0
 * @date 2023/12/3
 * @description:
 */
@RestController
@RequestMapping("/index/hook/")
public class ZlmHookController {

    @Autowired
    private ZlmHookService    zlmHookService;

    @Qualifier("taskExecutor")
    @Autowired
    private AsyncTaskExecutor executor;

    /**
     * 服务器定时上报时间，上报间隔可配置，默认10s上报一次
     * 
     * @param param
     * @return
     */
    @PostMapping(value = "/on_server_keepalive", produces = "application/json;charset=UTF-8")
    public HookResult onServerKeepalive(@RequestBody OnServerKeepaliveHookParam param) {
        executor.execute(() -> {
            zlmHookService.onServerKeepLive(param);
        });
        return HookResult.SUCCESS();
    }
}
