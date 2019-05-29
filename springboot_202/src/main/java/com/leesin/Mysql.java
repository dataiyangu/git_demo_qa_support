package com.leesin;


import java.sql.*;

public class Mysql {

	public static void main(String[] args) throws Exception {
		// 1.加载数据访问驱动
		Class.forName("com.mysql.jdbc.Driver");
		//2.连接到数据"库"上去
		Connection conn = DriverManager.getConnection("jdbc:mysql://10.0.3.42:3306/school?characterEncoding=GBK", "kevin", "000000");

		String sql1 = "insert into yitian values(?)";
		PreparedStatement ps = null;
		ps = conn.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1,"赵敏");
		ps.executeUpdate();


		//3.构建SQL命令
		Statement state =  conn.createStatement();
		System.out.println(state.getClass().getName());
		String s = "insert into yitian values('周芷若')";
		state.executeUpdate(s);
		String sql = "select * from yitian";//生成一条sql语句
		ResultSet rs = state.executeQuery(sql);//执行查询，把查询结果赋值给结果集对象
		int id, age, sex;//声明3个变量分别为id，age,sex
		String username, password;//声明2个变量分别为用户名，密码
		System.out.println("江湖人称 \r\n----------");//其中\t相当于8个空格
		while (rs.next()) {//遍历结果集
			username = rs.getString(1);//
			System.out.println(username);
		}

	}

}