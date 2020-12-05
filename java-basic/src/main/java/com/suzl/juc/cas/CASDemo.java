package com.suzl.juc.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Suz1
 * @date 2020/4/1 11:39 下午
 */
public class CASDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(25);
        // expect update
        // 如果期望的值达到了就更新,否则不更新

        atomicInteger.compareAndSet(25, 26);
        System.out.println(atomicInteger.get());

        // ABA 问题测试
        // ------------- 捣乱线程线程 -------------
        atomicInteger.compareAndSet(26, 25);
        System.out.println(atomicInteger.get());
        // ------------- 期待线程 ------------
        atomicInteger.compareAndSet(25, 26);
        System.out.println(atomicInteger.get());

    }
}
