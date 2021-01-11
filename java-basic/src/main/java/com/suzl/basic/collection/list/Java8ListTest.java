package com.suzl.basic.collection.list;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

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
        book.setName("Java-Book");
        book.setCity("a,b,c");
        Book b1 = new Book();
        b1.setName("Go-Book");
        b1.setCity("a,c,e");
        Book b2 = new Book();
        Book b3 = new Book();
        b3.setName("js-book");
        b3.setCity("a,c");
        ArrayList<Book> list = Lists.newArrayList(book, b1, b3);
        HashMap<String, List<Book>> m = new HashMap<>(8);
        // key tenantId, filed: adCode value list
        Map<Object[], List<Book>> collect = list.stream().collect(Collectors.groupingBy(p -> Arrays.stream(p.getCity().split(",")).toArray(), Collectors.toList()));
        System.out.println(JSON.toJSONString(collect, true));

    }


}

