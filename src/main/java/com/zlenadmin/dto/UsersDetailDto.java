package com.zlenadmin.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UsersDetailDto {
	
	@JsonFormat(pattern = "dd/MMM/yyyy HH:mm")
	private Date createdOn;
	
	private String zlenCode;
	
	private String deviceType;
	
	private String userMobile;
	
	private String userName;
	
	private String gender;
	
	private Integer age;
	
	private Long id;
	
	private String longitude;
	
	private String latitude;
	
	private String frnds_count;
	
	private boolean isbanned;
	
	
	public boolean isIsbanned() {
		return isbanned;
	}

	public void setIsbanned(boolean isbanned) {
		this.isbanned = isbanned;
	}

	public String getFrnds_count() {
		return frnds_count;
	}

	public void setFrnds_count(String frnds_count) {
		this.frnds_count = frnds_count;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getZlenCode() {
		return zlenCode;
	}

	public void setZlenCode(String zlenCode) {
		this.zlenCode = zlenCode;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	

}
