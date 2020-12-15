package com.zr.component.sercurity.config;

import com.zr.component.sercurity.service.MyUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 拦截所有请求根据用户ID授权其可以访问的资源
 */
public class MyAuthenticationTokenFilter extends OncePerRequestFilter {

    private MyUserDetailsService myUserDetailsService;

    private MyAuthenticationTokenFilter(){}

    public MyAuthenticationTokenFilter(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // 经过网关后，网关会在头信息添加用户名称， 根据用户名称查询用户权限，然后再每个微服务中添加权限校验
        String userId = request.getHeader("userName");
        if(!StringUtils.isEmpty(userId)){

            UserDetails userDetails = myUserDetailsService.loadUserByUsername(userId);

            //给用户进行授权,可以访问哪些接口
            UsernamePasswordAuthenticationToken authenticationToken
                    = new UsernamePasswordAuthenticationToken(
                            userDetails,null, userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        }

        filterChain.doFilter(request,response);
    }
}
