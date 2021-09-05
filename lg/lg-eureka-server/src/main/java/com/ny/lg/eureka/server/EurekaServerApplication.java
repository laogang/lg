package com.ny.lg.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @version 1.0.0
 * @author： guog
 * @date 2021/5/26 11:08
 * @description
 */
//druid-spring-boot-starter 会再次扫描数据源，将druid踢出公共pom文件 或者添加 exclude
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
//        DataSourceTransactionManagerAutoConfiguration.class,
//        DruidDataSourceAutoConfigure.class,
//        HibernateJpaAutoConfiguration.class})
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
