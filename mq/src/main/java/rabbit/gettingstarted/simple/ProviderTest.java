package rabbit.gettingstarted.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * rabbit-mq getting started
 *
 * @author Suz1
 * @date 2020/6/26 12:20 上午
 */
public class ProviderTest {
    /** 连接工厂 */
    ConnectionFactory connFactory;
    /** 链接对象 */
    Connection        conn;
    /** 通道对象 */
    Channel           channel;

    @Before
    public void createConnection() throws IOException, TimeoutException {
        // Create connection factory
        // simple mode with out exchange
        connFactory = new ConnectionFactory();
        connFactory.setHost("localhost");
        connFactory.setPort(5672);
        // set VH
        connFactory.setVirtualHost("/day1");
        // set user and pd
        connFactory.setUsername("suz1");
        connFactory.setPassword("rabbit");

        // create connection
        conn = connFactory.newConnection();
        // get the channel of conn
        // ! 链接通过通道来发送消息
        channel = conn.createChannel();

        /*
         * 通道绑定对应的消息队列
         * 参数 1: 队列名称, 不存在自动创建
         * 参数 2: 是否持久化
         * 参数 3: 是否独占队列
         * 参数 4: 是否自动删除
         * 参数 5: 额外参数
         */
        channel.queueDeclare("hello", false, false, false, null);
    }

    /** 生产消息 */
    @Test
    public void testSendMessage() throws IOException, TimeoutException {
        // 发布消息
        // 参数 1: 交换机名称, 2: 对队列名称 3: 传递消息额外设置 参数 4: 消息体
        channel.basicPublish("", "hello", null, "first message".getBytes());
        channel.close();
        conn.close();
    }
}
