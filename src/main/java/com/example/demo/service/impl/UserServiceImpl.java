package com.example.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.User;
import com.example.demo.common.RedisContact;
import com.example.demo.dao.UserDao;
import com.example.demo.service.RedisServer;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	RedisServer redisServer;
	
	@Override
	public User getUserInfoById(String userid) {
		String redisKey = RedisContact.USER_INFO + userid;
		User user = (User) redisServer.get(redisKey);
		if(user == null) {
			user = userDao.getUserInfoById(userid);
			redisServer.set(redisKey, user);
			System.out.println("=======================插入缓存");
		}else {
			System.out.println("=======================取了缓存");
		}
		return user;
	}

	@Override
	public boolean addUser(User user) {
		return userDao.addUser(user);
	}

}
