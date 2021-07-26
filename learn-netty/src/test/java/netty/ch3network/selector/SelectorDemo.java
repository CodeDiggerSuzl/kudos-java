package netty.ch3network.selector;

import com.common.JsonUtils;
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
        // 1. 创建 selector
        Selector selector = Selector.open();

        ByteBuffer buffer = ByteBuffer.allocate(16);
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        // 2. 建立 selector 和 channel 的关系(注册到 selector 上)
        SelectionKey sscKey = ssc.register(selector, 0, null); // 事件发生后,得到事件和哪个 channel 发生的事件
        sscKey.interestOps(SelectionKey.OP_ACCEPT); // 关注的事件 只要 4 种, 只关注链接事件

        log.debug("register key:[{}]", JsonUtils.toJson(sscKey));

        ssc.bind(new InetSocketAddress(8080));

        while (true) {
            // 3. select 方法,  没事件就阻塞, 有事件恢复执行
            int select = selector.select();

            // 4. 处理事件 获取所有事件, 内部包含了所有的发生的事件
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator(); // NOTE: selector 会在发生事件中, 向 selectedKeys 中加入 key 但是不会删除
            while (iterator.hasNext()) { // 只能用迭代器,不能用增强 for
                SelectionKey key = iterator.next();
                // NOTE 处理完一个 key 后 要从 selectedKey 中移出掉这个 key, 否则下次处理的时候会有问题 link: https://www.bilibili.com/video/BV1py4y1E7oA?p=30&spm_id_from=pageDriver
                iterator.remove();
                log.debug("key:[{}]", JsonUtils.toJson(sscKey));
                // 5. 区分事件类型
                if (key.isAcceptable()) { // 接收类型的
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel sc = channel.accept(); // NOTE 没有的时候会返回 null, 下一行可能会导致空指针
                    sc.configureBlocking(false);
                    SelectionKey scKey = sc.register(selector, 0, null);
                    scKey.interestOps(SelectionKey.OP_READ);
                } else if (key.isReadable()) { // 如果是 read
                    try {
                        SocketChannel ch = (SocketChannel) key.channel();
                        ByteBuffer bf = ByteBuffer.allocate(16); // NOTE 每个 socket channel 都必须有自己独立的 ByteBuffer, 附件 attachment
                        int read = ch.read(bf);
                        // NOTE read 方法正常断开 会返回 -1
                        if (read == -1) {
                            key.cancel();
                        } else {
                            // NOTE 这里需要处理消息的边界问题
                            // buffer.flip();
                            // debugAll(bf);
                            /*
                             消息边界 处理问题第一步
                             1. 当传入的消息长度长于 16 个字节,但是没有遇到 换行符的话
                             会丢失前 16个字节的数据, 比如传入 1234567890abcdhahah\n  只会得到 hahah, 前 16 个自己丢失了
                             2. ByteBuffer 不能为局部变量
                             */
                            split(buffer);
                        }
                    } catch (IOException e) {
                        // NOTE 远程异常断开链接后会强制断开链接, 会触发一个 read 事件, 这里捕获异常后要进行事件的处理
                        e.printStackTrace();
                        key.cancel(); // 从 selector Key 的 key 集合中
                    }
                }
            }
        }
    }

    /**
     * ! 重要代码 底层
     *
     * @param source bf
     */
    private static void split(ByteBuffer source) {
        source.flip(); // 读模式
        for (int i = 0; i < source.limit(); i++) {
            if (source.get(i) == '\n') {
                // 找到消息的长度
                int len = i + 1 - source.position();
                ByteBuffer target = ByteBuffer.allocate(len);
                // from source to target
                for (int j = 0; j < len; j++) {
                    target.put(source.get(j));
                }

            }
        }
        source.compact();
    }


}
