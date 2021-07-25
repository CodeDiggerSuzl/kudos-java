package netty.ch1basic;

import java.nio.ByteBuffer;

/**
 * ByteBuffer 常用方法
 */
public class TestByteBufferAllocate {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(16); // 分配大小不能动态修改
        System.out.println(buffer.getClass());
        // class java.nio.HeapByteBuffer
        // Java 堆内存,读写效率低, 受到 GC 影响(内存会进行移动)

        ByteBuffer directBuffer = ByteBuffer.allocateDirect(16);
        System.out.println(directBuffer.getClass());
        // class java.nio.DirectByteBuffer
        // 直接/系统内存.读写效率高(少一次拷贝) 不会受到 GC 影响. 但是分配的效率低(需要调用系统函数), 有可能造成内存泄漏


    }
}
