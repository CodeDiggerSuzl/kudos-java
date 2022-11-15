package com.kudos;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@EnableAspectJAutoProxy
@Slf4j
public class AopMonitor {

    @Pointcut("@annotation(AopMark)")
    public void fun() {

    }


    @Before("fun()")
    public void before(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        String className = signature.getDeclaringType().getSimpleName();
        Object[] args = joinPoint.getArgs();
        log.info("before method name={},className=={},ars={}", JSON.toJSONString(methodName), JSON.toJSONString(className), JSON.toJSONString(args));
    }

    @Around("fun()")
    public Object around(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        String className = method.getDeclaringClass().getName();
        String methodName = method.getName();

        Object proceed;
        try {
            proceed = joinPoint.proceed();
            log.info("aop result = {}", JSON.toJSONString(proceed));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return proceed;
    }

}
