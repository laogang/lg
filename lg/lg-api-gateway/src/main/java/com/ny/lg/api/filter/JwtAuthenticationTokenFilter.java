package com.ny.lg.api.filter;

import com.ny.lg.api.handler.JwtAuthenticationFailureHandler;
import com.ny.lg.api.token.JwtAuthenticationToken;
import com.ny.lg.api.utils.JwtTokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

    private final static Logger logger = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

    @Autowired
    private JwtAuthenticationFailureHandler failureHandler;

    public JwtAuthenticationTokenFilter(RequestMatcher matcher, AuthenticationManager authenticationManager) {
        super(matcher);
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        //先从url中取token
        String authToken = request.getParameter("token");
        String tokenHeader = JwtTokenUtils.HEADER_STRING;
        String authHeader = request.getHeader(tokenHeader);
        String tokenHead = JwtTokenUtils.TOKEN_PREFIX;
        if (!StringUtils.isEmpty(authHeader) && authHeader.startsWith(tokenHead)) {
            //如果header中存在token,则覆盖掉url中的token "Bearer" 之后的内容
            authToken = authHeader.substring(tokenHead.length());
        }
        return getAuthenticationManager().authenticate(new JwtAuthenticationToken(authToken));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        logger.warn(String.format("token invalid Message: %s", failed.getMessage()));
        failureHandler.onAuthenticationFailure(request, response, failed);
    }
}
