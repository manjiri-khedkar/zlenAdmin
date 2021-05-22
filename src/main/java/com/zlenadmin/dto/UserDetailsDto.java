package com.zlenadmin.dto;

import java.math.BigInteger;
import java.util.Date;

public class UserDetailsDto {

    private BigInteger count;
	
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
