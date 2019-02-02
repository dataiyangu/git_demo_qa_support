package com.cloudwise.test;

import java.io.Serializable;

public class Users implements Serializable {
	private Integer id;
	private String name;
	
	public Users() {
	}
	
	public Integer getId() {
		return this.id;
	}
 
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
 
	
	
 
}
