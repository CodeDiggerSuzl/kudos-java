package com.codedigger.demo.collection.map;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author Suz1
 * @date 2020/10/22 10:17 上午
 */
public class MapTest {
    @Test
    public void hashMapTest() {
        HashMap<String, String> map = new HashMap<>();
        map.put("hah", "1");
        map.put(null, null);
        map.put(null, "aa");
        map.get(null);

    }
}
