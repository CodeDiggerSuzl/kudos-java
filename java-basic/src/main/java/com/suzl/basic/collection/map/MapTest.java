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
        String put = map.put(null, null);
        String aa = map.put(null, "aa");
        String s = map.get(null);
    }

    @Test
    public void xorTest() {
        String s = "i";
        int hashCode = s.hashCode();
        System.out.println("hashCode = " + hashCode);
        int i = hashCode >>> 16;
        System.out.println("i = " + i);
    }

    @Test
    public void initMapTest() {
        HashMap<String, String> map = new HashMap<>(10);
        String put = map.put("1", "2");
        String put1 = map.put("1", "3");
        System.out.println(put1);
    }
}
