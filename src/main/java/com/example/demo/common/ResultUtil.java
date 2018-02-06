package com.example.demo.common;

import com.example.demo.bean.Result;

public class ResultUtil {
	
	public static Result success(Object object) {
		return new Result()
				.setCode(0)
				.setMsg("success")
				.setData(object);
	}
	
	public static Result success(){
        return success(null);
    }
	
	
	public static Result errorByDB(String msg){
        return new Result()
				.setCode(-1)
				.setMsg(msg)
				.setData("");
	}
	
	public static Result error(Integer code,String msg){
        return new Result()
				.setCode(code)
				.setMsg(msg)
				.setData("");
	}

	public static Result error(ExceptionType e) {
		return new Result()
				.setCode(e.getCode())
				.setMsg(e.getMsg())
				.setData("");
	}
	
}
