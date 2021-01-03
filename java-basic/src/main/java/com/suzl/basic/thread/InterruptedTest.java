package com.suzl.basic.thread;

/**
 * <p>interrupted</p>
 *
 * @author suzailong
 * @date 2021/1/2 10:07 下午
 */
public class InterruptedTest {
    public static void main(String[] args) throws InterruptedException {
        MyThread t = new MyThread();
        t.start();
        Thread.sleep(2);
        t.interrupt();
        t.join();
        System.out.println("end");
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        int n = 0;
        while (!isInterrupted()) {
            n++;
            System.out.println("n+\" hello\" = " + n + " hello");
        }
    }
}
