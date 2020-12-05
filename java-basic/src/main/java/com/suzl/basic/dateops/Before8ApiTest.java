package com.suzl.basic.dateops;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Suz1
 * @date 2020/8/15 12:04 下午
 */
public class Before8ApiTest {
    @Test
    public void dateTest() {
        LocalDate now = LocalDate.now();
        LocalDateTime now1 = LocalDateTime.now();
        System.out.println("now1 = " + now1);
        Date date = new Date();
        System.out.println(now);
        System.out.println(date);
    }
}
