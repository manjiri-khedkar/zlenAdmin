package com.zlenadmin.dao;

import java.util.Date;
import java.util.List;

import org.jboss.logging.Param;

import com.zlenadmin.dto.PollDto;

public interface Poll {
	
	List<PollDto> getPoll(String zlenCode,boolean zlenWorld,Date date);

}
