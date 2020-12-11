package com.zr.component.sercurity.model;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "zimug.security")
public class MySecurityProperties {

    //允许哪些域对本服务的跨域请求
    private List<String> corsAllowedOrigins;
    //允许哪些HTTP方法跨域
    private List<String> corsAllowedMethods;
    //是否关闭csrf跨站攻击防御功能
    private Boolean csrfDisabled = true;
    //开发过程临时开放的URI
    private List<String> devOpeningURI;
    //权限全面开放的接口，不需要JWT令牌就可以访问
    private List<String> permitAllURI;

}
