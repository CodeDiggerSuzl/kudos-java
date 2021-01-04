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
@Order(0)
public class SkillLearning implements BeBetter {
    @Override
    public void learning() {
        System.out.println("learning skill.");
    }
}
