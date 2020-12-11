package com.zr.aservice.rbac.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 配置restTemplate
 * 使用restTemplate必须是spring环境
 * restTemplate内部有三种实现：
 *  SimpleClientHttpRequestFactory（封装URLConnection，JDK自带，默认实现）
 *  HttpComponentsClientHttpRequestFactory（封装第三方类库HttpClient）
 *  OkHttp3ClientHttpRequestFactory(封装封装第三方类库OKHttp)
 */
@Configuration
public class ContextConfig {

    // 默认实现，实际与urlConnection是一样的底层实现
    @Bean
    // 添加负载均衡
    @LoadBalanced
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

//    //默认实现
//    @Bean("urlConnection")
//    public RestTemplate urlConnectionRestTemplate(){
//        RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
//        return restTemplate;
//    }
//
//    @Bean("httpClient")
//    public RestTemplate httpClientRestTemplate(){
//        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
//        return restTemplate;
//    }
//
//    @Bean("OKHttp3")
//    public RestTemplate OKHttp3RestTemplate(){
//        RestTemplate restTemplate = new RestTemplate(new OkHttp3ClientHttpRequestFactory());
//        return restTemplate;
//    }
}
