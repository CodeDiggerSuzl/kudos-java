package com.suzl.juc.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * @author Suz1
 * @date 2020/4/1 4:38 下午
 */
public class ForkJoinDemo extends RecursiveTask<Long> {
    private Long start;
    private Long end;
    private Long tmp = 10000L;

    public ForkJoinDemo(Long start, Long end) {
        this.start = start;
        this.end = end;
    }


    @Override
    protected Long compute() {
        if ((end - start) < tmp) {
            long sum = 0L;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            long mid = (start + end) / 2;
            ForkJoinDemo t1 = new ForkJoinDemo(start, mid);
            t1.fork(); // 拆分任务 把任务压入线程队列
            ForkJoinDemo t2 = new ForkJoinDemo(mid + 1, end);
            t2.fork();
            return t1.join() + t2.join();
        }
    }
}
