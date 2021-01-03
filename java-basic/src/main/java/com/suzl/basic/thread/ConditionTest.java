package com.suzl.basic.thread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>Condition</p>
 *
 * @author suzailong
 * @date 2021/1/3 12:31 上午
 */
public class ConditionTest {

}

class TaskQueue {
    private final Lock          lock  = new ReentrantLock();
    private final Condition     cond  = lock.newCondition();
    private       Queue<String> queue = new LinkedList<>();

    public void addTask(String s) {
        lock.lock();
        try {
            queue.add(s);
            cond.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public String getTask() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                cond.await();
            }
            return queue.remove();
        } finally {
            lock.unlock();
        }
    }

}
