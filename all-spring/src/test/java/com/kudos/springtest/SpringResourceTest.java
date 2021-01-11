package com.kudos.springtest;

import com.kudos.SpringAllApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>SpringResourceReadTest</p>
 * <p>
 * Spring提供了一个org.springframework.core.io.Resource（注意不是javax.annotation.Resource），它可以像String、int一样使用@Value注入
 * </p>
 *
 * @author suzailong
 * @date 2021/1/11 10:51 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringAllApp.class)
public class SpringResourceTest {

    @Value("classpath:/SpringResourceFile.txt")
    private Resource resource;

    @Test
    public void ReadFileTest() throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
            Stream<String> lines = reader.lines();
            String line = lines.collect(Collectors.joining("\n"));
            System.out.println(line);
        }
    }
}
