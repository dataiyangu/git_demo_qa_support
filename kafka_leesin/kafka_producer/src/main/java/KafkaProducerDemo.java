import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaProducerDemo {
	private  final Producer<String,String> kafkaProducer;

	public final static String TOPIC="my-topic";

	private KafkaProducerDemo(){
		kafkaProducer=createKafkaProducer() ;
	}
	private Producer<String,String> createKafkaProducer(){
		Properties props = new Properties();
		props.put("bootstrap.servers", "10.0.1.224:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		Producer<String, String> kafkaProducer = new KafkaProducer<String,String>(props);
		return kafkaProducer;
	}

	void produce(){
		for(int i=1;i<2;i++){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			String key=String.valueOf("key"+i);
			String data="hello kafka message:"+key;
			kafkaProducer.send(new ProducerRecord<String,String>(TOPIC, key, data), new Callback() {
				public void onCompletion(RecordMetadata recordMetadata, Exception e) {
					//do sth
				}
			});


			System.out.println(data);
		}
	}

	public  static  void main(String[] args){
		new KafkaProducerDemo().produce();
	}
}
