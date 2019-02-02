package com.mybatis.student;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.mybatis.classes.Classes;
import com.mybatis.http.HttpUtil3;
import com.mybatis.http.HttpUtil4;
import com.utils.ConnUtil;
import com.utils.HttpURLConnectUtil;

public class IStudentServiceImpl implements IStudentService {

	private IStudentDAO sudentDAO;

	public int addStudent(Student student) {
		// TODO Auto-generated method stub
		return sudentDAO.addStudent(student);
	}

	public int addStudentBySequence(Student student) {
		// TODO Auto-generated method stub
		return sudentDAO.addStudentBySequence(student);
	}

	public int delStudentById(int id) {
		// TODO Auto-generated method stub
		return sudentDAO.delStudentById(id);
	}

	public Map getAllStudentAfterupdate(Map map) {
		// TODO Auto-generated method stub
		return sudentDAO.getAllStudentAfterupdate(map);
	}

	public List<Student> queryAllStudent() {
		// TODO Auto-generated method stub
		return sudentDAO.queryAllStudent();
	}

	public Student queryStudentById(int id) {
		// TODO Auto-generated method stub
		return sudentDAO.queryStudentById(id);
	}

	public List<Student> queryStudentByName(Student name) {
		// TODO Auto-generated method stub
		return sudentDAO.queryStudentByName(name);
	}

	public Classes selectClassAndStudentListById(int id) {
		// TODO Auto-generated method stub
		return sudentDAO.selectClassAndStudentListById(id);
	}

	public List<Student> selectStudentAndClassBySname(String sname) {
		// TODO Auto-generated method stub
		return sudentDAO.selectStudentAndClassBySname(sname);
	}

	public List<Student> selectStudentByDynamicSql(Student student) {
		// TODO Auto-generated method stub
		return sudentDAO.selectStudentByDynamicSql(student);
	}

	public List<Student> selectStudentByDynamicSqlChoose(Student student) {
		// TODO Auto-generated method stub
		return sudentDAO.selectStudentByDynamicSqlChoose(student);
	}

	public List<Student> selectStudentByIdArray(Integer[] idArry) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Student> selectStudentByIds(ArrayList<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Student> studentResultMap(String sname) {
		// TODO Auto-generated method stub
		return null;
	}

	public int updStudentById(Student student) {
		// TODO Auto-generated method stub
		return 0;
	}

	public IStudentDAO getSudentDAO() {
		return sudentDAO;
	}

	public void setSudentDAO(IStudentDAO sudentDAO) {
		this.sudentDAO = sudentDAO;
	}

	public void testStackB() {
		System.out.println("request to method B !!!");
//		testStackD();
		testStackE();
		try {
			testStackF();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	public void testStackC() {
		System.out.println("request to method C !!!");
	}

	public void testStackG() {
		System.out.println("request to method G !!!");
	}

	public void testStackD() {
		String data = null;
		try {
			String url = "http://10.0.1.21:8888/springboot/login2";
//			String url = "http://10.0.1.21:9999/school/students";
			URL dataUrl = new URL(url);
//			HttpURLConnection con = (HttpURLConnection) dataUrl
//					.openConnection();
//			con.setRequestMethod("GET");
//			con.setRequestProperty("Proxy-Connection", "Keep-Alive");
//			con.setDoOutput(true);
//			con.setDoInput(true);
			HttpURLConnection con = HttpURLConnectUtil.getConnection(dataUrl);

			
			String param= "name=" +URLEncoder.encode("aaa","UTF-8");
					
//					+ "&password=" +URLEncoder.encode("bbb","UTF-8");
			OutputStream os = con.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			dos.writeBytes(param);
			dos.flush();
			dos.close();

			InputStream is = con.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			byte d[] = new byte[dis.available()];
			dis.read(d);
			data = new String(d);
			System.out.println(data);
//			con.disconnect();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		System.out.println("request to method D , and call http request !!!");
	}

	public void testStackE() {

//		sudentDAO.queryAllStudent();
//		System.out.println("request to method E , and call database !!!");
		
//		URL url = new URL("http://localhost:8080/TPSServer/door.do");
		String url = "http://10.0.3.52:8888/school/student/allStudent.action";
//		String resp = HttpUtil3.doPost(url, null, "UTF-8", true); 
		String resp = HttpUtil4.doPost(url, new HashMap<String, String>());
		System.out.println(resp);
	}

	public void testStackF() throws InterruptedException {
		
		
		
		Thread.sleep(1000);
		System.out.println("request to method F , and sleeped 1000ms !!!");
	}

	@Override
	public void testPGExec() {
		// TODO Auto-generated method stub
		try{
			Statement statement = null;
			PreparedStatement pstmt = null;
			String sql = "delete from  student where sid = '0'";
			Connection connection = ConnUtil.getConn();
			statement = connection.createStatement();
			// statement.addBatch("select * from student");
			// statement.addBatch("select * from student where sid = '1'");
			// statement.executeBatch();

			boolean flag =  statement.execute(sql);
			System.out.println("删除sid为0 的数据，是否成功：" + flag);

		} catch(Exception e){
			e.printStackTrace();
		}
	

	}

	@Override
	public void testPGExecQuery() {
		// TODO Auto-generated method stub
		try{
			Statement statement = null;
			PreparedStatement pstmt = null;
			String sql = "select * from student";
			Connection connection = ConnUtil.getConn();
			statement = connection.createStatement();
			// statement.addBatch("select * from student");
			// statement.addBatch("select * from student where sid = '1'");
			// statement.executeBatch();

			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				// 取出列值
				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);

				System.out.println(id + "," + name + ",");
			}

		} catch(Exception e){
			e.printStackTrace();
		}
	}
	@Override
	public void testPGBatch() {
		// TODO Auto-generated method stub
		try{
			Statement statement = null;
			PreparedStatement pstmt = null;
			String sql = "select * from student";
			Connection connection = ConnUtil.getConn();
			statement = connection.createStatement();
			 statement.addBatch("select * from student");
	         statement.addBatch("select * from student where sid = '1'");
	         statement.addBatch("delete from student where sid = '0'");
	         statement.executeBatch();

		} catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	

}
