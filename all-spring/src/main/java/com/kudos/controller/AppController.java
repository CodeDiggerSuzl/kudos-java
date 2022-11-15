package com.kudos.controller;

import com.kudos.AopMark;
import com.kudos.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 测试统一异常拦截 {@link BaseExceptionHandler}
 *
 * @author Suz1
 * @date 2020/10/9 11:20 下午
 */
@RestController
@RequestMapping("/test")
@Scope("prototype")
public class AppController {
    @Autowired
    private TestService testService;

    @GetMapping("/")
    public String hello() {
        // 测试异常
        int a = 1 / 0;
        return "test";
    }

    /**
     * 测试 com.suzl.allspring.spring 的缓存
     */
    @GetMapping("/testCache/{id}")
    @AopMark
    private String testCache(@PathVariable int id) {
        return testService.testCache("str", id);
    }

    @RequestMapping("/testReqAndResp")
    private String testReqAndResp(HttpServletResponse response, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        HttpSession session = request.getSession();
        response.addCookie(new Cookie("cookie", "now"));
        return "testReqAndResp";
    }
}
