package com.kudos.entity;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * <p>Food</p>
 *
 * @author suzailong
 * @date 2021/1/3 10:44 下午
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) // "prototype"
//@Data

public class Food implements Serializable {
    private String name;

    private String priceDetail;

    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriceDetail() {
        return priceDetail;
    }

    public void setPriceDetail(String priceDetail) {
        this.priceDetail = priceDetail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
