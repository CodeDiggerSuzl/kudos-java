package com.suzl.juc.locks.selfspin;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 *
 * @author Suz1
 * @date 2020/4/2 9:38 下午
 */
public class SelfSpinLock {

    AtomicReference<Thread> ref = new AtomicReference<>();

    // lock
    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "--> my lock");
        // while
        while (!ref.compareAndSet(null, thread)) { }
    }

    // unlock
    public void myUnlock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + " --> my unlock");
        ref.compareAndSet(thread, null);
    }
}
