package com.suzl.basic;

import org.junit.Test;

/**
 * <p> 白龙马 分库分表工具类</p>
 *
 * @author suzailong
 * @date 2021/1/8 10:33 上午
 */
public class HashTest {
    @Test
    public void hashTest() {
        String shardingFiled = "8662214805";
        int dbCnt = 2;
        int tableCnt = 4;
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
