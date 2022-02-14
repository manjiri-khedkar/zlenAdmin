package com.zlenadmin.dto;

import java.util.Date;

public class StoriesDto {
		
	private Date uploadedDateTime;
	
	private String mimeType;
	
	private String zlenCode;
	
	private String uploadedPath;
	
	private String userName;
	
	private Long commentCount;
	
	private Long likesCount;
	
	private Long id;
	
	private boolean isbanned;
	
	private boolean zlenWorld;
	
	private boolean isbanned1;
	
	private Long uid;
	
	private String userMobile;
	

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public boolean isIsbanned1() {
		return isbanned1;
	}

	public void setIsbanned1(boolean isbanned1) {
		this.isbanned1 = isbanned1;
	}

	public boolean isIsbanned() {
		return isbanned;
	}

	public void setIsbanned(boolean isbanned) {
		this.isbanned = isbanned;
	}

	public boolean isZlenWorld() {
		return zlenWorld;
	}

	public void setZlenWorld(boolean zlenWorld) {
		this.zlenWorld = zlenWorld;
	}

	public Long getLikesCount() {
		return likesCount;
	}

	public void setLikesCount(Long likesCount) {
		this.likesCount = likesCount;
	}

	public Long getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Long commentCount) {
		this.commentCount = commentCount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getUploadedDateTime() {
		return uploadedDateTime;
	}

	public void setUploadedDateTime(Date uploadedDateTime) {
		this.uploadedDateTime = uploadedDateTime;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getZlenCode() {
		return zlenCode;
	}

	public void setZlenCode(String zlenCode) {
		this.zlenCode = zlenCode;
	}

	public String getUploadedPath() {
		return uploadedPath;
	}

	public void setUploadedPath(String uploadedPath) {
		this.uploadedPath = uploadedPath;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

}
