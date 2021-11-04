package ch3_eventloop;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.DefaultEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

/**
 * 处理 IO 操作
 *
 * @author suzailong
 * @date 2021/10/5 11:43 上午 周二
 */
@Slf4j
public class EventLoopHandleIoTaskTest {
    public static void main(String[] args) {
        // 细分 2: 创建独立的 event loop group 处理耗时长的操作
        DefaultEventLoop defEventLoop = new DefaultEventLoop();
        new ServerBootstrap()
                // 细分 1: boss & worker
                .group(new NioEventLoopGroup()/* boss 处理 accept 事件*/, new NioEventLoopGroup(2)/*处理 socket 上的读写*/)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSctpChannel>() {
                    // 链接建立后调用
                    @Override
                    protected void initChannel(NioSctpChannel ch) throws Exception {
                        // 这样处理耗时长的操作就是 defEventLoop, 而不是 NIO eventLoop
                        ch.pipeline().addLast("handler-1", new ChannelInboundHandlerAdapter() {
                            @Override  // ByteBuf
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                ByteBuf buf = (ByteBuf) msg;
                                log.debug(buf.toString(Charset.defaultCharset()));// 指定字符集
                                ctx.fireChannelRead(msg);// 传递给下一个 channel
                            }
                        }).addLast(defEventLoop, "longTimeHandler", new ChannelInboundHandlerAdapter() {
                            @Override  // ByteBuf
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                ByteBuf buf = (ByteBuf) msg;
                                log.debug(buf.toString(Charset.defaultCharset()));// 指定字符集
                            }
                        });
                    }
                }).bind(8080);
    }
}
