package com.suzl.basic;

import org.junit.Test;

/**
 * <p>位操作测试</p>
 *
 * @author suzailong
 * @date 2020/12/17 9:24 下午
 */
public class BitOpsTest {
    static int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= 1 << 30) ? 1 << 30 : n + 1;
    }

    @Test
    public void bitOpsTest() {
        String key = "su";
        int h;

        int h1 = key.hashCode();
        System.out.println(h1);
        int i = (h = h1) ^ (h >>> 16);
        System.out.println(i);
    }

    @Test
    public void tableSizeTest() {
        int i = tableSizeFor(21);
        System.out.println(i);
    }

}
