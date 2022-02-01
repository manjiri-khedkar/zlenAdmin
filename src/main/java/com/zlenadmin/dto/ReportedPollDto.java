package com.zlenadmin.dto;

import java.util.Date;

public class ReportedPollDto {
	
	private String userName;
	
	private String userZlenCode;
	
	private String pollZlenCode;
	
	private Date createdAt;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserZlenCode() {
		return userZlenCode;
	}

	public void setUserZlenCode(String userZlenCode) {
		this.userZlenCode = userZlenCode;
	}


	public String getPollZlenCode() {
		return pollZlenCode;
	}

	public void setPollZlenCode(String pollZlenCode) {
		this.pollZlenCode = pollZlenCode;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	
}
