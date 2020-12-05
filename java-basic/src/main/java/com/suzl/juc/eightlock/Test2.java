package com.suzl.juc.eightlock;

import java.util.concurrent.TimeUnit;

/**
 * 增加一个普通方法
 * 3. 线程执行完,先执行普通方法
 * 4. 两个对象后,先执行
 *
 * @author Suz1
 * @date 2020/3/31 9:16 下午
 */
public class Test2 {
    public static void main(String[] args) {
        Phone2 phone1 = new Phone2();
        Phone2 phone2 = new Phone2();

        new Thread(phone1::sendMsg, "a").start();
        System.out.println("Here");
        try {
            System.out.println("sleep for 1 second");
            TimeUnit.SECONDS.sleep(1);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(phone2::call, "b").start();

    }
}

class Phone2 {
    public synchronized void sendMsg() {
        try {
            System.out.println("sleep for 4 second");
            TimeUnit.SECONDS.sleep(4);
            System.out.println("sleep for 4 second done");
            System.out.println("send msg");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void call() {
        System.out.println("call");
    }

    // 没有锁 不受锁的影响
    public void hello() {
        System.out.println("hello");
    }

}
