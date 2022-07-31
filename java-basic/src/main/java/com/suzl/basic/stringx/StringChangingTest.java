package com.suzl.basic.stringx;

import org.junit.Test;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;

public class StringChangingTest {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        String s = new String("abc");
        Field value = s.getClass().getDeclaredField("value");
        value.setAccessible(true);
        // value.set(s, "a".toCharArray()); // 1.7 后不 OK 了
        value.set(s, "abcd".getBytes(StandardCharsets.UTF_8));
        System.out.println(s);
    }

    @Test
    public void testEquals() {
        String s1 = new String("123");
        String s2 = "123";

        System.out.println(s1 == s2);

        String s3 = s1.intern();
        System.out.println(s3 == s2);
    }
}
