package com.example.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="home")
public class HomeProperties {
	/**
     * 省份
     */
    private String province;
 
    /**
     * 城市
     */
    private String city;
 
    /**
     * 描述
     */
    private String desc;
    
    private String secret;
    private int number;
    private long bignumber;
    private String uuid;
    
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public long getBignumber() {
		return bignumber;
	}

	public void setBignumber(long bignumber) {
		this.bignumber = bignumber;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public String toString() {
		return "Home [province=" + province + ", city=" + city + ", desc=" + desc + "]";
	}
}
