package com.suzl.juc.singleton;

/**
 * 饿汉式单例
 *
 * @author Suz1
 * @date 2020/4/1 9:35 下午
 */
public class HungrySingleton {
    // 恶汉式,一上来就进行加载了
    // 存在问题,一上来就加载了全部变量
    private final static HungrySingleton HUNGRY_SINGLETON = new HungrySingleton();
    // 上来就会加载, 浪费内存
    private byte[] b1 = new byte[1024];
    private byte[] b2 = new byte[1024];
    private byte[] b3 = new byte[1024];
    private byte[] b4 = new byte[1024];

    private HungrySingleton() {}

    public static HungrySingleton getInstance() {
        return HUNGRY_SINGLETON;
    }
}
