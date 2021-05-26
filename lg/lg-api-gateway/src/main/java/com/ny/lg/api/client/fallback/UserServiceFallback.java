package com.ny.lg.api.client.fallback;

import com.ny.lg.api.client.UserServiceFeignClient;
import com.ny.lg.api.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @version 1.0.0
 * @author： guog
 * @date 2021/5/26 15:39
 * @description
 */
@Component
public class UserServiceFallback implements UserServiceFeignClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceFallback.class);

    @Override
    public User authenticate(User user) {
        LOGGER.warn("调用lg-user 服务异常");
        return null;
    }
}
