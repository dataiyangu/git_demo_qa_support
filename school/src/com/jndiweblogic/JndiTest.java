package com.jndiweblogic;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class JndiTest {
	
	 public static void main(String[] args) throws Exception {
	      Hashtable ht = new Hashtable(); 
	      ht.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory"); 
	      ht.put(Context.PROVIDER_URL, "t3://127.0.0.1:7001"); 
	      
	      Context context=new InitialContext(ht);  

	    //注意：lookup 中的参数 是你在weblogic中配置的JNDI名称
	     
	      DataSource  ds = (DataSource) context.lookup("jdbc-oracle");  //配置的JNDI名
	      Connection  conn = ds.getConnection("weblogic", "12341234");  //登陆weblogic的用户名、密码
	      Statement stmt=conn.createStatement();
	      String sql="select e.* from employee e";
	      ResultSet rs=stmt.executeQuery(sql);
	      while (rs.next()){
	        System.out.println(rs.getInt(1));
	      }
	  }
	 
}
