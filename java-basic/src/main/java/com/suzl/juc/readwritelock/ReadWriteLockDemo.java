package com.suzl.juc.readwritelock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Suz1
 * @date 2020/4/1 12:29 下午
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyLockCache cache = new MyLockCache();
        for (int i = 1; i <= 5; i++) {
            final int t = i;
            new Thread(() -> {cache.put(t + "", t + "");}, String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++) {
            final int t = i;
            new Thread(() -> {cache.get(t + "");}, String.valueOf(i)).start();
        }
    }
}

class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();

    // 写
    public void put(String k, Object v) {
        String name = Thread.currentThread().getName();
        System.out.println(name + " 写入 " + k);
        map.put(k, v);
        System.out.println(name + " 写入 OK");
    }

    // 读
    public void get(String k) {
        String name = Thread.currentThread().getName();
        System.out.println(name + " 读取 " + k);
        map.get(k);
        System.out.println(name + " 读取OK");
    }
}

class MyLockCache {
    private volatile Map<String, Object> map = new HashMap<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    // 写
    public void put(String k, Object v) {
        lock.writeLock().lock();
        String name = Thread.currentThread().getName();
        System.out.println(name + " 写入 " + k);
        map.put(k, v);
        System.out.println(name + " 写入 OK");
        lock.writeLock().unlock();
    }

    // 读
    public void get(String k) {
        lock.readLock().lock();
        String name = Thread.currentThread().getName();
        System.out.println(name + " 读取 " + k);
        map.get(k);
        System.out.println(name + " 读取OK");
        lock.readLock().unlock();
    }
}
