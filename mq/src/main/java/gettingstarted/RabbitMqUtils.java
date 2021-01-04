package rabbit.gettingstarted;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * RabbitMQ Connection utils
 *
 * @author Suz1
 * @date 2020/6/26 2:15 下午
 */
public class RabbitMqUtils {

    /**
     * 静态变量: 虽有对象共享,内存中只有同一个副本,仅仅在类初始化的时候加载
     * final 变量: 保证变量不可以变化
     */
    private static final ConnectionFactory CONN_FACTORY;

    // 静态代码代码块的作用: 类加载的时候执行
    static {
        CONN_FACTORY = new ConnectionFactory();
        CONN_FACTORY.setHost("127.0.0.1");
        CONN_FACTORY.setPort(5672);
        CONN_FACTORY.setUsername("suz1");
        CONN_FACTORY.setPassword("rabbit");
        CONN_FACTORY.setVirtualHost("/day1");
    }

    /**
     * 提供链接对象的方法
     *
     * @return 链接对象
     */
    public static Connection getConn() {
        try {
            return CONN_FACTORY.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Close the channel and conn of rabbit mq
     *
     * @param channel    channel to close
     * @param connection connection to close
     */
    public static void closeConnAndChannel(Channel channel, Connection connection) {
        try {
            if (channel != null) { channel.close(); }
            if (connection != null) { connection.close(); }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
