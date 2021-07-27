package netty.ch3network.writeserver_4;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * write client
 *
 * @author suzl
 * @date 2021/7/27 12:00 上午
 */
public class WriteClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        boolean connect = socketChannel.connect(new InetSocketAddress("localhost", 8080));
        int cnt = 0;
        while (true) {
            ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);
            cnt += socketChannel.read(buffer);
            System.out.println("cnt = " + cnt);
            buffer.clear();
        }
    }
}
