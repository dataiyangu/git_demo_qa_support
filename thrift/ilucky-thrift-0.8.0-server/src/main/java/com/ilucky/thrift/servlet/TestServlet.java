package com.ilucky.thrift.servlet;


import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServlet;

import com.ilucky.thrift.HelloWorldImpl;
import com.ilucky.thrift.HelloWorldService;

/**
 * httpClient demo: http://halloffame.iteye.com/blog/2347581
 * @author IluckySi
 *
 */
public class TestServlet  extends TServlet {
		
	/*public TestServlet(TProcessor processor, TProtocolFactory inProtocolFactory, TProtocolFactory outProtocolFactory) {
		super(new HelloWorldService.Processor(new HelloWorldImpl()), new TBinaryProtocol.Factory(), new TBinaryProtocol.Factory());
	}*/

	private static final long serialVersionUID = 1L;
	
	public TestServlet() {
		super(new HelloWorldService.Processor(new HelloWorldImpl()),  new TBinaryProtocol.Factory());
	}
	
/*	public TestServlet(TProcessor processor, TProtocolFactory protocolFactory) {
			super(new HelloWorldService.Processor(new HelloWorldImpl()),  new TBinaryProtocol.Factory());
	}*/

	/*	@Override
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
		 }*/
		
//		@Override
//		 public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//				try {
//					System.out.println("test----------------");
//					Thread.sleep(3000);
//					/* PrintWriter out = response.getWriter();*/
//					HelloWorldService.Processor testProcessor = new HelloWorldService.Processor(new HelloWorldImpl()); 
//					 TProtocolFactory tProtocolFactory = new TBinaryProtocol.Factory();
//					 testProcessor.process(tProtocolFactory.getProtocol(null), tProtocolFactory.getProtocol(null));
//					 //out.write("SUCCESS");
//					 //out.close();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//		 }
		/*protected TProcessor getProcessor() {
			System.out.println("Process -------------------");
			HelloWorldService.Processor testProcessor = new HelloWorldService.Processor(new HelloWorldImpl()); 
			return testProcessor;  
		}*/
}