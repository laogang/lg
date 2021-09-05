package com.ny.lg.auth.handler;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @version 1.0.0
 * @author： guog
 * @date 2021/5/26 10:13
 * @description
 */
@Component
public class CustomLogoutHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setHeader("Content-type", "text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JSONObject.toJSONString("登出成功"));
    }
}
