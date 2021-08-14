package com.zlenadmin.dao;


import java.util.Date;
import java.util.List;

import com.zlenadmin.api.entity.LastSeenSummary;
import com.zlenadmin.dto.AccountsDto;

public interface Accounts {
	
	LastSeenSummary getCreate(Date daysAgo);
	void insert(LastSeenSummary lSS);
	List<LastSeenSummary> getSummary();
	List<AccountsDto> getGraphQuery31(Date daysAgo);
   }
