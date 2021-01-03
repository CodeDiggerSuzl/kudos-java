package com.suzl.basic.thread;

import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author suzailong
 * @date 2021/1/3 12:59 上午
 */
public class ThreadPoolTest {
    @Test
    public void schedulePoolTest() {
        ScheduledExecutorService sec = Executors.newScheduledThreadPool(4);


    }

}


