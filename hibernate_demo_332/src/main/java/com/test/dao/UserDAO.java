package com.test.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.entity.User;

@Repository
public class UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public String save(User user){
		return (String) sessionFactory.getCurrentSession().save(user);
	}

}
