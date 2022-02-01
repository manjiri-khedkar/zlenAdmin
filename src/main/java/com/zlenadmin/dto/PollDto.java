package com.zlenadmin.dto;

import java.util.Date;

public class PollDto {

	private long id;
	
	private String content;
	
	
	private Date createdAt;
	
	private boolean zlenWorld;
	
	private boolean isBanned;
	
	private boolean iscompleted;
	
	private String zlenCode;
	
	private String userName;
	
	private boolean postisbanned;
	
	private boolean userisbanned;
	
	private long pid;
	
	private long uid;
	
	

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

	public boolean isPostisbanned() {
		return postisbanned;
	}

	public void setPostisbanned(boolean postisbanned) {
		this.postisbanned = postisbanned;
	}

	public boolean isUserisbanned() {
		return userisbanned;
	}

	public void setUserisbanned(boolean userisbanned) {
		this.userisbanned = userisbanned;
	}

	public boolean isIscompleted() {
		return iscompleted;
	}

	public void setIscompleted(boolean iscompleted) {
		this.iscompleted = iscompleted;
	}

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
