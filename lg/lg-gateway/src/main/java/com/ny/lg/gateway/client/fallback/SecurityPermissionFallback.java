package com.ny.lg.gateway.client.fallback;

import com.alibaba.fastjson.JSONObject;
import com.ny.lg.gateway.client.SecurityPermissionFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @version: 1.0.0
 * @author: guog
 * @date: 2021/8/22 0:06
 * @description:
 **/
@Component
public class SecurityPermissionFallback implements SecurityPermissionFeignClient {
    private static Logger logger = LoggerFactory.getLogger(SecurityPermissionFallback.class);

    @Override
    public JSONObject verficationToken(String token) {
        logger.warn("调用lg-auth 服务异常");
        return null;
    }
}
