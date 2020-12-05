package com.suzl.basic.dateops;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * <p>分钟转换为小时</p>
 *
 * @author SuZaiLong
 * @date 2020/11/29 下午5:48
 */
public class Minute2HourTest {

    @Test
    public void mTest() {
        Integer minutes = 261;
        BigDecimal decimal = new BigDecimal(minutes.floatValue());
        BigDecimal bigDecimal = new BigDecimal(60.00F);
        float v = decimal.divide(bigDecimal, 3, RoundingMode.HALF_EVEN).floatValue();
        System.out.println(v);
        System.out.println(minute2Hour(263));
        hour2Min("4.38");
    }

    public String minute2Hour(Integer minute) {
        BigDecimal min = BigDecimal.valueOf(minute.doubleValue());
        BigDecimal bigDecimal = BigDecimal.valueOf(60.00D);
        float v = min.divide(bigDecimal, 2, RoundingMode.HALF_EVEN).floatValue();
        return String.valueOf(v);
    }

    public Integer hour2Min(String hour) {
        BigDecimal hour2 = BigDecimal.valueOf(Double.parseDouble(hour));
        BigDecimal multiply = hour2.multiply(BigDecimal.valueOf(60.00D));
        System.out.println(multiply.intValue());
        return multiply.intValue();
    }
}
