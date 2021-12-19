package faqs.prepare.provider_and_consumer;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 使用 wait 和 notify 来实现生产者消费者模型
 */
public class ProviderConsumerUsingNotify {

    static final int size = 1;
    static final Queue<Integer> queue = new PriorityQueue<>(size);

    // 是用 Java 8 lambda 的方式
    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                synchronized (queue) {
                    while (queue.size() == size) {
                        try {
                            queue.wait();
                            System.out.println("q full wait...");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.offer(1);
                    System.out.println("produce one ...");
                    queue.notify();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                synchronized (queue) {
                    while (queue.size() == 0) {
                        try {
                            queue.wait();
                            System.out.println("queue empty wait...");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.poll();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("consumer one ...");
                    queue.notify();
                }
            }
        }).start();
    }


    // 下面的是使用实现 Runnable 方法
//    public static void main(String[] args) {
//
//        ProviderConsumerUsingNotify domo = new ProviderConsumerUsingNotify();
//        Provider provider = domo.new Provider();
//        Consumer consumer = domo.new Consumer();
//
//        new Thread(provider).start();
//        new Thread(consumer).start();
//    }
//
//
//    class Provider implements Runnable {
//        @Override
//        public void run() {
//            while (true) {
//                synchronized (queue) {
//                    while (queue.size() == size) {
//                        try {
//                            queue.wait();
//                            // System.out.println("queue is full, wait...");
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    queue.offer(1);
//                    System.out.println("生产一个元素了...");
//                    queue.notify(); //  生产之后通知等待的线程
//                }
//            }
//        }
//    }
//
//    class Consumer implements Runnable {
//        @Override
//        public void run() {
//            while (true) {
//                synchronized (queue) {
//                    while (queue.size() == 0) {
//                        try {
//                            queue.wait();
//                            // System.out.println("queue is empty, wait...");
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    queue.poll();
//                    System.out.println("消费一个元素了...");
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    queue.notify(); //  消费之后通知等待的线程
//                }
//            }
//        }
//
//    }
}