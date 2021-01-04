package com.kudos.entity;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * <p>language </p>
 *
 * @author suzailong
 * @date 2021/1/3 10:48 下午
 */
@Component
@Order(1)
public class LangLearning implements BeBetter {
    @Override
    public void learning() {
        System.out.println("learning language.");
    }
}
