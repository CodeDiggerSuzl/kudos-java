package rabbit.gettingstarted.workqueue;

import rabbit.gettingstarted.RabbitMqUtils;
import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author Suz1
 * @date 2020/6/26 4:02 下午
 */
@Slf4j
public class Consumer1 {
    public static void main(String[] args) throws IOException {
        Connection conn = RabbitMqUtils.getConn();
        assert conn != null;
        Channel channel = conn.createChannel();
        channel.basicQos(1);
        channel.queueDeclare("workQueue", true, false, false, null);
        channel.basicConsume("workQueue", false, new DefaultConsumer(channel) {

            // 消费者 1
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("consumer--1 get message:" + new String(body));
                // 手动确认 参数 1 手动确认消息标识 参数 2 每次确定一个
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
