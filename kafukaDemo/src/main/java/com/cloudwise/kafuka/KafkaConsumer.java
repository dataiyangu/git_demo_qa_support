 package com.cloudwise.kafuka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.consumer.ZookeeperConsumerConnector;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.javaapi.consumer.SimpleConsumer;
import kafka.serializer.StringDecoder;
import kafka.utils.VerifiableProperties;

public class KafkaConsumer {

    private final ConsumerConnector consumer;
//    SimpleConsumer sa ;
    
    private KafkaConsumer() {
        Properties props = new Properties();
//        sa =new SimpleConsumer("10.0.3.153", 12, 12, 12, "a");
//       String a = sa.host();
//       System.out.println(a);

       //zookeeper 配置
        props.put("zookeeper.connect", "10.0.1.48:2181");
//        props.put("metadata.broker.list", "10.0.1.48:9092,10.0.1.49:9092");

//        props.put("zookeeper.connect", "192.168.193.148:2181");

        //group 代表一个消费组
        props.put("group.id", "jd-group");

        //zk连接超时
        props.put("zookeeper.session.timeout.ms", "4000");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");
        props.put("auto.offset.reset", "smallest");
        //序列化类
        props.put("serializer.class", "kafka.serializer.StringEncoder");

        ConsumerConfig config = new ConsumerConfig(props);

        consumer = kafka.consumer.Consumer.createJavaConsumerConnector(config);
    }

     void consume() {
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(KafkaProducer.TOPIC, new Integer(1));

        StringDecoder keyDecoder = new StringDecoder(new VerifiableProperties());
        StringDecoder valueDecoder = new StringDecoder(new VerifiableProperties());
        Map<String, List<KafkaStream<String, String>>> consumerMap = 
                consumer.createMessageStreams(topicCountMap,keyDecoder,valueDecoder);
//        int i =0;
//        int j=10;
//        int b =i/j;
//        System.out.println(b);
        KafkaStream<String, String> stream = consumerMap.get(KafkaProducer.TOPIC).get(0);
        ConsumerIterator<String, String> it = stream.iterator();

        while (it.hasNext())
            System.out.println(it.next().message());
    }
    public static void main(String[] args) {
        new KafkaConsumer().consume();
    }
}
