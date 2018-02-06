package com.example.demo.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.bean.Result;

@ControllerAdvice
public class ExceptionHandle {
	private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);
	
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public Result catchFromControler(Exception e) {
		logger.error("【系统异常】",e);
		if(e instanceof ServletRequestBindingException) {
			return ResultUtil.error(ExceptionType.PARAM_ERROR);
		}
		return ResultUtil.error(ExceptionType.UNKONW_ERROR);
	}
	
	
}
