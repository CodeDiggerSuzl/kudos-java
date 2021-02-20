package com.suzl.guava;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.suzl.utils.PrintUtils;
import org.junit.Test;

import java.util.List;

/**
 * <p></p>
 *
 * @author suzailong
 * @date 2021/2/2 9:57 下午
 */
public class MultiMapTest {
    @Test
    public void mulMapTest() {
        ArrayListMultimap<String, Integer> multiMap = ArrayListMultimap.create();
        multiMap.put("1", 12);
        multiMap.put("1", 123);
        List<Integer> list = multiMap.get("1");
        PrintUtils.pJ(list);

        // HashMultiMap
        HashMultimap<Object, Object> hashMultimap = HashMultimap.create();
        hashMultimap.put("1", 12);
        hashMultimap.put("1", "1");
        PrintUtils.pJ(hashMultimap.get("1"));
    }
}
