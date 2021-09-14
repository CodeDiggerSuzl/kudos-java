package ch1_nio.ch3network.nonblocking_2;

import ch1_nio.ByteBufferUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

/**
 * 使用 NIO 来理解阻塞模式
 */
@Slf4j
public class NonBlockingServer {
    public static void main(String[] args) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(16);
        // 1.建立服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false); // NOTE 设置为非阻塞模式
        // 2.绑定端口
        ssc.bind(new InetSocketAddress(8080));
        // 3.accept
        ArrayList<SocketChannel> channels = new ArrayList<>();
        while (true) { // 缺点: 线程也在不停循环, 消耗 cpu
            // 4. 建立与客户端连接
            // log.debug("connecting ...");
            SocketChannel sc = ssc.accept(); // 非阻塞, 线程还会继续执行, 但是没有链接建立的话, sc 是 null
            // socket channel 用来进行和和客户端的通信
            if (sc != null) {
                log.debug("connected..{}", sc);
                sc.configureBlocking(false); // 设置 socket channel 为非阻塞
                channels.add(sc);
                // 5. 接收数据
                for (SocketChannel channel : channels) {
                    // log.debug("before read...{}", channel);
                    int read = channel.read(buffer);// 通过上面的  sc.configureBlocking(false); read 会变成非阻塞模式, 此时非阻塞返回 0
                    if (read > 0) {
                        buffer.flip(); // 转换为读模式
                        ByteBufferUtil.debugRead(buffer);
                        buffer.clear(); // 切回写模式
                        log.debug("after read...{}", channel);
                    }
                }
            }
        }

    }
}
