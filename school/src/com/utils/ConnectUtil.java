package com.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class ConnectUtil {
	
	private static final Log log = LogFactory.getLog(ConnectUtil.class);
	
	public static Connection getConnect(){
		
		Properties props = getProps();
		
	    Connection conn = null;
//	    String url =  props.getProperty("url") + "?useServerPrepStmts=false";  
	    String url =  props.getProperty("url");
	    url = "jdbc:mysql://127.0.0.1:3306/school?autoReconnect=true&failOverReadOnly=false";
        String username = props.getProperty("username");  
        String password = props.getProperty("password");
        String driver =  props.getProperty("driver");
	    try {   
            Class.forName(driver);   
            conn = DriverManager.getConnection( url,username, password );   
            }  
        //捕获加载驱动程序异常  
         catch ( ClassNotFoundException cnfex ) {  
             System.err.println(  
             "upload JDBC driver occur error." );  
             cnfex.printStackTrace();   
         }   
         //捕获连接数据库异常  
         catch ( SQLException sqlex ) {  
             System.err.println( "unable to connect to DB error." );  
             System.out.println("数据库连接失败！！！");
             String errMsg = "数据库连接失败！！！";
             sqlex.printStackTrace();   
         }
        
	    return conn;
	}
	
public static Connection getConnect1(){
		
		Properties props = getProps();
		
	    Connection conn = null;
//	    String url =  props.getProperty("url") + "?useServerPrepStmts=false";  
	    String url =  props.getProperty("url");
        String username = props.getProperty("username");  
        String password = props.getProperty("password");
        password = "123";
        String driver =  props.getProperty("driver");
	    try {   
            Class.forName(driver);   
            conn = DriverManager.getConnection( url,username, password );   
            }  
        //捕获加载驱动程序异常  
         catch ( ClassNotFoundException cnfex ) {  
             System.err.println(  
             "upload JDBC driver occur error." );  
             cnfex.printStackTrace();   
         }   
         //捕获连接数据库异常  
         catch ( SQLException sqlex ) {  
             System.err.println( "unable to connect to DB error." );  
             System.out.println("数据库连接失败！！！");
             String errMsg = "数据库连接失败！！！";
             sqlex.printStackTrace();   
         }
        
	    return conn;
	}
	
	public static Properties getProps(){
		
		Properties props = new Properties();
	    try {
//			props.load(new InputStreamReader(new FileInputStream(realPath),"utf-8"));
	    	props=PropertiesLoaderUtils.loadAllProperties("mybatis-config.properties");
	    } catch (Exception e){
	    	log.error("--getProps-->"+e);
	    	e.printStackTrace();
	    }
	    return props;
	}
	
	
	public static void testInsert(){
		Connection conn = null;
		String sql = "";
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			conn = ConnectUtil.getConnect1();
			sql = "insert into student(sid,sname,major,birth,score,cid) values (101,'bbb','cc大苏打','2018-06-22 22:37:17',99,1)";
			stmt = conn.prepareStatement(sql);
			stmt.execute();
			stmt.close();
            conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
//		System.out.println(System.currentTimeMillis());
		Connection conn = null;
		String sql = "";
		ResultSet rs = null;
		String name = "leo";
		String id = "1";
		if (id == null || "".equals(id)) {
			id = "1";
		}
		PreparedStatement stmt = null;
//		Statement stmt = null;
		try {
			Thread.currentThread();
//			Thread.sleep(5000);
//			conn = DBUtils.getConnection();
			conn = ConnectUtil.getConnect1();
//			stmt = conn.createStatement();
			
			sql = "insert into student(sid,sname,major,birth,score,cid) values (101,'aaa','cc大苏打','2018-06-22 22:37:17',99,1)";
			stmt = conn.prepareStatement(sql);
			stmt.execute();
//			sql = " SELECT tt.cname FROM classes tt  WHERE tt.CID=? and ?=? and ?=?";
////			sql = " select * from student ";
////			sql = "select st.sname from t_student st where st.sid ="+id;
//			stmt = conn.prepareStatement(sql);
//			stmt.setString(1, id);
//			stmt.setLong(2, 2);
//			stmt.setLong(3, 2);
//			stmt.setLong(4, 3);
//			stmt.setLong(5, 3);
//			rs = stmt.executeQuery();
////			rs = stmt.executeQuery(sql);
//			
//			while (rs.next()){
//				name = rs.getString(1);
//			}
//			System.out.println(name);
//			rs.close();
			stmt.close();
            conn.close();
            
//            sqlend = 1L/0L;
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main_02(String[] args) {

		String cr = "";
//		cr = "J45Engw88NchTUhqO1yVcm5Cp91u\\/zBePvYBqT95TCMKDaaEb**4EdjCRqjI5M16mpxso1GfT18KeK9"
//				+ "qv3XkakxC**rU9BupSbYB6viZ7KcM0JqcCdLFMSb\\/4liwUPkivPoE5en6HGoME"
//				+ "_34a04345-f45c-4cd6-8f45-cd6f421dc90d@j:1958247686806670:1487419984086;";
////		cr = cr.substring(0, cr.indexOf("_")) + "";
//		System.out.println(cr.substring(0, cr.indexOf("_")));
//		System.out.println(cr.substring(cr.indexOf("_")+1,cr.indexOf("@")));
//		System.out.println(cr.substring(cr.indexOf("@"), cr.lastIndexOf(":")+1));
//		
//		Map<String, Object> m = new HashMap<String, Object>();
//		m.put("com.aa", false);
//		if (m.get("com.aa") != null) {
//			if (!(Boolean)m.get("com.aa")) {
//				System.out.println("test boolean");
//			}
//		}
//		System.out.println(m.get("com.aa"));
		
		cr = "dubbo://com.uu.dubbotest.DemoService.sayHello";
		
		System.out.println(cr.replace("dubbo", "http"));
		
		System.out.println(cr.replaceFirst("dubbo", "http"));
		
		
	}

}
