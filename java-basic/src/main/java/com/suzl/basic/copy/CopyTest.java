package com.suzl.basic.copy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>深拷贝浅拷贝测试</p>
 *
 * @author suzailong
 * @date 2021/6/15 11:15 下午
 */
public class CopyTest {
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        Runnable runnable = () -> Collections.sort(list1);

        ExecutorService pool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            pool.execute(runnable);
        }
        pool.shutdown();
    }
}
