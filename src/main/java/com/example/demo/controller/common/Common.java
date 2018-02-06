package com.example.demo.controller.common;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.Result;
import com.example.demo.common.ResultUtil;
import com.example.demo.conf.PublicConfig;

@RestController
public class Common {
	
	@RequestMapping(value="/common/peerid",method=RequestMethod.GET)
	public Result getPeerid(@RequestParam("clienttype")String clienttype){
		String preeid = PublicConfig.createPeerid(clienttype);
		return ResultUtil.success(preeid);
	}
	
}
