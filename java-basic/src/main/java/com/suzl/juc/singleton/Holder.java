package com.suzl.juc.singleton;

/**
 * 使用静态内部类实现单例
 *
 * @author Suz1
 * @date 2020/4/1 10:06 下午
 */
public class Holder {

    // private
    private Holder() {}

    public static Holder getInstance() {
        return InnerClass.HOLDER;
    }

    public static class InnerClass {
        // 创建外部类的单例s
        private final static Holder HOLDER = new Holder();
    }
}
