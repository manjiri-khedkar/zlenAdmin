package com.zlenadmin.dto;

import java.util.Date;

public class ReportedPollDto {
	
	private String userName;
	
	private String userZlenCode;
	
	private String pollZlenCode;
	
	private Date createdAt;
	
private boolean pollisbanned;
	
	private boolean userisbanned;
	
	private long pid;
	
	private long uid;
	
	private String type;

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

	public boolean isPollisbanned() {
		return pollisbanned;
	}

	public void setPollisbanned(boolean pollisbanned) {
		this.pollisbanned = pollisbanned;
	}

	public boolean isUserisbanned() {
		return userisbanned;
	}

	public void setUserisbanned(boolean userisbanned) {
		this.userisbanned = userisbanned;
	}

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
}
