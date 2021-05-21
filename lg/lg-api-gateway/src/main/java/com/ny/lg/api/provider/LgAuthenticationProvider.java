package com.ny.lg.api.provider;

import com.alibaba.fastjson.JSONObject;
import com.ny.lg.api.pojo.Role;
import com.ny.lg.api.pojo.User;
import com.ny.lg.api.pojo.UserContext;
import com.ny.lg.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LgAuthenticationProvider implements AuthenticationProvider {

    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.expiration}")
    private int expiration;

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = (User) authentication.getPrincipal();
        userInfoIsNotEmpty(user);
        UserContext userContext = userService.authenticate(user);
        if (userContext == null) {
            throw new BadCredentialsException("用户名或者密码输入错误，请重新输入!");
        }
        userContext.setSecret(this.secret);
        userContext.setExpiration(this.expiration);
//        List<Role> roles = userContext.getRoles();
        List<Role> roles = new ArrayList<>();
        Role rl = new Role();
        rl.setCode("ADMIN");
        roles.add(rl);
        return new UsernamePasswordAuthenticationToken(JSONObject.toJSONString(userContext), user.getPassword(),
                AuthorityUtils.commaSeparatedStringToAuthorityList(roles.stream().map(role -> role.getCode()).collect(Collectors.toList()).toString()));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private void userInfoIsNotEmpty(User user) {
//        AssertUtils.notNull(user, BizCode.INVALID_USER_CREDENTIALS.getMessage());
//        AssertUtils.notNull(user.getUsername(), BizCode.INVALID_USER_CREDENTIALS.getMessage());
//        AssertUtils.notNull(user.getPassword(), BizCode.INVALID_USER_CREDENTIALS.getMessage());
    }
}
