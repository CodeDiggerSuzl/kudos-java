package com.suzl.basic;


import org.junit.Assert;
import org.junit.Test;

/**
 * 测试 hashcode and equals
 *
 * @author Suz1
 * @date 2020/9/11 4:36 下午
 */
public class HashCodeEqualsTest {
    @Test
    public void equalsTest() {
        Integer b = 2;
        int a = 2;
        Assert.assertTrue(a == b);
        new String("ss");
    }
}
