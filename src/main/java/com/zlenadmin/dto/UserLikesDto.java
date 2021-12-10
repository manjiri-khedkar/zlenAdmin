package com.zlenadmin.dto;

import java.util.Date;

public class UserLikesDto {
	
	private String name;
	
	private String zlenCode;
	
	private Date dateTime;
	
	
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getZlenCode() {
		return zlenCode;
	}
	public void setZlenCode(String zlenCode) {
		this.zlenCode = zlenCode;
	}

	

}
