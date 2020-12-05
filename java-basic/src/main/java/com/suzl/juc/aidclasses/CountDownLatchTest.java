package com.suzl.juc.aidclasses;

import java.util.concurrent.CountDownLatch;

/**
 * 计数器
 *
 * @author Suz1
 * @date 2020/4/1 11:37 上午
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch downLatch = new CountDownLatch(6);
        // 用在必须要执行任务的情况下使用
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " went out");
                downLatch.countDown();
            }, String.valueOf(i)).start();
        }
        // 等待计数器归零,在向下执行
        downLatch.await();
        System.out.println("end");
    }
}
