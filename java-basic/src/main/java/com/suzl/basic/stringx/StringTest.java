package com.suzl.basic.stringx;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;

/**
 * 字符串相关的测试
 *
 * @author Suz1
 * @date 2020/10/12 9:34 上午
 */
@Slf4j
public class StringTest {
    private static final String HIERARCHY_REGEX = "([0-9]|,|\\.)+";
    private static final String EACH_HIERARCHY_REGEX = "[0-9]\\d*\\.?\\d*";

    @Test
    public void testRegex() {

        ArrayList<Long> tenantIdList = new ArrayList<>();
        String tenantIdListStr = "nul,l,1,"; // 改为使用字符串接收
        if (tenantIdListStr != null && !tenantIdListStr.isEmpty()) {
            String[] split = tenantIdListStr.split(",");
            for (String s : split) {
                try {
                    tenantIdList.add(Long.parseLong(s));
                } catch (NumberFormatException e) {

                }
            }
        }
        System.out.println(JSON.toJSONString(tenantIdList, true));

    }

    @Test
    public void poolTest() {
        String tableNoKey = String.format("%04d", 1);
        String format = String.format("%s", "hah,");
        System.out.println(format);
        System.out.println(tableNoKey);
    }


    @Test
    public void splitTest() {
        String str = "a,b,c,,";
        String[] ary = str.split(",");
        // 预期大于 3，结果是 3
        System.out.println(ary.length);
    }

}
