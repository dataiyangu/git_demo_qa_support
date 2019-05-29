package com.test.test;

import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.test.entity.User;

public class Test{

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		FileSystemXmlApplicationContext ac =
				new FileSystemXmlApplicationContext("src/main/java/applicationContext.xml");
		User user = (User) ac.getBean("user");
		System.out.println(user.getUsername());
	}

}