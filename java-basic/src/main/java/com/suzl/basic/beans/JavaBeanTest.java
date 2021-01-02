package com.suzl.basic.beans;

import org.junit.Test;

import java.beans.*;

/**
 * <p></p>
 *
 * @author suzailong
 * @date 2021/1/2 1:06 上午
 */
public class JavaBeanTest {
    @Test
    public void introTest() throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class);
        EventSetDescriptor[] eventSetDescriptors = beanInfo.getEventSetDescriptors();

        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor descriptor : propertyDescriptors) {
            System.out.println(descriptor.getName());
            System.out.println(descriptor.getReadMethod());
            System.out.println(descriptor.getWriteMethod());
        }
    }
}

class Person {
    private String name;
    private int    age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
