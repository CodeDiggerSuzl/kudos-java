package com.suzl.basic.collection.entity;

import lombok.Data;

import java.util.Date;

/**
 * <p></p>
 *
 * @author suzailong
 * @date 2021/1/6 10:36 下午
 */
@Data
public class Book {
    private Long id;
    private String name;
    private String city;

    private Date createTime;


    public static void test() {
        System.out.println("1");
        try {
            System.out.println("true = " + true);
            return;
        } catch (Exception e) {
            System.out.println("e = " + e);
        } finally {
            System.out.println("finally = " + true);
        }
    }

    public static void main(String[] args) {
        test();
        Thread thread = Thread.currentThread();
        long id1 = thread.getId();
        String name1 = thread.getName();
        System.out.println("name1 = " + name1);
        System.out.println("id1 = " + id1);
    }
}
