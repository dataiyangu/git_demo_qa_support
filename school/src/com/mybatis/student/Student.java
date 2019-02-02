package com.mybatis.student;

import java.io.Serializable;
import java.util.Date;

import com.mybatis.classes.Classes;

public class Student implements Serializable {
private int sid;
private String sname;
private String major;
private Date birth;
private float score;
private int cid;
private int status;

public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
public int getCid() {
	return cid;
}
public void setCid(int cid) {
	this.cid = cid;
}
private Classes classes;

public Classes getClasses() {
	return classes;
}
public void setClasses(Classes classes) {
	this.classes = classes;
}
public int getSid() {
	return sid;
}
public void setSid(int sid) {
	this.sid = sid;
}
public String getSname() {
	return sname;
}
public void setSname(String sname) {
	this.sname = sname;
}
public String getMajor() {
	return major;
}
public void setMajor(String major) {
	this.major = major;
}
public Date getBirth() {
	return birth;
}
public void setBirth(Date birth) {
	this.birth = birth;
}
public float getScore() {
	return score;
}
public void setScore(float score) {
	this.score = score;
}


public Student() {
}

public Student(Integer sid, String sname, Float score) {
	this.sid = sid;
	this.sname = sname;
	this.score = score;
}
@Override
public String toString() {
	// TODO Auto-generated method stub
	String content="sid:"+sid+"\tsname:"+sname+"\tmajor:"+major+"\tbirth:"+birth+"\tscore="+score;
	return content;
}


}
