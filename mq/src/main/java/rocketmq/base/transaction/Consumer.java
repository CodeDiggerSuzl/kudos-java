package rocketmq.base.transaction;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import rocketmq.constant.ConfigConst;

/**
 * 事务消息消费者
 *
 * @author Suz1
 * @date 2020/12/20 6:16 下午
 */
public class Consumer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("first_group");
        consumer.setNamesrvAddr(ConfigConst.REMOTE_ROCKET_MQ_NAMESRV_ADDR);
        // 订阅 topic
        consumer.subscribe("TransactionTopic", "*");
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
