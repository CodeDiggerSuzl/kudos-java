package faqs.prepare.crossaandb;

/**
 * 使用 volatile 交叉打印奇数和偶数
 * <p>
 * 交叉打印问题其实本质是线程间的通信问题
 * <ul>
 *     <li>
 *           共享内存:使用 volatile
 *     </li>
 * </ul>
 * </p>
 */
public class CrossAbDemo {
    private static volatile int flag = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                if (flag % 2 != 0) {
                    System.out.println(flag);
                    try {
                        Thread.sleep(1000);
                        flag++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "odd-printer").start();
        new Thread(() -> {
            while (true) {
                if (flag % 2 == 0) {
                    System.out.println(flag);
                    flag++;
                }
            }
        }, "even-printer").start();
    }
}
