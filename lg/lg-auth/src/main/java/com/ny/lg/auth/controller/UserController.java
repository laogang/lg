package com.ny.lg.auth.controller;

import com.ny.lg.auth.utils.JwtTokenUtils;
import com.ny.lg.common.constants.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version: 1.0.0
 * @author: guog
 * @date: 2021/8/21 23:17
 * @description:
 **/
@RestController
@RequestMapping(value = "/atuh")
public class UserController {
    @PostMapping(value = "/verficationToken")
    public Result verficationToken(@RequestParam("token") String token) {
        System.out.println("************************come in");
        JwtTokenUtils.validateToken(token);
        return Result.success();
    }
}
