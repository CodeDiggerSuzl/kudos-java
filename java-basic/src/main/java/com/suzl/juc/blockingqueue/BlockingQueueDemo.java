package com.suzl.juc.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Suz1
 * @date 2020/4/1 12:57 下午
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        test1();
    }

    public static void test1() throws InterruptedException {
        ArrayBlockingQueue<Object> queue = new ArrayBlockingQueue<>(3);

        // 抛出异常
        queue.add("1");
        queue.remove("1");
        queue.element();

        // 有返回值,无异常
        queue.offer("2");
        queue.poll();
        queue.peek();

        // 一直阻塞
        queue.put(1);
        queue.take();

        // 超时等待
        queue.offer(3);
        queue.poll(2, TimeUnit.SECONDS);
    }
}
