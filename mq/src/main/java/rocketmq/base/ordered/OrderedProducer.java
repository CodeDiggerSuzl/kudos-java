package rocketmq.base.ordered;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;
import rocketmq.constant.ConfigConst;

import java.util.List;

/**
 * 顺序生产者
 *
 * @author Suz1
 * @date 2020/12/20 3:55 下午
 */
public class OrderedProducer {
    /**
     * 订单流程：创建，付款，推送，完成 顺序一致
     */
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("ordered_group_id");
        producer.setNamesrvAddr(ConfigConst.ROCKET_MQ_NAMESRV_ADDR);
        producer.start();
        String[] tags = {"TagA", "TagB", "TagC"};
        List<OrderStep> orderSteps = OrderStep.buildOrders();

        // send msg
        for (OrderStep step : orderSteps) {
            Message msg = new Message("OrderTopic", "Order", (step.toString()).getBytes());
            // 1. 消息对象
            // 2. 选择器
            // 3. 选择队列的业务标识
            SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                /**
                 *
                 * @param mqs 所有的队列集合
                 * @param msg 消息对象
                 * @param arg 业务的表示参数，即下面的 order id
                 */
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    long orderId = (long) arg;
                    // 使用取模来获取特定的队列
                    long idx = orderId % mqs.size();
                    return mqs.get((int) idx);
                }
            }, step.getOrderId());
        }
        producer.shutdown();
    }
}
