package com.zr.aservice.sms.controller;

import com.zr.common.exception.AjaxResponse;
import com.zr.common.exception.CustomExceptionType;
import org.springframework.web.bind.annotation.RequestParam;

public class SmsControllerHandler {

  public static AjaxResponse sendFallback(@RequestParam String phoneNo,
                                          @RequestParam String content,
                                          Throwable e) {
    return AjaxResponse.error(CustomExceptionType.SYSTEM_ERROR,
            "短信服务发送接口出现错误");
  }

}
