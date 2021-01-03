package com.suzl.basic.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p>动态代理</p>
 *
 * @author suzailong
 * @date 2021/1/2 2:30 下午
 */
public class DynamicProxyTest {
    public static void main(String[] args) {
        Greeting instance = (Greeting) Proxy.newProxyInstance(Greeting.class.getClassLoader(), new Class[]{Greeting.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(" ---- ");

                if ("sayMorning".equals(method.getName())) {
                    System.out.println("do something...");
                }
                return "morning" + args[0];
            }
        });
        String liu = instance.sayMorning("Liu");
        System.out.println(liu);
    }
}
