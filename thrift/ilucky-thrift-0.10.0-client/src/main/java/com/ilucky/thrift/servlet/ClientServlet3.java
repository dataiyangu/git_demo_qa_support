package com.ilucky.thrift.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.ilucky.thrift.HelloWorldService;

public class ClientServlet3 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("socket-------client----------------default locahost:80");
			Thread.sleep(3000);

			TTransport transport = null;
			try {
				transport = new TSocket(getParam("server"), Integer.parseInt(getParam("port")), 10000);
				TProtocol protocol = new TBinaryProtocol(transport);
				HelloWorldService.Client client = new HelloWorldService.Client(protocol);
				transport.open();
				String result = client.sayHello("db");
				System.out.println("Thrify client result = " + result);
			} catch (TTransportException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			} finally {
				if (null != transport) {
					transport.close();
				}
			}
			PrintWriter out = response.getWriter();
			out.write("SOCKET SUCCESS");
			out.close();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static String getParam(String param) {
		String url = null;
		try {
			// Read config from properties
			Properties props = new Properties();
			InputStream in = ClientServlet3.class.getClassLoader().getResourceAsStream("conf.properties");
			try {
				props.load(in);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("加载conf文件报异常了！");
			}
			System.out.println("======"+param);
			if (props.get(param) == null) {
				if("server".equals(param)) {
					return "localhost";
				} else if("port".equals(param)) {
					return "80";
				} else {
					return null;
				}
			} else {
				url = props.get(param).toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}
	
	public static void main(String[] args) {
		String server = getParam("server");
		System.out.println(server);
		String port = getParam("port");
		System.out.println(port);
	}
}
