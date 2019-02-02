package com.ilucky.thrift.http;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.ilucky.thrift.HelloWorldService;

public class HelloHttpClientDemo {
	
	public static final String URL = "http://localhost:8080/ilucky-thrift-0.9.0-server/test";
	//public static final String URL = "http://localhost:8090";

	/**
	 *
	 * @param userName
	 */
	public void startClient(String userName) {
		TTransport transport = null;
		try {
			transport = new THttpClient(URL);
			
			// 协议要和服务端一致
			TProtocol protocol = new TBinaryProtocol(transport);
			// TProtocol protocol = new TCompactProtocol(transport);
			// TProtocol protocol = new TJSONProtocol(transport);
			
			HelloWorldService.Client client = new HelloWorldService.Client(protocol);
			transport.open();
			String result = client.sayHello(userName);
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
	}

	public static void main(String[] args) {
		HelloHttpClientDemo client = new HelloHttpClientDemo();
		client.startClient("Michael");
	}
}
