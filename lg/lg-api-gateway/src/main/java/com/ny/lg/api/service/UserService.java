package com.ny.lg.api.service;


import com.ny.lg.api.pojo.User;
import com.ny.lg.api.pojo.UserContext;

public interface UserService {
    UserContext authenticate(User user);
}
