package com.zlenadmin.dto;

import java.util.List;

public class UserFeedBackDto {
	private long id; 
	
	private String user_id;
	
	private String title;
	
	private String data;
	
	private String[] media_url;
	
	private String media_type;
	
	private String user_name;
	
	private String user_mobile;
	
	private long uid;
	

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_mobile() {
		return user_mobile;
	}

	public void setUser_mobile(String user_mobile) {
		this.user_mobile = user_mobile;
	}

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

	public String[] getMedia_url() {
		return media_url;
	}

	public void setMedia_url(String[] media_url) {
		this.media_url = media_url;
	}

	public String getMedia_type() {
		return media_type;
	}

	public void setMedia_type(String media_type) {
		this.media_type = media_type;
	}
	
	
	
	

}
