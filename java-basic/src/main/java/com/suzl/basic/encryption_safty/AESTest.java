package com.suzl.basic.encryption_safty;

import org.junit.Test;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Base64;

/**
 * <p>AES 加密算法</p>
 *
 * @author suzailong
 * @date 2021/1/2 9:15 下午
 */
public class AESTest {
    private static final String RAW_MSG = "The Big Bang Theory";

    // 加密
    public static byte[] encrypt(byte[] key, byte[] input) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey aes = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, aes);
        return cipher.doFinal(input);
    }

    // 解密:
    public static byte[] decrypt(byte[] key, byte[] input) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey keySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        return cipher.doFinal(input);
    }

    @Test
    public void encryptTest() throws GeneralSecurityException {
        byte[] key = "1234567890abcdef".getBytes(StandardCharsets.UTF_8);
        byte[] data = RAW_MSG.getBytes(StandardCharsets.UTF_8);

        byte[] encrypted = encrypt(key, data);
        System.out.println("Encrypted: " + Base64.getEncoder().encodeToString(encrypted));

        // 解密:
        byte[] decrypted = decrypt(key, encrypted);
        System.out.println("Decrypted: " + new String(decrypted, StandardCharsets.UTF_8));

    }
}
