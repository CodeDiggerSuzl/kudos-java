package rocketmq.base.transaction;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import rocketmq.constant.ConfigConst;

import java.util.concurrent.TimeUnit;

/**
 * 十事务性消息生产者
 *
 * @author Suz1
 * @date 2020/12/20 6:02 下午
 */
public class Producer {
    public static void main(String[] args) throws MQClientException {
        TransactionMQProducer producer = new TransactionMQProducer("first_group");
        producer.setNamesrvAddr(ConfigConst.ROCKET_MQ_NAMESRV_ADDR);
        String[] tags = new String[]{"TagA", "TagB", "TagC"};
        // 设置消息事务的监听器
        producer.setTransactionListener(new TransactionListener() {
            /**
             * 执行本地事务
             */
            @Override
            public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
                String tag = msg.getTags();
                if (tags[0].equals(tag)) {
                    return LocalTransactionState.COMMIT_MESSAGE;
                } else if (tags[1].equals(tag)) {
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                } else {
                    return LocalTransactionState.UNKNOW;
                }
            }

            /**
             * 该方法 mq 进行消息事务状态的回查
             */
            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt msg) {
                System.out.println("消息回查：消息的 tag: " + msg.getTags());
                return LocalTransactionState.COMMIT_MESSAGE;
            }
        });

        producer.start();

        int length = tags.length;
        for (int i = 0; i < length; i++) {
            try {
                Message msg = new Message("TransactionTopic", tags[i % length], "KEY" + i, ("Hello RocketMQ " + tags[i]).getBytes());
                // 发送事务消息
                SendResult sendResult = producer.sendMessageInTransaction(msg, null);
                System.out.println(sendResult.getMsgId());
                TimeUnit.SECONDS.sleep(1);
            } catch (MQClientException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        //  producer.shutdown();
    }
}
