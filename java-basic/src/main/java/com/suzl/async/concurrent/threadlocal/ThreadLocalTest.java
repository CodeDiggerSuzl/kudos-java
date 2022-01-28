package com.suzl.async.concurrent.threadlocal;

import org.junit.Test;

/**
 * Test thread local.
 *
 * @author Suz1
 * @date 2020/8/15 2:56 下午
 */
public class ThreadLocalTest {
    /**
     * 最后，特别注意ThreadLocal一定要在finally中清除：
     * <p>这是因为当前线程执行完相关代码后，很可能会被重新放入线程池中，如果ThreadLocal没有被清除，该线程执行其他代码时，会把上一次的状态带进去。</p>
     */
    @Test
    public void threadLocalTest() {
        ThreadLocal<String> t1 = new ThreadLocal<>();
        Thread thread = Thread.currentThread();
        long id = thread.getId();
        System.out.println("id:" + id);
        t1.set("test1");
        t1.get();
        try {
            t1.get();
        } finally {
            t1.remove();
        }
        System.out.println(t1.get());
    }

    /**
     * 为了保证能释放ThreadLocal关联的实例，我们可以通过AutoCloseable接口配合try (resource) {...}结构，
     * 让编译器自动为我们关闭。例如，一个保存了当前用户名的ThreadLocal可以封装为一个UserContext对象：
     */
    @Test
    public void autoCloseableTest() {
        try (UserContext ctx = new UserContext("test")) {
            String s = UserContext.currentUser();
        }
    }


    static class UserContext implements AutoCloseable {
        final static ThreadLocal<String> CTX = new ThreadLocal<String>();

        public UserContext(String user) {
            CTX.set(user);
        }

        public static String currentUser() {
            return CTX.get();
        }

        @Override
        public void close() {
            CTX.remove();
        }
    }
}
