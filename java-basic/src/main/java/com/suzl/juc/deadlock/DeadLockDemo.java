package com.suzl.juc.deadlock;

import java.util.concurrent.TimeUnit;

/**
 * @author Suz1
 * @date 2020/4/2 10:43 下午
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        String A = "A";
        String B = "B";

        new Thread(new MyThread(A, B), "T1 ").start();
        new Thread(new MyThread(B, A), "T2 ").start();
    }
}

class MyThread implements Runnable {

    private String A;
    private String B;

    public MyThread(String a, String b) {
        this.A = a;
        this.B = b;
    }

    @Override
    public void run() {
        synchronized (A) {
            System.out.println(Thread.currentThread().getName() + "lock:" + A + "=> try to get" + B);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (B) {
                System.out.println(Thread.currentThread().getName() + "lock:" + B + "=> try to get" + A);
            }
        }
    }
}
