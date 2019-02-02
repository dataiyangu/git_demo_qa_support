package com.mybatis.student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.mybatis.classes.Classes;

/**
 *  @author Administrator
 *  如果要直接使用 mybatis 的 sqlSession 不由spring 管理 sqlSession 
 *  可以只注入sqlSessionFactory 然后 像mybatis 中使用 sqlSession 一样  openSession()  .close() 
 *  否则  可以 继承 SqlSessionDaoSupport ("getSqlSession() insert/select/delete/update")
 *  和  SqlSessionTemplate 得到 spring 管理的 线程安全的 sqlSession 
 *  或者 简单的使用XML中配置  MapperFactoryBean 这样就省去了我们 获取sqlSession了 
 */
public class IStudentDAOImpl_sqlSessionTemplate extends SqlSessionTemplate implements IStudentDAO {

	//同样是 创建一个 可以批量操作的 sqlSession
	public IStudentDAOImpl_sqlSessionTemplate(
			SqlSessionFactory sqlSessionFactory) {
		super(sqlSessionFactory);
		// TODO Auto-generated constructor stub
	}

	public int addStudent(Student student) {
		// TODO Auto-generated method stub
		
		return this.insert("com.mybatis.student.addStudent", student);
		
	}

	public int addStudentBySequence(Student student) {
		// TODO Auto-generated method stub
		return this.insert("com.mybatis.student.addStudentBySequence", student);
	}

	public int delStudentById(int id) {
		int rows=this.delete("com.mybatis.student.delStudentById", id);
		System.out.println(rows);
		return rows;
	}

	public List<Student> queryAllStudent() {
		List<Student> stuList=new ArrayList<Student>();
		stuList=this.selectList("com.mybatis.student.queryAllStudent");
		return stuList;
	}

	public Student queryStudentById(int id) {
		// TODO Auto-generated method stub
		return (Student)this.selectOne("com.mybatis.student.queryStudentById",id);
	}

	public Map getAllStudentAfterupdate(Map map) {
		// TODO Auto-generated method stub
		 this.selectOne("com.mybatis.student.getAllStudentAfterupdate",map);
		 return map;
	}

	public Classes selectClassAndStudentListById(int id) {
		// TODO Auto-generated method stub
		return (Classes)this.selectOne("com.mybatis.classes.selectClassAndStudentListById",id);
	}

	public List<Student> selectStudentAndClassBySname(String sname) {
		// TODO Auto-generated method stub
		List<Student> stuList=new ArrayList<Student>();
		stuList=this.selectList("com.mybatis.student.selectStudentAndClassBySname",sname);
		return stuList;
	}

	public List<Student> selectStudentByDynamicSql(Student student) {
		// TODO Auto-generated method stub
		return this.selectList("com.mybatis.student.selectStudentByDynamicSql",student);
	}

	public List<Student> selectStudentByDynamicSqlChoose(Student student) {
		// TODO Auto-generated method stub
		return this.selectList("com.mybatis.student.selectStudentByDynamicSqlChoose",student);
	}

	public List<Student> selectStudentByIdArray(Integer[] idArry) {
		// TODO Auto-generated method stub
		return this.selectList("com.mybatis.student.selectStudentByIdArray",idArry);
	}

	public List<Student> selectStudentByIds(ArrayList<Integer> ids) {
		// TODO Auto-generated method stub
		return this.selectList("com.mybatis.student.selectStudentByIds",ids);
	}

	public List<Student> studentResultMap(String sname) {
		// TODO Auto-generated method stub
		return this.selectList("com.mybatis.student.selectMapResult",sname);
	}

	public List<Student> queryStudentByName(Student name) {
		// TODO Auto-generated method stub
		List<Student> stuList=new ArrayList<Student>();
		stuList=this.selectList("com.mybatis.student.queryStudentByName","%"+name+"%");
		return stuList;
	}

	public int updStudentById(Student student) {
		return this.update("com.mybatis.student.addStudentBySequence", student);
	}

}
