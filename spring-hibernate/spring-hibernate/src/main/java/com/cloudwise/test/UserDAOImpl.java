package com.cloudwise.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class UserDAOImpl  {
    private SessionFactory sessionFactory;//注入sessionFactory
    
    public List<Users> select_user() {
    	String path = this.getClass().getClassLoader().getResource("").getPath();
        String filepath = "/"+path + "/spring-servlet.xml";
        ApplicationContext ac = new FileSystemXmlApplicationContext(filepath);
        sessionFactory = (SessionFactory) ac.getBean("sessionFactory");
        String hql = "from UserEntity";//hql语句
        Query query = sessionFactory.getCurrentSession().createQuery(hql);

        return query.list();
    }
 
}
