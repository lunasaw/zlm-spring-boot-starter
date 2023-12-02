package io.github.lunasaw.zlm.api;

import com.luna.common.check.Assert;
import com.luna.common.net.HttpUtils;
import io.github.lunasaw.zlm.config.ZlmProperties;
import io.github.lunasaw.zlm.constant.Constant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author luna
 * @version 1.0
 * @date 2023/12/2
 * @description:
 */
@Service
public class ZlmRestService {

    @Autowired
    private ZlmProperties zlmProperties;


    public static String doApi(String host, String secret, String api, Map<String, String> params) {
        Assert.notNull(host, "host is null");
        Assert.notNull(api, "api is null");
        Assert.notNull(secret, "secret is null");

        params = Optional.ofNullable(params).orElse(new HashMap<>());
        params.put("secret", secret);
        String path = Constant.API_INDEX + api;
        return HttpUtils.doPostHander(host, path, new HashMap<>(), params, StringUtils.EMPTY);
    }

}
