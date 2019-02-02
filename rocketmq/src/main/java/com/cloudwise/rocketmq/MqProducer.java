package com.cloudwise.rocketmq;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
//import org.apache.log4j.Logger;

/**
 * aaron
 * @date：2018/8/23 11:11
 */
public class MqProducer {
//    private static Logger logger = Logger.getLogger(MqProducer.class);
    public static void main(String[] args) {
        DefaultMQProducer producer = new DefaultMQProducer("ProducerA");
        producer.setNamesrvAddr("10.0.5.100:9876");
        try {
            producer.start();
            System.out.println("producer启动成功");
//            logger.info("producer启动成功");
       //     for (int i = 0; i < 5; i++) {
            
                Message msg = new Message("TopicB", "tagB", "OrderID188", "Hello world".getBytes());
                msg.getProperties().put("TEST","TEST");
                msg.putUserProperty("USER", "TTTT");
                SendResult result = producer.send(msg);
              //  producer.
                System.out.println("id：" + result.getMsgId() + " result:" + result.getSendStatus());
       //     }
        } catch (Exception e) {
            System.out.println("发送消息失败，Exception error：" + e);
        } finally {
            producer.shutdown();
        }
    }
}
