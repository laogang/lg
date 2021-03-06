package com.ny.lg.auth.utils;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class JwtTokenUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtils.class);

    public static final String CLAIM_KEY_USERNAME = "sub";
    public static final String CLAIM_KEY_CREATED = "created";
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";

    /**
     * 生成token所用秘钥
     */
    public static String secret;

    /**
     * token的有效期，单位为秒
     */
    public static int expiration;


    /**
     * 从token中获取用户名
     *
     * @param token token
     * @return String 用户名
     */
    public static String getUsernameFromToken(String token) {
        String username;
        try {
            username = getClaimsFromToken(token).getSubject();
            User user = JSONObject.parseObject(username, User.class);
            username = user.getUsername();
        } catch (Exception e) {
            logger.info(String.format("Can not get username from token %s", token));
            throw new BadCredentialsException("Token失效，请重新登录.", e);
        }
        return username;
    }

    /**
     * 从过期的token中获取到过期时间
     * 这个方法专门做从异常token中获取过期时间
     *
     * @param token
     * @return
     */
    public static String getUsernameFromExpiredToken(String token) {
        String username;
        try {
            username = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
        } catch (ExpiredJwtException ex) {
            String context = (String) ex.getClaims().get(CLAIM_KEY_USERNAME);
            User user = JSONObject.parseObject(context, User.class);
            username = user.getUsername();
        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException ex) {
            logger.info(String.format("Invalid JWT token, token: %s", token));
            throw new BadCredentialsException("Token失效，请重新登录.", ex);
        }
        return username;
    }

    /**
     * 从token中获取token的创建时间
     *
     * @param token token
     * @return token生成日期对象 Date
     */
    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
        } catch (Exception e) {
            logger.info(String.format("Can not get CreatedDate from token %s", token));
            throw new BadCredentialsException("Token失效，请重新登录.", e);
        }

        return created;
    }

    /**
     * 从token 中获取token 的过期时间
     * 这个方法当token 异常时不需要返回Claims等信息，只需要返回token不合法就行了
     *
     * @param token token
     * @return 过期日期对象 Date
     */
    public static Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            expiration = claims.getExpiration();
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException ex) {
            logger.info(String.format("Invalid JWT token, token: %s", token));
            throw new BadCredentialsException("Token失效，请重新登录.", ex);
        } catch (ExpiredJwtException ex) {
            logger.info(String.format("Expired JWT token, token: %s", token));
            expiration = null;
        }
        return expiration;
    }

    /**
     * 获取token 的Claims 记录信息
     *
     * @param token token
     * @return Claims 对象
     */
    public static Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException ex) {
            logger.info(String.format("Invalid JWT token, token: %s", token));
            throw new BadCredentialsException("Token失效，请重新登录.", ex);
        } catch (ExpiredJwtException ex) {
            logger.info(String.format("Expired JWT token, token: %s", token));
            throw new BadCredentialsException("Token失效，请重新登录.", ex);
        }
        return claims;
    }


    /**
     * 生成过期时间
     *
     * @return 过期日期对象 Date
     */
    public static Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 判断token 是否已过期
     *
     * @param token token
     * @return Boolean 过期返回true ,否则返回false
     */
    public static Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration == null || expiration.before(new Date());
    }

    /**
     * 生成token
     *
     * @param userDetails userDetail 信息
     * @return 生成的token
     */
    public static String generateToken(User userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 生成token
     *
     * @param claims 所需Claims
     * @return token 字符串
     */
    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 生成token
     *
     * @param username    用户名
     * @param authorities 该用户的权限信息
     * @return token字符串
     */
    public static String generateToken(String username, Collection<? extends GrantedAuthority> authorities) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, username);
        claims.put(CLAIM_KEY_CREATED, new Date());
        List<String> authStrs = new ArrayList<>();
        for (GrantedAuthority authority : authorities) {
            authStrs.add(authority.toString());
        }
        claims.put("scopes", authStrs);
        return generateToken(claims);
    }

    /**
     * 该token 是否可刷新
     *
     * @param token token
     * @return 能刷新返回 true，否则返回false
     */
    public static Boolean canTokenBeRefreshed(String token) {
        return !isTokenExpired(token);
    }

    /**
     * 刷新token
     *
     * @param token 要刷新的token
     * @return 刷新后的token
     */
    public static String refreshToken(String token) {
        final Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 验证token 是否有效
     *
     * @param token token
     * @return Jws<Claims> token中包含的信息
     */
    public static Jws<Claims> validateToken(String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException ex) {
            logger.info(String.format("Invalid JWT token, token: %s", token));
            throw new BadCredentialsException("Token失效，请重新登录.", ex);
        } catch (ExpiredJwtException ex) {
            logger.info(String.format("expired JWT token, token: %s", token));
            throw new BadCredentialsException("Token失效，请重新登录.", ex);
        }
    }


    public String getSecret() {
        return secret;
    }

    public int getExpiration() {
        return expiration;
    }
}
