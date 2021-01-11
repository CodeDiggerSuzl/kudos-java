package com.kudos.translike;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author suzailong
 * @date 2021/1/11 11:46 下午
 */
@Aspect
@Component
public class MetricAspect {
    @Around("@annotation(metricTimeMark)")
    public Object metric(ProceedingJoinPoint joinPoint, MetricTimeMark metricTimeMark) throws Throwable {
        String name = metricTimeMark.value();
        long start = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        } finally {
            long t = System.currentTimeMillis() - start;
            // 写入日志或发送至JMX:
            System.err.println("[Metrics] " + name + ": " + t + "ms");
        }
    }
}
