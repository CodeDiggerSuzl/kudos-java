package com.suzl.juc.function;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Function 函数接口
 *
 * @author Suz1
 * @date 2020/4/1 3:51 下午
 */
public class FunctionalDemo {
    public static void main(String[] args) {
        Function<String, String> f = String::toUpperCase;
        System.out.println(f.apply("ahh"));

        Predicate<String> p = String::isEmpty;
        System.out.println(p.test(""));

        Consumer<String> c = System.out::println;
        c.accept("ah");
        Supplier<Integer> s = () -> {return 10;};
        System.out.println(s.get());
    }
}
