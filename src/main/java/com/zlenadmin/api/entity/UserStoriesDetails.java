package com.zlenadmin.api.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class UserStoriesDetails implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String userId;
	
	private String mimeType;
	
	private String uploadedPath;
	
	private String videoType;
	
	private String videoPath;
	
	private Date uploadedDateTime;
	
	private String isActive;
	
	private boolean isBanned;
	
	public UserStoriesDetails() { }

//	public boolean isIs_banned() {
//		return is_banned;
//	}
//
//	public void setIs_banned(boolean is_banned) {
//		this.is_banned = is_banned;
//	}

	public boolean isBanned() {
		return isBanned;
	}

	public void setBanned(boolean isBanned) {
		this.isBanned = isBanned;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getVideoType() {
		return videoType;
	}

	public void setVideoType(String videoType) {
		this.videoType = videoType;
	}

	public String getVideoPath() {
		return videoPath;
	}

	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}

	public Date getUploadedDateTime() {
		return uploadedDateTime;
	}

	public void setUploadedDateTime(Date uploadedDateTime) {
		this.uploadedDateTime = uploadedDateTime;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "UserStoriesDetails [id=" + id + ", userId=" + userId + ", mimeType=" + mimeType + ", uploadedPath="
				+ uploadedPath + ", videoType=" + videoType + ", videoPath=" + videoPath + ", uploadedDateTime="
				+ uploadedDateTime + ", isActive=" + isActive + "]";
	}
	
}
