import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.HashSet;
import java.util.Properties;

@WebServlet(name = "Servlet" ,value = "/")
public class Servlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			runTest();
			response.getWriter().print("success!success!success!success!success!success!success!success!");
		}catch(SQLException ex){
			for(Throwable t:ex)
				t.printStackTrace();
		}

	}
	/**
	 *Runs a test by creating a table,adding a value,showing the table contents,and removing the table.
	 */
	public static void runTest() throws SQLException,IOException{
		try(Connection conn = getConnection())
		{
			Statement stat = conn.createStatement();
			DatabaseMetaData meta = conn.getMetaData();
			ResultSet res = meta.getTables(null, null, null, new String[]{"TABLE"});
			HashSet<String> set=new HashSet<String>();
			while (res.next()) {
				set.add(res.getString("TABLE_NAME"));
			}
			if(set.contains("Greetings1".toUpperCase())){
				stat.executeUpdate("drop TABLE Greetings1");
			}
			if(set.contains("Greetings2".toUpperCase())){
				stat.executeUpdate("drop TABLE Greetings2");
			}
			if(set.contains("Greetings3".toUpperCase())){
				stat.executeUpdate("drop TABLE Greetings3");
			}

			//*******************************derby中的droop table if exists xxx 是不允许的***********************
			stat.executeUpdate("CREATE TABLE Greetings1 (Message CHAR(40))");
			stat.executeUpdate("CREATE TABLE Greetings2 (Message CHAR(40))");
			stat.executeUpdate("CREATE TABLE Greetings3 (Message CHAR(40))");
			stat.executeUpdate("INSERT INTO Greetings1 VALUES('这个语句是属于statmeny的')");
			PreparedStatement prepareStatement = conn.prepareStatement("INSERT INTO Greetings2 VALUES('这个语句是属于preparestatment的')");
			prepareStatement.execute();
			CallableStatement calls = conn.prepareCall("INSERT INTO Greetings3 VALUES('这个语句是属于callablestatement的')");
			calls.execute();
			try(ResultSet result = stat.executeQuery("SELECT * FROM Greetings1")){
				//将光标移动到下一行，初始在第一行之前
				while(result.next())
					System.out.println(result.getString("Message"));
			}
			//*******************************预编译语句上不允许使用方法“executeQuery(String)”。*********************************
//            try(ResultSet result = prepareStatement.executeQuery("SELECT * FROM Greetings2")){
//                //将光标移动到下一行，初始在第一行之前
//                while(result.next())
//                    System.out.println(result.getString("Message"));
//            }
//            try(ResultSet result = calls.executeQuery("SELECT * FROM Greetings3")){
//                //将光标移动到下一行，初始在第一行之前
//                while(result.next())
//                    System.out.println(result.getString("Message"));
//            }
			try(ResultSet result = stat.executeQuery("SELECT * FROM Greetings2")){
				//将光标移动到下一行，初始在第一行之前
				while(result.next())
					System.out.println(result.getString("Message"));
			}try(ResultSet result = stat.executeQuery("SELECT * FROM Greetings3")){
			//将光标移动到下一行，初始在第一行之前
			while(result.next())
				System.out.println(result.getString("Message"));
		}
			stat.executeUpdate("DROP TABLE Greetings1");
			stat.executeUpdate("DROP TABLE Greetings2");
			stat.executeUpdate("DROP TABLE Greetings3");

		}
	}
	/**
	 *Gets a connection from the properties specified in the file database.properties.
	 *@return the database connection
	 */
	public static Connection getConnection() throws SQLException,IOException{
		Properties props = new Properties();
//        try(InputStream in = Files.newInputStream(Paths.get("db.txt"))){
//            props.load(in);
//        }
		try(InputStream r = Start.class.getClassLoader().getResourceAsStream("db.txt")){
			props.load(r);
		}
		String drivers = props.getProperty("jdbc.drivers");

		//为了适应那些不能自动注册的数据库驱动程序
		if(drivers != null)
			//这种方式可以提供多个驱动器，使用冒号分割
			System.setProperty("jdbc.drivers",drivers);
		try{
//            Class.forName("org.apache.derby.jdbc.ClientDriver");
			Class.forName(drivers);
		}catch(Exception ex){
			ex.printStackTrace();
		}

		String url = props.getProperty("jdbc.url");
		System.out.println(url);
		String username = props.getProperty("jdbc.username");
		//System.out.println(username);
		String password = props.getProperty("jdbc.password");
		//System.out.println(password);
		// return DriverManager.getConnection("jdbc:derby://127.0.0.1:1527/newdb","root","root");
		return DriverManager.getConnection(url,username,password);
	}

	}

