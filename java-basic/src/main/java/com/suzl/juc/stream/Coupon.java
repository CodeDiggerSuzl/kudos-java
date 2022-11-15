package com.suzl.juc.stream;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Coupon {
    private String expireTime;
    private String money;
}
