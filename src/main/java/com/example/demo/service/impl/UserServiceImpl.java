package com.example.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import com.example.demo.bean.User;
import com.example.demo.dao.UserDao;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserDao userDao;
	
	@Autowired(required = false)
	public void setRedisTemplate(RedisTemplate redisTemplate) {
	    RedisSerializer stringSerializer = new StringRedisSerializer();
	    redisTemplate.setKeySerializer(stringSerializer);
	    redisTemplate.setHashKeySerializer(stringSerializer);
	    this.redisTemplate = redisTemplate;
	}
	
	RedisTemplate redisTemplate;
	
	@Override
	public User getUserInfoById(String userid) {
		String redisKey = "userinfo."+userid;
		ValueOperations<String, User> operations = redisTemplate.opsForValue();
		if(redisTemplate.hasKey(redisKey)){
			logger.info("取了缓存");
			return operations.get(redisKey);
		}
		User user = userDao.getUserInfoById(userid);
		operations.set(redisKey, user);
		logger.info("插入了缓存");
		return user;
	}

}
