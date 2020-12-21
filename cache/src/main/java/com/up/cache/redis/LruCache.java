package com.up.cache.redis;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Suz1
 * @date 2020/9/22 12:29 下午
 */
public class LruCache extends LinkedHashMap<String, String> {
    private final int CACHE_SIZE;


    /**
     * 这里就是传递进来最多能缓存多少数据
     */
    public LruCache(int cacheSize) {
        // // 这块就是设置一个hashmap的初始大小，同时最后一个true指的是让 LinkedHashMap 按照访问顺序来进行排序，最近访问的放在头，最老访问的就在尾
        super((int) Math.ceil(cacheSize / 0.75) + 1, 0.75f, true);
        this.CACHE_SIZE = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        // 这个意思就是说当map中的数据量大于指定的缓存个数的时候，就自动删除最老的数据
        return size() > CACHE_SIZE;
    }
}
