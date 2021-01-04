package com.kudos.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理类 <a href="https://juejin.im/post/6844903826412011533"></a>
 *
 * @author Suz1
 * @date 2020/10/9 11:27 下午
 */
@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String error(Exception e) {
        e.printStackTrace();
        System.out.println(e.getMessage());
        return "ops,something wrong happened";
    }
}
