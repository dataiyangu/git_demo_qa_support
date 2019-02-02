package com.quartzjob.service.impl;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.net.URL;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;






import org.quartz.JobExecutionException;

import sun.net.www.protocol.http.HttpURLConnection;

import com.quartzjob.service.EmpManager;

public class EmpManagerImpl implements EmpManager {
	
	public static final Log log = LogFactory.getLog(EmpManagerImpl.class);
	
	private String name;
	private String id;
	private String empnum;
	private String card;
	
	private Long apistart;
	private Long apiend;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmpnum() {
		return empnum;
	}
	public void setEmpnum(String empnum) {
		this.empnum = empnum;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	@Override
	public String toString() {
		return "EmpManager [name=" + name + ", id=" + id + ", empnum=" + empnum
				+ ", card=" + card + "]";
	}
	@Override
	public void autoWrite() {
		this.setCard("cn");
		this.setEmpnum("1-1");
		this.setId("01");
		this.setName("应用");
		
		this.toString();
		log.info(this.toString());
	}
	
	@Override
	public void opclient(){
		
		String validateURL = "http://10.0.3.90:4449/school/student/allStudent.action";
//		 validateURL = "http://www.cloudwise.com/";
		 validateURL = "http://www.baidu.com/";
//		 validateURL = "http://10.0.5.145:8101/strutsTest/Hello.do?userName=123";
		 HttpURLConnection conn = null;
		 System.out.println("execute task 1");
		 apistart = System.currentTimeMillis();
		 log.info("execute task 1 -- start >"+new Date(apistart));
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
		 log.info("execute task 1 -- end >"+new Date(apiend)
		 +"\t exec time:"+(apiend - apistart));
	}
	
	
	
	
	
	
}
