package com.ilucky.quartz;

import java.util.Random;

import com.ilucky.bean.Message;

//import java.util.concurrent.ThreadPoolExecutor;


public class HelloJobImpl extends HelloJob {



	@Override
	public void sendMessage(Message msg) {
		// TODO Auto-generated method stub
			 Random r = new Random();
		        int i = r.nextInt(2);
		        System.out.println(10/i);
			System.out.println("Thread is Sending msg : " + msg.getId() 
					+ "--" + msg.getProducer() + "--" + msg.getConsumer());
		
		
	}

}
