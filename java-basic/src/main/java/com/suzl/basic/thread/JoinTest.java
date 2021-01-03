package com.suzl.basic.thread;

/**
 * <p>join method test</p>
 *
 * @author suzailong
 * @date 2021/1/2 10:03 下午
 */
public class JoinTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("hello in thread");
        });
        System.out.println("start");
        thread.start();
        // 一个线程还可以等待另一个线程直到其运行结束。例如，main线程在启动t线程后，可以通过t.join()等待t线程结束后再继续运行
        thread.join();
        System.out.println("end");

    }
}
