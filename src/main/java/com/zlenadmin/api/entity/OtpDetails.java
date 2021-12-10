package com.zlenadmin.api.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OtpDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String userMobile;
	
	private String userId;
	
	private String otp;
	
	private String active;
	
	private Date createdOn;
	
	private Date modifiedOn;
	
	public OtpDetails() { }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	@Override
	public String toString() {
		return "OtpDetails [id=" + id + ", userMobile=" + userMobile + ", userId=" + userId + ", otp=" + otp
				+ ", active=" + active + ", createdOn=" + createdOn + ", modifiedOn=" + modifiedOn + "]";
	}
	
}
