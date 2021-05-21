package com.ny.lg.api.dao;


import com.ny.lg.api.pojo.User;
import com.ny.lg.api.pojo.UserContext;

public interface UserMapper {

    UserContext findSecurityUserByUser(User user);
}
