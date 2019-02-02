package com.ilucky.ejb;

import java.io.InputStream;
import java.util.Properties;

import javax.naming.InitialContext;

import weblogic.jndi.WLInitialContextFactory;


public class EJBDemoTest {
	private static String ip="10.0.2.90";
	private static String port = "7001";
	
//	/**
//	 * 获取配置
//	 */
//	public static void initConf() {
//		
//	}
	public static void init() {
		try {
			System.out.println("=======LOAD PROPERTIES=======");
			Properties p = new Properties();
			InputStream is = EJBDemoTest.class.getClassLoader().getResourceAsStream("conf.properties");
			p.load(is);
			ip = p.getProperty("ip");
			port = p.getProperty("port");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("=====PROPERTIES====ERROR============");
		}
	}
	
	public static void main(String[] args) {// throws Exception {
		System.out.println("----------wo kao------------");
		try {
			init();
			Properties prop = new Properties();  
	        prop.setProperty(javax.naming.Context.INITIAL_CONTEXT_FACTORY,  WLInitialContextFactory.class.getName());  
			// prop.setProperty(javax.naming.Context.INITIAL_CONTEXT_FACTORY,  "weblogic.jndi.WLInitialContextFactory");
			 System.out.println("=======IP PORT======="+ "t3://"+ip+":" + port);
	        prop.setProperty(javax.naming.Context.PROVIDER_URL, "t3://"+ip+":" + port);
	        // prop.setProperty(javax.naming.Context.PROVIDER_URL, "t3://localhost:7001");  
	        
	        System.out.println("=========1============");
	        InitialContext ic = new InitialContext(prop);
	        System.out.println("=========2============");
			EJBDemoIntf h = (EJBDemoIntf)ic.lookup("EJBSessBean#com.ilucky.ejb.EJBDemoIntf");  
			// System.out.println("=========3============");
	        String result = h.sayHi();
	        // 两次调用了......
	       // h.sayHi();
	        // h.sayHi("Hello!");
	        System.out.println("=======ejb return result=======>"+result);
	        // throw new Exception("=");
	        // int i =0;
	        // System.out.println(11/i);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("=========ERROR============");
		}  
		// throw new Exception("=");
	}
}
