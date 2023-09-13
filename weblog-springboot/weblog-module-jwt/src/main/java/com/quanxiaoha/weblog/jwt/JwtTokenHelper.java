package com.quanxiaoha.weblog.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;

/**
 * @author: 犬小哈
 * @from: 公众号：小哈学Java, 网站：www.quanxiaoha.com
 * @date: 2023-04-15 22:01
 * @version: v1.0.0
 * @description: JWT 工具类
 **/
@Component
public class JwtTokenHelper implements InitializingBean {

    /**
     * 签发人
     */
    @Value("${jwt.issuer}")
    public String issuer;

    private Key key;
    private JwtParser jwtParser;

    /**
     * Token 失效时间（分钟）
     */
    @Value("${jwt.tokenExpireTime}")
    private Long tokenExpireTime;

    ///////////////////////////////////////////////////////////////////////////
    // JWT 初始化
    ///////////////////////////////////////////////////////////////////////////

    @Value("${jwt.secret}")
    public void setBase64Key(String base64) {
        key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(base64));
    }
    
    @Override
    public void afterPropertiesSet() {
        jwtParser = Jwts.parserBuilder().requireIssuer(issuer)
                .setSigningKey(key).setAllowedClockSkewSeconds(10)
                .build();
    }

    ///////////////////////////////////////////////////////////////////////////
    // JWT 令牌生成与解析
    ///////////////////////////////////////////////////////////////////////////
    public String generateToken(String username) {
        LocalDateTime now = LocalDateTime.now();
        // 设置 Token 失效时间
        LocalDateTime expireTime = now.plusHours(tokenExpireTime);

        return Jwts.builder()
                .setSubject(username)
                .setIssuer(issuer)
                .setIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(expireTime.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(key)
                .compact();
    }

    public Jws<Claims> parseToken(String token) {
        try {
            return jwtParser.parseClaimsJws(token);
        } catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            throw new BadCredentialsException("Invalid Token", e);
        } catch (ExpiredJwtException e) {
            throw new CredentialsExpiredException("Token Expired", e);
        }
    }

    public String getUsernameByToken(String token) {
        try {
            Claims claims = jwtParser.parseClaimsJws(token).getBody();
            String username = claims.getSubject();
            return username;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isTokenExpire(String token) {
        try {
            Claims claims = jwtParser.parseClaimsJws(token).getBody();
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean validateToken(String token) {
        try {
            jwtParser.parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public  boolean isTokenValid(String token, UserDetails userDetails) {
        String username = getUsernameByToken(token);
        return (Objects.equals(username, userDetails.getUsername())) && !isTokenExpire(token);
    }



}
