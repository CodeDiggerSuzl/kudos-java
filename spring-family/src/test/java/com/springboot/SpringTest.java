package com.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;
import springboot.SpringAllApp;
import springboot.util.IdWorker;

import java.io.IOException;

/**
 * <p>Spring test</p>
 *
 * @author suzailong
 * @date 2020/12/29 2:09 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringAllApp.class)
public class SpringTest {
    @Autowired
    private IdWorker idWorker;

    @Autowired
    private ThreadPoolTaskExecutor poolExecutor;

    @Test
    public void beanTest() throws IOException {
        long l = idWorker.nextId();
        System.out.println(l);
        poolExecutor.submit(() -> {
            System.out.println(poolExecutor.getThreadNamePrefix());
        });
        System.in.read();
    }
}
