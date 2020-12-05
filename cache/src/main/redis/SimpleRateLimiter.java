package com.codedigger.up.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

/**
 * 使用 redis 做简单限流 滑动窗口
 *
 * @author Suz1
 * @date 2020/9/17 4:28 下午
 */
public class SimpleRateLimiter {
    private final Jedis jedis;

    public SimpleRateLimiter(Jedis jedis) {
        this.jedis = jedis;
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        SimpleRateLimiter limiter = new SimpleRateLimiter(jedis);
        for (int i = 0; i < 20; i++) {
            System.out.println(limiter.isActionAllowed("userid", "reply", 60, 5));
        }
    }

    /**
     * <b>LGTM</b>
     * <p>
     * 每一个行为到来时，都维护一次时间窗口。将时间窗口外的记录全部清理掉，只保留窗口内的记录。
     * <p>
     * zset 集合中只有 score 值非常重要，value 值没有特别的意义，只需要保证它是唯一的就可以了。
     * <p></p>
     * <p>指定用户 user_id 的某个行为 action_key 在特定的时间内 period 只允许发生一定的次数 max_count</p>
     *
     * @param userId    userId for key
     * @param actionKey also for key
     * @param period    某个时间端 单位秒
     * @param maxCount  最大允许的行为
     * @return 行为是否允许
     */
    public boolean isActionAllowed(String userId, String actionKey, int period, int maxCount) {
        String key = String.format("hist:%s:%s", userId, actionKey);
        // 时间戳 保证唯一
        long nowTs = System.currentTimeMillis();
        Pipeline pipe = jedis.pipelined();
        pipe.multi();
        // 记录行为
        pipe.zadd(key, nowTs, "" + nowTs);
        // nowTs 60 * 100 毫秒
        // 移除时间窗口之前的行为记录，剩下的都是时间窗口内的
        pipe.zremrangeByScore(key, 0, nowTs - period * 1000);
        // 获取窗口内的行为数量
        Response<Long> count = pipe.zcard(key);
        // 设置 zset 过期时间，避免冷用户持续占用内存
        // 为节省内存，我们只需要保留时间窗口内的行为记录，同时如果用户是冷用户，滑动时间窗口内的行为是空记录，那么这个 zset 就可以从内存中移除，不再占用空间。
        // 过期时间应该等于时间窗口的长度，再多宽限 1s
        pipe.expire(key, period + 1);
        pipe.exec();
        pipe.close();
        // 比较数量是否超标
        return count.get() <= maxCount;
    }
}
