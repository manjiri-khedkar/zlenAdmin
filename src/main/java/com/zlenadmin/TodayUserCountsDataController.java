package com.zlenadmin;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zlenadmin.dao.Accounts;
import com.zlenadmin.dto.TodayUserCountsDataDto;
import com.zlenadmin.dto.UserPerDayCountDataDto;
import com.zlenadmin.service.ExcelService;

@Controller
public class TodayUserCountsDataController {
	
	@Autowired
	private Accounts accountDao;
	
	@Autowired
	private ExcelService fileService;
	
	@GetMapping("/todayUserCountsData")
	public String viewLikes(Model model) {
		
	
		Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
		// cal.add(Calendar.DAY_OF_MONTH, -1);
		Date daysAgo = cal.getTime();
		
		List<TodayUserCountsDataDto> todayUserCountsDatalist = accountDao.getTodayUserCountsData(daysAgo);
		
		model.addAttribute("todayUserCountsDatalist",   todayUserCountsDatalist);	
		return "todayUserCountsData";
	} 
	
	@GetMapping("/todayUserCountsDataDownload")
	@ResponseBody
	public ResponseEntity<InputStreamResource> getTodayUserCountsDataDownload(Model model) {
		
		Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
		// cal.add(Calendar.DAY_OF_MONTH, -1);
		Date daysAgo = cal.getTime();
		String filename = "todayUserCountsData.xls";
		InputStreamResource file = new InputStreamResource( fileService.loadTodayUserCountsDataDownload(daysAgo));
	
		// InputStreamResource file = new
		// InputStreamResource(fileService.loadinActive(-30));

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
	}

}
