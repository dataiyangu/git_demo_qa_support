package com.mybatis.student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mybatis.classes.Classes;

/**
 * 手动写dao 然后注入sqlSession 或者 继承
 * @author Administrator
 *
 */
public interface IStudentDAO {
	public int addStudent(Student student);

	public int addStudentBySequence(Student student);

	public int delStudentById(int id);
	
	public int updStudentById(Student student);

	public List<Student> queryAllStudent();

	public List<Student> queryStudentByName(Student name);

	public Student queryStudentById(int id);
	
	List<Student> studentResultMap(String sname);
	
	List<Student> selectStudentAndClassBySname(String sname);
	
	Classes selectClassAndStudentListById(int id);
	
	List<Student> selectStudentByDynamicSql(Student student);
	
	List<Student> selectStudentByDynamicSqlChoose(Student student);
	
	List<Student> selectStudentByIds(ArrayList<Integer> ids);
	
	List<Student> selectStudentByIdArray(Integer[] idArry);
	
	Map getAllStudentAfterupdate(Map map);
	
	
	
}
