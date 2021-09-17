package com.zlenadmin.dao;


import java.util.Date;

import java.util.List;

import com.zlenadmin.api.entity.LastSeenSummary;
import com.zlenadmin.dto.AccountsDto;
import com.zlenadmin.dto.InactiveDto;
import com.zlenadmin.dto.PendingRegistrationDto;
import com.zlenadmin.dto.RegisterPendingDto;

public interface Accounts {
	
	LastSeenSummary getCreate(Date daysAgo);
	void insert(LastSeenSummary lSS);
	List<LastSeenSummary> getSummary();
	List<AccountsDto> getGraphQuery31(Date daysAgo);
	
	List<PendingRegistrationDto> getPendingRegistrationDto();
	List<InactiveDto> getInactiveDto(Date daysAgo);
	List<RegisterPendingDto> getPendingRegistrations(Date daysAgo);
	
   }
