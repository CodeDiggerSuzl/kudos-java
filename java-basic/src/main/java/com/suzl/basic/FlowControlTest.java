package com.suzl.basic;

import org.junit.Test;

/**
 * <p>流程控制语句的测试</p>
 *
 * @author suzailong
 * @date 2020/12/16 9:16 下午
 */
public class FlowControlTest {
    /**
     * 【强制】不要在 finally 块中使用 return。
     * <p>
     * 说明：finally 块中的 return 返回后方法结束执行，不会再执行 try 块中的 return 语句。
     */
    @Test
    public void tryTest() {
        try {

        } catch (Exception e) {

        } finally {

        }
    }

    /**
     * 没有被抛出的异常称为“被屏蔽”的异常（Suppressed Exception）。
     * <p>
     * 在极少数的情况下，我们需要获知所有的异常。如何保存所有的异常信息？方法是先用origin变量保存原始异常，然后调用Throwable.addSuppressed()，把原始异常添加进来，最后在finally抛出
     * <a href="https://www.liaoxuefeng.com/wiki/1252599548343744/1264738764506656"></a>
     */
    @Test
    public void suppressedExceptionTest() throws Exception {
        Exception origin = null;
        try {
            System.out.println(Integer.parseInt("abc"));
        } catch (Exception e) {
            origin = e;
            throw e;
        } finally {
            Exception e = new IllegalArgumentException();
            if (origin != null) {
                e.addSuppressed(origin);
            }
            throw e;
        }
    }
}
