package com.zlenadmin.dto;

import java.util.Date;

public class ReportPostDto {
	
	private String userName;
	
	private String mimeType;
	
	private String uploadedPath;
	
	private String userZlenCode;
	
	private String postZlenCode;
	
	private Date createdAt;
	
	private boolean postisbanned;
	
	private boolean userisbanned;
	
	private long pid;
	
	private long uid;
	
	private String type;
	
	private String userMobile;
	
	
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
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
	public String getUserZlenCode() {
		return userZlenCode;
	}
	public void setUserZlenCode(String userZlenCode) {
		this.userZlenCode = userZlenCode;
	}
	public String getPostZlenCode() {
		return postZlenCode;
	}
	public void setPostZlenCode(String postZlenCode) {
		this.postZlenCode = postZlenCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public String getUploadedPath() {
		return uploadedPath;
	}
	public void setUploadedPath(String uploadedPath) {
		this.uploadedPath = uploadedPath;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	
}
