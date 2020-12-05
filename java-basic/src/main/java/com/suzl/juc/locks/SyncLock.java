package com.suzl.juc.locks;

/**
 * 测试传统 sync 锁
 *
 * @author Suz1
 * @date 2020/3/31 4:46 下午
 */
public class SyncLock {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        // 资源类丢入线程中
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "Thread-1").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "Thread-2").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "Thread-3").start();
    }

}

class Ticket {
    private int num = 30;

    // 卖票
    public void sale() {

        if (num > 0) {
            System.out.println(Thread.currentThread().getName() + " sold " + num-- + "票,剩余->" + num);
        }
    }
}
