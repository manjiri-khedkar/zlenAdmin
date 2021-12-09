package com.zlenadmin;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
		Date daysAgo1 = cal1.getTime();
		Integer todayActiveUser = activeUserUpdateRepository.getTodayActiveUser(daysAgo1);
		model.addObject("todayActiveUser", todayActiveUser);
		
		Integer totalCount = activeUserUpdateRepository.getAverageTimeSpendOneUserPerDay(daysAgo);
		Integer averageTimeSpendOneUserPerDay = totalCount / monthlyActiveUser/30;
		model.addObject("averageTimeSpendOneUserPerDay", averageTimeSpendOneUserPerDay);
		
		if ("".equals(todaydate)) {
			todaydate=null;
		}
		
		if ("".equals(fromdate)) {
			fromdate=null;
		}
		
		Calendar cal2 = new GregorianCalendar();
		//cal2.add(Calendar.DATE, -30);
		todaydate= cal2.getTime();
		
		Calendar cal3 = new GregorianCalendar();
		cal3.add(Calendar.DATE, -30);
		fromdate = cal3.getTime();
		
		
		List<UserUpdateDto> eventList = userUpdate.getEventType(todaydate, fromdate);
		
		model.addObject("eventList", eventList);
		

		return model;
	}
	
	@GetMapping("/activeUserDashboardListContent") 
	@ResponseBody
	public List<UserUpdateDto> getUserUpdates(Model model, @Param("todaydate")@DateTimeFormat(pattern = "yyyy-MM-dd") Date  todaydate, @Param("fromdate")@DateTimeFormat(pattern = "yyyy-MM-dd") Date  fromdate) {
		
		if ("".equals(todaydate)) {
			todaydate=null;
		}
		
		if ("".equals(fromdate)) {
			fromdate=null;
		}
		
//		Calendar cal2 = new GregorianCalendar();
//		cal2.add(Calendar.DATE, -30);
//		todaydate= cal2.getTime();
		
		List<UserUpdateDto> eventList = userUpdate.getEventType(todaydate, fromdate);
		
		return eventList;
	
	}

}
