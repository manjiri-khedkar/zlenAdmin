package com.zlenadmin.dto;

import java.util.Date;

public class ActivityDto {
	
	private long id;
	
	private String userId;
	
	private int activity;
	
	private Date createdDate;
	
	private String notifyUserId;
	
	private String notifyUserDeviceId;
		
	private String zlenCode;

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

	public int getActivity() {
		return activity;
	}

	public void setActivity(int activity) {
		this.activity = activity;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getNotifyUserId() {
		return notifyUserId;
	}

	public void setNotifyUserId(String notifyUserId) {
		this.notifyUserId = notifyUserId;
	}

	public String getNotifyUserDeviceId() {
		return notifyUserDeviceId;
	}

	public void setNotifyUserDeviceId(String notifyUserDeviceId) {
		this.notifyUserDeviceId = notifyUserDeviceId;
	}

	public String getZlenCode() {
		return zlenCode;
	}

	public void setZlenCode(String zlenCode) {
		this.zlenCode = zlenCode;
	}
	
	
}
