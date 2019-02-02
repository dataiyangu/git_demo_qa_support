package com.ilucky.thrift.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.UUID;

import com.ilucky.thrift.model.User;
import com.mysql.jdbc.ReplicationDriver;

public class DbUtil {

	public static void main(String[] args) {
		//getConn();
		 User user = new User();
		 user.setId(UUID.randomUUID().toString());
		 user.setName( "SDX"+user.getId());
		 user.setPassword("PWD"+user.getId());
		 DbUtil.insert(user);
	}
	public static void insert(User user) {
		  Connection conn = getConn();
		    int i = 0;
		    String sql = "insert into user (id,name,password) values(?,?,?)";
		    PreparedStatement pstmt;
		    try {
		    	 // Perform read/write work on the master by setting the read-only flag to "false"
		    	conn.setReadOnly(false);
		        pstmt = (PreparedStatement) conn.prepareStatement(sql);
		        pstmt.setString(1, user.getId());
		        pstmt.setString(2, user.getName());
		        pstmt.setString(3, user.getPassword());
		        i = pstmt.executeUpdate();
		        pstmt.close();
		        conn.close();
		        System.out.println("======插入:"+i);
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	}
	
	/**
	 * @return
	 */
	public static Connection getConn() {
		Connection conn = null;
		try {
			// Read config from properties
			Properties props = new Properties();
			InputStream in = DbUtil.class.getClassLoader().getResourceAsStream("conf.properties"); 
			try {
				props.load(in);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("加载conf文件报异常了！");
			}
			
			
			// Get connection
			ReplicationDriver driver = new ReplicationDriver();
			if(props.get("user") == null) {
				 props.put("user", "root");
			}
			if(props.get("password") == null) {
				 props.put("password", "root");
			}
		    // We want this for failover on the slaves
		    props.put("autoReconnect", "true");
		    // We want to load balance between the slaves
		    props.put("roundRobinLoadBalance", "true");
		    String url = null;
		    if(props.get("url") == null) {
		    	url = "jdbc:mysql:replication://10.0.3.47:3306,10.0.3.49:3306/ms?useUnicode=true&characterEncoding=utf8";
		    } else {
		    	url = props.get("url").toString();
		    }
		    System.out.println(props);
		   conn = driver.connect(url, props);
		} catch (Exception e) {
			e.printStackTrace();
		}
		  return conn;
	}
	
}
