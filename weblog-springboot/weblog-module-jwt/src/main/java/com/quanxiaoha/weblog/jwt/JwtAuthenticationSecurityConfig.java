package com.quanxiaoha.weblog.jwt;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author: 犬小哈
 * @url: www.quanxiaoha.com
 * @date: 2023-04-18 19:29
 * @description: TODO
 **/
@Configuration
public class JwtAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    /**
     * 登录成功处理器
     */
    @Autowired
    private LoginAuthenticationSuccessHandler loginAuthenticationSuccessHandler;

    /**
     * 登录失败处理器
     */
    @Autowired
    private LoginAuthenticationFailureHandler loginAuthenticationFailureHandler;

    /**
     * 加密
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        JwtAuthenticationLoginFilter filter = new JwtAuthenticationLoginFilter();
        filter.setAuthenticationManager(httpSecurity.getSharedObject(AuthenticationManager.class));

        filter.setAuthenticationSuccessHandler(loginAuthenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(loginAuthenticationFailureHandler);
        // 直接使用 DaoAuthenticationProvider
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        // 设置 userDetailService
        provider.setUserDetailsService(userDetailsService);
        // 设置加密算法
        provider.setPasswordEncoder(passwordEncoder);
        httpSecurity.authenticationProvider(provider);
        // 将这个过滤器添加到 UsernamePasswordAuthenticationFilter 之前执行
        httpSecurity.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }


}
