package com.cloudwise.test;

public class Yingyingying {
	public String name =  new  String("Yingyingying");
	
	public Yingyingying(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "Yingyingying [name=" + name + ", getName()=" + getName() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
