package com.hlsq;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class HsqlDemo {
    public static void main(String[] args) {
    	Statement stmt = null;
    	ResultSet rs = null;
    	Connection connection = null;

        try {
            Class.forName("org.hsqldb.jdbcDriver");
//    		String URL="jdbc:hsqldb:hsql://10.0.1.21:5432/school";  
    		String URL="jdbc:hsqldb:hsql://localhost";  
    		//
    		String USER="SA";
    		String PASSWORD="";
             connection = DriverManager.getConnection("jdbc:hsqldb:hsql://10.0.4.118:9001/", "SA", "");

//            Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
            if (connection != null) {
                System.out.println("Connected db success!");
//                String sql = "CREATE TABLE TBL_USERSSYXFA(ID INTEGER, NAME VARCHAR, BIRTHDAY DATE);";
                //增删改查
//                String sql = "CREATE TABLE TBL_USERSAARONAY(ID INTEGER, NAME INTEGER, BIRTHDAY DATE);";
//                sql = "INSERT INTO TBL_USERSAARONAY(ID, NAME, BIRTHDAY) VALUES ('2', '2', SYSDATE);";

                String sql1 = "CREATE TABLE TEST(NAME INTEGER,SEX INTEGER)";
        		String sql2 = "INSERT INTO TEST VALUES('1','2')";
        		String sql5 = "INSERT INTO TEST VALUES('3','4')";
        		String sql3 = "select * from TEST";
        		String sql4 = "DROP TABLE IF EXISTS TEST";
                stmt = connection.createStatement();
                stmt.execute(sql4);
     			stmt.execute(sql1);
     			stmt.execute(sql2);
     			PreparedStatement prepareStatement1 = connection.prepareStatement(sql5);
    			prepareStatement1.execute();
    			PreparedStatement prepareStatement2 = connection.prepareStatement(sql3);
    			rs  = prepareStatement2.executeQuery();
    			while(rs.next()) {
    				System.out.println(rs.getString("NAME")+"的性别是"+rs.getString("SEX"));
    			}
//                stmt.executeUpdate(sql);
                if (stmt != null) {
                	stmt.close();
                }
                connection.close();
            }
        } catch(Exception e) {
            System.out.println("ERROR:failed to load HSQLDB JDBC driver.");
            e.printStackTrace();
            return;
        }
    }   
}
