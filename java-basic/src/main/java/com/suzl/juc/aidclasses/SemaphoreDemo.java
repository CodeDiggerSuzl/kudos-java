package com.suzl.juc.aidclasses;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author Suz1
 * @date 2020/4/1 12:11 下午
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        // 线程数量,停车位, 在有限的资源下维持一种秩序,限流
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 7; i++) {
            new Thread(() -> {
                // acquire() 获得
                try {
                    semaphore.acquire(); // 抢到停车位
                    System.out.println(Thread.currentThread().getName() + " ⏰ 停车 1 秒");
                    TimeUnit.SECONDS.sleep(1); // 停车一秒
                    System.out.println(Thread.currentThread().getName() + " 🚙 离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release(); // release() 释放
                }
            }, String.valueOf(i)).start();
        }
    }
}
