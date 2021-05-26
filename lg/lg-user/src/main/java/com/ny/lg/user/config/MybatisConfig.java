package com.ny.lg.api.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.zybank.crq.front.dao")
public class MybatisConfig {
}
