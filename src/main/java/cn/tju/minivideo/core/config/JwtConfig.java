package cn.tju.minivideo.core.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
//@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {

    public static String secret;

    public static Long expiration;

    public static String header;

    public static final String UserId = "user_id";

    public static final String UserName = "username";

    public String getSecret() {
        return secret;
    }
    @Value("${jwt.secret}")
    public void setSecret(String secret) {
        JwtConfig.secret = secret;
    }

    public Long getExpiration() {
        return expiration;
    }
    @Value("${jwt.expiration}")
    public void setExpiration(Long expiration) {
        JwtConfig.expiration = expiration;
    }

    public static String getHeader() {
        return header;
    }

    @Value("${jwt.header}")
    public void setHeader(String header) {
        JwtConfig.header = header;
    }
}
