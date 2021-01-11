package com.kudos.translike;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>和 Transaction 类似的注解</p>
 * <a href="https://www.liaoxuefeng.com/wiki/1252599548343744/1310052317134882">链接</a>
 *
 * @author suzailong
 * @date 2021/1/11 11:38 下午
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MetricTimeMark {
    String value();
}
