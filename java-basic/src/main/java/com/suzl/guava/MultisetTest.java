package com.suzl.guava;

import com.google.common.collect.HashMultiset;
import org.junit.Test;

/**
 * <p>Multiset</p>
 * <p>无序可重复的集合</p>
 *
 * @author suzailong
 * @date 2021/2/2 9:49 下午
 */
public class MultisetTest {
    @Test
    public void multisetTest() {
        // 底层是 Map<E,count>
        HashMultiset<Object> multiset = HashMultiset.create();
        multiset.add("1");
        multiset.add("1");
        multiset.add("3");
        multiset.add("4");
        System.out.println("multiset.count(\"1\") = " + multiset.count("1"));
    }

}
