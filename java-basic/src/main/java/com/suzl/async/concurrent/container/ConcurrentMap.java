package com.suzl.async.concurrent.container;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Suz1
 * @date 2020/3/17 17:10
 */
public class ConcurrentMap {
    public static void main(String[] args) throws InterruptedException {
        final ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>(2);
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                new Thread(() -> {
                    map.put(UUID.randomUUID().toString(), "");
                    System.out.println(Thread.currentThread().getName());
                });
            }
        }, "ftf");
        thread.start();
        thread.join();
    }
}
