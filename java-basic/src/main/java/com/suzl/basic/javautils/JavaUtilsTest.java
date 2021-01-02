package com.suzl.basic.javautils;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

/**
 * <p>java general utils test</p>
 *
 * @author suzailong
 * @date 2021/1/2 1:55 上午
 */
public class JavaUtilsTest {
    @Test
    public void randTest() {
        Random random = new Random();
        double v = random.nextGaussian();
        System.out.println("v = " + v);
    }

    @Test
    public void secureRandTest() {
        SecureRandom sr = null;
        try {
            sr = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            sr = new SecureRandom();
        }
        byte[] buffer = new byte[16];
        sr.nextBytes(buffer); // 用安全随机数填充buffer
        System.out.println(Arrays.toString(buffer));
    }
}
