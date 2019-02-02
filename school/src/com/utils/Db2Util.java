package com.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db2Util {
	private static Connection conn = null;
	public static Connection getConnection() {
		
         try{
         System.out.println("正在连接数据库..........");
         Class.forName("com.ibm.db2.jcc.DB2Driver");//加载mysql驱动程序类
         String url = "jdbc:db2://10.0.1.24:50000/SCHOOL";//url为连接字符串
         String user = "db2inst1";//数据库用户名
         String pwd = "123456";//数据库密码
         conn=(Connection)DriverManager.getConnection(url,user,pwd);
         System.out.println("数据库连接成功！！！");
         }catch(Exception e){ 
          System.out.println(e.getMessage());
          //e.printStackTrace();
         }
         return conn;
	}
	
	public static void closeConn(){
		try {
			if(conn != null)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
