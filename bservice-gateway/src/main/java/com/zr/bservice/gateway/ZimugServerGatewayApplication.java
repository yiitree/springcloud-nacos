package com.zr.bservice.gateway;

import com.alibaba.csp.sentinel.adapter.spring.webflux.callback.WebFluxCallbackManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class ZimugServerGatewayApplication {

	public static void main(String[] args) {
        // 自定义限流相应处理
        WebFluxCallbackManager.setBlockHandler(new MySentinelBlockHandler());
		SpringApplication.run(ZimugServerGatewayApplication.class, args);
	}


	// 方式二：编码方式实现路由
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("zimug-blog", r -> r.path("/category/**")
				//.and().method(HttpMethod.GET)
				//.or().host("192.168.1.4")
				.uri("http://zimug.com"))
				.build();
	}


}
