package com.suzl.juc.locks.reentrantlock;

/**
 * 可重入锁测试
 *
 * @author Suz1
 * @date 2020/4/2 8:52 下午
 */
public class SyncDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(phone::sms, "A").start();

        new Thread(phone::sms, "B").start();
    }
}

class Phone {
    public synchronized void sms() {
        System.out.println(Thread.currentThread().getName() + " sms");
        // 在第一个锁方法中加入第二个锁
        call();
    }

    public synchronized void call() {
        System.out.println(Thread.currentThread().getName() + " call");
    }
}
