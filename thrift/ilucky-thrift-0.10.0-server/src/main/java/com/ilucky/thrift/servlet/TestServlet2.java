package com.ilucky.thrift.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;

import com.ilucky.thrift.HelloWorldImpl;
import com.ilucky.thrift.HelloWorldService;

/**
 * httpClient demo: http://halloffame.iteye.com/blog/2347581
 * 
 * @author IluckySi
 *
 */
public class TestServlet2 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			System.out.println("socket server----------------");
			Thread.sleep(1);
		
			//这里会发送阻塞, 所以将其放到一个子线程中.
			
			TProcessor tprocessor = new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloWorldImpl());
			TServerSocket serverTransport = new TServerSocket(Integer.parseInt(getParam("port")));
			TServer.Args tArgs = new TServer.Args(serverTransport);
			tArgs.processor(tprocessor);
			tArgs.protocolFactory(new TBinaryProtocol.Factory());
			TServer server = new TSimpleServer(tArgs);
			server.serve();
			
			System.out.println("Socket server start SUCCESS");
			out.write("Socket server start SUCCESS");
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			out.write("Socket server start Faild, Maybe you have already start it!");
			out.close();
		}
	}
	
	public static String getParam(String param) {
		String url = null;
		try {
			// Read config from properties
			Properties props = new Properties();
			InputStream in = TestServlet2.class.getClassLoader().getResourceAsStream("conf.properties");
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
}