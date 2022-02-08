package com.suzl.juc.aidclasses;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 循环屏障
 *
 * @author Suz1
 * @date 2020/4/1 11:53 上午
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(7, () -> {
            System.out.println("all done");
        });

        for (int i = 1; i <= 7; i++) {
            // 只能拿到 final 型的
            final int t = i;
            int finalI = i;
            new Thread(() -> {
                try {
                    if (finalI == 3) {
                        barrier.reset();
                        System.out.println(Thread.currentThread().getName() + " get " + t);
                    } else {
                        // 等待计数器变为 7
                        barrier.await();
                    }
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
