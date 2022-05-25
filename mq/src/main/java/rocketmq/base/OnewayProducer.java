package rocketmq.base;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import rocketmq.constant.ConfigConst;

/**
 * 单向消息
 *
 * @author Suz1
 * @date 2020/12/20 2:30 下午
 */
public class OnewayProducer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException {
        // 1. 指定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("first_group");
        // 2. 指定 nameserver 地址
        producer.setNamesrvAddr(ConfigConst.REMOTE_ROCKET_MQ_NAMESRV_ADDR);
        // 3. 启动 producer
        producer.start();
        for (int i = 0; i < 10; i++) {
            // 4. 创建消息对象，指定主题 topic tag 和消息体
            Message message = new Message("test_topic_name", "tag_name_1", ("oneway_msg, No_" + i).getBytes());
            // 5. 发送消息
            producer.sendOneway(message);
        }
        // 6. 关闭消费者
        producer.shutdown();
    }
}
