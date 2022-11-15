package com.suzl.juc.stream;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;

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

//    public static void main(String[] args) {
//        Date date = new Date();
//        User u1 = new User(1, "1", null, date);
//        User u2 = new User(2, "2", null, DateUtils.addDays(date, 1));
//        User u3 = new User(null, "3.3", 23, DateUtils.addDays(date, 2));
//        //        User u4 = new User(4, "d", 24);
//        //        User u5 = new User(6, "e", 25);
//
//        //        Stream.of(u1, u2, u3,)
//        //                .filter(t -> t.getId() % 2 == 0 && t.getAge() > 23)
//        //                .map(t -> t.getName().toUpperCase())
//        //                .sorted(String::compareTo)
//        //                .limit(2)
//        //                .forEach(System.out::println);
//        //        // min and max
//        //        // comparing 源码
//        ////        User user = Stream.of(u1, u2, u3).min(Comparator.comparing(User::getAge)).get();
//        ArrayList<User> users = Lists.newArrayList(u1, u3, u2);
//        //        Comparator<User> comparator = Comparator.comparing(User::getId).thenComparing(User::getAge);
//        //       User user = users.stream().min(comparator).orElse(null);
//        //        int i = users.stream().mapToInt(t -> t.getAge() == null ? 0 : t.getAge()).max().orElse(0);
//        //        System.out.println(i);
//        //        String s = JsonUtils.toJson(users);
//        //        System.out.println(s);
//        //
//        users.sort(Comparator.comparing(User::getId, Comparator.nullsFirst(Comparator.naturalOrder())));/*.reversed()*/
//
//        final BigDecimal total = users.stream().map(User::getName).map(BigDecimal::new).reduce(BigDecimal.ZERO, BigDecimal::add);
//        System.out.println(total.toPlainString());
//
//        String s2 = JsonUtils.toJson(users);
//        System.out.println(s2);
//    }

    public static void main(String[] args) {
        Coupon c1 = new Coupon("20221221235959", "10.88");
        Coupon c2 = new Coupon("20221121235959", "8.80");
        ArrayList<Coupon> list = Lists.newArrayList(c2, c1);
        list.sort(
                Comparator.comparing(Coupon::getExpireTime, Comparator.nullsLast(Comparator.naturalOrder()))
                        .thenComparing(Coupon::getMoney, (o1, o2) -> {
                            if (StringUtils.isAnyBlank(o1, o2)) {
                                return 0;
                            }
                            BigDecimal b1 = new BigDecimal(o1);
                            BigDecimal b2 = new BigDecimal(o2);
                            int i = b2.compareTo(b1);
                            return i;
                        }));
        System.out.println(list);
    }
}
