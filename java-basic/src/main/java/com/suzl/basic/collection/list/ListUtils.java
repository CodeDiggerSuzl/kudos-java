package com.suzl.basic.collection.list;

import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.List;

/**
 * @author Suz1
 * @date 2020/3/27 17:29
 */
public class ListUtils<T> {
    public static <T> List<T> removeDuplicate(List<T> list) {
        HashSet<T> h = Sets.newHashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }
}
