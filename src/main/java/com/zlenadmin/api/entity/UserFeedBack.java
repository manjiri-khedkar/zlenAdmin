package com.zlenadmin.api.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class UserFeedBack implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private long id;
	
	private String user_id;
	
	private String title;
	
	private String data;
	
	private String media_url;
	
	private String media_type;
	
	private Date created_at;
	
	private Date updated_at;
	
	private boolean is_deleted;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getMedia_url() {
		return media_url;
	}

	public void setMedia_url(String media_url) {
		this.media_url = media_url;
	}

	public String getMedia_type() {
		return media_type;
	}

	public void setMedia_type(String media_type) {
		this.media_type = media_type;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public boolean isIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}
	
	
	
}
