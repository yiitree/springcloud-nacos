package com.zr.common.config;


import com.zr.common.exception.AjaxResponse;
import com.zr.common.exception.CustomException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public AjaxResponse customerException(CustomException e) {
        return AjaxResponse.error(e);
    }
}
