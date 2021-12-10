package com.zlenadmin;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;
import com.zlenadmin.api.entity.LastSeenSummary;
import com.zlenadmin.dao.UserUpdate;
import com.zlenadmin.dto.UserUpdateDto;
import com.zlenadmin.repository.ActiveUserUpdateRepository;
@Controller
public class ActiveUserDashboardController {
	
	@Autowired
	private ActiveUserUpdateRepository activeUserUpdateRepository;
	
	@Autowired
	private UserUpdate userUpdate;
	
	@RequestMapping( value = "/activeUserDashboard", method = RequestMethod.GET)
	public Object dashboard(HttpServletResponse response, @RequestParam(required=false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date todaydate,
			@RequestParam(required=false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date  fromdate ) {

		ModelAndView model = new ModelAndView();
		model.setViewName("activeUserDashboard");

		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.DATE, -30);
		Date daysAgo = cal.getTime();
		
		Integer monthlyActiveUser = activeUserUpdateRepository.getMonthlyActiveUser(daysAgo);
		model.addObject("monthlyActiveUser", monthlyActiveUser);
		
		Calendar cal1 = new GregorianCalendar();
		cal1.add(Calendar.DATE, -1);
		fromdate = cal1.getTime();
		
		Calendar cal2 = new GregorianCalendar();
		cal2.add(Calendar.DATE, 0);
		todaydate = cal2.getTime();
		
		Integer todayActiveUser = activeUserUpdateRepository.getTodayActiveUser(fromdate,todaydate);
		model.addObject("todayActiveUser", todayActiveUser);
		
		Integer totalCount = activeUserUpdateRepository.getAverageTimeSpendOneUserPerDay(daysAgo);
		model.addObject("totalCount", totalCount);
		Integer averageTimeSpendOneUserPerDay = totalCount / todayActiveUser/30;
		model.addObject("averageTimeSpendOneUserPerDay", averageTimeSpendOneUserPerDay);
		
		if ("".equals(todaydate)) {
			todaydate=null;
		}
		
		if ("".equals(fromdate)) {
			fromdate=null;
		}
		
		Calendar cal5 = new GregorianCalendar();
		//cal2.add(Calendar.DATE, -30);
		todaydate= cal5.getTime();
		
		Calendar cal3 = new GregorianCalendar();
		cal3.add(Calendar.DATE, -30);
		fromdate = cal3.getTime();
		
		
		List<UserUpdateDto> eventList = userUpdate.getEventType(todaydate, fromdate);
		
		model.addObject("eventList", eventList);
		

		return model;
	}
	
	@GetMapping("/activeUserDashboardListContent") 
	@ResponseBody
	public Object getUserUpdates(Model model, @Param("todaydate")@DateTimeFormat(pattern = "yyyy-MM-dd") Date  todaydate, @Param("fromdate")@DateTimeFormat(pattern = "yyyy-MM-dd") Date  fromdate) {
		
		if ("".equals(todaydate)) {
			todaydate=null;
		}
		
		if ("".equals(fromdate)) {
			fromdate=null;
		}
		 List<HashMap<String,Object>> monthlyCount = new ArrayList<HashMap<String,Object>>();
		 List<HashMap<String,Object>> today = new ArrayList<HashMap<String,Object>>();
		 List<HashMap<String,Object>> averageCount = new ArrayList<HashMap<String,Object>>();
			Integer monthly = activeUserUpdateRepository.getMonthlyActiveUserSearch(todaydate, fromdate);
			
			Integer todayCount = activeUserUpdateRepository.getTodayActiveUser(fromdate, todaydate);
			
			
			Calendar cal = new GregorianCalendar();
			cal.add(Calendar.DATE, -30);
			Date daysAgo = cal.getTime();
				Integer totalAvg =  activeUserUpdateRepository.getAverageTimeSpendOneUserPerDay(daysAgo);
				
				@SuppressWarnings("deprecation")
				Integer theCalcDays = ((int) (fromdate.getDate() - todaydate.getDate()))  ;
				System.out.println("total1==:"+theCalcDays );
				
				Integer Average = (int) (totalAvg/todayCount/theCalcDays);
				System.out.println("total==:"+Average );
				
				
				
		 HashMap<String, Object> finalMap1 = new HashMap<String, Object>();
    	 finalMap1.put("monthly", monthly);
    	 
    	 HashMap<String, Object> finalMap2 = new HashMap<String, Object>();
    	 finalMap2.put("todayCount", todayCount);
    	 
    	 HashMap<String, Object> finalMap3 = new HashMap<String, Object>();
    	 finalMap3.put("Average", Average);
    	 
//		Calendar cal2 = new GregorianCalendar();
//		cal2.add(Calendar.DATE, -30);
//		todaydate= cal2.getTime();
		
		List<UserUpdateDto> eventList = userUpdate.getEventType(todaydate, fromdate);
		
		

		monthlyCount.add(finalMap1);
		today.add(finalMap2);
		averageCount.add(finalMap3);
		HashMap<String, Object> finalMap4 = new HashMap<String, Object>();
    	finalMap4.put("eventList",eventList);
    	finalMap4.put("monthlyCount", monthlyCount);
    	finalMap4.put("today", today);
    	finalMap4.put("averageCount", averageCount);
		
		return finalMap4;
	
	}
	
	

}
