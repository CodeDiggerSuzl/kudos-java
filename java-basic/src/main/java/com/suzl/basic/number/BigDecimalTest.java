package com.suzl.basic.number;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * <p>BigDecimal 常用方法测试</p>
 *
 * @author SuZaiLong
 * @date 2020/11/9 9:22 下午
 */
public class BigDecimalTest {
    @Test
    public void bigDecimalTest() {
        String s = new BigDecimal("10.0010").stripTrailingZeros().toPlainString();
        System.out.println(s);
        BigDecimal divide = new BigDecimal("10").divide(new BigDecimal("2"));
        System.out.println(divide);
    }

    @Test
    public void addTest() {
        BigDecimal bigDecimal = BigDecimal.valueOf(0.1D);
        BigDecimal b2 = BigDecimal.valueOf(1.3D);
        BigDecimal add = bigDecimal.add(b2);
        System.out.println(add);
        BigDecimal subtract = b2.subtract(bigDecimal);
        System.out.println(subtract);

    }
}
