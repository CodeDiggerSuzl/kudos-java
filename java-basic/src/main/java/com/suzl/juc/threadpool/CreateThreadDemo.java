package com.suzl.juc.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Executors 工具类
 * 三大方法
 *
 * @author Suz1
 * @date 2020/4/1 1:52 下午
 */
public class CreateThreadDemo {
    public static void main(String[] args) {
        // 工具类 3 中方法
        // ExecutorService singlePool = Executors.newSingleThreadExecutor();// 单个线程
        // ExecutorService fixedPool = Executors.newFixedThreadPool(5); // 固定线程池大小
        // ExecutorService pool = Executors.newCachedThreadPool(); // 可伸缩的

        // ---------------------------------------------------------------------------
        // 原生线程池对应场景

        //   总共 5 个窗口工作,某一天只有两个上班,其余 3 个休息,当 2 个满了,新来的人休息,在等待区等待.
        //   人特多, 3 个窗口开始上班
        //   又来人了,5 个窗口全满,等待区也满了,再来的人要么走,要么等(不在等待区).

        //   两个常开的窗口就是 core 线程数
        //   等待区就是阻塞队列
        //   在等待区也满了的时候,3 窗口打开达到 max 线程数
        //   全满的情况下,再来人就是拒绝策略
        //   超时等待,超过指定时间后,3 个窗口关闭,线程池释放

        int processors = Runtime.getRuntime().availableProcessors();
        // 自定义线程池
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                2, // 核心线程数
                processors, // 最大线程数
                3, // 超时时间
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3), // 阻塞队列
                Executors.defaultThreadFactory(), // 线程工厂
                new ThreadPoolExecutor.AbortPolicy()); // 拒绝策略 不处理,抛出异常
        try {
            for (int i = 1; i <= 10; i++) {
                // 使用线程池来创建线程
                pool.execute(() -> {System.out.println(Thread.currentThread().getName());});
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭线程池
            pool.shutdown();
        }
    }
}

