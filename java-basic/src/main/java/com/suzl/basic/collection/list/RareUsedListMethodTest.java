package com.suzl.basic.collection.list;

import com.common.JsonUtils;
import com.common.entity.UserGroup;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * 很少使用的 list 方法
 *
 * @author suzailong
 * @date 2022/4/1-10:56
 */
public class RareUsedListMethodTest {

    List<UserGroup> l1;
    List<UserGroup> l2;
    String s1 = "[{\"groupId\":\"968\",\"groupName\":\"test_user_group_user_159\"},{\"groupId\":\"962\",\"groupName\":\"test_user_group_user_153\"}]";
    String s2 = "[{\"groupId\":\"961\",\"groupName\":\"test_user_group_user_159\"},{\"groupId\":\"962\",\"groupName\":\"test_user_group_user_153\"}]";

    @Before
    public void init() {
        l1 = JsonUtils.fromListValue(s1, UserGroup.class);
        l2 = JsonUtils.fromListValue(s2, UserGroup.class);
    }

    /**
     * The retainAll() method of ArrayList is used to remove all the array list’s elements that are not contained in the
     * specified collection or retains all matching elements in the current ArrayList instance that match all elements
     * from the Collection list passed as a parameter to the method.
     */
    @Test
    public void retainAllTest() {
        // * l1 中删除掉 l2 中包含不包含的元素
        boolean b = l1.retainAll(l2);
        System.out.println("b = " + b);
        System.out.println("JsonUtils.toJson(l1) = " + JsonUtils.toJson(l1));
        System.out.println("JsonUtils.toJson(l2) = " + JsonUtils.toJson(l2));
    }

}
