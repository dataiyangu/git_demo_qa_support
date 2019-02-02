//package com.ilucky.thrift.servlet;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//
//public class TestServlet extends  HttpServlet {
//
//	private static final long serialVersionUID = 1L;
//
//	 public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//			try {
//				Thread.sleep(3000);
//				 PrintWriter out = response.getWriter();
//				 String result = response2.body().string();
//				 System.out.println("=======>"+ result);
//				 out.write(result);
//				 out.close();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//	 }
//}
