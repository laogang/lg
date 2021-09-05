package com.ny.lg.user.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @version 1.0.0
 * @author： guog
 * @date 2021/5/20 17:28
 * @description
 */
@Configuration
@EnableSwagger2
@ConditionalOnProperty(name = "swagger.enable",  havingValue = "true")
public class Swagger2 {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ny.lg.*.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * apiInfo()用来创建该Api的基本信息
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                .description("使用RestFul风格, 创建人：crq-front")
                .termsOfServiceUrl("http://localhost:8080/swagger-ui.html")
                .version("1.0")
                .build();
    }
}
