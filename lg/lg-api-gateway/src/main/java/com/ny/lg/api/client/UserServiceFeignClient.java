package com.ny.lg.api.client;

import com.ny.lg.api.client.fallback.UserServiceFallback;
import com.ny.lg.api.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @version 1.0.0
 * @author： guog
 * @date 2021/5/26 15:13
 * @description
 */
@FeignClient(value = "lg-user", fallback = UserServiceFallback.class)
public interface UserServiceFeignClient {

    @RequestMapping(value = "/permission/authenticate", method = RequestMethod.POST)
    User authenticate(@RequestBody User user);

}
