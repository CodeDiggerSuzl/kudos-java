package com.suzl.juc.producerconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition 精准唤醒其他线程
 * 多个线程顺序执行
 * A->B->C
 *
 * @author Suz1
 * @date 2020/3/31 8:37 下午
 */
public class ConditionTest {
    public static void main(String[] args) {
        Data3 data3 = new Data3();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data3.printA();
            }
        }, "a").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data3.printB();
            }
        }, "b").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data3.printC();
            }
        }, "c").start();
    }
}

class Data3 {
    /**
     * 不同的监视器 监视不同的线程
     */
    private final Lock lock = new ReentrantLock();
    private final Condition cond1 = lock.newCondition();
    private final Condition cond2 = lock.newCondition();
    private final Condition cond3 = lock.newCondition();

    private volatile int n = 1;

    public void printA() {
        lock.lock();
        try {
            // 判断是否等待, 执行 然后进行通知
            while (n != 1) {
                // 等待
                cond1.await();
            }
            System.out.println(Thread.currentThread().getName() + "-->aaa");
            // 唤醒特定的线程
            n = 2;
            cond2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        lock.lock();
        try {
            // 判断是否等待, 执行 然后进行通知
            while (n != 2) {
                cond2.await();
            }
            System.out.println(Thread.currentThread().getName() + "-->bbb");
            n = 3;
            cond3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        lock.lock();
        try {
            //判断是否等待, 执行 然后进行通知
            while (n != 3) {
                cond3.await();
            }
            System.out.println(Thread.currentThread().getName() + "-->ccc");
            n = 1;
            cond1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
