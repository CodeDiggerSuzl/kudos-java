package com.suzl.basic;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * <p> 白龙马 分库分表工具类</p>
 *
 * @author suzailong
 * @date 2021/1/8 10:33 上午
 */
public class HashTest {

    @Test
    public void test() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sd = sdf.format(new Date(Long.parseLong("1614224365000")));
        System.out.println("格式化结果：" + sd);
        System.out.println("TimeUnit.DAYS.toSeconds(1) = " + TimeUnit.DAYS.toSeconds(1));
    }


    @Test
    public void hashTest() {
        String shardingFiled = "1139624814653";
        int dbCnt = 4;
        int tableCnt = 256;
        int hash = shardingFiled.hashCode();
        if (hash < 0) {
            hash = Math.abs(hash);
        }
        int tableNo = hash % tableCnt;
        int dbNo = tableNo / (tableCnt / dbCnt);
        System.out.println("dbNo = " + dbNo);
        System.out.println("tableNo = " + tableNo);
    }
}
