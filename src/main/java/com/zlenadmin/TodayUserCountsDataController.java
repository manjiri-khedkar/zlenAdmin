package com.zlenadmin;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zlenadmin.dao.Accounts;
import com.zlenadmin.dto.TodayUserCountsDataDto;
import com.zlenadmin.dto.UserPerDayCountDataDto;

@Controller
public class TodayUserCountsDataController {
	
	@Autowired
	private Accounts accountDao;
	
	@GetMapping("/todayUserCountsData")
	public String viewLikes(Model model) {
		
	
		Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
		// cal.add(Calendar.DAY_OF_MONTH, -1);
		Date daysAgo = cal.getTime();
		
		List<TodayUserCountsDataDto> todayUserCountsDatalist = accountDao.getTodayUserCountsData(daysAgo);
		
		model.addAttribute("todayUserCountsDatalist",   todayUserCountsDatalist);	
		return "todayUserCountsData";
	} 

}
