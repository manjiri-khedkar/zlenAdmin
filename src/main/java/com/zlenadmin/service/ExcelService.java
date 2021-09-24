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
import com.zlenadmin.dto.InactiveDto;
import com.zlenadmin.dto.RegisterPendingDto;
import com.zlenadmin.repository.UserDetailsRepository;

@Service
public class ExcelService {
	
	 @Autowired
	  Accounts accountDao;
	 
	 @Autowired
		UserDetailsRepository userDetailsRepository;
	 
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
		  
		  if ("All".equals(days)) {
				days=null;
			}
			
			Calendar cal = new GregorianCalendar();
			cal.add(Calendar.DAY_OF_MONTH, days);
			Date daysAgo = cal.getTime(); 
			List<RegisterPendingDto> registerPending = accountDao.getPendingRegistrations(daysAgo);
		  

	    ByteArrayInputStream in = ExcelHelper1.PendingToExcel(registerPending);
	    return in;
	  }
 public ByteArrayInputStream loadUserDetails(@Param("deviceType") String deviceType, @Param("userMobile") String userMobile,@Param("zlenCode") String zlenCode, @Param("userName") String userName, @Param("createdOn") @DateTimeFormat(pattern = "yyyy-MM-dd")Date createdOn) {
	  
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
		
		if ("".equals(createdOn)) {
			createdOn=null;
		}
		
		
		ArrayList<UserDetails> userDetails = userDetailsRepository.getUserDetails(deviceType, userMobile, zlenCode, userName, createdOn);
	  

   ByteArrayInputStream in = ExcelHelper1.userDetailsToExcel(userDetails);
   return in;
 }


 
}


