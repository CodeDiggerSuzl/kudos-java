package netty.ch3network.writeserver_4;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * write server test
 *
 * @author suzl
 * @date 2021/7/26 11:50 下午
 */
public class WriteServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        serverSocketChannel.bind(new InetSocketAddress(8080));
        while (true) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();

                if (key.isAcceptable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    SelectionKey registerKey = socketChannel.register(selector, 0, null);
                    StringBuffer sb = new StringBuffer();

                    sb.append("a".repeat(300_000_000));

                    ByteBuffer bf = Charset.defaultCharset().encode(sb.toString());
                    // while (bf.hasRemaining()) { 将 while 循环替换成多次的可写处理
                    int write = socketChannel.write(bf);
                    System.out.println("write = " + write);
                    if (bf.hasRemaining()) {
                        // NOTE 关注可写事件(不覆盖原来关注的事件)
                        registerKey.interestOps(registerKey.interestOps() + SelectionKey.OP_WRITE);
                        // registerKey.interestOps(registerKey.interestOps() | SelectionKey.OP_WRITE); 或者位运算
                        // 把未写完的数据挂到 registerKey
                        registerKey.attach(bf);
                        // int write = socketChannel.write(bf);// NOTE 返回值表示实际写入的字节数a
                        // System.out.println("write = " + write);
                    }
                } else if (key.isWritable()) {
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    SocketChannel channel = (SocketChannel) key.channel();
                    int write = channel.write(buffer);
                    System.out.println("write = " + write);
                    // 6. 清理操作
                    if (!buffer.hasRemaining()) {
                        key.attach(null);
                        key.interestOps(key.interestOps() - SelectionKey.OP_WRITE); // 不再关注可写事件
                    }

                }
            }
        }
    }
}
