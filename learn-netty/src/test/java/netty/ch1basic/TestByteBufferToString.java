package netty.ch1basic;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class TestByteBufferToString {
    public static void main(String[] args) {
        // 1 string to bytebuffer
        ByteBuffer bf = ByteBuffer.allocate(16);
        bf.put("hello".getBytes(StandardCharsets.UTF_8)); // ha
        // debugAll();

        // 2. charset
        ByteBuffer bf2 = StandardCharsets.UTF_8.encode("hello"); // 会自动切换为读模式

        // warp
        ByteBuffer bf3 = ByteBuffer.wrap("hello".getBytes(StandardCharsets.UTF_8)); //


        // -------------- buffer to string --------------
        String s2 = StandardCharsets.UTF_8.decode(bf2).toString();
        System.out.println(s2);

        bf.flip();  // 不加这一个行会什么也读取不出来, 因为没有转化为读模式呢
        String x = StandardCharsets.UTF_8.decode(bf).toString();
        System.out.println(x);


    }
}
