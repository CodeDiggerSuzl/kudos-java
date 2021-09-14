package ch2_netty.ch1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

public class HelloNettyClient {
    public static void main(String[] args) throws InterruptedException {
        // 创建启动器类
        new Bootstrap()
                // 2. 添加 eventLoop
                .group(new NioEventLoopGroup())
                // 3. 客户端的 channel 实现
                .channel(NioSocketChannel.class)
                // 4. 添加处理器
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringEncoder());
                    }
                    // 链接到服务器
                }).connect(new InetSocketAddress("localhost", 8080))
                .sync()
                .channel().writeAndFlush("hello netty");

    }
}
