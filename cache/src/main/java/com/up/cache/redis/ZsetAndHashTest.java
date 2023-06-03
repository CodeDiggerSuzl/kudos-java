package com.up.cache.redis;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Tuple;

import java.util.Date;
import java.util.Set;

public class ZsetAndHashTest {

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
    public void zsetTest() {
        Jedis client = pool.getResource();
        Date curr = new Date();
        long currTs = curr.getTime();

        String key = "zsetKey";
        for (int i = 0; i < 10; i++) {
            Date date = DateUtils.addDays(curr, -1 * i);
            long secondTs = date.getTime();
            client.zadd(key, secondTs, -i + "");
            System.out.println(date);
        }

        Date date = DateUtils.addDays(curr, -7);
        long startTs = date.getTime();
        Set<Tuple> keySet = client.zrangeByScoreWithScores(key, startTs, currTs);
        Set<String> strings = client.zrangeByScore(key, startTs, currTs);
        System.out.println(strings);
        for (Tuple tuple : keySet) {
            double score = tuple.getScore();
            System.out.println(score);
        }
        System.out.println(JSON.toJSONString(keySet, true));

    }

    public static void main(String[] args) {
        long l = System.currentTimeMillis() / 1000;
        int l1 = (int) l;
        System.out.println(l);
        System.out.println(l1);
    }

    long test(double a) {
        double b = 1L;
        return (long) a;
    }
}
