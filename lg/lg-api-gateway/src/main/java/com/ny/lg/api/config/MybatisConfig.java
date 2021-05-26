package com.ny.lg.api.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.ny.lg.api.dao")
public class MybatisConfig {
}
