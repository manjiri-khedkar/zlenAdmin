package com.zlenadmin.api.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "poll")
public class Poll implements Serializable  {
	
	@Id
	
	private long id;
	
	private String content;
	
	private Date createdAt;
	
	private boolean isZlenWorld;
	
	private boolean isBanned;
	
	private boolean isCompleted;
	

	public boolean isZlenWorld() {
		return isZlenWorld;
	}

	public void setZlenWorld(boolean isZlenWorld) {
		this.isZlenWorld = isZlenWorld;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public boolean isBanned() {
		return isBanned;
	}

	public void setBanned(boolean isBanned) {
		this.isBanned = isBanned;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}



	
	

}
