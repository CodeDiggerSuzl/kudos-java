package rabbit.gettingstarted.workqueue;

import rabbit.gettingstarted.RabbitMqUtils;
import com.rabbitmq.client.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author Suz1
 * @date 2020/6/26 4:02 下午
 */
@Slf4j
public class Consumer2 {
    public static void main(String[] args) throws IOException {
        Connection conn = RabbitMqUtils.getConn();
        assert conn != null;
        Channel channel = conn.createChannel();
        channel.basicQos(1);
        channel.queueDeclare("workQueue", true, false, false, null);
        channel.basicConsume("workQueue", false, new DefaultConsumer(channel) {

            // 消费者 1
            @SneakyThrows
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("consumer--2 get message:" + new String(body));
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
