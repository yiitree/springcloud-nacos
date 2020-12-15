package com.zr.bservice.gateway;

import com.alibaba.csp.sentinel.adapter.spring.webflux.callback.WebFluxCallbackManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class BServerGatewayApplication {

	public static void main(String[] args) {
        // 自定义限流相应处理
        WebFluxCallbackManager.setBlockHandler(new MySentinelBlockHandler());
		SpringApplication.run(BServerGatewayApplication.class, args);
	}


	// 方式二：编码方式实现路由
    // 优点：更加灵活，多了or，配置文件的都是and
    // 缺点：比较麻烦没发结合配置中心实现热修改
//	@Bean
//	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//		return builder.routes()
//                // 路由名称
//				.route("zimug-blog", r -> r.path("/category/**")
//				//.and().method(HttpMethod.GET)
//				//.or().host("192.168.1.4")
//				.uri("http://zimug.com"))
//				.build();
//	}

}
