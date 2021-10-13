package com.zlenadmin.dto;

import java.math.BigInteger;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserDetailsDto {

    private BigInteger count;
	
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createDate;

	public BigInteger getCount() {
		return count;
	}

	public void setCount(BigInteger count) {
		this.count = count;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
