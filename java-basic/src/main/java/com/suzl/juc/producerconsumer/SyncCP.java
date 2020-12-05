package com.suzl.juc.producerconsumer;

/**
 * @author Suz1
 * @date 2020/3/31 5:44 下午
 */
public class SyncCP {
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread 1").start();
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread 2").start();
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread 3").start();
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread 4").start();
    }

}

class Data {
    private int num = 0;

    public synchronized void increment() throws InterruptedException {
        // * Use while to avoid
        while (num != 0) {
            this.wait();
        }
        num++;
        // 通知其他线程
        System.out.println(Thread.currentThread().getName() + ">>> " + num);
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        while (num == 0) {
            this.wait();
        }
        num--;
        System.out.println(Thread.currentThread().getName() + ">>> " + num);
        this.notifyAll();
    }
}
