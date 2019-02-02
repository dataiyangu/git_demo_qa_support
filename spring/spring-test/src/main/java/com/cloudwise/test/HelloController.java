package com.cloudwise.test;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class HelloController {
	
	@RequestMapping("/hello")
	public ModelAndView test(HttpSession session){
	     ModelAndView mav=new ModelAndView("hello");
         mav.setViewName("hello");

	     mav.addObject("time", new Date());
	     mav.addObject("spring", "hello  Spring");
	     String path = this.getClass().getClassLoader().getResource("").getPath();
	        System.out.println("path = " + path);
	        String filepath = "/"+path + "/spring-servlet.xml";

	     ApplicationContext ac = new FileSystemXmlApplicationContext(filepath); 
	     Yingyingying ying = (Yingyingying) ac.getBean("Yingyingying");
	    
	     mav.addObject("ying",ying.toString());
	     return mav;
	 }

}
