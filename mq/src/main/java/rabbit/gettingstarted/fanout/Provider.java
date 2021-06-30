package rabbit.gettingstarted.fanout;

import rabbit.gettingstarted.RabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * @author Suz1
 * @date 2020/6/27 1:27 下午
 */
public class Provider {
    public static void main(String[] args) throws IOException {
        Connection conn = RabbitMqUtils.getConn();
        assert conn != null;
        Channel channel = conn.createChannel();

        // 将通道声明指定的交换机-不存在就创建
        channel.exchangeDeclare("fan-out-exchange", "fanout");
        // send message
        channel.basicPublish("fan-out-exchange", "", null, "fan out type message".getBytes());
        // release conn
        RabbitMqUtils.closeConnAndChannel(channel, conn);
    }
}
