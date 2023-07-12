package com.quanxiaoha.weblog.admin.config;

import com.quanxiaoha.weblog.jwt.JwtAuthenticationSecurityConfig;
import com.quanxiaoha.weblog.jwt.RestAccessDeniedHandler;
import com.quanxiaoha.weblog.jwt.RestAuthenticationEntryPoint;
import com.quanxiaoha.weblog.jwt.TokenAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author: 犬小哈
 * @from: 公众号：小哈学Java, 网站：www.quanxiaoha.com
 * @date: 2023-04-15 14:18
 * @version: v1.0.0
 * @description: TODO
 **/
@Configurable
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private RestAuthenticationEntryPoint authEntryPoint;
    @Autowired
    private RestAccessDeniedHandler deniedHandler;

    @Autowired
    private JwtAuthenticationSecurityConfig jwtAuthenticationSecurityConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().disable()
                .apply(jwtAuthenticationSecurityConfig)
            .and()
                // .addFilterAt(jwtAuthenticationFilter(), AbstractPreAuthenticatedProcessingFilter.class)
                .csrf().disable()
            .authorizeRequests()
                .mvcMatchers("/admin/**").authenticated()
                .anyRequest().permitAll()
            .and()
                .httpBasic().authenticationEntryPoint(authEntryPoint)
            .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 无需创建会话
            .and()
                .exceptionHandling()
                    .accessDeniedHandler(deniedHandler)
            .and()
                .addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                ;
    }

    /**
     * Token 校验过滤器
     * @return
     */
    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

}
