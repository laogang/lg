package com.ny.lg.user.dao;

import com.ny.lg.common.module.po.User;

/**
 * @version 1.0.0
 * @author： guog
 * @date 2021/5/26 16:09
 * @description
 */
public interface UserMapper {
    User findSecurityUserByUser(User user);
}
