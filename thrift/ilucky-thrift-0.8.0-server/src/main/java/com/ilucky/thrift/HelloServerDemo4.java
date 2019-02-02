package com.ilucky.thrift;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TNonblockingServerSocket;

/**
 * 半同步半异步的服务端模型，需要指定为： TFramedTransport 数据传输的方式。
 * @author IluckySi
 */
public class HelloServerDemo4 {

	public static final int SERVER_PORT = 8090;

	public void startServer() {
			try {
				System.out.println("HelloWorld TSimpleServer start ....");
				TProcessor tprocessor = new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloWorldImpl());
				// 简单的单线程服务模型，一般用于测试
				TNonblockingServerSocket  serverTransport = new TNonblockingServerSocket (SERVER_PORT);
				THsHaServer.Args tArgs = new THsHaServer.Args(serverTransport);
				tArgs.processor(tprocessor);
				tArgs.protocolFactory(new TBinaryProtocol.Factory());
				// tArgs.protocolFactory(new TCompactProtocol.Factory());
				// tArgs.protocolFactory(new TJSONProtocol.Factory());
				TServer server = new THsHaServer(tArgs);
				server.serve();
			} catch (Exception e) {
				System.out.println("Server start error!!!");
				e.printStackTrace();
			}
		}

	public static void main(String[] args) {
		HelloServerDemo4 server = new HelloServerDemo4();
		server.startServer();
	}
}
