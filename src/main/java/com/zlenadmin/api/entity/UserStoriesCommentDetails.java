package com.zlenadmin.api.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserStoriesCommentDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Long snapId;
	
	private Long parentCommentId;
	
	private String commenterUserId;
	
	private String commentMessage;
	
	private Date createdDate;
	
	private String isActive;
	
	public UserStoriesCommentDetails() { }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSnapId() {
		return snapId;
	}

	public void setSnapId(Long snapId) {
		this.snapId = snapId;
	}

	public Long getParentCommentId() {
		return parentCommentId;
	}

	public void setParentCommentId(Long parentCommentId) {
		this.parentCommentId = parentCommentId;
	}

	public String getCommenterUserId() {
		return commenterUserId;
	}

	public void setCommenterUserId(String commenterUserId) {
		this.commenterUserId = commenterUserId;
	}

	public String getCommentMessage() {
		return commentMessage;
	}

	public void setCommentMessage(String commentMessage) {
		this.commentMessage = commentMessage;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "UserStoriesCommentDetails [id=" + id + ", snapId=" + snapId + ", parentCommentId=" + parentCommentId
				+ ", commenterUserId=" + commenterUserId + ", commentMessage=" + commentMessage + ", createdDate="
				+ createdDate + ", isActive=" + isActive + "]";
	}
}
