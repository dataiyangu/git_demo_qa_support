package com.quartzjob.simple;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionException;

import com.quartzjob.task.subtask.CommonThread;
import com.quartzjob.task.subtask.SecCommonThread;
import com.quartzjob.task.subtask.TradeThread;
import com.utils.ConnectUtil;

import sun.net.www.protocol.http.HttpURLConnection;


public class TestQuartzJob{

	private static final Log log = LogFactory.getLog(TestQuartzJob.class);
	
	private Long apistart;
	private Long apiend;
	private Long sqlstart;
	private Long sqlend;
	private String threadName;
	
	public void checkLine()
	{	
		// 我们的任务执行体
//		long i2 = 1/0;
		List<CommonThread> list= new ArrayList<CommonThread>();
		List<SecCommonThread> list2= new ArrayList<SecCommonThread>();
		System.out.println("execute task 1");
		threadName = Thread.currentThread().getName();
		log.info("execute task 1 -- START of ->" + threadName);
		String str = "fox";
		Long lfox = 249L;
		for (int i = 0; i < 3; i++) {
			str = str + i;
			lfox += i;
			CommonThread thread = CommonThread.newInstanceThread(threadName, "Line", str, threadName, str, str, lfox);
			list.add(thread);
			log.info("-thc->" + thread.hashCode());
			thread.start();
		}
		
//		for (int i = 0; i < 3; i++) {
//			str = str + i;
//			lfox += i;
//			SecCommonThread thread = SecCommonThread.newInstanceThread(threadName, "Line2", str, threadName, str, str, lfox + 100);
//			list2.add(thread);
//			log.info("-thc->" + thread.hashCode());
//			thread.start();
//		}
//		
		log.info("execute task 1 -- Middle of ->" + Thread.currentThread().getName()
				+ "\n tID->" + Thread.currentThread().getId()
				+ " thashcode->" + Thread.currentThread().hashCode());
		int count = 0;
		while (1==1) {
			Boolean flag=false;
			if(list!=null&&list.size()>0){
				for(int ix=0;ix<list.size();ix++){
					CommonThread thread=(CommonThread) list.get(ix);
					if(thread.isAlive()){
						flag=true;
//						log.info("execute task 1 -- stop ->" + thread.getThreadName());
//						thread.stop();
					}else{
//						Connection conn=(Connection) conlist.get(ix);
//						OpenApiContants.close(null, conn);
					}
				}
			}
			if(list2!=null&&list2.size()>0){
				for(int ix=0;ix<list2.size();ix++){
					SecCommonThread thread=(SecCommonThread) list2.get(ix);
					if(thread.isAlive()){
						flag=true;
					}else{
					}
				}
			}
			if(!flag){
				break;
			}else{
				count++;
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		list=null;
		log.info("execute task 1 -- END of ->" + Thread.currentThread().getName() + "  -c->" + count);
	}

	public void  checkClient()
	{	
		 //我们的任务执行体
		 String validateURL = "http://10.0.3.90:4449/school/student/allStudent.action";
		 validateURL = "http://www.tingyun.com/";
		 validateURL = "http://www.baidu.com/";
//		 validateURL = "http://10.0.5.145:8101/strutsTest/Hello.do?userName=123";
		 HttpURLConnection conn = null;
		 System.out.println("execute task 2");
		 apistart = System.currentTimeMillis();
		 log.info("execute task 2 -- start >"+new Date(apistart));
		 DataInputStream dis = null;
		 BufferedInputStream ois = null;
		 byte[] b = new byte[1024];
		 try {
		       URL url = new URL(validateURL); //创建URL对象
		       //返回一个URLConnection对象，它表示到URL所引用的远程对象的连接
		       conn = (HttpURLConnection) url.openConnection();
		       conn.setConnectTimeout(5000); //设置连接超时为5秒
		       conn.setRequestMethod("POST"); //设定请求方式
		       conn.connect(); //建立到远程对象的实际连接
		       //返回打开连接读取的输入流
//		       DataInputStream dis = new DataInputStream(conn.getInputStream());  
		       ois = new BufferedInputStream(conn.getInputStream());
			   ois.read(b);
			   
//			   url = new URL("http://10.0.5.176:8090/school/student/allStudent.action");
//			   conn = (HttpURLConnection)url.openConnection();
//			   conn.setConnectTimeout(5000);
//			   conn.setRequestMethod("POST");
//			   conn.connect();
//			   
//			   url = new URL("http://10.0.5.176:8090/school/student/index.jsp");
//			   conn = (HttpURLConnection)url.openConnection();
//			   conn.setConnectTimeout(5000);
//			   conn.setRequestMethod("POST");
//			   conn.connect();
		       //判断是否正常响应数据 
		        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
		           System.out.println("newwork error exception!!!");
//		           return  false;
		       }
//		       apiend = 1L/0L;
		} catch (Exception e) {
//		   e.printStackTrace();
		   System.out.println("this is exception!");
		   JobExecutionException e2 = new JobExecutionException(e);
		   e2.setRefireImmediately(true);
		   e2.printStackTrace();
		} finally {
		    if (conn != null) {
		     conn.disconnect(); //中断连接
		    }
		 }
		 apiend = System.currentTimeMillis();
		 log.info("execute task 2 -- end >"+new Date(apiend)
		 +"\t exec time:"+(apiend - apistart));
		 
	}

	public void checksql(){
		
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
		sqlstart = System.currentTimeMillis();
		log.info("execute task 3 -- start >"+new Date(sqlstart));
		try {
			Thread.currentThread();
			Thread.sleep(5000);
//			conn = DBUtils.getConnection();
			conn = ConnectUtil.getConnect();
//			stmt = conn.createStatement();
			sql = " SELECT tt.cname FROM classes tt  WHERE tt.CID=? and ?=? and ?=?";
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
            
//            sqlend = 1L/0L;
            
		} catch (Exception e) {
			System.err.println("--getStr-->"+e);
//			e.printStackTrace();
			JobExecutionException e2 = new JobExecutionException(e);
			e2.setUnscheduleAllTriggers(true);
			e2.printStackTrace();
		}
		sqlend = System.currentTimeMillis();
		System.err.println("execute task 3");
		log.info("execute task 3 -- end >"+name+"---->"+new Date(sqlend)
		 +"\t exec time:"+(sqlend - sqlstart));
		
	}
	
	
	public void checkfouth()
	{	
		//
		List<TradeThread> list= new ArrayList<TradeThread>();
//		System.out.println("execute task 4");
		threadName = Thread.currentThread().getName();
		log.info("execute task 4 -- START of ->" + threadName);
		String str = "fox";
		Long lfox = 249L;
		for (int i = 0; i < 3; i++) {
			str = str + i;
			if (i==0) {
				str = "incInfo";
			}else if (i == 1) {
				str = "saveInc";
			}else if (i == 2){
				str = "all";
			}
			lfox += i;
			TradeThread thread = TradeThread.newInstanceThread(i+"", str, str, threadName, "fouth", str, lfox);
			list.add(thread);
//			log.info("-thc->" + thread.hashCode());
			thread.start();
		}
		
//		log.info("execute task 4 -- Middle of ->" + Thread.currentThread().getName()
//				+ "\n tID->" + Thread.currentThread().getId()
//				+ " thashcode->" + Thread.currentThread().hashCode());
		int count = 0;
		while (1==1) {
			Boolean flag=false;
			if(list!=null&&list.size()>0){
				for(int ix=0;ix<list.size();ix++){
					TradeThread thread=(TradeThread) list.get(ix);
					if(thread.isAlive()){
						flag=true;
//						log.info("execute task 4 -- stop ->" + thread.getThreadName());
//						thread.stop();
					}else{
//						Connection conn=(Connection) conlist.get(ix);
//						OpenApiContants.close(null, conn);
					}
				}
			}
			if(!flag){
				break;
			}else{
				count++;
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		list=null;
		System.out.println("execute task 4");
		log.info("execute task 4");
	}
	
	
	public void checkfifth()
	{	
		//
		List<TradeThread> list= new ArrayList<TradeThread>();
//		System.out.println("execute task 5");
		threadName = Thread.currentThread().getName();
		log.info("execute task 5 -- START of ->" + threadName);
		String str = "fox";
		Long lfox = 249L;
		for (int i = 0; i < 1; i++) {
			str = "saveAll";
			lfox += i;
			TradeThread thread = TradeThread.newInstanceThread(i+"", str, str, threadName, "fifth", str, lfox);
			list.add(thread);
//			log.info("-thc->" + thread.hashCode());
			thread.start();
		}
		
//		log.info("execute task 5 -- Middle of ->" + Thread.currentThread().getName()
//				+ "\n tID->" + Thread.currentThread().getId()
//				+ " thashcode->" + Thread.currentThread().hashCode());
		int count = 0;
		while (1==1) {
			Boolean flag=false;
			if(list!=null&&list.size()>0){
				for(int ix=0;ix<list.size();ix++){
					TradeThread thread=(TradeThread) list.get(ix);
					if(thread.isAlive()){
						flag=true;
//						log.info("execute task 5 -- stop ->" + thread.getThreadName());
//						thread.stop();
					}else{
//						Connection conn=(Connection) conlist.get(ix);
//						OpenApiContants.close(null, conn);
					}
				}
			}
			if(!flag){
				break;
			}else{
				count++;
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		list=null;
		System.out.println("execute task 5");
		log.info("execute task 5");
	}
	
	
	public void checksixth()
	{	
		//我们的任务执行体
//		long i = 1/0;
		System.out.println("execute task 6");
		log.info("execute task 6");
	}
}
