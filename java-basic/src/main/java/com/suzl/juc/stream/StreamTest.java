package com.suzl.juc.stream;

import java.util.Comparator;
import java.util.stream.Stream;

/**
 * 题目要求：一分钟内完成此题，只能用一行代码实现！
 * 现在有5个用户！筛选：
 * 1、ID 必须是偶数
 * 2、年龄必须大于23岁
 * 3、用户名转为大写字母
 * 4、用户名字母倒着排序
 * 5、只输出一个用户！
 *
 * @author com.suzl
 */
public class StreamTest {

    public static void main(String[] args) {
        User u1 = new User(1, "a", 21);
        User u2 = new User(2, "b", 22);
        User u3 = new User(3, "c", 23);
        User u4 = new User(4, "d", 24);
        User u5 = new User(6, "e", 25);

        Stream.of(u1, u2, u3, u4, u5)
                .filter(t -> t.getId() % 2 == 0 && t.getAge() > 23)
                .map(t -> t.getName().toUpperCase())
                .sorted(String::compareTo)
                .limit(2)
                .forEach(System.out::println);
        // min and max
        // comparing 源码
        User user = Stream.of(u1, u2, u3, u4).min(Comparator.comparing(User::getAge)).get();
    }
}
