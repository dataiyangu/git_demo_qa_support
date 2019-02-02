package com.ilucky.quartz;

import java.util.Random;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

/**
 * 针对捕获的异常是抓不到的
 * @author IluckySi
 *
 */
public class HelloJob1 implements Job{
	
    /**
     * 可以抓获到异常
     */
//	public void execute(JobExecutionContext context) throws JobExecutionException {
//	      Random r = new Random();
//        int i = r.nextInt(2);
//        System.out.println(10/i);
//        System.out.println("Hello Quartz!");
//	}
	
    /**
     * 不可以抓获到异常
     */
//	public void execute(JobExecutionContext context) {
//	    try {
//	        Random r = new Random();
//	        int i = r.nextInt(2);
//	        System.out.println(10/i);
//	        System.out.println("Hello Quartz!");
//	    } catch (Exception e) {
//	      e.printStackTrace();  
//	    }
//    }
	
	/**
	 * 可以捕获到异常
     */
    public void execute(JobExecutionContext context) {
        try {
            Random r = new Random();
            int i = r.nextInt(2);
            System.out.println(10/i);
            System.out.println("Hello Quartz!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
