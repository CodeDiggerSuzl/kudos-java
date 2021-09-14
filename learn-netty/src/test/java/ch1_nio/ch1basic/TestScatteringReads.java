package ch1_nio.ch1basic;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestScatteringReads {
    // 分散读取测试思想
    public static void main(String[] args) {
        try (FileChannel channel = new RandomAccessFile("learn-netty/words.txt", "r").getChannel()) {
            ByteBuffer b1 = ByteBuffer.allocate(3);
            ByteBuffer b2 = ByteBuffer.allocate(3);
            ByteBuffer b3 = ByteBuffer.allocate(5);
            channel.read(new ByteBuffer[]{b1, b2, b3});
            b1.flip();
            b2.flip();
            b3.flip();
            // debuggerAll()
        } catch (IOException e) {
        }
    }
}
