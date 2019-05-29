package com.test.test;

import com.test.entity.User;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Test{

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		FileSystemXmlApplicationContext ac =
				new FileSystemXmlApplicationContext("applicationContext.xml");
		User user = (User) ac.getBean("user");
		System.out.println(user.getUsername());
	}

}