package com.kudos.service.impl;

import com.kudos.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * com.suzl.allspring.spring com.up.cache 的策略: 和 com.suzl.allspring.spring 事务一样
 *
 * <a href="https://blog.csdn.net/wjacketcn/article/details/50945887"></a>
 *
 * @author Suz1
 * @date 2020/10/10 12:26 上午
 */
@Service
@Slf4j
public class TestServiceImpl implements TestService {
    /**
     * 测试 com.suzl.allspring.spring 缓存功能 注解里面的是 com.suzl.allspring.spring El 表达式
     * 数据更新的时候 需要删除缓存
     *
     * @param str  str
     * @param code code
     * @return str + code
     */
    @Override
    @Cacheable(value = "test", key = "#code")
    public String testCache(String str, int code) {
        log.info("get test com.up.cache : {},{}", str, code);
        return str + code;
    }
}
