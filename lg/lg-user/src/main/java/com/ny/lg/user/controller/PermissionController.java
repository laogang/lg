package com.ny.lg.user.controller;

import com.ny.lg.common.module.po.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0.0
 * @author： guog
 * @date 2021/5/26 15:52
 * @description
 */
@RestController
@RequestMapping("/permission")
public interface PermissionController {

    /**
     * 登陆认证查询
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    User authenticate(@RequestBody User user);
}
