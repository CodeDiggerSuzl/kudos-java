package com.kudos.translike;

import org.springframework.stereotype.Component;

/**
 * @author suzailong
 * @date 2021/1/11 11:44 下午
 */
@Component
public class UserService {

    @MetricTimeMark("register")
    public String register(String email, String password, String name) {
        return email + "-" + password + "-" + name;
    }
}
