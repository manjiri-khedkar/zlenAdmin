package com.zlenadmin.dao;

import java.util.Date;
import java.util.List;

import org.jboss.logging.Param;

import com.zlenadmin.dto.PollDto;
import com.zlenadmin.dto.PollOptionDto;
import com.zlenadmin.dto.PollVoteDto;

public interface Poll {
	
	List<PollDto> getPoll(String zlenCode,Date createdAt,boolean zlenWorld,String userMobile);
	List<PollOptionDto> getPollOption(long id);
	List<PollVoteDto> getPollVote(long id);
 
}
