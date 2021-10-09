package com.zlenadmin.dto;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BannerDto {
	

	private int banner_id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	//@JsonFormat(pattern = "yyyy-MM-dd")
	private Date start_date;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	//@JsonFormat(pattern = ")
	private Date end_date;
	
	private boolean is_active;
	
	private Integer frequency;
	
	private String content;

	public int getBanner_id() {
		return banner_id;
	}

	public void setBanner_id(int banner_id) {
		this.banner_id = banner_id;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public Integer getFrequency() {
		return frequency;
	}

	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	

	
	
	

}
