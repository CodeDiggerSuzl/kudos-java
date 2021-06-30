package rabbit.gettingstarted.fanout;

import rabbit.gettingstarted.RabbitMqUtils;
import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author Suz1
 * @date 2020/6/27 6:55 下午
 */
@Slf4j
public class Consumer2 {
    public static void main(String[] args) throws IOException {
        Connection conn = RabbitMqUtils.getConn();
        assert conn != null;
        Channel channel = conn.createChannel();
        // 绑定交换机
        channel.exchangeDeclare("fan-out-exchange", "fanout");
        // 创建临时对列
        String queue = channel.queueDeclare().getQueue();
        // binding the exchange and queue
        channel.queueBind(queue, "fan-out-exchange", "");
        // 消费消息
        channel.basicConsume(queue, true, new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body));
            }
        });
    }
}
