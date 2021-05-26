package com.ny.lg.user.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.ny.lg.user.dao")
public class MybatisConfig {
}
