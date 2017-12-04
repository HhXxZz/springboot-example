package com.example.demo.dao;

import org.apache.ibatis.annotations.Param;

import com.example.demo.bean.User;

public interface UserDao {
	User getUserInfoById(@Param("userid")String userid);
}
