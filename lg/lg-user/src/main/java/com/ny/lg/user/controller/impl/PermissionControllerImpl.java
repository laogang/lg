package com.ny.lg.user.controller.impl;

import com.ny.lg.common.module.po.User;
import com.ny.lg.user.controller.PermissionController;
import com.ny.lg.user.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @version 1.0.0
 * @author： guog
 * @date 2021/5/26 16:07
 * @description
 */
@Component
public class PermissionControllerImpl implements PermissionController {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User authenticate(@RequestBody User user) {
        return userMapper.findSecurityUserByUser(user);
    }
}
