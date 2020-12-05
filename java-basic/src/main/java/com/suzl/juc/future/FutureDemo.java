package com.suzl.juc.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 异步调用
 * - 异步执行
 * - 成功回调
 * - 失败回调
 *
 * @author Suz1
 * @date 2020/4/1 7:13 下午
 */
public class FutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Void void 的 placeHolder
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "async");
        });

        System.out.println("main");
        Void aVoid = future.get();// 获取阻塞的执行结果
        System.out.println(aVoid);


        /* 有返回值 */
        // 供给性参数 有返回值的异步回调
        // 有两种结果,要么成功,要么失败
        CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> {
            int i = 10 / 0;
            return "success";
        });
        // BiConsumer
        supplyAsync.whenComplete((t, u) -> {
            System.out.println("t : " + t); // t 正常的返回结果
            System.out.println("u : " + u); // u 错误信息
        }).exceptionally((e) -> {
            System.out.println(e.getMessage());
            return "fail";
        }).get();
    }
}
