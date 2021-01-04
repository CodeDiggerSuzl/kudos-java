package com.up.cache.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author Suz1
 * @date 2020/9/16 7:05 下午
 */
@Configuration
public class RedisConfig {
    @Value("${com.suzl.allspring.spring.redis.host}")
    private String host;

    @Value("${com.suzl.allspring.spring.redis.port}")
    private int port;

    @Value("${com.suzl.allspring.spring.redis.timeout}")
    private int timeout;

    @Value("${com.suzl.allspring.spring.redis.jedis.pool.max-idle}")
    private int maxIdle;

    @Value("${com.suzl.allspring.spring.redis.jedis.pool.max-wait}")
    private long maxWaitMillis;

    @Value("${com.suzl.allspring.spring.redis.password}")
    private String password;

    @Value("${com.suzl.allspring.spring.redis.database}")
    private int database;

    @Bean
    public JedisPool jedisPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        return new JedisPool(jedisPoolConfig, host, port, timeout, password, database);
    }

}
