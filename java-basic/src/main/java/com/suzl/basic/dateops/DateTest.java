package com.suzl.basic.dateops;

import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>Java 中 date 的 test类</p>
 *
 * @author SuZaiLong
 * @date 2020/11/12 9:33 下午
 */
public class DateTest {
    @Test
    public void currentTimeTest() throws ParseException {
        Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-03-30 23:59:59");
        // Not recommend
        long l = endDate.getTime() - new Date().getTime();
        long l1 = endDate.getTime() - System.currentTimeMillis();
        Assert.assertEquals(l, l1);
    }

    @Test
    public void translateDateTest() {
        Date date = new Date();
        String str = "08:09";
        Date date1 = this.translateDate(date, str);
        System.out.println(date1);
    }

    private Date translateDate(Date date, String str) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        //String str = "08:09";
        instance.set(Calendar.HOUR_OF_DAY, Integer.parseInt(str.split(":")[0]));
        instance.set(Calendar.MINUTE, Integer.parseInt(str.split(":")[1]));
        instance.set(Calendar.SECOND, 0);
        return instance.getTime();
    }

    @Test
    public void dateTimeTest() {
        Date date = new Date(System.currentTimeMillis() + 864000);
        System.out.println(date);
    }

}
