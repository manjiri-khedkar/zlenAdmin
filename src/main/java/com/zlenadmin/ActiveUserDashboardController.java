package com.zlenadmin;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

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
		todaydate = cal2.getTime();
		
		Integer todayActiveUser = activeUserUpdateRepository.getTodayActiveUser(fromdate,todaydate);
		model.addObject("todayActiveUser", todayActiveUser);
		
		Integer totalCount = activeUserUpdateRepository.getAverageTimeSpendOneUserPerDay(fromdate,todaydate);
		model.addObject("totalCount", totalCount);
		Integer averageTimeSpendOneUserPerDay = totalCount / todayActiveUser;
		model.addObject("averageTimeSpendOneUserPerDay", averageTimeSpendOneUserPerDay);
		
				
		List<UserUpdateDto> eventList = userUpdate.getEventType(todaydate, fromdate);
		
		model.addObject("eventList", eventList);

		return model;
	}
	
	@GetMapping("/activeUserDashboardListContent") 
	@ResponseBody
	public Object getUserUpdates(Model model, @Param("todaydate")@DateTimeFormat(pattern = "yyyy-MM-dd") Date  todaydate, 
			@Param("fromdate")@DateTimeFormat(pattern = "yyyy-MM-dd") Date  fromdate) {
		
		if ("".equals(todaydate)) {
			todaydate=null;
		}
		
		if ("".equals(fromdate)) {
			fromdate=null;
		}
			Integer todayCount = activeUserUpdateRepository.getTodayActiveUser(fromdate, todaydate);
			Integer totalAvg =  activeUserUpdateRepository.getAverageTimeSpendOneUserPerDay(fromdate, todaydate);
				
				
				long diff = todaydate.getTime() - fromdate.getTime();
				Integer theCalcDays = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
				
				Integer Average = (int) (totalAvg/todayCount/theCalcDays);
				System.out.println("total==:"+Average );
				
    	 
		List<UserUpdateDto> eventList = userUpdate.getEventType(todaydate, fromdate);
		
		
		HashMap<String, Object> finalMap4 = new HashMap<String, Object>();
    	finalMap4.put("eventList",eventList);
    	finalMap4.put("monthlyCount", totalAvg);
    	finalMap4.put("today", todayCount);
    	finalMap4.put("averageCount", Average);
		
		return finalMap4;
	
	}
	
	

}
