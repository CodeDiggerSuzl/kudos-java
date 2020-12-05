package com.suzl.basic.collection.list;

import org.junit.Test;

import java.util.*;

/**
 * @author Suz1
 * @date 2020/10/12 10:32 上午
 */
public class LinkedHashSetTest {
    @Test
    public void linkedHashListTest() {
        LinkedHashSet<String> t = new LinkedHashSet<>();
        t.add("d");
        HashSet<String> set = new HashSet<>();
        set.add("");
    }

    @Test
    public void listTest() {
        List<Object> list = Collections.synchronizedList(new ArrayList<>(16));
        list.add("hah");

    }
}
