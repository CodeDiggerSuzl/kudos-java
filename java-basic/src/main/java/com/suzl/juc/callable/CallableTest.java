package com.suzl.juc.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Suz1
 * @date 2020/4/1 12:29 上午
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread thread = new MyThread();
        // 启动 Callable
        // Thread 构造器中只能传入 Runnable 接口
        // FutureTask 是 Runnable 的实现类
        // FutureTask 可以传入 Callable 接口
        // 这就是怎么将 Callable 传入到 Thread 中进行启动的过程
        FutureTask<String> futureTask = new FutureTask<>(thread);
        // 两个 Thread 仅仅打了输入了一次,说明有缓存,结果会进行缓存
        new Thread(futureTask, "A").start();
        new Thread(futureTask, "B").start();
        // get 方法可能会产生阻塞,因为 call 方法中可能会有耗时的操作
        // 解决方法:
        // 1. 将 get 放到代码的最下面
        // 2. 使用异步通信
        String s = futureTask.get();
        System.out.println(s);
    }
}

class MyThread implements Callable<String> {
    @Override
    public String call() {
        System.out.println("call...");
        return "s";
    }
}
