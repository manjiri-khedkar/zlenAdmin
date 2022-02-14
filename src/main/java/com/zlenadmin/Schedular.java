package com.zlenadmin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.zlenadmin.api.entity.LastSeenSummary;
import com.zlenadmin.dao.Accounts;
import com.zlenadmin.dto.PendingRegistrationDto;
import com.zlenadmin.dto.RegisterPendingDto;
import com.zlenadmin.dto.UserUpdateDto;


@Configuration
@EnableScheduling
public class Schedular {

	@Autowired
	private Accounts accountDao ;
	
	@Autowired
	private SmsSender smsSender ;
	
//	@Value("")
//    private String CRON_DAILY_SEEN_SUMMARY ;
//	
//	@Value("")
//    private String CRON_PENDING_REGISTRATION;
	
	@Value("${adminMobile}")
    private String ADMIN_MOBILE;
	
	@Value("${pendingRegistration.duration}")
    private int DURATION;
	
	//run every night at 12:00:30
	@Scheduled(cron = "${cron.dailySeenSummary}")
	  public void createDailyLastSeenSummary()
	  {
		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.DAY_OF_MONTH, -1);
		Date daysAgo = cal.getTime();
		UserUpdateDto lastSeen = accountDao.getCreate(daysAgo);
		lastSeen.setTodaydate(daysAgo);
	//	accountDao.insert(lastSeen);
	  }
	
	@Scheduled(cron = "${cron.pendingRegistration}")
	public void findPendingRegistration() {
		
		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.MINUTE, DURATION);
		Date daysAgo = cal.getTime();
		
		List<PendingRegistrationDto>  pendingRegistration = accountDao.getPendingRegistrationDto(daysAgo);
		List<RegisterPendingDto> registerPending = accountDao.getPendingRegistrations(daysAgo);
		Set<String> numbers = new HashSet<String>();
		for (PendingRegistrationDto curRec: pendingRegistration) {
			numbers.add( curRec.getNumber());
		}
		
		for (RegisterPendingDto curRec: registerPending) {
			numbers.add( curRec.getNumber());
		}
	
		if (numbers.size()>0) {
			String msg = String.join(",", numbers);
			smsSender.sendSms(ADMIN_MOBILE, msg);
		}
	}
	
}
