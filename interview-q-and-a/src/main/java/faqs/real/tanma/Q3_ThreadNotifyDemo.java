package faqs.real.tanma;

/**
 * 三个线程交替打印 ABC ABC 循环 10 次
 * <p>
 * 类似于生产者消费者的问题.
 *
 * </p>
 * 一般是使用 wait notify & notifyAll 或者 Condition 来实现.
 */
public class Q3_ThreadNotifyDemo {

    public static void main(String[] args) {
        Data data = new Data();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.printA();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.printB();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.printC();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}

class Data {
    private final Object o = new Object();
    private volatile int flag = 1;

    public void printA() throws InterruptedException {
        synchronized (o) {
            while (flag != 1) {
                o.wait();
            }
            System.out.println("A");
            flag = 2;
            o.notifyAll();
        }
    }

    public void printB() throws InterruptedException {
        synchronized (o) {
            while (flag != 2) {
                o.wait();
            }
            System.out.println("B");
            flag = 3;
            o.notifyAll(); // ! 另外两个线程都在等待锁, 所以必须用 notifyAll()
        }
    }

    public void printC() throws InterruptedException {
        synchronized (o) {
            while (flag != 3) {
                o.wait();
            }
            System.out.println("C");
            flag = 1;
            o.notifyAll();
        }
    }
}