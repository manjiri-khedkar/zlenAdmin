package com.zlenadmin.api.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserFriendsDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String userId;
	
	private String userZLenCode;
	
	private String friendUserId;

	private String friendZlenCode;
	
	private String friendNumber;
	
	private Boolean isRequestAccepted;
	
	private String active;
	
	private Date createdOn;
	
	private Date modifiedOn;
	
	private String isBlocked;
	
	private String blockedByUserId;
	
	private Date blockedDateTime;
	
	private Boolean isDeleted;
	
	public UserFriendsDetails() { }

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

	public String getUserZLenCode() {
		return userZLenCode;
	}

	public void setUserZLenCode(String userZLenCode) {
		this.userZLenCode = userZLenCode;
	}

	public String getFriendUserId() {
		return friendUserId;
	}

	public void setFriendUserId(String friendUserId) {
		this.friendUserId = friendUserId;
	}

	public String getFriendZlenCode() {
		return friendZlenCode;
	}

	public void setFriendZlenCode(String friendZlenCode) {
		this.friendZlenCode = friendZlenCode;
	}

	public String getFriendNumber() {
		return friendNumber;
	}

	public void setFriendNumber(String friendNumber) {
		this.friendNumber = friendNumber;
	}

	public Boolean isRequestAccepted() {
		return isRequestAccepted;
	}

	public void setRequestAccepted(Boolean isRequestAccepted) {
		this.isRequestAccepted = isRequestAccepted;
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

	public String getIsBlocked() {
		return isBlocked;
	}

	public void setIsBlocked(String isBlocked) {
		this.isBlocked = isBlocked;
	}

	public String getBlockedByUserId() {
		return blockedByUserId;
	}

	public void setBlockedByUserId(String blockedByUserId) {
		this.blockedByUserId = blockedByUserId;
	}

	public Date getBlockedDateTime() {
		return blockedDateTime;
	}

	public void setBlockedDateTime(Date blockedDateTime) {
		this.blockedDateTime = blockedDateTime;
	}

	public Boolean getIsRequestAccepted() {
		return isRequestAccepted;
	}

	public void setIsRequestAccepted(Boolean isRequestAccepted) {
		this.isRequestAccepted = isRequestAccepted;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserFriendsDetails other = (UserFriendsDetails) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserFriendsDetails [id=" + id + ", userId=" + userId + ", userZLenCode=" + userZLenCode
				+ ", friendUserId=" + friendUserId + ", friendZlenCode=" + friendZlenCode + ", friendNumber="
				+ friendNumber + ", isRequestAccepted=" + isRequestAccepted + ", active=" + active + ", createdOn="
				+ createdOn + ", modifiedOn=" + modifiedOn + ", isBlocked=" + isBlocked + ", blockedByUserId="
				+ blockedByUserId + ", blockedDateTime=" + blockedDateTime + ", isDeleted=" + isDeleted + "]";
	}

}
