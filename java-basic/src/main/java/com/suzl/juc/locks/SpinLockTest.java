package com.suzl.juc.locks;

import com.suzl.juc.locks.selfspin.SelfSpinLock;

import java.util.concurrent.TimeUnit;

/**
 * @author Suz1
 * @date 2020/4/2 9:55 下午
 */
public class SpinLockTest {
    public static void main(String[] args) throws InterruptedException {

        SelfSpinLock spinLock = new SelfSpinLock();
        new Thread(() -> {
            spinLock.myLock();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                spinLock.myUnlock();
            }
        }, "A").start();


        TimeUnit.SECONDS.sleep(1); // make the Thread A gets lock first

        new Thread(() -> {
            spinLock.myLock();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                spinLock.myUnlock();
            }
        }, "B").start();
    }
}
