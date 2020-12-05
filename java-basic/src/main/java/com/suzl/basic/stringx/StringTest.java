package com.suzl.basic.stringx;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        if (tenantIdListStr!= null && !tenantIdListStr.isEmpty()) {
            String[] split = tenantIdListStr.split(",");
            for (String s : split) {
                try {
                    tenantIdList.add(Long.parseLong(s));
                } catch (NumberFormatException e) {

                }
            }
        }
        System.out.println(JSON.toJSONString(tenantIdList,true));

    }

    @Test
    public void poolTest() {
        String tableNoKey = String.format("%04d", 1);
        String format = String.format("%s", "hah,");
        System.out.println(format);
        System.out.println(tableNoKey);
    }
    public static List<Integer> SPECIAL_PROVCODE = Arrays.asList(11, 12, 31, 50, 81, 82);
    // 最小adcode值
    public static Integer MIN_LIMIT = 110000;
    // 最大adcode值
    public static Integer MAX_LIMIT = 829999;
    // 重庆郊区
    public static Integer CQJX_CODE = 5002;
    // 重庆市区
    public static Integer CQSX_CODE = 5001;

    @Test
    public void splitTest() {
        String str = "1,3.00,1.00,2.00|2,3.00,2.00,1.00|3,6.00,3.00,3.00";
        String[] split = str.split("\\|");

        List<String> strings = Arrays.asList(split);
        BigDecimal temp = BigDecimal.valueOf(0.00D);
        for (String s : strings) {
            if (!s.matches(HIERARCHY_REGEX)) {
                System.out.println(s + "no pass");
            }
            BigDecimal val = new BigDecimal(s.split(",")[1]);
            int i = val.compareTo(temp);
            if (i > 0) {
                temp = val;
            }
        }
        Assert.assertEquals(0, BigDecimal.valueOf(6.00D).compareTo(temp));
        System.out.println(JSON.toJSONString(strings, true));
    }

    public static String dealToCityCode(String adcode) {
        Integer city;
        try {
            city = Integer.valueOf(adcode);
        } catch (NumberFormatException e) {
            return adcode;
        }
        // 非法行政编码不处理
        if (city < MIN_LIMIT || city > MAX_LIMIT) {
            return adcode;
        }
        // 重庆500100 500200
        Integer cqcode = Integer.valueOf(adcode.substring(0,4));
        if (cqcode.equals(CQJX_CODE)){
            return CQSX_CODE + "00";
        }
        // 京、津、沪、渝、港、澳
        //Integer provCode = city / 10000;
        //if (SPECIAL_PROVCODE.contains(provCode)) {
        //    return String.valueOf(provCode * 10000);
        //}
        // 自治区、省直辖行政单位（城市级，且行政编码为县级编码）
        Integer midcode = Integer.valueOf(adcode.substring(2,4));
        if (midcode.equals(90)) {
            return adcode;
        }
        // 通用城市编码
        return String.valueOf((city / 100) * 100);
    }

    /**
     * @Description: 处理为系统城市
     * @author: tangzhilong@yueyuechuxing.cn
     * @date: 2019/10/22 12:15
     * @param: cityCode
     * @return: java.lang.String
     */
    public static String dealSysCity(String cityCode){
        if(StringUtils.isEmpty(cityCode)){
            return cityCode;
        }
        cityCode = dealToCityCode(cityCode);
        return dealDirectCity(cityCode);
    }
    public static String dealDirectCity(String cityCode) {
        if (StringUtils.isEmpty(cityCode)) {
            return cityCode;
        }
        cityCode = cityCode.trim();
        // 处理直辖市问题 客户端直辖市传过来的adcode是省份 需要转为城市
        if (cityCode.equals("110000")) {
            return "110100";
        } else if (cityCode.equals("120000")) {
            return "120100";
        } else if (cityCode.equals("310000")) {
            return "310100";
        } else if (cityCode.equals("500000")) {
            return "500100";
        }
        return cityCode;
    }

    public static void main(String[] args) {
        String s = dealSysCity("1");
        System.out.println(s);
    }

    @Test
    public void split2Test(){

    }
}
