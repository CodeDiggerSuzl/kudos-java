package faqs.real.tanma;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 探马面试题牛客网第一题
 * <p>
 * 集合去重, 一个 String 中的数字相同即视为该字符串相同,删除列表中的相等的字符串
 * </p>
 * <p>
 * 思路:  正则匹配
 */
public class Q1_RemoveSameString {
    static final Pattern COMPILE = Pattern.compile("\\d+");

    public static void main(String[] args) {
        // set to store the unique num
        HashSet<String> set = new HashSet<>();

        ArrayList<String> list = new ArrayList<>();
        list.add("123abccd");
        list.add("asdfas123");
        list.add("1234asdf");
        list.add("lkjh1234");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            String num = findNum(next);
            if (num != null && Boolean.FALSE.equals(set.add(num))) {
                iterator.remove();
            }
        }
        System.out.println(JSON.toJSON(list));
    }

    /**
     * find num in str by using regex
     */
    public static String findNum(String rawString) {
        Matcher matcher = COMPILE.matcher(rawString);
        if (matcher.find()) {
            return matcher.group(0);
        }
        return null;
    }
}
