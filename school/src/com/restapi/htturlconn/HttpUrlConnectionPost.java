package com.restapi.htturlconn;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.net.www.protocol.http.HttpURLConnection;

public class HttpUrlConnectionPost extends HttpServlet {
	
	
	private String requestInfo = "wS0n2SF8WRBSft68i3UyVnRQwKbowJ4IvDW0NMGFL/lMNXUiZ**toOemBekNY7nUlNPF10amvujE!!"
			+ "_" + "ABCDEF123456"+"@sdk_android:";
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String validateURL="http://10.0.5.176:8101/strutsTest/Hello.do?userName=leo";
//		validateURL="http://10.0.5.176:8770/strutsTest/Hello.do?userName=leo";
//		validateURL = "http://10.0.3.90:4449/school/student/allStudent.action";
		validateURL = "http://10.0.5.145:7001/school_j6/student/allStudent.action";
		validateURL = "http://10.0.5.115:9000/hotels";
		HttpURLConnection conn = null;
		DataInputStream dis = null;
		BufferedInputStream ois = null;
		byte[] b = new byte[1024];
		try {
		       URL url = new URL(validateURL); //创建URL对象
		       //返回一个URLConnection对象，它表示到URL所引用的远程对象的连接
		       
//		       conn.setRequestProperty("CLOUDWISEREQUESTINFO", requestInfo + System.currentTimeMillis()+";");
//		       conn.setRequestProperty("CLOUDWISE_REQUEST_INFO", requestInfo + System.currentTimeMillis()+";");
		       
		       
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
		           System.out.println("网络错误异常！!!!");
//		           return  false;
		       }
		} catch (Exception e) {
		   e.printStackTrace();
		   System.out.println("这是异常！");
		  } finally {
		    if (conn != null) {
		     conn.disconnect(); //中断连接
		    }
		 }
		PrintWriter pw = resp.getWriter();
        
	      pw.println("<html>");
	      pw.println("<header>");
	      pw.println("</header>");
	      pw.println("<body>");
	      pw.println("<form action=\"login?path=" + java.net.URLEncoder.encode("", "UTF-8") + "\" method=\"POST\">");
	      pw.println("UserName:<input type=\"text\" id=\"txtUserName\" name=\"txtUserName\" /><br/>");
	      pw.println("Password:<input type=\"password\" id=\"txtPassword\" name=\"txtPassword\" /><br/>");
	      pw.println("<input type=\"submit\" value=\"Submit\" />");
	      pw.println("</form>");
	      pw.println("<div>"+"-"+new String(b,"UTF-8")+"-"+"</div>");
	      pw.println("</body>");
	      pw.println("</html>");
	}
	
	
	
}
