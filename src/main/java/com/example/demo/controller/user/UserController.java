package com.example.demo.controller.user;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.Result;
import com.example.demo.bean.User;
import com.example.demo.common.ExceptionType;
import com.example.demo.common.ResultUtil;
import com.example.demo.service.UserService;
import com.example.demo.util.UUIDUtil;

@RestController
public class UserController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/user",method=RequestMethod.GET)
	public User getUserInfo(@RequestParam(value="userid",required = true)String userid){
		User user = userService.getUserInfoById(userid);
		return user;
	}
	
	
	@RequestMapping(value="/test/user",method=RequestMethod.GET)
	public String getUserInfo2(){
		 logger.info("logback 访问hello");
		return "hello world";
	}
	
	@RequestMapping(value="/user/register",method=RequestMethod.POST)
	public Result register(@RequestParam(value="username",required = true)String username,
			@RequestParam(value="password",required = true)String password,
			@RequestParam(value="sex",required = true)Integer sex,
			@RequestParam(value="age",required = true)Integer age) {
		String userid = UUIDUtil.creatUserid();
//		String username = reqMap.get("username").toString();
//		String password = reqMap.get("password").toString();
//		if(password.length() <6 || password.length() > 16) {
//			return ResultUtil.error(ExceptionType.PARAM_ERROR);
//		}
//		password = UUIDUtil.md5(password);
//		Integer sex = (Integer) reqMap.get("sex");
//		Integer age = (Integer)reqMap.get("age");
		
		if(password.length() <6 || password.length() > 16) {
			return ResultUtil.error(ExceptionType.PARAM_ERROR);
		}
		password = UUIDUtil.md5(password);
		
		User user = new User();
		user.setAge(age);
		user.setPassword(password);
		user.setUserid(userid);
		user.setSex(sex);
		user.setUsername(username);
		
		if(!userService.addUser(user)) {
			return ResultUtil.errorByDB("USER ADD ERROR ");
		}
		
		return ResultUtil.success();
	}
	
}
