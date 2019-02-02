package com.ilucky.thrift;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TServerSocket;

/**
 * 使用非阻塞式IO，服务端和客户端需要指定 TFramedTransport 数据传输的方式。
 * @author IluckySi
 */
public class HelloServerDemo3 {

	public static final int SERVER_PORT = 8090;

	public void startServer() {
			try {
				System.out.println("HelloWorld TSimpleServer start ....");
				TProcessor tprocessor = new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloWorldImpl());
				// 简单的单线程服务模型，一般用于测试
				TNonblockingServerSocket  serverTransport = new TNonblockingServerSocket (SERVER_PORT);
				TNonblockingServer.Args tArgs = new TNonblockingServer.Args(serverTransport);
				tArgs.processor(tprocessor);
				tArgs.transportFactory(new TFramedTransport.Factory());
				tArgs.protocolFactory(new TBinaryProtocol.Factory());
				// tArgs.protocolFactory(new TCompactProtocol.Factory());
				// tArgs.protocolFactory(new TJSONProtocol.Factory());
				// 使用非阻塞式IO，服务端和客户端需要指定TFramedTransport数据传输的方式
				TServer server = new TNonblockingServer(tArgs);
				server.serve();
			} catch (Exception e) {
				System.out.println("Server start error!!!");
				e.printStackTrace();
			}
		}

	public static void main(String[] args) {
		HelloServerDemo3 server = new HelloServerDemo3();
		server.startServer();
	}
}
