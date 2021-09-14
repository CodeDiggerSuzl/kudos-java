package ch2_netty.ch1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

public class HelloNettyServer {
    public static void main(String[] args) {
        // 1. 启动器:负责组装 netty 组件
        new ServerBootstrap()
                .group(new NioEventLoopGroup()) // 2. 添加了 NIO event loop group(selector + thread), group 组
                .channel(NioServerSocketChannel.class) // 3. 选择服务器的 server socket 的实现(共 4 个)
                .childHandler( // 4. boss 处理链接, worker 负责读写, 决定了 worker 能执行哪些操作(handler)
                        // 5. channel 代表和客户端进行数据读写的通道 初始化 Initializer 初始化, 负责添加别的 handler
                        new ChannelInitializer<NioSocketChannel>() {
                            @Override
                            protected void initChannel(NioSocketChannel ch) throws Exception {
                                // 6.添加具体的 handler
                                ch.pipeline().addLast(new StringDecoder()); // 将 ByteBuf 转换为字符串
                                ch.pipeline().addLast(new ChannelInboundHandlerAdapter() { // 自定义的 handler
                                    @Override // 读时间
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) {
                                        System.out.println(msg);
                                    }
                                });
                            }
                        }).bind(8080); // 7. 决定绑定的端口

    }
}
