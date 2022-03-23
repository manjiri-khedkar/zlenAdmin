package com.zlenadmin.dao;


import java.util.Date;

import java.util.List;

import com.zlenadmin.api.entity.LastSeenSummary;
import com.zlenadmin.api.entity.UserUpdate;
import com.zlenadmin.dto.AccountsDto;
import com.zlenadmin.dto.InactiveDto;
import com.zlenadmin.dto.PendingRegistrationDto;
import com.zlenadmin.dto.RegisterPendingDto;
import com.zlenadmin.dto.TodayUserCountsDataDto;
import com.zlenadmin.dto.UserUpdateDto;

public interface Accounts {
	
	UserUpdateDto getCreate(Date daysAgo);
	List<TodayUserCountsDataDto> getTodayUserCountsData(Date daysAgo);
	//void insert(UserUpdateDto lastSeen);
	List<LastSeenSummary> getSummary();
	List<UserUpdateDto> getGraphQuery31(Date daysAgo);
	
	List<PendingRegistrationDto> getPendingRegistrationDto(Date date);
	List<InactiveDto> getInactiveDto(Date daysAgo);
	List<RegisterPendingDto> getPendingRegistrations(Date daysAgo);
	
   }
