package com.ny.lg.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @version: 1.0.0
 * @author: guog
 * @date: 2021/8/20 18:25
 * @description:
 **/
@RestController
public class FallbackController {

    @GetMapping("/fallback")
    public Map<String, Object> fallback() {
        Map<String, Object> res = new HashMap(2);
        res.put("code", 500);
        res.put("message", "Hystrix fallback!");
        return res;
    }
}
