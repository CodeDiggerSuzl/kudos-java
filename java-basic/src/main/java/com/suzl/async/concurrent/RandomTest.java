package com.suzl.async.concurrent;

import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

/**
 * <p>Random 测试</p>
 *
 * @author suzailong
 * @date 2020/12/16 9:00 下午
 */
public class RandomTest {
    @Test
    public void threadLocalTest() {
        for (int i = 0; i < 100; i++) {
            int k = ThreadLocalRandom.current().nextInt(0, 30);
            System.out.println(k);
        }

    }
}
