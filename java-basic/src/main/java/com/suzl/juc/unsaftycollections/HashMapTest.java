package com.suzl.juc.unsaftycollections;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Suz1
 * @date 2020/3/31 11:17 下午
 */
public class HashMapTest {
    public static void main(String[] args) {
        // map 的使用
        // 解决方法
        Map<String, String> map = Collections.synchronizedMap(new HashMap<>());
        // Map<String, String> map = new HashMap<>();
        ConcurrentHashMap<String, String> map1 = new ConcurrentHashMap<>();
        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                String s = UUID.randomUUID().toString().substring(0, 5);
                map.put(Thread.currentThread().getName(), s);
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
