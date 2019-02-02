package com.jndiweblogic;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jndiweblogic.WeblogicJndiUtil;

/**
 * <p>ClassName: JndiTestServlet<p>
 * <p>Description: <p>
 * @author 
 * @version 1.0 V
 * @createTime 2015-1-4 下午03:37:53
 */
public class JndiTestServlet extends HttpServlet {
   
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Oracle数据库连接对象
        Connection oracleConn = null;
        //MySQL数据库连接对象
        Connection mysqlConn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            String sqlOracle = "SELECT * FROM LEAD_OAMS_APPLICATIONS";
            //获取数据库连接对象
            oracleConn = WeblogicJndiUtil.getOracleConnection();
            stmt = oracleConn.createStatement();
            rs = stmt.executeQuery(sqlOracle);
            while (rs.next()) {
                System.out.println(rs.getString("RESOURCEID")+"---"+rs.getString("APP_NAME"));
            }
            System.out.println("-----------------------------分割线-------------------------------------");
            
            String sqlMySQL = "SELECT * FROM LEAD_OAMS_DBSOURCES";
            //获取数据库连接对象
            mysqlConn = WeblogicJndiUtil.getMySQLConnection();
            stmt = mysqlConn.createStatement();
            rs = stmt.executeQuery(sqlMySQL);
            while (rs.next()) {
                System.out.println(rs.getString("RESOURCEID")+"---"+rs.getString("DBSOURCE_NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                rs.close();
                stmt.close();
                oracleConn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }
}
