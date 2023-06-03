package com.suzl.guava;

import com.google.common.collect.*;
import com.suzl.utils.PrintUtils;
import org.junit.Test;

import java.util.Collections;
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

    @Test
    public void hashBasedTableTest() {
        Table<String, String, Integer> table = HashBasedTable.create();
        //存放元素
        table.put("Hydra", "Jan", 20);
        table.put("Hydra", "Feb", 28);

        table.put("Trunks", "Jan", 28);
        table.put("Trunks", "Feb", 16);

        //取出元素
        Integer dayCount = table.get("Hydra", "Feb");
        System.out.println("dayCount = " + dayCount);
    }

    @Test
    public void BiMapTest() {
        HashBiMap<Object, Object> bm = HashBiMap.create();
            List<Integer> coll = Lists.newArrayList();
            Integer min = Collections.min(coll);
            System.out.println(min);


    }
}
