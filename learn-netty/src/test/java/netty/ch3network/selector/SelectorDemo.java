package netty.ch3network.selector;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

@Slf4j
public class SelectorDemo {
    public static void main(String[] args) throws IOException {
        // 创建 selector
        Selector selector = Selector.open();

        ByteBuffer buffer = ByteBuffer.allocate(16);
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        // 2. 建立 selector 和 channel 的关系(注册到 selector 上)
        SelectionKey sscKey = ssc.register(selector, 0, null); // 事件发生后,得到事件和哪个 channel 发生的事件
        sscKey.interestOps(SelectionKey.OP_ACCEPT); // 关注的事件 只要 4 种, 只关注链接事件

        log.debug("register key:[{}]", sscKey);

        ssc.bind(new InetSocketAddress(8080));
        while (true) {
            // 3. select 方法,  没事件就阻塞, 有事件恢复执行
            int select = selector.select();

            // 4. 处理事件 获取所有事件, 内部包含了所有的发生的事件
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                log.debug("key:[{}]", sscKey);
                ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                SocketChannel accept = channel.accept();
                log.debug("==[{}]==", accept);

            }
        }

    }
}
