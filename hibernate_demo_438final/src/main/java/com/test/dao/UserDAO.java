package com.test.dao;

import com.test.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	//	public String save(User user){
//		return (String) sessionFactory.getCurrentSession().save(user);
//	}
//	在hibernate4中用opensession
	public String save(User user) {
		return (String) sessionFactory.getCurrentSession().save(user);


//		Transaction transaction = sessionFactory.openSession().getTransaction();
//
//		transaction.begin();
//
//
//		String a =  (String) sessionFactory.getCurrentSession().save(user);
//		sessionFactory.openSession().flush();
//		transaction.commit();
//		return a;
	}

}
