package com.leo.mcd.xmcd.bean;

import java.util.List;
import java.util.Map;


public class User {
	
	private Integer id;
	private String mobile;
	private String email;
	private String passwd;
	private String userName;
	private List asseat;
	private Map<String, String> car;
	private Book book;
	private List books;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List getAsseat() {
		return asseat;
	}
	public void setAsseat(List asseat) {
		this.asseat = asseat;
	}
	public Map<String, String> getCar() {
		return car;
	}
	public void setCar(Map<String, String> car) {
		this.car = car;
	}
	public List getBooks() {
		return books;
	}
	public void setBooks(List books) {
		this.books = books;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
}
