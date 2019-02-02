package com.cloudwise.test;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
public class HelloController implements Controller{
	
//	public ModelAndView test(HttpSession session){
//	     ModelAndView mav=new ModelAndView("hello");
//         mav.setViewName("hello");
//
//	     mav.addObject("time", new Date());
//	     mav.addObject("spring", "hello  ......Spring");
//
//	     return mav;
//	 }

	

	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		System.out.println(arg0.getRequestURI());
		ModelAndView mav=new ModelAndView("hello");
		mav.setViewName("hello");
	     mav.addObject("time", new Date());
	     mav.addObject("spring", "hello  ......Spring");
	     //获取xml中的bean
	     String path = this.getClass().getClassLoader().getResource("").getPath();
	        System.out.println("path = " + path);
	        String filepath = "/"+path + "/spring-servlet.xml";

	     ApplicationContext ac = new FileSystemXmlApplicationContext(filepath); 
	     Yingyingying ying = (Yingyingying) ac.getBean("Yingyingying");
	     mav.addObject("ying",ying.toString());
	     //hibernate整合部分。
	     UserDAOImpl userDAOImpl =  (UserDAOImpl) ac.getBean("UserDaoImpl");
	     	Users conditon=new Users();
			List list=userDAOImpl.select_user();
			mav.addObject("list", list);

	     return mav;
	}

}
