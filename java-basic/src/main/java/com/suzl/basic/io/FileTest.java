package com.suzl.basic.io;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * <p>file test</p>
 *
 * @author suzailong
 * @date 2021/1/2 3:34 下午
 */
public class FileTest {
    static void printFiles(File[] files) {
        System.out.println("==========");
        if (files != null) {
            for (File f : files) {
                System.out.println(f);
            }
        }
        System.out.println("==========");
    }

    @Test
    public void pathTest() {
        Path p1 = Paths.get(".", "project", "study"); // 构造一个Path对象
        System.out.println(p1);
        Path p2 = p1.toAbsolutePath(); // 转换为绝对路径
        System.out.println(p2);
        Path p3 = p2.normalize(); // 转换为规范路径
        System.out.println(p3);
        File f = p3.toFile(); // 转换为File对象
        System.out.println(f);
        for (Path p : Paths.get("..").toAbsolutePath()) { // 可以直接遍历Path
            System.out.println("  " + p);
        }
    }

    @Test
    public void dirTest() {
        File f = new File("/Users/suzailong/dev/java-com.com.suzl.allspring.spring-all");
        File[] fs1 = f.listFiles();
        printFiles(fs1);
        // 仅列出.exe文件
        File[] fs2 = f.listFiles((dir, name) -> {
            return name.endsWith(".java"); // 返回true表示接受该文件
        });
        printFiles(fs2);
    }

    @Test
    public void classPathTest() throws IOException {
        Class<? extends FileTest> aClass = getClass();
        InputStream inputStream = aClass.getResourceAsStream("/application.properties");
        if (inputStream != null) {
            int n;
            while ((n = inputStream.read()) != -1) {
                System.out.println((char) n);
            }
        }


    }
}
