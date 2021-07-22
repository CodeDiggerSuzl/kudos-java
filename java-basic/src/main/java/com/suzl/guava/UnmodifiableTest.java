package com.suzl.guava;

import com.suzl.utils.JsonUtils;
import lombok.Data;
import org.junit.Test;

/**
 * <p>不可变的集合测试 JDK vs Guava</p>
 *
 * @author suzailong
 * @date 2021/2/3 10:43 上午
 */
public class UnmodifiableTest {
    @Test
    public void unmodifiableJdkTest() {

        final Person person = new Person();
        person.setName("hah");

        Person w = person;
        test1(person);
        System.out.println(JsonUtils.toJson(person));
        System.out.println(JsonUtils.toJson(w));
    }

    void test1(Person p) {
        p.setName("xixi");
    }

    @Data
    static class Person {
        String name;
    }

}
