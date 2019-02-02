package com.ilucky.thrift;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;

/**
 * 线程池服务模型: 使用标准的阻塞式io，预先创建一组线程处理请求。
 * @author IluckySi
 */
public class HelloServerDemo2 {

	public static final int SERVER_PORT = 8090;

	public void startServer() {
			try {
				System.out.println("HelloWorld TSimpleServer start ....");
				TProcessor tprocessor = new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloWorldImpl());
				// 简单的单线程服务模型，一般用于测试
				TServerSocket serverTransport = new TServerSocket(SERVER_PORT);
				TThreadPoolServer.Args tArgs = new TThreadPoolServer.Args(serverTransport);
				tArgs.processor(tprocessor);
				tArgs.protocolFactory(new TBinaryProtocol.Factory());
				// tArgs.protocolFactory(new TCompactProtocol.Factory());
				// tArgs.protocolFactory(new TJSONProtocol.Factory());
				TServer server = new TThreadPoolServer(tArgs);
				server.serve();
			} catch (Exception e) {
				System.out.println("Server start error!!!");
				e.printStackTrace();
			}
		}

	public static void main(String[] args) {
		HelloServerDemo2 server = new HelloServerDemo2();
		server.startServer();
	}
}
