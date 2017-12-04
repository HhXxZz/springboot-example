package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.User;
import com.example.demo.dao.UserDao;
import com.example.demo.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;
	
	@Override
	public User getUserInfoById(String userid) {
		return userDao.getUserInfoById(userid);
	}

}
