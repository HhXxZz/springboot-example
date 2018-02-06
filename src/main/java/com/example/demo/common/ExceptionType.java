package com.example.demo.common;

public enum ExceptionType {
	UNKONW_ERROR(-1,"UNKONW_ERROR"),
	PARAM_ERROR(-2,"PARAM_ERROR"),
	PEERID_ERROR(-4,"PEERID_ERROR"),
	SESSION_ERROR(-3,"SESSION_ERROR"),
	;
	private Integer code;

    private String msg;

    ExceptionType(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
