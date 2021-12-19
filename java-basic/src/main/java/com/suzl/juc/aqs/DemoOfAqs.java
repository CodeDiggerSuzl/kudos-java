package com.suzl.juc.aqs;

import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>aqs</p>
 *
 * @author suzailong
 * @date 2021/2/25 12:20 上午
 */
public class DemoOfAqs {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            System.out.println("模拟业务操作...");
        } finally {
            lock.unlock();
        }
    }
}
