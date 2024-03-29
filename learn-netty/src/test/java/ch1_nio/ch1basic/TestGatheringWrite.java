package ch1_nio.ch1basic;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class TestGatheringWrite {
    // 集中写 思想
    public static void main(String[] args) {
        ByteBuffer b1 = StandardCharsets.UTF_8.encode("hello");
        ByteBuffer b2 = StandardCharsets.UTF_8.encode("world");
        ByteBuffer b3 = StandardCharsets.UTF_8.encode("你好");
        try (FileChannel channel = new RandomAccessFile("learn-netty/words2.txt", "rw").getChannel()) {
            long write = channel.write(new ByteBuffer[]{b1, b2, b3});

        } catch (IOException e) {
        }
    }
}
