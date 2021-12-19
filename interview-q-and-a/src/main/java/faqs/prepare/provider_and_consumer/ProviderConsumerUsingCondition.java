package faqs.prepare.provider_and_consumer;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用 Condition 来做线程同步
 */
public class ProviderConsumerUsingCondition {
    static final int queueSize = 2;
    static Queue<Integer> queue = new PriorityQueue<>(queueSize);
    static Lock lock = new ReentrantLock();
    static Condition emptyCond = lock.newCondition();
    static Condition fullCond = lock.newCondition();

    public static void main(String[] args) {
        // 生产者
        new Thread(() -> {
            while (true) {
                try {
                    lock.lock();
                    while (queue.size() == queueSize) {
                        fullCond.await(); // 如果满了就使用 等待
                    }
                    queue.offer(1);
                    System.out.println("生产了一个数据....");
                    Thread.sleep(1000);
                    emptyCond.signal(); // 生产一个告诉消费者不用等待了 开始消费
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    lock.lock();
                    while (queue.size() == 0) {
                        emptyCond.await();
                    }
                    queue.poll();
                    System.out.println("消费了一个数据");
                    fullCond.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();

    }
}
