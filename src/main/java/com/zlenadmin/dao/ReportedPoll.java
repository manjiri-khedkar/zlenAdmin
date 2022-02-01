package com.zlenadmin.dao;

import java.util.Date;
import java.util.List;

import com.zlenadmin.dto.ReportPostDto;
import com.zlenadmin.dto.ReportedPollDto;

public interface ReportedPoll {
	List<ReportedPollDto> getReportPoll(String userZlenCode, String PollZlenCode, Date createdAt);
	
	

}
