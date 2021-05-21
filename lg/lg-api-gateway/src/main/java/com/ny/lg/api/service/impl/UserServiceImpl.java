package com.ny.lg.api.service.impl;

import com.ny.lg.api.dao.UserMapper;
import com.ny.lg.api.pojo.User;
import com.ny.lg.api.pojo.UserContext;
import com.ny.lg.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserContext authenticate(User user) {
        return userMapper.findSecurityUserByUser(user);
    }
}
