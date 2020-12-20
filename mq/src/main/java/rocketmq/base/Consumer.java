package rocketmq.base;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import rocketmq.constant.ConfigConst;

/**
 * 消息的消费者
 *
 * @author Suz1
 * @date 2020/12/20 2:36 下午
 */
public class Consumer {
    /**
     * 1. 创建消费者 Consumer，制定消费者组名
     * 2. 指定 Nameserver 地址
     * 3. 订阅主题 Topic 和 Tag
     * 4. 设置回调函数，处理消息
     * 5. 启动消费者 consumer
     */
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("first_group");
        consumer.setNamesrvAddr(ConfigConst.ROCKET_MQ_NAMESRV_ADDR);
        // 订阅 topic
        consumer.subscribe("test_topic_name", "*");
        // 设置消费负载均衡模式
        // consumer.setMessageModel(MessageModel.CLUSTERING);
        // 广播消费模式
        consumer.setMessageModel(MessageModel.BROADCASTING);
        // 回调函数
        consumer.registerMessageListener((MessageListenerConcurrently) (msg, context) -> {
            for (MessageExt ext : msg) {
                System.out.println(new String(ext.getBody()));
            }
            // System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msg.toString());
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        consumer.start();
    }
}
