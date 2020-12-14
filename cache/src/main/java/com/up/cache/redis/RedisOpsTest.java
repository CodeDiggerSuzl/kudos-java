package com.up.cache.redis;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Suz1
 * @date 2020/9/17 12:39 上午
 */
@Component
public class RedisOpsTest {

    final Logger log = LoggerFactory.getLogger(RedisOpsTest.class);

    private JedisPool pool;

    @Before
    public void init() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(10);
        poolConfig.setMaxIdle(5);
        pool = new JedisPool(poolConfig, "localhost", 6379, 3000);
    }

    @Test
    public void setRandDataTest() {
        try (Jedis jedis = pool.getResource()) {
            for (int i = 0; i < 10000; i++) {
                jedis.set("key" + i, String.valueOf(i));
            }
        }
    }

    @Test
    public void hyperLogLogTest() {

        try (Jedis jedis = pool.getResource()) {
            for (int i = 0; i < 100000; i++) {
                if (i % 2 == 0) {jedis.pfadd("hll2", "name" + i);}
                jedis.pfadd("hll", "user" + i);
            }
            long total = jedis.pfcount("hll");
            long total2 = jedis.pfcount("hll2");

            Assert.assertNotEquals(100000, total);
            jedis.pfmerge("hll3", "hll", "hll2");
            final long hll3 = jedis.pfcount("hll3");
            Assert.assertEquals(hll3, total + total2);

        }

    }

    @Test
    public void simpleRateLimiterTest() {
        try (final Jedis jedis = pool.getResource()) {
            SimpleRateLimiter limiter = new SimpleRateLimiter(jedis);
            for (int i = 0; i < 20; i++) {
                System.out.println(limiter.isActionAllowed("userid", "reply", 60, 5));
            }
        }
    }

    @Test
    public void setObjectTest() {
        try (Jedis jedis = pool.getResource()) {
            String pong = jedis.ping();
            Assert.assertEquals("PONG", pong);
            // TODO Try with resource https://cloud.tencent.com/developer/article/1011951
            Person person = new Person("person", 123, 178d);
            String result = jedis.set("person", JSON.toJSONString(person));
            log.info(result);
            String getResult = jedis.get("person");
            log.info(getResult);
            Map<String, String> pMap = new HashMap<>(3);
            pMap.put("name", "person");
            pMap.put("weight", "123");
            pMap.put("height", "178");
            jedis.hmset("personJoe", pMap);
            Map<String, String> hgetAllResult = jedis.hgetAll("personJoe");
            log.info("get result of hgetAll:{}", JSON.toJSONString(hgetAllResult));

        }

    }

    @Data
    @AllArgsConstructor
    static class Person {
        private String  name;
        private Integer wight;
        private Double  height;
    }
}

