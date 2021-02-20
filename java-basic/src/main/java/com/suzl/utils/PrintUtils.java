package com.suzl.utils;

import com.alibaba.fastjson.JSON;

/**
 * <p>打印 utils</p>
 *
 * @author suzailong
 * @date 2021/2/2 9:21 下午
 */
public class PrintUtils {
    public static void pJF(Object o) {
        System.out.println(JSON.toJSONString(o, true));
    }

    public static void pJ(Object o) {
        System.out.println(JSON.toJSONString(o));
    }
}
