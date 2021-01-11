package com.kudos.springtest;

import com.kudos.SpringAllApp;
import com.kudos.translike.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>aop test</p>
 *
 * @author suzailong
 * @date 2021/1/11 11:18 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringAllApp.class)
public class AopTest {
    @Autowired
    private UserService userService;

    @Test
    public void aopTest() {
        String s = userService.register("email", "passwd", "joy");
        System.out.println("s = " + s);
    }
}
