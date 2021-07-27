package netty.ch3network.blocking_1;

import lombok.extern.slf4j.Slf4j;
import netty.ByteBufferUtil;

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
public class Server {
    public static void main(String[] args) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(16);
        // 1.建立服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // 2.绑定端口
        ssc.bind(new InetSocketAddress(8080));
        // 3.accept
        ArrayList<SocketChannel> channels = new ArrayList<>();
        while (true) {
            // 4. 建立与客户端连接
            log.debug("connecting ...");
            SocketChannel sc = ssc.accept(); // 阻塞方法, 线程暂停, 线程不占用 CPU, 连接建立后, 线程恢复执行
            // socket channel 用来进行和和客户端的通信
            log.debug("connected..{}", sc);
            channels.add(sc);
            // 5. 接收数据
            for (SocketChannel channel : channels) {
                log.debug("before read...{}", channel);
                channel.read(buffer); // 也是阻塞方法

                buffer.flip(); // 转换为读模式
                ByteBufferUtil.debugRead(buffer);
                buffer.clear(); // 切回写模式
                log.debug("after read...{}", channel);
            }
        }

    }
}
