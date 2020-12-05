package com.suzl.juc.eightlock;

import java.util.concurrent.TimeUnit;

/**
 * 什么是锁? 执行顺序问题
 * 0、标准情况下，两个线程先打印发短信还是打电话？ 1/发短信  2/打电话
 * 1、sendSms延迟4秒，两个线程先打印发短信还是打电话？ 1/发短信  2/打电话
 * 结论:
 * synchronize 锁的对象是方法的调用者
 * 两个方法使用的是同一把锁,谁先拿先执行
 *
 * @author Suz1
 * @date 2020/3/31 9:16 下午
 */
public class Test1 {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(phone::sendMsg, "a").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(phone::call, "b").start();

    }
}

class Phone {
    public synchronized void sendMsg() {
        // synchronized 锁的对象是方法的调用者！
        // 两个方法用的是同一个锁，谁先拿到谁执行！
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("send msg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void call() {
        System.out.println("call");
    }


}
