package com.ny.lg.gateway.client;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @version: 1.0.0
 * @author: guog
 * @date: 2021/8/22 0:04
 * @description:
 **/
//@FeignClient(value = "lg-user", fallback = SecurityPermissionFallback.class)
public interface SecurityPermissionFeignClient {

    @PostMapping(value = "/verficationToken")
    JSONObject verficationToken(@RequestParam("token") String token);
}
