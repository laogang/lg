package com.ny.lg.gateway.filter;

import com.ny.lg.gateway.client.SecurityPermissionFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @version: 1.0.0
 * @author: guog
 * @date: 2021/8/22 0:02
 * @description:
 **/
@Component
public class ApplicationContextFilter implements GlobalFilter, Ordered {

    private static Logger logger = LoggerFactory.getLogger(ApplicationContextFilter.class);

    @Autowired
    private SecurityPermissionFeignClient securityPermissionFeignClient;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("**********Come in ApplicationContextFilter...***********");
        ServerHttpRequest request = exchange.getRequest();
        logger.info("请求的url: {}" + "\n" + "请求的参数: {}", request.getPath(), request.getQueryParams());
        HttpHeaders headers = request.getHeaders();
        //TODO 判空处理
        String token = headers.get("token").get(0);
//        securityPermissionFeignClient.verficationToken(token);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
