package com.suzl.basic.encryption;

import org.junit.Test;

import java.util.Base64;

public class Base64EncodeTest {
    @Test
    public void baseEncodeTest() {
        byte[] input = new byte[]{(byte) 0xe4, (byte) 0xb8, (byte) 0xad};
        String b64encoded = Base64.getEncoder().encodeToString(input);
        System.out.println(b64encoded);
    }
}
