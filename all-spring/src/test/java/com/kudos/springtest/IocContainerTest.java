package com.kudos.springtest;

import com.kudos.SpringAllApp;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>Spring ioc 容器</p>
 *
 * @author suzailong
 * @date 2021/1/5 7:17 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringAllApp.class)
public class IocContainerTest {

    @Test
    public void appContextTest() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(SpringAllApp.class, HikariDataSource.class); // nested exception is org.springframework.boot.autoconfigure.jdbc.DataSourceProperties$DataSourceBeanCreationException: Failed to determine a suitable driver class
        ctx.refresh();
        ctx.scan("com.kudos");
        int beanDefinitionCount = ctx.getBeanDefinitionCount();
        BeanFactory parentBeanFactory = ctx.getParentBeanFactory();
        ConfigurableEnvironment environment = ctx.getEnvironment();
        String id = ctx.getId();

        String appName = ctx.getApplicationName();
        System.out.println("applicationName = " + appName);
        // IdWorker idWorker = (IdWorker) ctx.getBean("IdWorker");
        // System.out.println("idWorker.nextId() = " + idWorker.nextId());
    }
}
