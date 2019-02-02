package com.ilucky.thrift.servlet;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TExtensibleServlet;

import com.ilucky.thrift.HelloWorldImpl;
import com.ilucky.thrift.HelloWorldService;

/**
 * httpClient demo: http://halloffame.iteye.com/blog/2347581
 * @author IluckySi
 *
 */
public class TestServlet extends TExtensibleServlet {
		
		private static final long serialVersionUID = 1L;
		
		/*@Override
		public void doPost(HttpServletRequest request, HttpServletResponse response) {
			doGet(request, response);
		}

		@Override
		public void doGet(HttpServletRequest request, HttpServletResponse response) {
			try {
				System.out.println("test----------------");
				Thread.sleep(3000);
				 PrintWriter out = response.getWriter();
				 out.write("SUCCESS");
				 out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/

		@Override
		protected TProcessor getProcessor() {
			System.out.println("Process -------------------");
			HelloWorldService.Processor testProcessor = new HelloWorldService.Processor(new HelloWorldImpl()); 
			return testProcessor;  
			//具体的业务逻辑类，实现ThriftTest.thrift里的getUser接口  
	        //TestHandler testHandler = new TestHandler();   
	        //ThriftTest.Processor是生成的服务端代码  
	       // ThriftTest.Processor testProcessor = new ThriftTest.Processor(testHandler);   
	       //return testProcessor;  
		}

		@Override
		protected TProtocolFactory getInProtocolFactory() {
			//return null;
			// TProtocolFactory tProtocolFactory = new TCompactProtocol.Factory();
			 TProtocolFactory tProtocolFactory = new TBinaryProtocol.Factory();
			 return tProtocolFactory;  
		}

		@Override
		protected TProtocolFactory getOutProtocolFactory() {
			  TProtocolFactory tProtocolFactory = new TBinaryProtocol.Factory();  
			  return tProtocolFactory; 
		}
}