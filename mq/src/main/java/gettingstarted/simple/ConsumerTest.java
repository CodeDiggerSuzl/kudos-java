package rabbit.gettingstarted.simple;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Rabbit mq 消费者测试
 * 最简单模型 p2p 没有 exchange
 * ! 使用 main 函数 不能使用 junit test
 *
 * @author Suz1
 * @date 2020/6/26 1:04 上午
 */
@Slf4j
public class ConsumerTest {
    /**
     * 链接工厂
     */
    ConnectionFactory connFactory;
    /**
     * 链接
     */
    Connection        conn;
    /**
     * 通道
     */
    Channel           channel;

    @Before
    public void setEnvUp() throws IOException, TimeoutException {
        connFactory = new ConnectionFactory();
        connFactory.setHost("localhost");
        connFactory.setPort(5672);
        connFactory.setVirtualHost("/day1");
        connFactory.setUsername("suz1");
        connFactory.setPassword("rabbit");

        conn = connFactory.newConnection();

        channel = conn.createChannel();

        channel.queueDeclare("hello", false, false, false, null);

    }

    @Test
    public void testSimpleGetMsg() throws IOException {
        // 消费消息
        // 参数 1: 队列名称
        // 参数 2: 开始消息自动确认机制
        // 参数 3: 消费时的回调接口
        channel.basicConsume("hello", true, new DefaultConsumer(channel) {
            // 重写方法 param1:  最后一个参数: 消息队列的参数
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
                // log.info("得到的消息:{}", new String(body));
            }
        });
        // 不关闭一直监听
        //        channel.close();
        //        conn.close();
    }
}
