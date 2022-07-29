package com.suzl.basic.stringx;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

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
        String s = "0,20";
        System.out.println(s.matches(HIERARCHY_REGEX));
    }

    @Test
    public void formatTest() {
        String s = "Hi %s, your score is %d!";
        System.out.printf("Hi %s, your score is %.2f!%n", "Bob", 59.5);
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

    @Test
    public void replaceTest() {
        String s = "${tenantName}司机师傅您好,12月31日和壹月一日${tenantName2}全天订单免抽佣,流水多赚50%!奖励最高可拿111元!记得出车参与活动呀!" +
                "${tenantName}天气寒冷请您注意保暖和疫情防护!服务过程中戴好口罩.感谢接听祝您生活愉快!再见!";
        String after = s.replace("${tenantName}", "central park");
        System.out.println("after = " + after);

    }

    @Test
    public void replaceAllTest() {
        String s = "00:30,01:30|02:30,03:30";
        //  String 反向引用 在 :30 后加上 ":00"
        // $0 $1
        // *组是用括号划分的正则表达式，可以根据组的编号来引用某个组。组号为 0 表示整个表达式，组号 1 表示第一对括号扩起的组，以此类推。
        // 再比如 A(B(C))D 有三个组：组 0 是 ABCD，组 1 是 BC，组 2 是 C，
        System.out.println(s.replaceAll(":\\d{2}", "$0:00"));       // 00:30:00,01:30:00|02:30:00,03:30:00
        System.out.println(s.replaceAll(":(\\d{2})", "$1"));        // 0030,0130|0230,0330 匹配出了 :30 然后把冒号替换成了 30, 正则还是很有趣的
        System.out.println(s.replaceAll("\\d{2}:(\\d{2})", "$1"));  // 30,30|30,30
    }

    @Test
    public void replaceAllTest2() {
        String keyString = "[rc6]groupKeyCacheKey";
        String cluster = keyString.substring(0, keyString.indexOf("]") + 1); // [rc6]
        // [rc(.*)] 可以匹配出 [rc6] 整个串,$1:即第一个括号的内容就是 6, 就是用 6 替换 [rc6]
        String afterReplace = cluster.replaceAll("\\[rc(.*)]", "$1");
        System.out.printf("cluster = %s, afterReplace = %s \n", cluster, afterReplace);


    }

}
