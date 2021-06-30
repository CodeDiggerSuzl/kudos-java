package com.suzl.basic;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.suzl.utils.JsonUtils;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * <p>分库分表工具类</p>
 *
 * @author suzailong
 * @date 2021/1/8 10:33 上午
 */
public class HashTest {

    private static final Splitter COMMA_SPLITTER = Splitter.on(",");
    private static final Splitter VERTICAL_SPLITTER = Splitter.on("|").trimResults().omitEmptyStrings();

    @Test
    public void test() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sd = sdf.format(new Date(Long.parseLong("1614224365000")));
        System.out.println("格式化结果：" + sd);
        System.out.println("TimeUnit.DAYS.toSeconds(1) = " + TimeUnit.DAYS.toSeconds(1));
    }

    @Test
    public void hashTest() {
        String shardingFiled = "2084751112156";
        int dbCnt = 4, tableCnt = 256;

        int hash = shardingFiled.hashCode();
        if (hash < 0) {
            hash = Math.abs(hash);
        }
        int tableNo = hash % tableCnt;
        int dbNo = tableNo / (tableCnt / dbCnt);
        System.out.println("dbNo = " + dbNo);
        System.out.println("tableNo = " + tableNo);
    }

    @Test
    public void Test() throws Exception {
        String s = " 00:30,01:30|02:30 ,03 :30";
        System.out.println(JsonUtils.toJson(fromTimeInterval2PeakTimeList(s)));
    }

    private Object[] fromTimeInterval2PeakTimeList(String timeIntervalStr) throws Exception {
        if (Strings.isNullOrEmpty(timeIntervalStr)) {
            return new PeakTimeVO[0];
        }
        String[] timeIntervalArr = timeIntervalStr.split("\\|");
        ArrayList<PeakTimeVO> resultList = new ArrayList<>();
        try {
            if (timeIntervalArr.length > 0) {
                for (String time : timeIntervalArr) {
                    String[] timeInterval = time.split(",");
                    resultList.add(new PeakTimeVO(timeInterval[0].replaceAll("\\s", ""), timeInterval[1].replaceAll("\\s", "")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("err during fromTimeInterval2PeakTimeList");
        }
        return resultList.toArray();
    }


}
