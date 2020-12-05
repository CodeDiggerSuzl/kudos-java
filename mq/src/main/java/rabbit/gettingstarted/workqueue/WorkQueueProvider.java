package rabbit.gettingstarted.workqueue;

import rabbit.gettingstarted.RabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * 第二种模式: work queue
 *
 * @author Suz1
 * @date 2020/6/26 3:53 下午
 */
public class WorkQueueProvider {
    public static void main(String[] args) throws IOException {
        Connection conn = RabbitMqUtils.getConn();
        assert conn != null;
        Channel channel = conn.createChannel();
        // 声明队列
        channel.queueDeclare("workQueue", true, false, false, null);
        for (int i = 0; i < 20; i++) {
            // 生产消息 MessageProperties.PERSISTENT_TEXT_PLAIN
            channel.basicPublish("", "workQueue", null, (i + "workQueue").getBytes());
        }

        RabbitMqUtils.closeConnAndChannel(channel, conn);
    }
}
