package com.suzl.basic.array;

import org.junit.Test;

import java.util.Arrays;

/**
 * <p>数组操作测试</p>
 *
 * @author suzailong
 * @date 2021/1/1 11:27 下午
 */
public class ArrayOpsTest {
    @Test
    public void arrTest() {
        int[] arr = {1, 3, 4, 5, 0};
        String s = Arrays.toString(arr);
        System.out.println(s);
        // str join
        String[] strArr = {"A", "B", "C"};
        String result = String.join("@", strArr);
        System.out.println(result);
    }

    @Test
    /** 打印多维数组可以使用Arrays.deepToString()； */
    public void arrTestTest() {
        int[][] ns = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };

        System.out.println(Arrays.deepToString(ns));
    }

    static class TempClass {
        String  name;
        Integer age;
    }
}
