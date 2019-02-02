//package com.mybatis.student;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import com.mybatis.classes.IClassesService;
//
//
//public class TestDelete {
//
//	private IStudentService studentService;
//	private IClassesService classesService;
//	@Before
//	public void init(){
//		ApplicationContext context=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
//		studentService=(IStudentService)context.getBean("studentService");
//		classesService=(IClassesService)context.getBean("classesService");
//	}
//	
//	@Test
//	public void testStudentDelete(){
//		int rows=studentService.delStudentById(12);
//		System.out.println(rows);
//	}
//	
//	@Test
//	public void testClassesDelete(){
//		int rows=classesService.delClassesBycid(12);
//		System.out.println(rows);
//	}
//	
//}
