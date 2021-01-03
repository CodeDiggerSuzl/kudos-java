package com.suzl.basic.collection;

import com.google.common.base.Objects;
import org.junit.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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
        // map 遍历
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey());
        }
        map.forEach((k, v) -> {
            System.out.println("k = " + k);
            System.out.println("v = " + v);
        });
    }

    @Test
    public void treeMapTest() {
        TreeMap<Actor, Integer> map = new TreeMap<>(new Comparator<Actor>() {
            @Override
            public int compare(Actor o1, Actor o2) {
                return o1.name.compareTo(o2.name);
            }
        });
        map.put(new Actor("Tom"), 1);
        map.put(new Actor("Bob"), 2);
        map.put(new Actor("Lily"), 3);
    }

    static class Actor {
        public String name;

        public Actor(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Actor{" +
                    "name='" + name + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Actor actor = (Actor) o;
            return Objects.equal(name, actor.name);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(name);
        }
    }
}
