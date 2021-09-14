package ch1_nio.ch3network.multithread_5;

import ch1_nio.ByteBufferUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程版本<p>
 * boss 管理链接, work 处理事件
 * </p>
 * <p>
 * NOTE:多线程版本 <br>
 * <b> NOTE: 任务作为对象在线程中传递, 使用队列进行传递</b> 队列在线程中进行通信
 *
 * @author suzl
 * @date 2021/7/28 12:06 上午
 */
@Slf4j
public class MultiThreadServer {
    public static void main(String[] args) throws IOException {
        Thread.currentThread().setName("boss");
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        Selector boss = Selector.open();
        SelectionKey bossKey = ssc.register(boss, 0, null);
        bossKey.interestOps(SelectionKey.OP_ACCEPT);
        ssc.bind(new InetSocketAddress(8080));

        // 创建固定的数量的 Worker, 并且初始化
        // Worker worker = new Worker("work-0");
        Worker[] workers = new Worker[Runtime.getRuntime().availableProcessors()]; // 线程数设置为核心线程数 坑 docker 中的核心数可能有误 读取到的是物理机的 cpu 核心数

        for (int i = 0; i < workers.length; i++) {
            workers[i] = new Worker("woker-" + i);
        }

        AtomicInteger index = new AtomicInteger();
        while (true) {
            boss.select();
            Iterator<SelectionKey> iter = boss.selectedKeys().iterator();
            while (iter.hasNext()) {
                SelectionKey key = iter.next();
                iter.remove();
                if (key.isAcceptable()) {
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    // 关联 worker 中的 selector
                    log.debug("Connect....{}", sc.getRemoteAddress());
                    log.debug("before....{}", sc.getRemoteAddress());
                    // sc.register(worker.selector, SelectionKey.OP_READ, null); // NOTE sc 的注册和 work 中的注册不再同一个线程. 导致阻塞
                    // STEP 1: 注册链接 初始化 worker
                    // worker.register(sc); // 被 boss 线程所调用, register 仍然在 Boss 线程中
                    // ! round robin(轮询)
                    workers[index.getAndIncrement() % workers.length].register(sc);
                    log.debug("after....{}", sc.getRemoteAddress());
                }
            }
        }
    }

    /**
     * worker
     */
    static class Worker implements Runnable {
        // NOTE 使用队列来进行两个线程中传输数据, 作为数据的通道
        private final ConcurrentLinkedQueue<Runnable> queue = new ConcurrentLinkedQueue<>();
        private Thread thread;
        private Selector selector;
        private final String name;
        private volatile boolean hasInit = false; // 线程和 selector 还没初始化,保证只初始化一次

        public Worker(String name) {
            this.name = name;
        }

        /**
         * NOTE 使用队列解决不同线程的数据通信
         * 初始化线程和 selector
         */
        public void register(SocketChannel sc) throws IOException {
            // STEP 2 仅仅初始化一次
            if (!hasInit) {
                thread = new Thread(this, name);
                thread.start(); // NOTE
                selector = Selector.open();
            }
            /*
             * NOTE 即使放在这是还是不在同一个线程
             */
            // sc.register(selector, SelectionKey.OP_READ, null);
            // NOTE 向队列添加了任务, 但是任务并有立即执行, 在 work 0 中执行
            // step 3: boss 线程 向队列添加了任务
            queue.add(() -> {
                try {
                    sc.register(selector, SelectionKey.OP_READ, null);
                } catch (ClosedChannelException e) {
                    e.printStackTrace();
                }
            });
            // NOTE  BOSS 线程主动唤醒 selector 唤醒下面的 selector
            selector.wakeup();
        }

        /**
         * NOTE: 使用 wakeup 来唤醒 selector 和上面的方式可以做到相同的效果
         * NOTE: 这里要重点理解 wakeup 方法
         *
         * @param sc
         * @throws IOException
         */
        public void registerByWakeUp(SocketChannel sc) throws IOException {
            // STEP 2 仅仅初始化一次
            if (!hasInit) {
                thread = new Thread(this, name);
                thread.start(); // NOTE
                selector = Selector.open();
            }
            // ! NOTE: wakeup 就像发了一张票 只要拿到一张票就不会阻塞,无论顺序
            selector.wakeup();
            sc.register(selector, SelectionKey.OP_READ, null);
        }

        @Override
        public void run() { // worker
            while (true) {
                try {
                    selector.select(); // NOTE wakeup 主动唤醒
                    Runnable task = queue.poll();
                    if (task != null) {
                        task.run(); // NOTE 执行了上面 lambda 的代码
                    }
                    Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
                    while (iter.hasNext()) {
                        SelectionKey key = iter.next();
                        iter.remove();
                        if (key.isReadable()) { // 关注可读事件
                            ByteBuffer buffer = ByteBuffer.allocate(16);
                            SocketChannel channel = (SocketChannel) key.channel();
                            int read = channel.read(buffer);
                            log.debug("worker read ..{}", channel.getRemoteAddress());
                            buffer.flip();
                            ByteBufferUtil.debugAll(buffer);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

