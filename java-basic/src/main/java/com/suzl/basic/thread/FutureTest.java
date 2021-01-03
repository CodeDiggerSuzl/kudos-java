package com.suzl.basic.thread;

import java.util.concurrent.*;

/**
 * @author suzailong
 * @date 2021/1/3 1:12 上午
 */
public class FutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        Callable<String> task = new Task();
        // 提交任务并获得Future:
        Future<String> future = executor.submit(task);
        String s = future.get(5, TimeUnit.SECONDS);
        System.out.println("s = " + s);
    }
}

class Task implements Callable<String> {

    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(10);
        return "get from call()";
    }
}
