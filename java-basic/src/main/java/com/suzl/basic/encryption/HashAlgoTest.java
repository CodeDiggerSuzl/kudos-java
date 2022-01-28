package com.suzl.basic.encryption;

import org.junit.Test;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * <p>
 * **常用的哈希算法有：**
 * <p>
 * | 算法       | 输出长度（位） | 输出长度（字节） |
 * | :--------- | :------------- | :--------------- |
 * | MD5        | 128 bits       | 16 bytes         |
 * | SHA-1      | 160 bits       | 20 bytes         |
 * | RipeMD-160 | 160 bits       | 20 bytes         |
 * | SHA-256    | 256 bits       | 32 bytes         |
 * | SHA-512    | 512 bits       | 64 bytes         |
 * </p>
 *
 * @author SuZaiLong
 * @date 2020/11/17 下午3:36
 */
public class HashAlgoTest {
    /**
     * 使用MessageDigest时，我们首先根据哈希算法获取一个MessageDigest实例，然后，反复调用update(byte[])输入数据。
     * <p>
     * 当输入结束后，调用digest()方法获得byte[]数组表示的摘要，最后，把它转换为十六进制的字符串。
     *
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void md5Test() throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("SHA-1");
        md5.update("Hello".getBytes(StandardCharsets.UTF_8));
        md5.update("joe".getBytes(StandardCharsets.UTF_8));
        byte[] digest = md5.digest();
        System.out.println("Arrays.toString(digest) = " + Arrays.toString(digest));
        System.out.println(new BigInteger(1, digest).toString(16));

    }
}
