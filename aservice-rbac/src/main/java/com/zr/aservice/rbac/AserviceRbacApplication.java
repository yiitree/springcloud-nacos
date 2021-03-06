package com.zr.aservice.rbac;

import feign.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(
        scanBasePackages={"com.zr"}
        // 添加微服务内部的权限校验，所以增加配置
//        exclude = {SecurityAutoConfiguration.class}
)
@MapperScan(basePackages = {"com.zr.**"})
// 服务发现，通用方式，其他注册中心也可以用
@EnableDiscoveryClient
// 开启openFeign远程调用
@EnableFeignClients
public class AserviceRbacApplication {

	public static void main(String[] args) {
		SpringApplication.run(AserviceRbacApplication.class, args);
	}

	// 在通用包中已经添加，所以就不需要在new了
//    /**
//     * 加密bean
//     * @return
//     */
//	@Bean
//	public PasswordEncoder passwordEncoder(){
//		return new BCryptPasswordEncoder();
//	}

    /**
     * openFeign日志 然后再在配置类（比如主程序入口类）中加入Looger.Level的Bean
     * @return
     */
    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }

}
