import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.h2.jdbcx.JdbcConnectionPool;

public class H2servlet extends HttpServlet {
	
	private static JdbcConnectionPool cp = null;
	Connection connection = null;
	Statement stmt = null;
	ResultSet rs = null;
	public void  doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
		InputStream in = H2servlet.class.getClassLoader().getResourceAsStream("h2.txt");
		Properties properties = new Properties();
		String sql1 = "CREATE TABLE TEST(NAME VARCHAR,SEX VARCHAR)";
		String sql2 = "INSERT INTO TEST VALUES('张三','男')";
		String sql5 = "INSERT INTO TEST VALUES('李四','女')";
		String sql3 = "select * from TEST";
		String sql4 = "DROP TABLE IF EXISTS TEST";
		try {
			properties.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//创建数据库连接池
	    //cp = JdbcConnectionPool.create(prop.getProperty("h2.jdbcUrl"), prop.getProperty("h2.username"), prop.getProperty("h2.password"));
		String driverClass = properties.getProperty("h2.driverClass");
        String jdbcUrl = properties.getProperty("h2.jdbcUrl");
        String username = properties.getProperty("h2.username");
        String password = properties.getProperty("h2.password");
        try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
       
		try {
			connection = DriverManager.getConnection(jdbcUrl,username,password);
			stmt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.execute(sql4);
			stmt.execute(sql1);
			stmt.execute(sql2);
			PreparedStatement prepareStatement1 = connection.prepareStatement(sql5);
			prepareStatement1.execute();
			PreparedStatement prepareStatement2 = connection.prepareStatement(sql3);
			rs  = prepareStatement2.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString("NAME")+"的性别是"+rs.getString("SEX"));
				 response.setContentType("text/html;charset=UTF-8");
				response.getWriter().println(rs.getString("NAME")+"的性别是"+rs.getString("SEX"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
