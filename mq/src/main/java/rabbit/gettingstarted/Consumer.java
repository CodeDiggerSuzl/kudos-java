package rabbit.gettingstarted;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author Suz1
 * @date 2020/6/27 11:19 下午
 */
public class Consumer {
    public static void main(String[] args) throws IOException {
        Connection conn = RabbitMqUtils.getConn();
        assert conn != null;
        Channel channel = conn.createChannel();
        channel.exchangeDeclare("topic-exchange", "topic");
        String queue = channel.queueDeclare().getQueue();
        // bind queue and exchange
        channel.queueBind(queue, "topic-exchange", "*.save");
        channel.basicConsume(queue, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("get message from topic routing:" + new String(body));
            }
        });
    }
}
