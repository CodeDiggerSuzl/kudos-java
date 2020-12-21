package com.suzl.basic.collection.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * list array test
 *
 * @author suzailong
 */
public class ListArrayTest {
    /**
     * 【强制】使用集合转数组的方法，必须使用集合的 toArray(T[] array)，传入一样的数组，大小就是 list.size()。
     * <p>
     * 说明：使用 toArray 带参方法，入参分配的数组空间不够大时，toArray 方法内部将重新分配 内存空间，并返回新数组地址；
     * <p></p>如果数组元素个数大于实际所需，下标为[ list.size() ] 的数组元素将被置为 null，其它数组元素保持原值，因此最好将方法入参数组大小定义与集合元素个数一致。
     */
    @Test
    public void list2ArrayTest() {
        List<String> list = new ArrayList<String>(2) {
            {
                add("1");
                add("2");
            }
        };
        String[] array = new String[list.size()];
        array = list.toArray(array);
        System.out.println("array = " + Arrays.toString(array));
    }

    /**
     * 【强制】使用工具类 Arrays.asList()把数组转换成集合时，不能使用其修改集合相关的方法，它的 add/remove/clear 方法会抛出 UnsupportedOperationException 异常。
     * <p>
     * 说明：asList 的返回对象是一个 Arrays 内部类，并没有实现集合的修改方法。Arrays.asList 体现的是适配器模式，只是转换接口，后台的数据仍是数组。
     * <p>
     * String[] str = new String[] { "you", "wu" };
     * <p>
     * List list = Arrays.asList(str);
     * <p>
     * 第一种情况：list.add("yangguanbao"); 运行时异常。
     * 第二种情况：str[0] = "gujin"; 那么 list.get(0)也会随之修改。
     */
    @Test
    public void array2ListTest() {
        String[] str = new String[]{"you", "wu"};

        List list = Arrays.asList(str);
        boolean a = list.add("a");

    }
}
