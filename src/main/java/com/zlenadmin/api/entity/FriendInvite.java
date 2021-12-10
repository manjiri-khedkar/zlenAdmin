package com.zlenadmin.api.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//@Table(name = "friend_invite")
public class FriendInvite implements Serializable {

	private static final long serialVersionUID = 6555871564256182933L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String userId;
	
	private String friendNumber;
	
	private String friendUserId;
	
	private String status;
	
	private Date createdAt;
	
	private Date updatedAt;
	
	private boolean isDeleted;

	public FriendInvite() {
		super();
	}
	
	public FriendInvite(Long id, String userId, String friendNumber, String friendUserId, String status, Date createdAt,
			Date updatedAt, boolean isDeleted) {
		super();
		this.id = id;
		this.userId = userId;
		this.friendNumber = friendNumber;
		this.friendUserId = friendUserId;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.isDeleted = isDeleted;
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
	
	public String getFriendNumber() {
		return friendNumber;
	}

	public void setFriendNumber(String friendNumber) {
		this.friendNumber = friendNumber;
	}

	public String getFriendUserId() {
		return friendUserId;
	}

	public void setFriendUserId(String friendUserId) {
		this.friendUserId = friendUserId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		FriendInvite other = (FriendInvite) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FriendInvite [id=" + id + ", userId=" + userId + ", friendNumber=" + friendNumber + ", friendUserId="
				+ friendUserId + ", status=" + status + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", isDeleted=" + isDeleted + "]";
	}
}
