package com.mybatis.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionFactory {
	static DataSource dsCache=null;
	static Properties properties=new Properties();
	
	void aMethodForGetConnection() throws NamingException, FileNotFoundException, IOException, SQLException{
		if(dsCache==null){
			synchronized(this){
                 if(dsCache==null){				
                	 if(!properties.contains("key"))
                		 properties.load(new FileInputStream("mybatis-config.properties"));//配置文件
                	 
                	 Context ct=new InitialContext();
                	 dsCache=(DataSource)ct.lookup(properties.getProperty("key"));
                	 
                	//当发生dsCache == null时,记录下发生对该方法的调用者: 
                	 PrintWriter pw=new PrintWriter(new FileWriter("connection.log",true));
                	 pw.println(new java.util.Date()+":当前方法名如:aMethodForGetConnection():"
                			 + sun.reflect.Reflection.getCallerClass(2)
                			 );
                	 pw.close();
                 }
			}
		}
		Connection con=dsCache.getConnection();
	}
	
}
