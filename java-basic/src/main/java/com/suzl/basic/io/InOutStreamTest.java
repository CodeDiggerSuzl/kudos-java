package com.suzl.basic.io;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * <p>In and Out Stream test</p>
 *
 * @author suzailong
 * @date 2021/1/2 3:49 下午
 */
public class InOutStreamTest {
    // 同步 IO
    @Test
    public void bufferReadTest() throws IOException {
        StringBuilder s = new StringBuilder();
        try (InputStream ins = new FileInputStream("/Users/suzailong/dev/WayToBeAwesome/计算机基础/编程语言/java/reflect.md")) {
            int n;
            StringBuilder sb = new StringBuilder();
            while ((n = ins.read()) != -1) {
                s.append((char) n);
            }
        }
        String s1 = new String(s);
        System.out.println(s1);
    }
}
