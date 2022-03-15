package com.zlenadmin.dto;

import java.util.Date;

public class UserPerDayCountDataDto {
	
	private String userName;
	
	private String zlenCode;

	private String userMobile;
	
	private Date createdAt;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getZlenCode() {
		return zlenCode;
	}

	public void setZlenCode(String zlenCode) {
		this.zlenCode = zlenCode;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	
}
