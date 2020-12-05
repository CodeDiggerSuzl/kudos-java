package com.suzl.juc.blockingqueue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Suz1
 * @date 2020/4/1 1:32 下午
 */
public class SyncQueueDemo {
    public static void main(String[] args) {
        SynchronousQueue<Integer> queue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " put 1");
                queue.put(1);
                System.out.println(Thread.currentThread().getName() + " put 2");
                queue.put(2);
                System.out.println(Thread.currentThread().getName() + " put 3");
                queue.put(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(queue.take());
                TimeUnit.SECONDS.sleep(1);
                System.out.println(queue.take());
                TimeUnit.SECONDS.sleep(1);
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T2").start();
    }
}
