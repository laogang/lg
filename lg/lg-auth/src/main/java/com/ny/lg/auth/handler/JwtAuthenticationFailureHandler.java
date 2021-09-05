package com.ny.lg.auth.handler;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @version 1.0.0
 * @author： guog
 * @date 2021/5/26 10:14
 * @description
 */
@Component
public class JwtAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.getWriter().println("认证失败");
//        response.getWriter().println(JSONObject.toJSONString(Result.error(HttpStatus.FORBIDDEN.value(),exception.getMessage())));
        response.getWriter().close();
    }
}
