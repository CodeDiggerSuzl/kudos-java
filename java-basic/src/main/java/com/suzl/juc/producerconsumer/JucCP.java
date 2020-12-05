package com.suzl.juc.producerconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Suz1
 * @date 2020/3/31 6:11 下午
 */
public class JucCP {
    public static void main(String[] args) {
        Data2 data2 = new Data2();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data2.increment();
            }
        }, "Thread 2").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data2.decrement();
            }
        }, "Thread 1").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data2.decrement();
            }
        }, "Thread 3").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data2.increment();
            }
        }, "Thread 4").start();

    }
}

class Data2 {
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    private int num = 0;

    public void increment() {
        lock.lock();
        try {
            // * Use while to avoid
            while (num != 0) {
                condition.await();
            }
            num++;
            // 通知其他线程
            System.out.println(Thread.currentThread().getName() + ">>> " + num);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void decrement() {
        lock.lock();
        try {
            // * Use while to avoid
            while (num == 0) {
                condition.await();
            }
            num--;
            // 通知其他线程
            System.out.println(Thread.currentThread().getName() + ">>> " + num);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
