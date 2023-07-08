package com.quanxiaoha.weblog.jwt;

import com.quanxiaoha.weblog.common.Response;
import com.quanxiaoha.weblog.jwt.model.CreateTokenRspVO;
import com.quanxiaoha.weblog.jwt.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: 犬小哈
 * @url: www.quanxiaoha.com
 * @date: 2023-04-18 16:30
 * @description: 登录认证成功后处理
 **/
@Component
public class LoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // 生成 token
        String username = userDetails.getUsername();
        String token = jwtTokenHelper.generateToken(username);

        // 返回结果
        CreateTokenRspVO tokenRspDTO = CreateTokenRspVO.builder()
                .token(token)
                .build();

        ResultUtil.ok(response, Response.success(tokenRspDTO));
    }
}
