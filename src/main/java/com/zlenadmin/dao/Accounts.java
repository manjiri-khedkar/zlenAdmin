package com.zlenadmin.dao;


import java.util.Date;
import java.util.List;

import com.zlenadmin.api.entity.LastSeenSummary;
import com.zlenadmin.dto.AccountsDto;

public interface Accounts {
	
	List<AccountsDto> getGraphQuery31();
	List<LastSeenSummary> getCreate(Date daysAgo);
	void insert(LastSeenSummary lSS);
   }
