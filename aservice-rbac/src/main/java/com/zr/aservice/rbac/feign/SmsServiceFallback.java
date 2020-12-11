package com.zr.aservice.rbac.feign;

import com.zr.common.exception.AjaxResponse;
import com.zr.common.exception.CustomExceptionType;
import org.springframework.stereotype.Component;

@Component
public class SmsServiceFallback implements SmsService {

   @Override
   public AjaxResponse send(String phoneNo, String content) {
      return AjaxResponse.error(CustomExceptionType.SYSTEM_ERROR
                  ,"短信发送接口失败!");
   }
}
