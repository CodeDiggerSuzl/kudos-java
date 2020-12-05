package com.suzl.juc.jmm;

import java.util.concurrent.TimeUnit;

/**
 * 保证可见性
 *
 * @author Suz1
 * @date 2020/4/1 8:23 下午
 */
public class JMMDemo {
    // main 线程
    private static volatile int n = 0;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            System.out.println(n);
            while (n == 0) {

            }
        }).start();

        TimeUnit.SECONDS.sleep(1); // 保证上面的线程的启动
        // main 修改了 n 的值
        n = 1;
    }
}
