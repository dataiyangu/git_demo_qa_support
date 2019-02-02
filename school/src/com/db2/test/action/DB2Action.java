package com.db2.test.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.toredis.action.EmployBaseAction;
import com.utils.Db2Util;

public class DB2Action extends EmployBaseAction {

	
	private static final long serialVersionUID = 1L;
	
	
	
	public String queryAll() throws Exception{
		HttpServletRequest request=this.getRequest();
		HttpServletResponse resp = this.getResponse();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Connection conn = Db2Util.getConnection();
			   /**查询语句**/
		     String sql="select * from student";
		     stmt = conn.prepareStatement(sql);
		     rs = stmt.executeQuery();
		     while(rs.next()){ 
			      int i = rs.getInt(1);
			      String name =rs.getString(2);
			      String param = rs.getString(3);
			      System.out.println(Integer.toString(i)+" "+name +  " " + param );
		     }
			  
		} catch (Exception e) {
			// TODO: handle exception
		} finally  {
			  rs.close();//后定义，先关闭
			    stmt.close();
			    
			    Db2Util.closeConn();
		}
		this.getRequest().setAttribute("flag", "success");
		return SUCCESS;
	}
}
