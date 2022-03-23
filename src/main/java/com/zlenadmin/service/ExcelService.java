package com.zlenadmin.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.zlenadmin.ExcelHelper1;
import com.zlenadmin.api.entity.UserDetails;
import com.zlenadmin.dao.Accounts;
import com.zlenadmin.dao.UserStories;
import com.zlenadmin.dto.InactiveDto;
import com.zlenadmin.dto.RegisterPendingDto;
import com.zlenadmin.dto.StoriesDto;
import com.zlenadmin.dto.UserPerDayCountDataDto;
import com.zlenadmin.dto.UsersDetailDto;
import com.zlenadmin.repository.UserDetailsRepository;

@Service
public class ExcelService {
	
	 @Autowired
	  Accounts accountDao;
	 
	 @Autowired
		UserDetailsRepository userDetailsRepository;
	 
	 @Autowired
	 com.zlenadmin.dao.UserDetails userDetails;
	 
	 @Autowired
		private UserStories userStories;
	 
	  public ByteArrayInputStream loadinActive(@Param("days") Integer days) {
		  
		  if ("All".equals(days)) {
				days=null;
			}
			
			Calendar cal = new GregorianCalendar();
			cal.add(Calendar.DAY_OF_MONTH, days);
			Date daysAgo = cal.getTime(); 
			List<InactiveDto> inActive = accountDao.getInactiveDto(daysAgo);
		  

	    ByteArrayInputStream in = ExcelHelper1.InActiveToExcel(inActive);
	    return in;
	  }
	  
 public ByteArrayInputStream loadPending(@Param("days") Integer days) {
		  
//		  if ("All".equals(days) || days==null) {
//				days=null;
//			}
//			
//			Calendar cal = new GregorianCalendar();
//			cal.add(Calendar.DAY_OF_MONTH, days);
//			Date daysAgo = cal.getTime(); 
	 if ("All".equals(days)) {
			days=null;
		}
		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.DAY_OF_YEAR, days);
		Date daysAgo = cal.getTime();
	 
			List<RegisterPendingDto> registerPending = accountDao.getPendingRegistrations(daysAgo);
		  

	    ByteArrayInputStream in = ExcelHelper1.PendingToExcel(registerPending);
	    return in;
	  }
 public ByteArrayInputStream loadUserDetails(String deviceType, String userMobile, String zlenCode, String userName, String gender,Integer age, Integer age1, Integer friendNumber, Integer friendNumber1, Integer pageid, Integer total,  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")Date fromdate, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")Date todaydate, @DateTimeFormat(pattern = "yyyy-MM-dd")Date createdOn) {
	  
	 
		
		if ("".equals(total)) {
			total=null;
		}
		
		if ("".equals(pageid)) {
			pageid=null;
		}
		
	 if ("All".equals(deviceType)) {
			deviceType=null;
		}
		if ("".equals(userMobile)) {
			userMobile=null;
		}
		if ("".equals(zlenCode)) {
			zlenCode=null;
		}
		
		if ("".equals(userName)) {
			userName=null;
		}
		if ("All".equals(gender)) {
			gender=null;
		}
		
		if("All".equals(age) || "All".equals(age1)) {
			age = null;
			age1 = null;
		}
		
		Integer valueAge = 0;
		Integer valueAge1 = 0;
		
		if (age == 0 && age1 == 0) {
			age = null;
			age1 = null;
		}
		valueAge = age;
		valueAge1 = age1;
		
		if("All".equals(friendNumber) || "All".equals(friendNumber1)) {
			friendNumber = null;
			friendNumber1 = null;
		}
		
		Integer valuefriendNumber = 0;
		Integer valuefriendNumber1 = 0;
		
		if (friendNumber == 0 && friendNumber1 == 0) {
			friendNumber = null;
			friendNumber1 = null;
		}
		valuefriendNumber = friendNumber;
		valuefriendNumber1 = friendNumber1;
		
		if ("".equals(createdOn)) {
			createdOn=null;
		}
		
		
		List<UsersDetailDto> userDetails1 = userDetails.getUserDetails(userName,userMobile,zlenCode,deviceType,createdOn,gender,valueAge,valueAge1,valuefriendNumber,valuefriendNumber1,total,pageid,fromdate,todaydate);
	  

   ByteArrayInputStream in = ExcelHelper1.userDetailsToExcel(userDetails1);
   return in;
 }

 
 public ByteArrayInputStream loadUserPerDayData(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date todaydate, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date fromdate) {
	  
	 if ("".equals(todaydate)) {
			todaydate = null;
		}
		if ("".equals(fromdate)) {
			fromdate = null;
		}
		
		
		List<UserPerDayCountDataDto> UserPerDayCountDataList = userDetails.getUserPerDayCountDataDto(todaydate, fromdate);
		
	  

   ByteArrayInputStream in = ExcelHelper1.userPerDayDataToExcel(UserPerDayCountDataList);
   return in;
 }

public ByteArrayInputStream loadUserStories(String zlenCode, String mimeType, @DateTimeFormat(pattern = "yyyy-MM-dd") Date uploadedDateTime, boolean zlenWorld, String userMobile, Date fromdate, Date todaydate) {
	
	if ("".equals(zlenCode)) {
		zlenCode = null;
	}
	
	if ("".equals(userMobile)) {
		userMobile = null;
	}

	if ("All".equals(mimeType)) {
		mimeType = null;
	}

	if ("".equals(uploadedDateTime)) {
		uploadedDateTime = null;
	}

	if ("".equals(zlenWorld)) {
		zlenWorld = (Boolean) null;
	}
	
	if ("".equals(todaydate)) {
		todaydate=null;
	}
	
	if ("".equals(fromdate)) {
		fromdate=null;
	}
	
	List<StoriesDto> userStoriesList = userStories.getUserStories(zlenCode,mimeType,uploadedDateTime,zlenWorld,userMobile,fromdate,todaydate);
	
	ByteArrayInputStream in = ExcelHelper1.userStoriesToExcel(userStoriesList);
	   return in;
	   
	}



 
}


