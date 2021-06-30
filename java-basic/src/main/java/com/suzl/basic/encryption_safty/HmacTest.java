package com.suzl.basic.encryption_safty;

import org.junit.Test;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class HmacTest {
    @Test
    public void hmacTest() throws NoSuchAlgorithmException, InvalidKeyException {
        KeyGenerator genKey = KeyGenerator.getInstance("HmacMD5");
        SecretKey secretKey = genKey.generateKey();
        byte[] encoded = secretKey.getEncoded();
        System.out.println(new BigInteger(1, encoded).toString(16));

        Mac mac = Mac.getInstance("HmacMD5");
        mac.init(secretKey);
        mac.update("HelloWorld".getBytes(StandardCharsets.UTF_8));
        byte[] result = mac.doFinal();
        System.out.println(new BigInteger(1, result).toString(16));
    }
}
