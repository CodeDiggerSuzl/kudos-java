package com.suzl.juc.eightlock;

import java.util.concurrent.TimeUnit;

/**
 * 5、增加两个静态的同步方法，只有一个对象，先打印 发短信？打电话？
 * 6、两个对象！增加两个静态的同步方法， 先打印 发短信？打电话？
 *
 * @author Suz1
 * @date 2020/3/31 9:45 下午
 */
public class Test3 {
    public static void main(String[] args) {
        // 两个对象的Class类模板只有一个，static，锁的是Class
        Phone3 phone3 = new Phone3();
        Phone3 phone1 = new Phone3();
        //锁的存在
        new Thread(Phone3::sendMsg, "A").start();
        // 捕获
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(Phone3::call, "B").start();
    }
}

class Phone3 {
    // synchronized 锁的是方法的调用者
    // static 静态方法 类一加载就有了,锁的是 Class
    public static synchronized void sendMsg() {
        try {
            System.out.println("sleep for 4 second");
            TimeUnit.SECONDS.sleep(4);
            System.out.println("sleep for 4 second done");
            System.out.println("send msg");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void call() {
        System.out.println("call");
    }

    // 没有锁 不受锁的影响
    public void hello() {
        System.out.println("hello");
    }

}
