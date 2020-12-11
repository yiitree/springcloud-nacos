package com.zr.component.sercurity;

import com.zr.component.sercurity.config.MyAuthenticationTokenFilter;
import com.zr.component.sercurity.model.MySecurityProperties;
import com.zr.component.sercurity.service.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * 微服务自身内部的权限管理
 * 适配器
 * @author 77270
 */
@EnableWebSecurity
@Configuration
@Order(1)
public class MyWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Resource
    private MySecurityProperties mySecurityProperties;
    @Resource
    private MyUserDetailsService myUserDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        if(mySecurityProperties.getCsrfDisabled()){
            http = http.csrf().disable();
        }
        http.addFilterBefore(
                    myAuthenticationTokenFilter(),
                    UsernamePasswordAuthenticationFilter.class
            );

        //RBAC权限控制级别的接口权限校验
        http.authorizeRequests().anyRequest()
          .access("@rabcService.hasPermission(request,authentication)");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 跨站资源共享配置,因为gateway网关统一做了cors配置，这里就不要再做http.cors()
     */
    /*@Bean
    CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(mySecurityProperties.getCorsAllowedOrigins());
        configuration.setAllowedMethods(mySecurityProperties.getCorsAllowedMethods());
        configuration.applyPermitDefaultValues();

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }*/

    @Bean
    public MyAuthenticationTokenFilter myAuthenticationTokenFilter() {
        return new MyAuthenticationTokenFilter(this.myUserDetailsService);
    }

}
