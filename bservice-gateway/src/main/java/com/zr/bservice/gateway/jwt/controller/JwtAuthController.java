package com.zr.bservice.gateway.jwt.controller;


import com.zr.bservice.gateway.exception.AjaxResponse;
import com.zr.bservice.gateway.exception.CustomException;
import com.zr.bservice.gateway.exception.CustomExceptionType;
import com.zr.bservice.gateway.jwt.jpa.SysUser;
import com.zr.bservice.gateway.jwt.jpa.SysUserRepository;
import com.zr.bservice.gateway.jwt.model.JwtProperties;
import com.zr.bservice.gateway.jwt.utils.JwtTokenUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Map;

/**
 * JWT获取令牌和刷新令牌接口
 */
@RestController
// 当ConditionalOnProperty---zimug.gateway.jwt.useDefaultController=true的时候，才初始化JwtAuthController 这个类的Bean
// 为了后面进行修改，支持oath
@ConditionalOnProperty(name = "zimug.gateway.jwt.useDefaultController", havingValue = "true")
public class JwtAuthController {

    @Resource
    private JwtProperties jwtProperties;
    @Resource
    private SysUserRepository sysUserRepository;
    @Resource
    private JwtTokenUtil jwtTokenUtil;
    @Resource
    private PasswordEncoder passwordEncoder;

    /**
     * 使用用户名密码换JWT令牌
     */
    @RequestMapping("/authentication")
    public Mono<AjaxResponse> authentication(@RequestBody Map<String,String> map){
        //从请求体中获取用户名密码
        String username  = map.get(jwtProperties.getUserParamName());
        String password = map.get(jwtProperties.getPwdParamName());

        if(StringUtils.isEmpty(username)
                || StringUtils.isEmpty(password)){
            return buildErrorResponse("用户名或者密码不能为空");
        }
        //根据用户名（用户Id）去数据库查找该用户
        SysUser sysUser = sysUserRepository.findByUsername(username);
        if(sysUser != null){
            //将数据库的加密密码与用户明文密码match
            boolean isAuthenticated = passwordEncoder.matches(password,sysUser.getPassword());
            if(isAuthenticated){ //如果匹配成功
                return buildSuccessResponse(jwtTokenUtil.generateToken(username,null));
            } else{ //如果密码匹配失败
                return buildErrorResponse("请确定您输入的用户名或密码是否正确！");
            }
        }else{
            return buildErrorResponse("请确定您输入的用户名或密码是否正确！");
        }
    }

    /**
     * 刷新JWT令牌,用旧的令牌换新的令牌
     */
    @RequestMapping("/refreshtoken")
    public  Mono<AjaxResponse> refreshtoken(@RequestHeader("${zimug.gateway.jwt.header}") String oldToken){
        if(!jwtTokenUtil.isTokenExpired(oldToken)){
            return buildSuccessResponse(jwtTokenUtil.refreshToken(oldToken));
        }
        return Mono.empty();
    }

    private Mono<AjaxResponse> buildErrorResponse(String message){
       return Mono.create(callback -> callback.success( //请求结果成功的回调
            AjaxResponse.error( //响应信息是Error的,携带异常信息返回
                    new CustomException(CustomExceptionType.USER_INPUT_ERROR, message)
            )
       ));
    }

    private Mono<AjaxResponse> buildSuccessResponse(Object data){
        return Mono.create(callback -> callback.success( //请求结果成功的回调
                AjaxResponse.success(data)  //成功响应,携带数据返回
        ));
    }

}
