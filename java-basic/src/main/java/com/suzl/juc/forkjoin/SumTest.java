package com.suzl.juc.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * 三种求和方法
 *
 * @author Suz1
 * @date 2020/4/1 4:58 下午
 */
public class SumTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        slowSum();   // 耗时5884,5320,5335,5264
        forkSum();   // 153,159,154,143
        streamSum(); //164,262,171,160
    }

    public static void slowSum() {
        long sum = 0L;
        long start = System.currentTimeMillis();
        for (long i = 0L; i <= 10_0000_0000L; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时" + (end - start) + ",sum: " + sum);
    }

    public static void forkSum() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinDemo(0L, 10_0000_0000L);
        // 提交任务
        ForkJoinTask<Long> submit = forkJoinPool.submit(task);
        Long sum = submit.get();
        long end = System.currentTimeMillis();

        System.out.println("耗时" + (end - start) + ",sum: " + sum);
    }

    public static void streamSum() {
        long start = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(0L, 10_0000_0000L).parallel().reduce(0, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("耗时" + (end - start) + ",sum: " + sum);
    }
}
