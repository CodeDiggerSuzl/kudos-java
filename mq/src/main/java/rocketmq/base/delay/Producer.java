package rocketmq.base.delay;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import rocketmq.constant.ConfigConst;

/**
 * @author Suz1
 * @date 2020/12/20 4:57 下午
 */
public class Producer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        // 1. 指定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("first_group");
        // 2. 指定 nameserver 地址
        producer.setNamesrvAddr(ConfigConst.ROCKET_MQ_NAMESRV_ADDR);
        // 3. 启动 producer
        producer.start();
        for (int i = 0; i < 10; i++) {
            // 4. 创建消息对象，指定主题 topic tag 和消息体
            Message message = new Message("topic_delay", "tag_delay_tag", ("delayMsg, No_" + i).getBytes());
            //  设置延时
            message.setDelayTimeLevel(1);
            // 5. 发送消息
            SendResult send = producer.send(message);
            System.out.println(JSON.toJSONString(send, true));
        }
        // 6. 关闭消费者
        producer.shutdown();
    }
}
