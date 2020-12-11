package com.zr.aservice.rbac.feign;

import com.zr.common.exception.AjaxResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// eureka大写
//@FeignClient("ASERVICE-SMS")
// nacos保持一致
//@FeignClient("aservice-sms")
@FeignClient(name="aservice-sms",fallback = SmsServiceFallback.class)
public interface SmsService {

  @PostMapping(value = "/sms/send")
  AjaxResponse send(@RequestParam("phoneNo") String phoneNo,
                    @RequestParam("content") String content);

}
