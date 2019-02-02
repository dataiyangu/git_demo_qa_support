package com.mybatis.classes;

import java.sql.Date;
import java.util.List;

import com.mybatis.student.Student;

public class Classes {
	private int cid;
	private String cname;
	private String teacher;
	private Date createDate;

	private List<Student> students;
	
	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public String service(){
		
		for (int i = 0; i < 10; i++) {
			System.out.print(i);
		}
		return "service";
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "cid:" + cid + "\tcname:" + cname + "\tteacher:" + teacher
				+ "\tcreateDate:" + createDate;
	}

}
