package com.ilucky.thrift.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.ilucky.thrift.HelloWorldService;
import com.ilucky.thrift.http.HelloHttpClientDemo;

public class ClientServlet2 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("client----------------");
			Thread.sleep(3000);

			////////////////////////////////////////////////////////////// START
			// HelloHttpClientDemo client = new HelloHttpClientDemo();
			// client.startClient("Michael");
			TTransport transport = null;
			try {
				// transport = new
				// THttpClient("http://localhost:8080/ilucky-thrift-0.9.0-server/test");
				//transport = new THttpClient("http://10.0.3.42:8080/ilucky-thrift-0.9.0-server/test");
				transport = new THttpClient(getUrl());
				
				// 协议要和服务端一致
				TProtocol protocol = new TBinaryProtocol(transport);
				// TProtocol protocol = new TCompactProtocol(transport);
				// TProtocol protocol = new TJSONProtocol(transport);

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
			////////////////////////////////////////////////////////////// END

			PrintWriter out = response.getWriter();
			out.write("SUCCESS");
			out.close();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static String getUrl() {
		String url = null;
		try {
			// Read config from properties
			Properties props = new Properties();
			InputStream in = ClientServlet2.class.getClassLoader().getResourceAsStream("conf.properties");
			try {
				props.load(in);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("加载conf文件报异常了！");
			}
			System.out.println("======"+props.get("url"));
			if (props.get("url") == null) {
				url = "http://localhost:8080/ilucky-thrift-0.8.0-server/test";
			} else {
				url = props.get("url").toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}
	
	public static void main(String[] args) {
		String url = getUrl();
		System.out.println(url +"-");
	}
}
