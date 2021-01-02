package com.suzl.basic.number;

import org.junit.Test;

enum Weekday {
    MON(1, "星期一"),
    TUE(2, "星期二"),
    WED(3, "星期三"),
    THU(4, "星期四"),
    FRI(5, "星期五"), SAT(6, "星期六"), SUN(0, "星期日");

    public final  int    dayValue;
    private final String chinese;

    private Weekday(int dayValue, String chinese) {
        this.dayValue = dayValue;
        this.chinese = chinese;
    }

    @Override
    public String toString() {
        return this.chinese;
    }
}

/**
 * <p>enum test</p>
 *
 * @author suzailong
 * @date 2021/1/2 1:13 上午
 */
public class EnumTest {
    @Test
    public void enumTest() {
        String name = Weekday.FRI.name();
        System.out.println(name); // FRI
        System.out.println(Weekday.FRI.toString()); // 星期五
        // ordinal()
        int ordinal = Weekday.FRI.ordinal(); // 4
        System.out.println(ordinal);
    }
}


