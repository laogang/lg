package com.ny.lg.common.utils;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class RestfulUtils {
    private static <T> T retrieveData(String url, HttpMethod method, String params, Class<T> resultType) {
        RestTemplate template = new RestTemplate();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON_UTF8);
        Object t = template.exchange(url, method, new HttpEntity(params, header), resultType, new Object[]{params});
        return (T) t;
    }

    public static <T> Object retrieveDataByPost(String url, String params, Class<T> resultType) {
        return ((ResponseEntity) retrieveData(url, HttpMethod.POST, params, resultType)).getBody();
    }

    public static <T> Object retrieveDataByGet(String url, String params, Class<T> resultType) {
        return ((ResponseEntity) retrieveData(url, HttpMethod.GET, params, resultType)).getBody();
    }
}
