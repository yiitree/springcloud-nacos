package com.zr.bservice.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.zr.bservice.gateway.jwt.exception.AjaxResponse;
import com.zr.bservice.gateway.jwt.exception.CustomExceptionType;
import com.zr.bservice.gateway.jwt.model.JwtProperties;
import com.zr.bservice.gateway.jwt.utils.JwtTokenUtil;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * 全局过滤器，所有访问都要先检验令牌
 * 检验是否登录
 *
 * 全局令牌检验过滤器，检验是否有登录
 * 验证JWT的合法性，对于不合法请求直接返回，不予转发。
 * 对于JWT合法的情况，从JWT中解析出userId（用户身份信息），并放入HTTP header中。（网关后面的服务会用到，下一节）
 */
@Configuration
public class JWTAuthCheckFilter {
  @Resource
  private JwtProperties jwtProperties;
  @Resource
  private JwtTokenUtil jwtTokenUtil;

  @Bean
  @Order(-101)
  public GlobalFilter jwtAuthGlobalFilter() {
    return (exchange, chain) -> {
      ServerHttpRequest serverHttpRequest = exchange.getRequest();
      ServerHttpResponse serverHttpResponse = exchange.getResponse();
      String requestUrl = serverHttpRequest.getURI().getPath();


      if(!requestUrl.equals("/authentication")){
        //从HTTP请求头中获取JWT令牌
        String jwtToken = serverHttpRequest
                .getHeaders()
                .getFirst(jwtProperties.getHeader());
         //对Token解签名，并验证Token是否过期（即校验令牌的合法性）
        boolean isJwtNotValid = jwtTokenUtil.isTokenExpired(jwtToken);
        if(isJwtNotValid){ //如果JWT令牌不合法
          return writeUnAuthorizedMessageAsJson(serverHttpResponse,"请先去登录，再访问服务！");
        }
        //从JWT中解析出当前用户的身份（userId），并继续执行过滤器链，转发请求
        ServerHttpRequest mutableReq = serverHttpRequest
                .mutate()
                .header("userName", jwtTokenUtil.getUsernameFromToken(jwtToken))
                .build();
        ServerWebExchange mutableExchange = exchange.mutate().request(mutableReq).build();
        return chain.filter(mutableExchange);
      }else{ //如果是登录认证请求，直接执行不需要进行JWT权限验证
        return chain.filter(exchange);
      }
    };
  }

  //将JWT鉴权失败的消息响应给客户端
  private Mono<Void> writeUnAuthorizedMessageAsJson(ServerHttpResponse serverHttpResponse,String message) {
    serverHttpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
    serverHttpResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
    AjaxResponse ajaxResponse = AjaxResponse.error(CustomExceptionType.USER_INPUT_ERROR,message);
    DataBuffer dataBuffer = serverHttpResponse.bufferFactory()
            .wrap(JSON.toJSONStringWithDateFormat(ajaxResponse,JSON.DEFFAULT_DATE_FORMAT)
                    .getBytes(StandardCharsets.UTF_8));
    return serverHttpResponse.writeWith(Flux.just(dataBuffer));
  }

}
