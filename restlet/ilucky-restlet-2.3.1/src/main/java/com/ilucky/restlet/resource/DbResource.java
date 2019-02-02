package com.ilucky.restlet.resource;

import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class DbResource extends ServerResource{
	
	public static void main(String[] args) {
		new DbResource().helloWorld();
	}
	@Get
	public String helloWorld(){
		/*Connection conn = getConn();
		String sql = "select * from map limit 0,10";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        int col = rs.getMetaData().getColumnCount();
	        System.out.println("============================");
	        while (rs.next()) {
	            for (int i = 1; i <= col; i++) {
	                System.out.print(rs.getString(i) + "\t");
	                if ((i == 2) && (rs.getString(i).length() < 8)) {
	                    System.out.print("\t");
	                }
	             }
	            System.out.println("");
	        }
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
		return "<h1>DB resource</h1>";
	}*/
	
	/*private static Connection getConn() {
	    String driver = "com.mysql.jdbc.Driver";
	    String url = "jdbc:mysql://10.0.3.42:3306/test_db";
	    String username = "kevin";
	    String password = "000000";
		try {
			InputStream input =  DbResource.class.getClassLoader().getResourceAsStream("db.properties");
			//InputStream input = ClassLoader.getSystemResourceAsStream("db.properties");  
			//InputStream input = DbResource.class.getResourceAsStream("classpath:db.properties");  
			Properties config = new Properties();
		    config.load(input);
		    driver = config.getProperty("driver");
		    url = config.getProperty("url");
		    username = config.getProperty("username");
		    password =config.getProperty("password");
		} catch (Exception e) {
			System.out.println("====================" + "使用默认值:");
		}
	    Connection conn = null;
	    try {
	        Class.forName(driver); 
	        conn = (Connection) DriverManager.getConnection(url, username, password);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return conn;
	}*/
		return null;
	}
}