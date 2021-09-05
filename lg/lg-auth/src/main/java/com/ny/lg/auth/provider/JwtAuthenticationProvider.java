package com.ny.lg.auth.provider;

import com.ny.lg.auth.token.JwtAuthenticationToken;
import com.ny.lg.auth.utils.JwtTokenUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationProvider.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Value("${jwt.token.secret}")
    private String secret;

    /**
     * 过期时长,单位为秒
     */
    @Value("${jwt.token.expiration}")
    private int expiration;

    @Override
    @SuppressWarnings("unchecked")
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String token = (String) authentication.getCredentials();
        JwtTokenUtils.secret = secret;
        JwtTokenUtils.expiration = expiration;
        if (!StringUtils.isEmpty(token) && JwtTokenUtils.isTokenExpired(token)) {
            logger.info("token is expired，If the user is logined in, then quit.");
//            User user = userServiceClient.retrieveUserByUsername(JwtTokenUtils.getUsernameFromExpiredToken(token));
//            if (user.getUserStatus().equals(LoginStatusEnum.LOGIN.getCode().toString())) {
//                userServiceClient.changeUserStatus(user.getId(), LoginStatusEnum.LOGOUT.getCode().toString());
//                Result result = logServiceClient.createUserLog(user.getUsername(), user.getId(),
//                        RequestUtils.getIpAddr(request), ModuleType.USER, UserOperationType.TIMEOUT_LOGOUT);
//                user.setUserStatus(LoginStatusEnum.LOGOUT.getCode().toString());
//
//                if (result.isSucceed()) {
//                    logger.info(String.format("user: %s log out. ，The log of log out is insert successful！", user.getUsername()));
//                }
//            } else {
//                logger.info(String.format("user: %s already log out", user.getUsername()));
//            }
        }
//        String username = JwtTokenUtils.getUsernameFromToken(token);
//        User user = userServiceClient.retrieveUserByUsername(username);
//        if (LoginStatusEnum.LOGOUT.getCode().toString().equals(user.getUserStatus())) {
//            logger.info(String.format("user: %s already log out,Please login！", user.getUsername()));
//            throw new BadCredentialsException(BizCode.USER_ALREADY_LOGOUT.getMessage());
//        }
        Jws<Claims> claimsJws = JwtTokenUtils.validateToken(token);
        String subject = claimsJws.getBody().getSubject();
        List<String> scopes = claimsJws.getBody().get("scopes", List.class);
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String s : scopes) {
            authorities.add(new SimpleGrantedAuthority(s));
        }
        authentication = new UsernamePasswordAuthenticationToken(subject, null, authorities);
        if (JwtTokenUtils.canTokenBeRefreshed(token)) {
            logger.info("Refresh and return the new token in response header");
            response.setHeader(JwtTokenUtils.HEADER_STRING, String.format("%s %s", JwtTokenUtils.TOKEN_PREFIX, JwtTokenUtils.refreshToken(token)));
        }
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}