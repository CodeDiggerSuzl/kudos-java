package rocketmq.base.ordered;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import rocketmq.constant.ConfigConst;

import java.util.List;

/**
 * @author Suz1
 * @date 2020/12/20 4:22 下午
 */
public class OrderConsumer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer pushConsumer = new DefaultMQPushConsumer("ordered_group_id");
        pushConsumer.setNamesrvAddr(ConfigConst.REMOTE_ROCKET_MQ_NAMESRV_ADDR);
        pushConsumer.subscribe("OrderTopic", "*");
        // 注册消息监听器
        pushConsumer.registerMessageListener(new MessageListenerOrderly() {

            @Override // 每个队列采用一个线程去消费
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                for (MessageExt msg : msgs) {
                    Thread thread = Thread.currentThread();
                    System.out.println("线程名称" + thread.getName());
                    System.out.println("消费消息：" + new String(msg.getBody()));
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        pushConsumer.start();
        System.out.println("consumer start......");
    }
}
