package com.suzl.basic.regex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Java 正则测试</p>
 *
 * @author suzailong
 * @date 2021/1/2 5:43 下午
 */
public class RegexTest {
    static Pattern pattern = Pattern.compile("(\\d{3,4})-(\\d{7,8})");
    static Pattern p       = Pattern.compile("(\\d{3,4})-(\\d{7,8})");


    @Test
    public void firstRegexTest() {
        String regex = "20\\d\\d";
        System.out.println("\"2019\".matches(regex) = " + "2019".matches(regex));
        System.out.println("\"2100\".matches(regex) = " + "2100".matches(regex));
        System.out.println("------");
        String re1 = "abc";
        System.out.println("abc".matches(re1));
        System.out.println("Abc".matches(re1));
        System.out.println("abcd".matches(re1));

        String re2 = "a\\&c"; // 对应的正则是a\&c
        System.out.println("a&c".matches(re2));
        System.out.println("a-c".matches(re2));
        System.out.println("a&&c".matches(re2));
    }

    @Test
    public void groupMatchTest() {
        Matcher m = p.matcher("010-12345678");

        if (m.matches()) {
            String g1 = m.group(1);
            String g2 = m.group(2);
            System.out.println("g1 = " + g1);
            System.out.println("g2 = " + g2);
        }
    }

    @Test
    public void betterMatchTest() {
        pattern.matcher("010-12345678").matches(); // true
        pattern.matcher("021-123456").matches(); // true
        pattern.matcher("022#1234567").matches(); // false
    }

    /**
     * 贪婪匹配
     */
    @Test
    public void nonGreedyTest() {
        Pattern pattern = Pattern.compile("(\\d+)(0*)");
        Matcher matcher = pattern.matcher("1230000");
        // 这是因为正则表达式默认使用贪婪匹配：任何一个规则，它总是尽可能多地向后匹配，因此，\d+总是会把后面的0包含进来。
        if (matcher.matches()) {
            System.out.println("group1=" + matcher.group(1)); // "1230000"
            System.out.println("group2=" + matcher.group(2)); // ""
        }
        System.out.println("======================");
    }

    /**
     * 要让\d+尽量少匹配，让0*尽量多匹配，我们就必须让\d+使用非贪婪匹配。在规则\d+后面加个?即可表示非贪婪匹配。
     */
    @Test
    public void greedyTest() {
        Pattern p = Pattern.compile("(\\d+?)(0*)");
        Matcher m = p.matcher("12300000");
        if (m.matches()) {
            System.out.println("m.group(1) = " + m.group(1));
            System.out.println("m.group(0) = " + m.group(0));
        }
    }

    @Test
    public void strReplaceTest() {
        String s = "the quick brown fox jumps over the lazy dog.";
        String r = s.replaceAll("\\s([a-z]{4})\\s", " <b>$1</b> ");
        System.out.println(r);
    }
}
