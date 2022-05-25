package rocketmq.base;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import rocketmq.constant.ConfigConst;


/**
 * 消息的同步样例，可靠,注重消息的可靠性。重要的消息，短信通知
 *
 * @author Suz1
 * @date 2020/12/20 12:12 上午
 */
public class SyncProducer {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        // 1. 指定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("first_group");
        // 2. 指定 nameserver 地址
        producer.setNamesrvAddr(ConfigConst.REMOTE_ROCKET_MQ_NAMESRV_ADDR);
        // 3. 启动 producer
        producer.start();
        for (int i = 0; i < 100; i++) {
            // 4. 创建消息对象，指定主题 topic tag 和消息体
            Message message = new Message("test_topic_name", "tag_name_1", ("first_msg, No_" + i).getBytes());
            // System.out.println(JSON.toJSONString(message, true));
            // 5. 发送消息
            SendResult send = producer.send(message);
            System.out.println(JSON.toJSONString(send, true));
        }
        // 6. 关闭消费者
        producer.shutdown();
    }
}
