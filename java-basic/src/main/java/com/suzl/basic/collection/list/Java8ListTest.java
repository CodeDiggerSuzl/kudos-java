package com.suzl.basic.collection.list;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.suzl.basic.collection.entity.Book;
import com.suzl.utils.JsonUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * <p>java 8 list test</p>
 *
 * @author suzailong
 * @date 2021/1/6 10:15 下午
 */
public class Java8ListTest {
    @Test
    public void groupTest() {
        Book book = new Book();
        Date curr = new Date();
        book.setName("Java-Book");
//        book.setCity("a,b,c");
        book.setId(1L);
        book.setCreateTime(curr);

        Book b1 = new Book();
        b1.setName("Go-Book");
//        b1.setCity("a,c,e");
        b1.setId(2L);
        b1.setCreateTime(DateUtils.addDays(curr, 1));

        Book b3 = new Book();
        b3.setName("js-book");
//        b3.setCity("a,c");
        b3.setId(-1L);
        Date minus = DateUtils.addDays(curr, -1);
        b3.setCreateTime(minus);


        ArrayList<Book> list = Lists.newArrayList(book, b1, b3);
        HashMap<String, List<Book>> m = new HashMap<>(8);
        // key tenantId, filed: adCode value list
//        Map<Object[], List<Book>> collect = list.stream().collect(Collectors.groupingBy(p -> Arrays.stream(p.getCity().split(",")).toArray(), Collectors.toList()));
//        System.out.println(JSON.toJSONString(collect, false));
        // list.sort(Comparator.comparing(Book::getId, Comparator.nullsFirst(Comparator.naturalOrder())).reversed());

        list.sort(Comparator.comparing(Book::getCreateTime).reversed());

        System.out.println("JsonUtils.toJson(list) = " + JsonUtils.toJson(list));
    }
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("xxx","xxxxs");
        map.put("xx","xxs");
        System.out.println("JsonMapper.toJson(map) = " + JSON.toJSONString(map));
    }
    @Test
    public void test() {
        BigDecimal bd = new BigDecimal("12.733");
        long l = bd.setScale(0, BigDecimal.ROUND_UP).longValue(); // 向上取整
        long l2 = bd.setScale(1, RoundingMode.FLOOR).longValue();
        System.out.println(JSON.toJSON(l));
        System.out.println(JSON.toJSON(l2));
    }
}

