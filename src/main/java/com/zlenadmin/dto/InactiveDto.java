package com.zlenadmin.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class InactiveDto {
	
	private String name;
	
	private String number;
	
	@JsonFormat(pattern = "dd/MMM/yyyy")
	private Date cdate;
	
	public InactiveDto() {} 

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}


	

	
		
	

}
