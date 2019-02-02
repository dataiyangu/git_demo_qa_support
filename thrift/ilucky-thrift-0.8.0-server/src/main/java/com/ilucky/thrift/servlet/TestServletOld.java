package com.ilucky.thrift.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServletOld extends  HttpServlet {

	private static final long serialVersionUID = 1L;

	 public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try {
				System.out.println("test----------------");
				Thread.sleep(3000);
				 PrintWriter out = response.getWriter();
				 out.write("SUCCESS");
				 out.close();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	 }
}
