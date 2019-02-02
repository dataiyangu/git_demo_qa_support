package com.ilucky.ejb;
import java.util.Random;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.ilucky.ejb.db.MainTest;


@Stateless(mappedName="EJBSessBean")
@Remote(EJBDemoIntf.class)
public class EJBSessBean implements EJBDemoIntf {

	public String sayHi() {
		Random r = new Random();
    	int i = r.nextInt(10);
    	System.out.println("=============EJB Client comming=================(i % 3）=" + (i % 3));
    	if(i % 3 == 0) {
    		int j = 0;
			System.out.println("========> zero " + i/j);
    	} 
    	return "Hello World !!!";
	}
	
	public String sayHi(String str) {
		// 操作数据库
		MainTest.test();
    	return str;
	}
}
