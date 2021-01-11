package com.kudos.springtest;

import com.alibaba.fastjson.JSON;
import com.kudos.SpringAllApp;
import com.kudos.entity.BeBetter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * <p>com.suzl.allspring.spring tips test</p>
 *
 * @author suzailong
 * @date 2021/1/3 10:51 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringAllApp.class)
public class SpringAllTest {
    @Autowired // autowired list
    private List<BeBetter> beBetterList;


    @Test
    public void springTest() {
        BeBetter beBetter = beBetterList.get(0);
        String s = beBetter.toString();
        beBetter.learning();
        System.out.println("s = " + s);
        System.out.println("JSON.toJSONString(beBetterList) = " + JSON.toJSONString(beBetterList));
    }

}
