package com.kudos;

import com.kudos.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p></p>
 *
 * @author suzailong
 * @date 2020/12/29 10:52 上午
 */
@SpringBootApplication
public class SpringAllApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringAllApp.class, args);
    }

    @Bean
    public IdWorker inject() {
        return new IdWorker();
    }

    @Bean
    public ThreadPoolTaskExecutor getThreadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(8);
        executor.setQueueCapacity(100);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("Pool-A");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;

    }
}
