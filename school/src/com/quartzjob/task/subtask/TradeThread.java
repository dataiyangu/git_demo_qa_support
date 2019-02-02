package com.quartzjob.task.subtask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.quartzjob.task.bean.TradeType;
import com.quartzjob.task.service.TradeService;


/**
 * multiplex used thread
 * 
 * @author leo
 *
 */
public class TradeThread implements Runnable {
	
	private static final Log log = LogFactory.getLog(TradeThread.class);
	
	private Thread thread;
	private boolean running = false;
	private volatile boolean stop = false;
	
	private String plat;
	private String taskType;
	private String taskName;
	private String threadName;
	private String profileName;
	private String threadService;
	private Long config; 
	
	public static void main(String[] args) {
		
	}
	
	public TradeThread(String plat, String taskType, String taskName, String threadName, String profileName, String threadService,Long config){
		this.plat = plat;
		this.taskType = taskType;
		this.taskName = taskName;
		this.threadName = threadName;
		this.profileName = profileName;
		this.threadService = threadService;
		this.config = config;
	}
	
	public static TradeThread newInstanceThread(String plat, String taskType, String taskName, String threadName, String profileName, String threadService,Long config){
//		System.out.println("- create ->" + Thread.currentThread().getName());
		return new TradeThread(plat, taskType, taskName, threadName, profileName, threadService, config);
	}
	
	
	public void start() {
		synchronized (this) {
			if (thread == null) {
				try {
					thread = new Thread(this);
//					thread.setName(threadName);
					thread.setDaemon(true);
					thread.start();
					running = true;
				} catch (Exception e) {
					log.error("Error starting datasend -> {}" + e);
				}
			}
		}
	}
	
	public boolean isAlive(){
		return this.running;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		TradeService tradeService = new TradeService();
		while (!Thread.currentThread().isInterrupted() && !stop) {
			try {
				
				int taskId = TradeType.equals(taskType);
				switch (taskId) {
					case 0:
						tradeService.getInc(taskName, taskType, taskId);
						break;
					case 1:
						tradeService.saveInc(taskName, taskType, taskId);
						break;
					case 2:
						tradeService.getAll(taskName, taskType, taskId);
						break;
					case 3:
						tradeService.saveAll(taskName, taskType, taskId);
						break;
	
//					default:
//						tradeService.getAll(taskName, taskType, taskId);
//						break;
				}
				
				
				setThreadName(Thread.currentThread().getName());
				
				log.info("threadName ->"+threadName + "--state->" +Thread.currentThread().getState()
						+ "\n tID->" + Thread.currentThread().getId()
						+ " thashcode->" + Thread.currentThread().hashCode());
			
			} catch (Exception ex) {
				log.error("--running error ->" + ex);
			}

			try {
				Thread.sleep(1 * 1000);
				this.stop();
			} catch (InterruptedException ex) {
				log.error("--sleep error ->" + ex);
			}
		}
	}
	
	public void stop() {
		this.stop = true;
		this.running = false;
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	public String getPlat() {
		return plat;
	}

	public void setPlat(String plat) {
		this.plat = plat;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public String getThreadService() {
		return threadService;
	}

	public void setThreadService(String threadService) {
		this.threadService = threadService;
	}

	public Long getConfig() {
		return config;
	}

	public void setConfig(Long config) {
		this.config = config;
	}
}
