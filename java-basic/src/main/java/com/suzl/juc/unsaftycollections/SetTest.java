package com.suzl.juc.unsaftycollections;

import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author Suz1
 * @date 2020/3/31 11:05 下午
 */
public class SetTest {
    public static void main(String[] args) {

        // HashSet<String> set = new HashSet<>();
        // Set<String> set = Collections.synchronizedSet(new HashSet<>());
        CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(set);
            }).start();
        }
    }
}
