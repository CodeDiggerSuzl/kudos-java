package netty.ch1basic;

import java.nio.ByteBuffer;

public class TestByteBufferReadWrite {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte) 0x61); // 写入 'a' 字符
        // TODO: 2021/7/25 add code

    }
}
