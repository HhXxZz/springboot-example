package com.example.demo.bean;


/*
 * 返回的json结果集
 */
public class Result {
	private Integer code;
	private String msg;
	private Object data;
	public Integer getCode() {
		return code;
	}
	public Result setCode(Integer code) {
		this.code = code;
		return this;
	}
	public String getMsg() {
		return msg;
	}
	public Result setMsg(String msg) {
		this.msg = msg;
		return this;
	}
	public Object getData() {
		return data;
	}
	public Result setData(Object data) {
		this.data = data;
		return this;
	}
	
	
	
}
