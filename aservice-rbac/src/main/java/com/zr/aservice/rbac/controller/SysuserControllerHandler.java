package com.zr.aservice.rbac.controller;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zr.common.exception.AjaxResponse;
import com.zr.common.exception.CustomExceptionType;
import org.springframework.web.bind.annotation.RequestParam;


public class SysuserControllerHandler {

  public static AjaxResponse pwdresetBlockHandler(@RequestParam Integer userId, BlockException blockException) {
    return AjaxResponse.error(CustomExceptionType.SYSTEM_ERROR, "尊敬的客户您好，系统服务繁忙，请稍后再试!");
  }

  public static AjaxResponse pwdresetFallback(@RequestParam Integer userId,Throwable e) {
    return AjaxResponse.error(CustomExceptionType.SYSTEM_ERROR, "尊敬的客户您好，系统服务繁忙，请稍后再试!（pwdresetFallBack）");
  }

  // 通用的fall方法，注意都是static方法
  public static AjaxResponse defaultFallback() {
    return AjaxResponse.error(CustomExceptionType.SYSTEM_ERROR,
            "尊敬的客户您好，系统服务繁忙，请稍后再试!（defaultFallback）");
  }

}
