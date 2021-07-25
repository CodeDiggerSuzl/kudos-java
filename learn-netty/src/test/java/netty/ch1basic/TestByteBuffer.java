package netty.ch1basic;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import static netty.ByteBufferUtil.debugAll;

@Slf4j
public class TestByteBuffer {
    // 文件复制感受 bytebuffer
    public static void main(String[] args) {
        // FileChannel 输入输出流 2. RandomAccessFile
        try (FileChannel fileChannel = new FileInputStream("learn-netty/data.txt").getChannel()) {
            // 准备缓冲区
            ByteBuffer bf = ByteBuffer.allocate(10); // 只能通过静态方法分配内存,单位字节(缓存区设置的少的话只能读取 10 个字节, 需要 read 多次读取)
            // 从 channel 中读取数据, 向 buffer 中写入
            // read 返回 -1 表示读取完毕
            while (true) {
                int read = fileChannel.read(bf);
                log.debug("读取到的字节数[{}]", read);
                if (read == -1) break;
                // 打印 buffer 内容
                bf.flip(); // 切换到 buffer 的读模式
                while (bf.hasRemaining()) { // 还有剩余的
                    byte b = bf.get();// 一次读一个字节
                    log.debug("实际字节[{}]", (char) b);
                    // System.out.println((char) b);
                }
                // 读完一次后 buffer 切换为写模式
                debugAll(bf);
                bf.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
