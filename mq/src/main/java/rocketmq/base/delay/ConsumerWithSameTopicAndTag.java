package rocketmq.base.delay;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import rocketmq.constant.ConfigConst;

/**
 * @author Suz1
 * @date 2020/12/20 4:57 下午
 */
public class ConsumerWithSameTopicAndTag {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("first_group");
        consumer.setNamesrvAddr(ConfigConst.ROCKET_MQ_NAMESRV_ADDR);
        // 订阅 topic
        consumer.subscribe("topic_delay", "tag_delay_tag");
        // 设置消费负载均衡模式
        // consumer.setMessageModel(MessageModel.CLUSTERING);
        // 广播消费模式
        consumer.setMessageModel(MessageModel.BROADCASTING);
        // 回调函数
        consumer.registerMessageListener((MessageListenerConcurrently) (msg, context) -> {
            for (MessageExt m : msg) {
                System.out.println("消息 id" + m.getMsgId() + ",延迟时间：" + (System.currentTimeMillis() - m.getStoreTimestamp()));
                System.out.println(new String(m.getBody()));
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        consumer.start();
    }
}
