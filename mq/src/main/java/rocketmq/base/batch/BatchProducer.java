package rocketmq.base.batch;

import com.google.common.collect.Lists;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import rocketmq.constant.ConfigConst;

import java.util.ArrayList;

/**
 * @author Suz1
 * @date 2020/12/20 5:11 下午
 */
public class BatchProducer {
    public static void main(String[] args) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("first_group");
        producer.setNamesrvAddr(ConfigConst.REMOTE_ROCKET_MQ_NAMESRV_ADDR);
        producer.start();
        ArrayList<Message> list = Lists.newArrayList();
        Message msg1 = new Message("topic_batch", "tag_batch", ("batch_test_1").getBytes());
        Message msg2 = new Message("topic_batch", "tag_batch", ("batch_test_2").getBytes());
        Message msg3 = new Message("topic_batch", "tag_batch", ("batch_test_3").getBytes());
        list.add(msg1);
        list.add(msg2);
        list.add(msg3);
        SendResult send = producer.send(list);
        producer.shutdown();
    }
}
