package com.kudos.entity;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * <p>Food</p>
 *
 * @author suzailong
 * @date 2021/1/3 10:44 下午
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) // "prototype"
public class Food {
    private String name;
    private String price;
}
