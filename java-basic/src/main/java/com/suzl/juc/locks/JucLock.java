package com.suzl.juc.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Suz1
 * @date 2020/3/31 5:17 下午
 */
public class JucLock {
    public static void main(String[] args) {
        Apple apple = new Apple();
        new Thread(() -> { for (int i = 0; i < 40; i++) {apple.sale(); }}, "Thread-1").start();
        new Thread(() -> { for (int i = 0; i < 40; i++) {apple.sale(); }}, "Thread-2").start();
        new Thread(() -> { for (int i = 0; i < 40; i++) {apple.sale(); }}, "Thread-3").start();
    }
}


class Apple {
    Lock lock = new ReentrantLock();
    private int num = 30;


    // 卖票
    public void sale() {
        lock.lock();
        try {
            if (num > 0) {
                System.out.println(Thread.currentThread().getName() + " sold " + num-- + ",剩余->" + num);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
