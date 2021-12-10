package com.zlenadmin.api.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserGroupDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String userId;
	
	private String groupId;
	
	private String uploadedPath;
	
	private Date uploadedDateTime;
	
	private String isActive;
	
	public UserGroupDetails() { }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getUploadedPath() {
		return uploadedPath;
	}

	public void setUploadedPath(String uploadedPath) {
		this.uploadedPath = uploadedPath;
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
		return "UserGroupDetails [id=" + id + ", userId=" + userId + ", groupId=" + groupId + ", uploadedPath="
				+ uploadedPath + ", uploadedDateTime=" + uploadedDateTime + ", isActive=" + isActive + "]";
	}
	
}
