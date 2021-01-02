package com.suzl.basic.number;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

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

    @Test
    public void methodTest() {
        BigDecimal b1 = new BigDecimal("123.000");
        int scale = b1.scale();
        System.out.println(scale); // 3
        BigDecimal b2 = b1.stripTrailingZeros();
        System.out.println(b2);
        int scale1 = b2.scale();
        System.out.println(scale1);

        // scale
        BigDecimal b3 = new BigDecimal("1200");
        System.out.println(b3.scale());
        BigDecimal b4 = b3.stripTrailingZeros();
        int scale2 = b4.scale();
        System.out.println("scale2 = " + scale2); // -2
        BigDecimal b5 = b4.setScale(3, RoundingMode.HALF_UP);
        System.out.println("b5 = " + b5);
    }
}
