package com.zr.aservice.sms.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.zr.common.exception.AjaxResponse;
import com.zr.common.exception.CustomException;
import com.zr.common.exception.CustomExceptionType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms")
public class SmsController {

    @Value("${server.port}")
    private String serverPort;

    /**
     * 模拟短信发送
     * @param phoneNo
     * @param content
     * @return 短信发送结果
     */
    @PostMapping(value = "/send")
    @SentinelResource(value = "smsSend",
            fallbackClass = SmsControllerHandler.class,
            fallback = "sendFallback")
    public AjaxResponse send(@RequestParam String phoneNo,
                             @RequestParam String content) {
        if(content.isEmpty() || phoneNo.isEmpty()){
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,
                    "消息内容或手机号不能为空！");
        }
        System.out.println(serverPort + "发送短消息:" + content);

        return AjaxResponse.success("短消息发送成功！");
    }
}
