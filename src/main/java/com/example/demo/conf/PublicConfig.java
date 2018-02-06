package com.example.demo.conf;

import com.example.demo.util.UUIDUtil;

public class PublicConfig {
	
	
	
	
	public static String createPeerid(String clienttype){
		StringBuffer peeridSB = new StringBuffer();
		String headStr = UUIDUtil.randomString(4, false).toLowerCase();
		peeridSB.append(headStr);
		peeridSB.append(UUIDUtil.md5(clienttype + System.currentTimeMillis()).toLowerCase());
		peeridSB.append(UUIDUtil.hashNum(headStr, 9));
		peeridSB.append(UUIDUtil.randomString(3, false).toLowerCase());
		return peeridSB.toString();
	}
	
	public static boolean checkPeerid(String peerid){
		if(peerid!=null && peerid.length()==40){
			String headStr = peerid.substring(0, 4);
			String num1 = UUIDUtil.hashNum(headStr, 9);
			String num2 = peerid.substring(36, 37);
			return num1.equals(num2);
		}
		return false;
	}
	
}
