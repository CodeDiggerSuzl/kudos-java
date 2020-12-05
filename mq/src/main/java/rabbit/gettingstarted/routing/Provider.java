package rabbit.gettingstarted.routing;

import rabbit.gettingstarted.RabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * Routing-direct mode
 *
 * @author Suz1
 * @date 2020/6/27 10:13 下午
 */
public class Provider {
    public static void main(String[] args) throws IOException {
        Connection conn = RabbitMqUtils.getConn();
        assert conn != null;
        Channel channel = conn.createChannel();
        // exchange
        channel.exchangeDeclare("routing-direct", "direct");
        // def routing key
        String routingKey = "error";
        channel.basicPublish("routing-direct", routingKey, null, ("this is direct 模型发布的消息:" + routingKey).getBytes());
        RabbitMqUtils.closeConnAndChannel(channel, conn);
    }
}
