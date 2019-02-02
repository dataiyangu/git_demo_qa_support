package com.quartzjob.task.subtask;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionException;

import com.utils.ConnectUtil;

public class CommonThread implements Runnable {

	private static final Log log = LogFactory.getLog(CommonThread.class);
	
	
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
	
	public CommonThread(String plat, String taskType, String taskName, String threadName, String profileName, String threadService,Long config){
		this.plat = plat;
		this.taskType = taskType;
		this.taskName = taskName;
		this.threadName = threadName;
		this.profileName = profileName;
		this.threadService = threadService;
		this.config = config;
	}
	
	public static CommonThread newInstanceThread(String plat, String taskType, String taskName, String threadName, String profileName, String threadService,Long config){
		System.out.println("- create ->" + Thread.currentThread().getName());
		return new CommonThread(plat, taskType, taskName, threadName, profileName, threadService, config);
	}
	
	
	public void getStudentList(String plat, String taskType, String taskName, String threadName, String profileName, String threadService,Long config){
		Connection conn = null;
		String sql = "";
		ResultSet rs = null;
		String name = "default";
		String id = "1";
		if (id == null || "".equals(id)) {
			id = "1";
		}
		PreparedStatement stmt = null;
//		Statement stmt = null;
//		log.info("commontread name ->" + Thread.currentThread().getName() + "--group->"
//				+ Thread.currentThread().getThreadGroup().getName());
		try {
			Thread.currentThread();
			Thread.sleep(3000);
//			conn = DBUtils.getConnection();
			conn = ConnectUtil.getConnect();
//			stmt = conn.createStatement();
			sql = " SELECT tt.cname FROM classes tt  WHERE tt.CID=? and ?=? and ?=?";
//			sql = " SELECT tt.cname FROM classes tt  WHERE tt.daisy=? and ?=? and ?=?";
//			sql = "select st.sname from t_student st where st.sid ="+id;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.setLong(2, 2);
			stmt.setLong(3, 2);
			stmt.setLong(4, 3);
			stmt.setLong(5, 3);
			rs = stmt.executeQuery();
//			rs = stmt.executeQuery(sql);
			
			while (rs.next()){
				name = rs.getString(1);
			}
			rs.close();
			stmt.close();
            conn.close();
            Long sqlend;
//            sqlend = 1L/0L;
            log.info("commontread query name --" +name + "--config-->"+config);    
		} catch (Exception e) {
			System.err.println("--getStr-->"+e);
//			e.printStackTrace();
			JobExecutionException e2 = new JobExecutionException(e);
			e2.setUnscheduleAllTriggers(true);
			e2.printStackTrace();
		}
		stop();
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
		while (!Thread.currentThread().isInterrupted() && !stop) {
			try {
				
				getStudentList(plat, taskType, taskName, threadName, profileName, threadService, config);
				setThreadName(Thread.currentThread().getName());
				
				log.info("threadName ->"+threadName + "--state->" +Thread.currentThread().getState()
						+ "\n tID->" + Thread.currentThread().getId()
						+ " thashcode->" + Thread.currentThread().hashCode());
			
			} catch (Exception ex) {
				log.error("--running error ->" + ex);
			}

			try {
				Thread.sleep(3 * 1000);
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
