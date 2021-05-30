package com.zlenadmin.api.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserActivityDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String userId;
	
	private int activity;
	
	private Date createdDate;
	
	private String notifyUserId;
	
	private String notifyUserDeviceId;
	
	public UserActivityDetails() { }

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

	@Override
	public String toString() {
		return "UserActivityDetails [id=" + id + ", userId=" + userId + ", activity=" + activity + ", createdDate="
				+ createdDate + ", notifyUserId=" + notifyUserId + ", notifyUserDeviceId=" + notifyUserDeviceId + "]";
	}
	
}
