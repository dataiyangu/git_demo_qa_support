package com.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
/**
 * @author Administrator
 * 模板类DBUtils
 */
public final class DBUtils {
    // 参数定义
    private static String url = "jdbc:mysql://127.0.0.1:3306/school"; // 数据库地址
    private static String username = "root"; // 数据库用户名
    private static String password = "123"; // 数据库密码
    private static String driver = "com.mysql.jdbc.Driver";
    private static int jdbcPoolInitSize = 10;
    
    private DBUtils() {

    }
    // 加载驱动
    static {
//    	getProps();
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println("驱动加载出错!");
        }
    }
    
    public static void getProps(){
        InputStream in = DBUtils.class.getClassLoader().getResourceAsStream("mybatis-config.properties");
        Properties prop = new Properties();
        try {
            prop.load(in);
            driver = prop.getProperty("driver");
            url = prop.getProperty("url") + "?useServerPrepStmts=false";
            username = prop.getProperty("username");
            password = prop.getProperty("password");
            //数据库连接池的初始化连接数大小
//            jdbcPoolInitSize =Integer.parseInt(prop.getProperty("jdbcPoolInitSize"));
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    // 获得连接
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    // 释放连接
    public static void free(ResultSet rs, Statement st, Connection conn) {
        try {
            if (rs != null) {
                rs.close(); // 关闭结果集
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close(); // 关闭Statement
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close(); // 关闭连接
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

        }

    }

}
