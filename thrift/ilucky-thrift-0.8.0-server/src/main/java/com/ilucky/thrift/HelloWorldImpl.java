package com.ilucky.thrift;

import java.util.Random;
import java.util.UUID;

import org.apache.thrift.TException;

import com.ilucky.thrift.model.User;
import com.ilucky.thrift.util.DbUtil;

public class HelloWorldImpl implements HelloWorldService.Iface {
	
	@Override
	public String sayHello(String username) throws TException {
		// 这里可以操作一下数据库，服务端就抓到了! 抛出异常！
		System.out.println("====HI====="+username);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if("db".equals(username)) {
			 User user = new User();
			 user.setId(UUID.randomUUID().toString());
			 user.setName( "SDX"+user.getId());
			 user.setPassword("PWD"+user.getId());
			 DbUtil.insert(user);
		} else {
			Random r =new Random();
			int i = r.nextInt(100);
			System.out.println(i);
			if(i%2 == 0) {
				//System.out.println(10/0);
			}
		}
		return "Hi, " + username + " welcome to my blog 【www.guoqingqing2.com】";
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(new HelloWorldImpl().sayHello("2"));
		} catch (TException e) {
			e.printStackTrace();
		}
	}
}
