package com.suzl.juc.unsaftycollections;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Suz1
 * @date 2020/3/31 10:20 下午
 */
public class ListTest {
    public static void main(String[] args) {
        /*
         * 并发修改异常,并发下ArrayList 不安全
         * 解决方案:
         * 1.Vector 不行,效率低
         * 2.使用 Collections.synchronizedList()
         * 3.JUC 中的并发容器  new CopyOnWriteArrayList<>();
         */
        // List<String> list = new Vector<>();
        // List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
