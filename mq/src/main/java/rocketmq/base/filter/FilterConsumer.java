package rocketmq.base.filter;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import rocketmq.constant.ConfigConst;

import java.util.List;

/**
 * 1. 使用 tag 进行过滤
 * <p></p>
 * 2. 使用 sql 进行过滤
 * <p>
 * 消息发送的时候设置属性： msg.putUserProperty("a", String.valueOf(i));
 * <br>
 * 消费的时候： consumer.subscribe("TopicTest", MessageSelector.bySql("a between 0 and 3");
 * </p>
 *
 * @author Suz1
 * @date 2020/12/20 5:32 下午
 */
public class FilterConsumer {
    public static void main(String[] args) throws MQClientException {
        // 使用 tag 过滤
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("first_group");
        consumer.setNamesrvAddr(ConfigConst.REMOTE_ROCKET_MQ_NAMESRV_ADDR);
        consumer.subscribe("topic_batch", "tag_batch");
        consumer.registerMessageListener(new MessageListenerConcurrently() {

            /**
             * It is not recommend to throw exception,rather than returning ConsumeConcurrentlyStatus.RECONSUME_LATER if
             * consumption failure
             *
             * @param msgs    msgs.size() >= 1<br> DefaultMQPushConsumer.consumeMessageBatchMaxSize=1,you can modify here
             * @param context
             * @return The consume status
             */
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
                    System.out.println(new String(msg.getBody()));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
    }
}
