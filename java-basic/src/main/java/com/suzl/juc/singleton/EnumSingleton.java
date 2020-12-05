package com.suzl.juc.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Suz1
 * @date 2020/4/1 10:52 下午
 */
public enum EnumSingleton {
    /**
     * holder
     */
    INSTANCE;

    public EnumSingleton getInstance() {
        return INSTANCE;
    }
}

class Test {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        EnumSingleton instance = EnumSingleton.INSTANCE;
        // 尝试使用反射
        // 报错 Exception in thread "main" java.lang.NoSuchMethodException: juc.singleton.EnumSingleton.<init>()
        // Constructor<EnumSingleton> constructor = EnumSingleton.class.getDeclaredConstructor();
        // EnumSingleton reflectInstance = constructor.newInstance();

        // 使用 javap 反编译发现不行,有一个空参构造
        // 使用 jad 反编译 发现有一个参构造,String int
        // 尝试使用有参构造
        Constructor<EnumSingleton> constructor1 = EnumSingleton.class.getDeclaredConstructor(String.class, int.class);
        constructor1.setAccessible(true);
        // 报错 java.lang.IllegalArgumentException: Cannot reflectively create enum objects
        EnumSingleton reflectInstance = constructor1.newInstance();
        System.out.println(instance == reflectInstance);
    }
}
