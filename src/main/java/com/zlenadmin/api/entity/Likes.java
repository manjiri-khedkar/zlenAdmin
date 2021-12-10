package com.zlenadmin.api.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "likes")
public class Likes {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Long postId;
	
	private Long commentId;
	
	private String reactorUserId;
	
	private Date createdAt;
	
	private Date updatedAt;
	
	private boolean isDeleted;

	public Likes() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public String getReactorUserId() {
		return reactorUserId;
	}

	public void setReactorUserId(String reactorUserId) {
		this.reactorUserId = reactorUserId;
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
		Likes other = (Likes) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Likes [id=" + id + ", postId=" + postId + ", commentId=" + commentId + ", reactorUserId="
				+ reactorUserId + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", isDeleted=" + isDeleted
				+ "]";
	}
	
}
