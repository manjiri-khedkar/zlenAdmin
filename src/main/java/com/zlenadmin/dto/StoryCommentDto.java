package com.zlenadmin.dto;

import java.util.Date;

public class StoryCommentDto {
		
	private Date dateTime;
	
	private String zlenCode;
	
	private String comment;

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getZlenCode() {
		return zlenCode;
	}

	public void setZlenCode(String zlenCode) {
		this.zlenCode = zlenCode;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	

}
