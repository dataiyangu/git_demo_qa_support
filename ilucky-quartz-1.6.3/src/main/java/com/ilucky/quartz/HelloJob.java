package com.ilucky.quartz;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.ilucky.bean.Message;
import com.ilucky.pool.ThreadPoolFactory;

/**
 * package org.quartz;
 * public abstract interface Job
{
  public abstract void execute(JobExecutionContext paramJobExecutionContext)
    throws JobExecutionException;
}
 * @author IluckySi
 * 
 * quartz 1.6.3
 */
public abstract class HelloJob implements Job{
	
	protected ThreadPoolExecutor executor;
	
    public HelloJob() {
		super();
		if(this.executor == null)
			this.executor = ThreadPoolFactory.getExecutor();
	}
	

	public void execute(JobExecutionContext context) throws JobExecutionException {
       
        System.out.println("Hello Quartz!");
        
        Message msg = new Message();
		msg.setConsumer("consumer");
		msg.setProducer("producer");
		msg.setId("test_1");
        
        executor.execute(new TaskSendMsg(msg));
    }
	
	public abstract void sendMessage(Message msg);
		
	class TaskSendMsg implements Runnable{
		private final Message message;
		

		public TaskSendMsg(Message message) {
			super();
			this.message = message;
		}


		public void run() {
			// TODO Auto-generated method stub
			try{
				 HelloJob.this.sendMessage(message);
			}catch(Exception e){
				System.out.println("ERROR!");
				e.printStackTrace();
			}
			
		}
		
	}
}