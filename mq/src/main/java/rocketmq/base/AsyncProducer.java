package rocketmq.base;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import rocketmq.constant.ConfigConst;

import java.util.concurrent.TimeUnit;

/**
 * mq 发送 异步消息
 *
 * @author Suz1
 * @date 2020/12/20 2:05 下午
 */
public class AsyncProducer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("first_group");
        producer.setNamesrvAddr(ConfigConst.ROCKET_MQ_NAMESRV_ADDR);
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(100);
        for (int i = 0; i < 10; i++) {
            Message message = new Message("test_topic_name", "tag_name_1", ("async_msg, No_" + i).getBytes());
            // 异步回调函数
            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println(JSON.toJSONString(sendResult, true));
                }

                @Override
                public void onException(Throwable throwable) {
                    Throwable cause = throwable.getCause();
                    System.out.println("cause.toString() = " + cause.toString());
                }
            });
            TimeUnit.MILLISECONDS.sleep(10);
        }
        producer.shutdown();
    }
}
