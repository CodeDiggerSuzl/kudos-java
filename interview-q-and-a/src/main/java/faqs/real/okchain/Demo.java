package faqs.real.okchain;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 欧科云联的面试题, 让判断出会打印出什么
 */
public class Demo {
    static int clientTotal = 4;
    static int threadTotal = 2;

    static StringBuilder stringBuilder = new StringBuilder();

    static void update() {
        stringBuilder.append("1");
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update();
                    semaphore.release();
                } catch (InterruptedException e) {
                    System.out.println(e + "");
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();


        System.out.println("===" + stringBuilder.toString());
    }
}
