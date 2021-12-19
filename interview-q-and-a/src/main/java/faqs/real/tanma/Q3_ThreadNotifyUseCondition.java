package faqs.real.tanma;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <b>进行线程同步</b>
 * <p>
 * 1. 使用 wait 和 notify & notifyAll
 * <p>
 * wait()、notify()和notifyAll()是Object类中的方法，
 * <p>
 * 调用某个对象的 wait() 方法能让当前线程阻塞，并且当前线程必须拥有此对象的monitor（即锁）
 * <p>
 * 调用某个对象的 notify()方法能够唤醒一个正在等待这个对象的monitor的线程，如果有多个线程都在等待这个对象的monitor，则只能唤醒其中一个线程
 * <p>
 * 调用notifyAll()方法能够唤醒所有正在等待这个对象的monitor的线程；
 * <p>
 * 如果调用某个对象的wait()方法，当前线程必须拥有这个对象的monitor（即锁），**因此调用wait()方法必须在同步块或者同步方法中进行**（synchronized块或者synchronized方法）。
 * <p>
 * **上面已经提到，如果调用某个对象的wait()方法，当前线程必须拥有这个对象的monitor（即锁），因此调用wait()方法必须在同步块或者同步方法中进行（synchronized块或者synchronized方法）。**
 * <p>
 * 2. 使用 Condition
 * <p>
 * Condition是在java 1.5中才出现的，它用来替代传统的Object的wait()、notify()实现线程间的协作，相比使用Object的wait()、notify()，使用Condition1的await()、signal()这种方式实现线程间协作更加安全和高效。因此通常来说比较推荐使用Condition，在阻塞队列那一篇博文中就讲述到了，阻塞队列实际上是使用了Condition来模拟线程间协作。
 * <p>
 * - Condition是个接口，基本的方法就是await()和signal()方法；
 * - **Condition依赖于Lock接口，生成一个Condition的基本代码是lock.newCondition()**
 * - **调用Condition的await()和signal()方法，都必须在lock保护之内，就是说必须在lock.lock()和lock.unlock之间才可以使用**
 * <p>
 * Conditon中的await()对应Object的wait()；
 * <p>
 * Condition中的signal()对应Object的notify()；
 * <p>
 * Condition中的signalAll()对应Object的notifyAll()。
 * <p>
 * Condition 能够通过多个 condition 实例指定特定的等待锁的线程， 而notify & notifyAll 就不能。
 * 使用 Condition 来进行数据的同步
 */
public class Q3_ThreadNotifyUseCondition {

    public static void main(String[] args) {
        ConditionData conditionData = new ConditionData();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                conditionData.printA();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                conditionData.printB();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                conditionData.printC();
            }
        }).start();
    }

}

class ConditionData {
    private final Lock lock = new ReentrantLock();
    private final Condition cond1 = lock.newCondition();
    private final Condition cond2 = lock.newCondition();
    private final Condition cond3 = lock.newCondition();
    private volatile int flag = 1;

    public void printA() {
        try {
            lock.lock();
            while (flag != 1) {
                cond1.await();
            }
            System.out.println("AAA");
            flag = 2;
            cond2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        try {
            lock.lock();
            while (flag != 2) {
                cond2.await();
            }
            System.out.println("BB");
            flag = 3;
            cond3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        try {
            lock.lock();
            while (flag != 3) {
                cond3.await();
            }
            System.out.println("C");
            flag = 1;
            cond1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
