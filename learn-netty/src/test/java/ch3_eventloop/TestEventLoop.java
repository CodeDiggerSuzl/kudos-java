package ch3_eventloop;

import io.netty.channel.DefaultEventLoop;
import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * Test Event Loop
 *
 * <p>
 * 执行定时任务和普通任务
 * </p>
 *
 * @author suzailong
 * @date 2021/10/5 11:14 上午 周二
 */
@Slf4j
public class TestEventLoop {
    public static void main(String[] args) {
        // 创建事件循环组
        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup(2);// io 事件， 普通任务， 定时任务
        DefaultEventLoop defaultEventLoop = new DefaultEventLoop(); // 普通任务， 定时任务
        // System.out.println(NettyRuntime.availableProcessors());
        // 1. 获取下一个循环对象(类似一个循环链表）
        EventLoop next = nioEventLoopGroup.next();
        System.out.println(next);
        System.out.println(nioEventLoopGroup.next());
        System.out.println(nioEventLoopGroup.next());
        // 2. 执行普通任务(继承了线程池, 有线程池的方法), 可以做异步处理, 也可以
        //  nioEventLoopGroup.execute(() -> {
        //      try {
        //          Thread.sleep(1000);
        //      } catch (InterruptedException e) {
        //          e.printStackTrace();
        //      }
        //      log.debug("OK");
        //  });
        //  log.debug("main");

        // 3. 执行定时任务
        nioEventLoopGroup.next().scheduleAtFixedRate(() -> {
            log.info("schedule job");
        }, 1, 1, TimeUnit.SECONDS);
    }
}
