package com.suzl.juc.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * 懒汉式单例,需要的时候,在进行加载
 *
 * @author Suz1
 * @date 2020/4/1 9:41 下午
 */
public class LazySingleton {
    // 标志位,防止使用反射实例被两个 instance
    private static boolean random = false;
    // 放在这里,并没有进行使用 lazy
    private volatile static LazySingleton lazySingleton;

    // 私有构造器
    private LazySingleton() {
        // 防止被反射破坏第1步,使用三重校验
        synchronized (LazySingleton.class) {
            if (!random) {
                random = true;
            } else {
                if (lazySingleton != null) {
                    throw new RuntimeException("不要通过反射破坏");
                }
            }
        }
        System.out.println(Thread.currentThread().getName() + " ok");
    }

    // 这样写是有问题的,single 线程确实没有问题
    public static LazySingleton getInstance() {
        if (lazySingleton == null) {
            lazySingleton = new LazySingleton();
        }
        return lazySingleton;
    }

    //  有锁版本 多线程下 OK DCL 懒汉式
    public static LazySingleton getInstanceWithLock() {
        if (lazySingleton == null) {
            //  双重校验
            synchronized (LazySingleton.class) {
                if (lazySingleton == null) {
                    /*
                      new LazySingleton 不是原子操作
                      执行步骤
                      1. 分配内存空间
                      2. 执行构造方法
                      3. 将对对象指向这个空间
                      可能发生指令重排序, 多个线程时可能发生问题 所以必须加上 volatile
                     */
                    lazySingleton = new LazySingleton();
                }
            }
        }
        return lazySingleton;
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        // with out lock is not ok
        // 多线程下测试是否单例
        // for (int i = 0; i < 10; i++) new Thread(LazySingleton::getInstance).start();
        // for (int i = 0; i < 10; i++) new Thread(LazySingleton::getInstanceWithLock).start();


        // 使用反射
        // LazySingleton constructorInstance = LazySingleton.getInstance();
        Constructor<LazySingleton> declaredConstructor = LazySingleton.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        LazySingleton reflectInstance1 = declaredConstructor.newInstance();


        // 1. 使用反射 测试单例模式是否被破坏
        // 还是被破坏了
        // 解决办法 -> 在构造器中加锁,抛出异常 3 重校验
        // 2. 还是不安全 ,加入第一个不是通过 getInstance 方法获取到的,都是通过反射获取也不安
        //      解决方法,加入标志位 进行检测 但是也不是安全了,可以通过反编译获取标志位的值,在通过反射修改
        //      还是有问题, 查看 newInstance 源码
        // System.out.println(constructorInstance);
        Field random = LazySingleton.class.getDeclaredField("random");
        random.setAccessible(true);
        random.set(reflectInstance1, false);
        LazySingleton reflectInstance2 = declaredConstructor.newInstance();

        System.out.println(reflectInstance1);
        System.out.println(reflectInstance2);
    }
}
