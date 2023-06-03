package com.suzl.utils;

import org.apache.commons.compress.utils.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class HashIdDemo {
//    public static void main(String[] args) {
//        String hash = hashids.encode(1L);
//
//        System.out.println(hash);
//        // hashids 这个库的原理是将数字转换成字符串，然后将字符串转换成字母，最后将字母转换成数字
//    }
//
//    /* 生成这个 main 方法的单元测试 */

    public static void main(String[] args) {
//        Hashids hashids = new Hashids("this is my salt");
//
//        String s = "https://etc-extension.gdzskj.tech/index.html#/apply/baidu2-land?promoterId=1074994257445117952&partnerId=1074994257466089472&promotionChannelId=1074994897286193152\n";
//        int x = s.hashCode();
//        x = Math.abs(x);
//        System.out.println("x = " + x);
//        System.out.println(hashids.encode(x));
//
        // a list fot string from G_1 to  G_13


//        List<String> list = Lists.newArrayList();
//        for (int i = 1; i <= 13; i++) {
//            list.add("G_" + i);
//        }
//
//        list.sort(String::compareTo);
//        System.out.println("list = " + list);


//        String s = "G_1";
//        System.out.println("s.split(\"_\") = " + Arrays.toString(s.split("_")));

        Integer integer = 1;
        int i = 1;
        System.out.println("integer.equals(i) = " + true);
    }

    public static void givenList_shouldReturnARandomElement() {
        List<Integer> givenList = Arrays.asList(1, 2, 3, 32, 3, 4, 42, 3);
        for (int k = 0; k < 100; k++) {
            int i = ThreadLocalRandom.current().nextInt(givenList.size());
            System.out.println("i = " + i);
            int randomElementIndex = i % givenList.size();
            System.out.println("randomElementIndex = " + randomElementIndex);
            System.out.println("---");
        }
    }

}
