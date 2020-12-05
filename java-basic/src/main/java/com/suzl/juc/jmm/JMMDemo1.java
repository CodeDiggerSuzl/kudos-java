package com.suzl.juc.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试
 *
 * @author Suz1
 * @date 2020/4/1 8:30 下午
 */
public class JMMDemo1 {
    // 使用原子类
    private volatile static AtomicInteger n = new AtomicInteger();

    public static void add() {
        // AtomInteger + 1 方法, 底层使用的是 CAS
        n.getAndIncrement();
    }

    public static void main(String[] args) {
        // 理论上 2W
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + " --> " + n);
    }
}
