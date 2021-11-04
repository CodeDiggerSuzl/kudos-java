package ch3_eventloop;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

/**
 * ChannelFuture test
 *
 * @author suzailong
 * @date 2021/10/5 12:27 下午 周二
 */
public class ChannelTest {
    public static void main(String[] args) throws InterruptedException {
        ChannelFuture future = new Bootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<>() {
                    @Override
                    protected void initChannel(Channel ch) {
                        ch.pipeline().addLast(new StringEncoder());
                    }
                }).connect("127.0.0.1", 8080); // connect 是异步非阻塞方法, 真正执行的是 NIO 的一个线程.

        // 方法1, 使用 sync 进行阻塞
        ChannelFuture sync = future.sync(); // 没有这行代码, 上面的 connect 还没有拿到, 下面调用任何方法都是白搭
        sync.channel().writeAndFlush("hh");

        // 方法2, 使用监听器, 异步处理结果
        // future.addListener((ChannelFutureListener) f -> {
        //     Channel channel = f.channel();
        //     System.out.println(channel);
        //     channel.writeAndFlush("using listener");
        // });
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                future.channel().writeAndFlush("using listener");
            }
        });
    }
}
