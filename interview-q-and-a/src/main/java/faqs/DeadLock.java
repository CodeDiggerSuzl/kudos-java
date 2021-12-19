package faqs;

/**
 * https://juejin.cn/post/6844903577660424206
 * 死锁
 *
 * @author suzl
 * @date 2021/12/9 1:38 AM
 */
public class DeadLock {
    public static void main(String[] args) throws InterruptedException {
        Object a = new Object();
        Object b = new Object();

        createThread(a, b).start();
        createThread(b, a).start();

    }

    private static Thread createThread(Object a, Object b) {

        return new Thread(() -> {
            synchronized (a) {
                try {
                    Thread.sleep(1000L);
                    synchronized (b) {

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}