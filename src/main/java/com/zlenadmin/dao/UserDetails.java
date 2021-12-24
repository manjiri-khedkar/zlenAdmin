package com.zlenadmin.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.query.Param;

import com.zlenadmin.dto.UsersDetailDto;

public interface UserDetails {
	
	List<UsersDetailDto> getUserDetails(@Param("userName") String userName, @Param("userMobile") String userMobile, @Param("zlenCode") String zlenCode, @Param("deviceType") String deviceType, @Param("createdOn") @Temporal  Date createdOn, @Param("gender") String gender, @Param("age") Integer age, @Param("age1") Integer age1, @Param("friendNumber") Integer friendNumber, @Param("friendNumber1") Integer friendNumber1);

}
