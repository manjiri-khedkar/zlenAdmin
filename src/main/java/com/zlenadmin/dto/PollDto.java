package com.zlenadmin.dto;

import java.util.Date;

public class PollDto {

	private long id;
	
	private String content;
	
	private Date createdAt;
	
	private boolean zlenWorld;
	
	private boolean isBanned;
	
	private  boolean isCompleted;
	
	private String zlenCode;
	
	private String userName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public boolean isZlenWorld() {
		return zlenWorld;
	}

	public void setZlenWorld(boolean zlenWorld) {
		this.zlenWorld = zlenWorld;
	}

	public boolean isBanned() {
		return isBanned;
	}

	public void setBanned(boolean isBanned) {
		this.isBanned = isBanned;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public String getZlenCode() {
		return zlenCode;
	}

	public void setZlenCode(String zlenCode) {
		this.zlenCode = zlenCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
}
