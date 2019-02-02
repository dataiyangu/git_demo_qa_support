package com.cloudwise.rocketmq;

import com.alibaba.rocketmq.client.consumer.DefaultMQPullConsumer;
import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerOrderly;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.impl.consumer.ConsumeMessageConcurrentlyService;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;







//import org.apache.log4j.Logger;
import java.util.List;

import javax.xml.ws.Service;

import sun.print.resources.serviceui;

/**
 * aaron
 * @date：2018/8/23 11:11
 * 
 * 
 * 1. pushconsumer pullconsumer 区别
 */
public class MqConsumer {
//    private static Logger logger = Logger.getLogger(MqConsumer.class);

    public static void main(String[] args) {
    	
    	test1();
//    	test2();
    	
    }
    
    public static void test1() {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("PushConsumer_yll");
        consumer.setNamesrvAddr("10.0.5.100:9876");
        try {
            consumer.subscribe("TopicA", "tagA||tagB");//可订阅多个tag，但是一个消息只能有一个tag
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumer.registerMessageListener(new MessageListenerConcurrently() {
            	ConsumeMessageConcurrentlyService service;
            	
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {


                	Message msg = list.get(0);
//                	String a =msg.getBuyerId();
//                    System.out.println("a="+a);

                    System.out.println(msg.toString());
                    
//                    int i =0;
//                    System.out.println(i);
//                    int j =10/i;
//                    System.out.println(j);
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            consumer.start();
        } catch (Exception e) {
        	e.printStackTrace();
        }
   }
    
       public static void test2() {
	    // Order
    	 DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("PushConsumer_yll");
         consumer.setNamesrvAddr("10.0.5.128:9876");
         try {
             consumer.subscribe("TopicA", "tagA||tagB");//可订阅多个tag，但是一个消息只能有一个tag
             consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
             consumer.registerMessageListener(new MessageListenerOrderly() {
				
				public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs,
						ConsumeOrderlyContext context) {
					 	Message msg = msgs.get(0);
	                  System.out.println(msg.toString());
	                  return ConsumeOrderlyStatus.SUCCESS;
				}
			});
	      consumer.start();
	     System.out.println("consumer启动成功");
		  } catch (MQClientException e) {
		      System.out.println("消费者订阅消息失败，error：" + e);
		 }
     }
}
