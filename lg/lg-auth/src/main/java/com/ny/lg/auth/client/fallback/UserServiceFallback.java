package com.ny.lg.auth.client.fallback;

import com.ny.lg.auth.client.UserServiceFeignClient;
import com.ny.lg.auth.pojo.User;
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

    private static Logger logger = LoggerFactory.getLogger(UserServiceFallback.class);

    @Override
    public User authenticate(User user) {
        logger.warn("调用lg-user 服务异常");
        return new User();
    }
}
