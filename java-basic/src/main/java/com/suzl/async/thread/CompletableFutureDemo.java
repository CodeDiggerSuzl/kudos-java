package com.suzl.async.thread;

import java.util.concurrent.CompletableFuture;

/**
 * <p>Completable Future</p>
 *
 * @author suzailong
 * @date 2021/1/3 1:27 上午
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws InterruptedException {
        // 创建异步任务
        CompletableFuture<Double> future = CompletableFuture.supplyAsync(CompletableFutureDemo::fetchPrice);
        // 执行成功
        future.thenAccept((result) -> {
            System.out.println(result.toString());
        });
        // 执行失败的话
        future.exceptionally((e) -> {
            e.printStackTrace();
            return null;
        });
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭
        Thread.sleep(500);
    }

    // 耗时操作
    static Double fetchPrice() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {
        }
        if (Math.random() < 0.3) {
            throw new RuntimeException("fetch price failed!");
        }
        return 5 + Math.random() * 20;
    }
}
