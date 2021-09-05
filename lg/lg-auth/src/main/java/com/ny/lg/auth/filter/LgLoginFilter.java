package com.ny.lg.auth.filter;

import com.alibaba.fastjson.JSONObject;
import com.ny.lg.auth.pojo.User;
import com.ny.lg.auth.pojo.UserContext;
import com.ny.lg.auth.utils.JwtTokenUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class LgLoginFilter extends AbstractAuthenticationProcessingFilter {
    private Logger logger = LoggerFactory.getLogger(LgLoginFilter.class);

    public LgLoginFilter(AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher("/login"));
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String body = IOUtils.toString(request.getInputStream(), "UTF-8");
        logger.info("request body: {}", body);
        if (!HttpMethod.POST.name().equals(request.getMethod())) {
            if (logger.isDebugEnabled()) {
                logger.debug(String.format("Authentication method not supported. Request Method: %s", request.getMethod()));
            }
        }
        User user = JSONObject.parseObject(body, User.class);
        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
                user,
                null,
                Collections.<GrantedAuthority>emptyList()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain, Authentication auth) throws IOException, ServletException {
        UserContext context = (UserContext) JSONObject.parseObject(auth.getPrincipal().toString(), UserContext.class);
        JwtTokenUtils.secret = context.getSecret();
        JwtTokenUtils.expiration = context.getExpiration();
        response.setHeader("Content-type", "text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.addHeader("Authorization", String.format("%s %s", new Object[]{"Bearer", JwtTokenUtils.generateToken(auth.getName(), auth.getAuthorities())}));

//        response.getWriter().write(JSONObject.toJSONString(BizCode.LOGIN_SUCCESS));
        response.getWriter().write("登录成功");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setHeader("Content-type", "text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write("登录失败");
//        response.getWriter().write(JSONObject.toJSONString(Result.error(HttpStatus.UNAUTHORIZED.value(), failed.getMessage())));
    }

}
