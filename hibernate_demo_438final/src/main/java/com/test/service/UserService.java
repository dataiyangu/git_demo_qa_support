package com.test.service;

import com.test.dao.UserDAO;
import com.test.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service("userService")
@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;

	public String save(User user){
		return userDAO.save(user);
	}

}