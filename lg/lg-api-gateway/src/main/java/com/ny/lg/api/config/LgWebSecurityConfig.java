package com.ny.lg.api.config;

import com.ny.lg.api.filter.FormHiddenHttpMethodFilter;
import com.ny.lg.api.filter.JwtAuthenticationTokenFilter;
import com.ny.lg.api.filter.LgLoginFilter;
import com.ny.lg.api.filter.SkipPathRequestMatcher;
import com.ny.lg.api.handler.CustomLogoutHandler;
import com.ny.lg.api.provider.JwtAuthenticationProvider;
import com.ny.lg.api.provider.LgAuthenticationProvider;
import com.ny.lg.common.constants.ConstantsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0.0
 * @author： guog
 * @date 2021/5/26 10:29
 * @description
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class LgWebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private LgAuthenticationProvider lgAuthenticationProvider;

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    private LgAuthenticationEntryPoint lgAuthenticationEntryPoint;

    @Autowired
    private CustomLogoutHandler customLogoutHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(lgAuthenticationProvider);
        auth.authenticationProvider(jwtAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //使用token ，这里就不需要csrf了
        http.csrf().disable()
                .exceptionHandling()
                //处理登陆认证异常
                .authenticationEntryPoint(lgAuthenticationEntryPoint)
                //基于token，也不需要session了
                .and().sessionManagement().sessionCreationPolicy((SessionCreationPolicy.STATELESS))
                //这里面的路径为所有人放开
                .and().authorizeRequests().antMatchers(ConstantsUtils.SKIP_URL).permitAll()
                //前面没有匹配上的请求，全部需要认证
                .anyRequest().authenticated()
                .and().logout().logoutUrl("/logout").logoutSuccessHandler(customLogoutHandler)
                .and().rememberMe()
        ;
        http.headers().cacheControl();
        http
                .addFilterBefore(new LgLoginFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(formHiddenHttpMethodFilter(), JwtAuthenticationTokenFilter.class);
        ;

    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        List<String> pathToSkip = Arrays.asList(ConstantsUtils.SKIP_URL);
        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathToSkip);
        return new JwtAuthenticationTokenFilter(matcher, authenticationManager());
    }

    @Bean
    public FormHiddenHttpMethodFilter formHiddenHttpMethodFilter() {
        return new FormHiddenHttpMethodFilter();
    }
}

