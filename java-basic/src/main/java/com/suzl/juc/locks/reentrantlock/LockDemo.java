package com.suzl.juc.locks.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock 可冲入锁
 *
 * @author Suz1
 * @date 2020/4/2 9:04 下午
 */
public class LockDemo {
    public static void main(String[] args) {
        Phone2 phone = new Phone2();
        new Thread(phone::sms, "A").start();

        new Thread(phone::sms, "B").start();
    }
}

class Phone2 {

    Lock lock = new ReentrantLock();

    public synchronized void sms() {
        /*
         细节问题: 两把锁
         lock 锁 必须配对 否则就会死锁,加两把锁就必须解两把锁
         */
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " sms");
            // 在第一个锁方法中加入第二个锁
            call();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public synchronized void call() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " call");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
