package com.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.UserDAO;
import com.test.entity.User;

//@Service("userService")
@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;

	public String save(User user){
		return userDAO.save(user);
	}

}