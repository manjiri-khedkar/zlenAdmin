package com.zlenadmin;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.zlenadmin.api.entity.LastSeenSummary;
import com.zlenadmin.dao.Accounts;

@Configuration
@EnableScheduling
public class Schedular {

	@Autowired
	private Accounts accountDao ;
	
	//run every night at 12:00:30
	@Scheduled(cron = "30 0 0 * * ?")
	  public void createDailyLastSeenSummary()
	  {
		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.DAY_OF_MONTH, -1);
		Date daysAgo = cal.getTime();
		LastSeenSummary lastSeen = accountDao.getCreate(daysAgo);
		lastSeen.setCdate(daysAgo);
		accountDao.insert(lastSeen);
	  }
	
}
