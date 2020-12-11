package com.zr.bservice.gateway.jwt.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "zimug.gateway.jwt")
@Component
public class JwtProperties {

    //是否开启JWT，即注入相关的类对象
    private Boolean enabled;
    //JWT密钥
    private String secret;
    //JWT有效时间
    private Long expiration;
    //前端向后端传递JWT时使用HTTP的header名称
    private String header;
    //用户登录-用户名参数名称
    private String userParamName = "username";
    //用户登录-密码参数名称
    private String pwdParamName = "password";
    //是否使用默认的JWTAuthController
    private Boolean useDefaultController = false;

}
