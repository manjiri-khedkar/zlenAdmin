package com.zlenadmin.dto;

import java.util.Date;

public class ReportPostDto {
	
	private String userName;
	private String mimeType;
	private String uploadedPath;
	private Date createdAt;
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

	
}
