package com.suzl.basic.stringx;

import org.junit.Test;

import java.util.StringJoiner;

/**
 * <p>builder and buffer</p>
 *
 * @author suzailong
 * @date 2021/1/2 12:55 上午
 */
public class BuilderJoinerBufferTest {
    @Test
    public void builderTest() {
        StringBuilder sb = new StringBuilder(1024)
                .append("Start")
                .append("Second")
                .insert(9, "--");
        System.out.println(sb.toString());
    }

    @Test
    public void joinerTest() {
        String[] names = {"Bob", "Alice", "Grace"};
        var sj = new StringJoiner(", ", "Hello ", "!"); // Hello Bob, Alice, Grace!
        for (String name : names) {
            sj.add(name);
        }
        System.out.println(sj.toString());
    }
}
