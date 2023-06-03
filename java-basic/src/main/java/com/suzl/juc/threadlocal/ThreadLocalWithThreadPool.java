package com.suzl.juc.threadlocal;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadLocalWithThreadPool implements Callable<Boolean> {

    private static final int N_CPU = 4;


    private static ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> {
        System.out.println("thread-" + Thread.currentThread().getId() + " init thread local");
        return "";
    });

    public static void main(String[] args) throws Exception {
        System.out.println("cpu core size : " + N_CPU);
        List<Callable<Boolean>> tasks = new ArrayList<>(N_CPU * 2);
        ThreadLocalWithThreadPool tl = new ThreadLocalWithThreadPool();
        for (int i = 0; i < N_CPU * 2; i++) {
            tasks.add(tl);
        }
        ExecutorService es = Executors.newFixedThreadPool(2);
        List<Future<Boolean>> futures = es.invokeAll(tasks);
        for (final Future<Boolean> future : futures) {
            future.get();
        }
        es.shutdown();
    }

    @Override
    public Boolean call() {
        String li = threadLocal.get();
        if (StringUtils.isNotEmpty(li)) {
            System.out.println(Thread.currentThread().getId() + "_get_" + li);
        } else {
            li = Thread.currentThread().getId() + "_" + RandomUtils.nextInt(0, 10);
            System.out.println(Thread.currentThread().getId() + "_set_" + li);
            threadLocal.set(li);
        }
        return true;
    }
}

