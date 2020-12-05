package rabbit.gettingstarted.topic;

import rabbit.gettingstarted.RabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Suz1
 * @date 2020/6/27 11:14 下午
 */
public class Provider {
    public static void main(String[] args) throws IOException {

        Connection conn = RabbitMqUtils.getConn();
        assert conn != null;
        try (Channel channel = conn.createChannel()) {
            channel.exchangeDeclare("topic-exchange", "topic");
            // send message
            String routeKey = "user.save";
            channel.basicPublish("topic-exchange", routeKey, null, "topic message".getBytes());
            RabbitMqUtils.closeConnAndChannel(channel, conn);
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
