package com.ny.lg.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @version 1.0.0
 * @author： guog
 * @date 2021/5/26 10:11
 * @description
 */
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
