package ch1_nio.ch1basic;

import java.nio.ByteBuffer;

public class TestByteBufferRead {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put(new byte[]{'a', 'b', 'c', 'd'}); // 向 buffer 写
        buffer.flip();

        // * 从头开始读起 abcd 全部读完
        // buffer.get(new byte[4]);
        // debugAll();
        // buffer.rewind(); // 将 position 设置为 0 开始
        // System.out.println((char) buffer.get());

        // * mark & reset 常常结合使用
        // mark 做一个标记, 记录 position 的位置, reset 重置到 mark 的位置 (对 rewind 的增加)
        // System.out.println((char) buffer.get());
        // System.out.println((char) buffer.get());
        // buffer.mark(); // 加标记到索引为 2 的位置
        // System.out.println((char) buffer.get());
        // System.out.println((char) buffer.get());
        // buffer.reset(); // 将 position 重置到 2 的位置
        // System.out.println((char) buffer.get());
        // System.out.println((char) buffer.get());

        // * get(i)  position 不会改变读索引的位置
        System.out.println((char) buffer.get(3));

    }
}
