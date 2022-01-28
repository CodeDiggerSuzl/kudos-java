package com.suzl.async;

import com.suzl.SystemException;
import com.suzl.pojo.Coin;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author suzl
 * @date 2022/1/28 10:08 下午
 */
@Slf4j
public class AsyncDemo {


    final Random random = new Random();
    int badLuck = random.nextInt(20);
    //    int badLuck = 99;

    @Test
    public void asyncTest() {
        System.out.println("bad luck :" + badLuck);

        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        long start = System.currentTimeMillis();
        for (Integer i : list) {
            try {
                Coin coin = aLongTimeMethod(i);
                if (coin.getWorth() == null) {
                    System.out.println("coin value == null");
                }
            } catch (SystemException e) {
                System.out.println("system error");
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("end:");
        System.out.println((end - start) / 1000);

    }

    public Coin aLongTimeMethod(Integer id) throws SystemException, InterruptedException {
        //        Thread.sleep(1000);
        if (badLuck == id) {
            throw new SystemException(1, "system happened...");
        }
        log.info("aLongTimeMethod 处理第{}个数 ", id);
        return new Coin("coin_name" + id, 10);
    }

    @Test
    public void asyncTestParallel() throws IOException, InterruptedException {
        String s = asyncMethod();
        log.info("asyncTestParallel returns ---  {}", s);
        Thread.sleep(9000);

    }

    private String asyncMethod() throws InterruptedException {
        System.out.println("bad luck :" + badLuck);
        int processors = Runtime.getRuntime().availableProcessors();
        log.info("可用线程数 == {}", processors);
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13);

        System.out.println(list.size());
        AtomicReference<String> flag = new AtomicReference<>("YES");
        CountDownLatch countDownLatch = new CountDownLatch(list.size());
        for (Integer i : list) {
            CompletableFuture<Coin> future = CompletableFuture.supplyAsync(() -> {
                // log.info("当前的线程名字-----> {}", Thread.currentThread().getName());
                try {
                    return aLongTimeMethod(i);
                } catch (SystemException | InterruptedException e) {
                    log.error("处理第{}个数出现问题", i, e);
                    return null;
                }
            }, Executors.newFixedThreadPool(10));
            future.thenAccept((re) -> {
                if (re != null && re.getWorth() == 1) {
                    flag.set("NO");
                }
                if (re == null) {
                    flag.set("NO");
                }
                countDownLatch.countDown();
            });
            future.exceptionally(e -> {
                log.info("handling {} future.exceptionally err", i, e);
                countDownLatch.countDown();
                flag.set("NO");
                return null;
            });
        }
        countDownLatch.await(2, TimeUnit.SECONDS);
        return flag.get();

    }
}
