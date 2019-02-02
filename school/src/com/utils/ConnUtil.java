package com.utils;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnUtil {
	private static  Connection connection = null;
	public static Connection getConn(){
		
		String url="jdbc:postgresql://10.0.1.21:5432/school";   
		
        //String url = "jdbc:mysql://192.168.101.44/amon";
//        String url = "jdbc:postgresql://192.168.100.120:5432/postgres";//换成自己PostgreSQL数据库实例所在的ip地址，并设置自己的端口
        //String user = "root";
//        String user = "postgres";
		String user="postgres";
		String password="postgres";

        //String password = "560128";
//        String password = "";  //在这里我的密码为空，读者可以自己选择是否设置密码
        //Class.forName("com.mysql.jdbc.Driver");
        try {
			Class.forName("org.postgresql.Driver");
			if(connection  == null){
				connection= DriverManager.getConnection(url, user, password);
			}
			
	        System.out.println("是否成功连接pg数据库"+connection);
		} catch (Exception e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  //一定要注意和上面的MySQL语法不同
        
        return connection;
        
	}

}
