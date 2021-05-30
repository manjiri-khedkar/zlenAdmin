package com.zlenadmin.api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class UserDotActivity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id; 
	
	private String senderUserId;
	
	private String receiverUserId;
	
	private String requestType;
	
	private boolean isRead;
	
	public UserDotActivity() { }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSenderUserId() {
		return senderUserId;
	}

	public void setSenderUserId(String senderUserId) {
		this.senderUserId = senderUserId;
	}

	public String getReceiverUserId() {
		return receiverUserId;
	}

	public void setReceiverUserId(String receiverUserId) {
		this.receiverUserId = receiverUserId;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	@Override
	public String toString() {
		return "UserDotActivity [id=" + id + ", senderUserId=" + senderUserId + ", receiverUserId=" + receiverUserId
				+ ", requestType=" + requestType + ", isRead=" + isRead + "]";
	}
	
}
