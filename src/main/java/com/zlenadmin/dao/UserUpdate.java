package com.zlenadmin.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.query.Param;

import com.zlenadmin.dto.UserUpdateDto;

public interface UserUpdate {
	
	List<UserUpdateDto> getEventType(@Param("todaydate") Date todaydate, @Param("fromdate") Date fromdate);

}
