package com.up.cache.redis;

import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;

public class DelRedisKey {
    @Test
    public void listTest() {
        ArrayList<A> l = new ArrayList<>();
        String s = l.get(0).getA();
        System.out.println(s);
    }

    @Data
    class A {
        String a;
    }
}
