package com.ilucky.ejb.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ilucky.ejb.EJBDemoTest;

public class TestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("test----------------");
			PrintWriter out = response.getWriter();
			System.out.println("test----------dd------");
			Thread.sleep(50);
			System.out.println("test----------ff------");
			try {
				// result = MainTest.test();
				//Client.main(null);
				System.out.println("test---------------1-");
				EJBDemoTest.main(null);
				System.out.println("test---------------2-");
				out.write("SUCCESS!!!!!!");	
			} catch (Exception e) {
				e.printStackTrace();
				out.write("Exception..."+e.getMessage());
				System.out.println("-----------"+e.getMessage());
			} finally {
				out.close();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
