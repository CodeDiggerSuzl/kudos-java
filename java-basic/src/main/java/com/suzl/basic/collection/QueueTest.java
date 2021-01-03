package com.suzl.basic.collection;

import org.junit.Test;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * <p>Queue</p>
 *
 * @author suzailong
 * @date 2021/1/2 2:59 下午
 */
public class QueueTest {
    @Test
    public void queueTest() {
        /*
         *
         - 通过add()/offer()方法将元素添加到队尾；
         - 通过remove()/poll()从队首获取元素并删除；
         - 通过element()/peek()从队首获取元素但不删除。
         - 要避免把null添加到队列。
         */
        ArrayBlockingQueue<String> q = new ArrayBlockingQueue<>(10);
        q.add("1");
        q.add("2");
        q.add("3");
        System.out.println("q = " + q);
        q.offer("0");
        System.out.println("q = " + q);

        String peek = q.peek();
        System.out.println(peek);

        String poll = q.poll();
        System.out.println(poll);
        System.out.println("q = " + q);
    }

    @Test
    public void priorityQueueTest() {
        Queue<String> q = new PriorityQueue<>();
        // 添加3个元素到队列:
        q.offer("apple");
        q.offer("pear");
        q.offer("banana");
        System.out.println(q.poll()); // apple
        System.out.println(q.poll()); // banana
        System.out.println(q.poll()); // pear
        System.out.println("q = " + q);
        System.out.println(q.poll()); // null,因为队列为空
    }
}
