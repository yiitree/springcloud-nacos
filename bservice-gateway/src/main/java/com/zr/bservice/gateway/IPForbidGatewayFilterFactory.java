package com.zr.bservice.gateway;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 自定义局部过滤器
 * 指定IP访问
 */
@Component
@Order(99)
public class IPForbidGatewayFilterFactory
    extends AbstractGatewayFilterFactory<IPForbidGatewayFilterFactory.Config> {

    public IPForbidGatewayFilterFactory()
    {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder()
    {
        return Arrays.asList("permitIp");  //对应config类的参数
    }

    @Override
    public GatewayFilter apply(Config config)
    {
        return (exchange, chain) -> {
            //获取服务访问的客户端ip
            String ip = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
            if (config.getPermitIp().equals(ip)) {
                //如果客户端ip=permitIp，继续执行过滤器链允许继续访问
                return chain.filter(exchange);
            }
            //否则返回，拒绝请求
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return exchange.getResponse().setComplete();
        };
    }

    static public class Config
    {
        private String permitIp;

        public String getPermitIp() {
            return permitIp;
        }

        public void setPermitIp(String permitIp) {
            this.permitIp = permitIp;
        }
    }
}
