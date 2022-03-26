package com.zlenadmin;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zlenadmin.dao.UserDetails;
import com.zlenadmin.dto.PollVoteDto;
import com.zlenadmin.dto.UserPerDayCountDataDto;
import com.zlenadmin.service.ExcelService;

@Controller
public class UserPerDayCountDataController {
	
	@Autowired
	private UserDetails userDetails;
	
	@Autowired
	private ExcelService fileService;
	
	@GetMapping("/userPerDayCountDataView")
	public String viewLikes(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm" ) Date todaydate, 
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date fromdate, Model model) {
		
		List<UserPerDayCountDataDto> userPerDayCountDatalist = null;
		if(fromdate == null || todaydate == null ) {
		Calendar cal1 = new GregorianCalendar();
		cal1.add(Calendar.DATE, -1);
		cal1.set(Calendar.HOUR_OF_DAY, 0);
		cal1.set(Calendar.MINUTE, 0);
		cal1.set(Calendar.SECOND, 0);
		cal1.set(Calendar.MILLISECOND, 0);
		fromdate = cal1.getTime();
		
		Calendar cal2 = new GregorianCalendar();
		cal2.set(Calendar.HOUR_OF_DAY, 0);
		cal2.set(Calendar.MINUTE, 0);
		cal2.set(Calendar.SECOND, 0);
		cal2.set(Calendar.MILLISECOND, 0);
		todaydate = cal2.getTime();
		
		 userPerDayCountDatalist = userDetails.getUserPerDayCountDataDto(todaydate, fromdate);
		}else {
		 userPerDayCountDatalist = userDetails.getUserPerDayCountDataDto(todaydate, fromdate);
		}
		
		model.addAttribute("userPerDayCountDatalist",   userPerDayCountDatalist);	
		return "userPerDayCountDataView";
	} 

	
	
	@GetMapping("/userPerDayCountDataViewdownload")
	@ResponseBody
	public ResponseEntity<InputStreamResource> getViewLikesDownload(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date fromdate, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm" ) Date todaydate, Model model) {
		
		if ("".equals(todaydate)) {
			todaydate = null;
		}
		if ("".equals(fromdate)) {
			fromdate = null;
		}
		
		String filename = null;
		InputStreamResource file = null;
		if(fromdate == null || todaydate == null ) {
		Calendar cal1 = new GregorianCalendar();
		cal1.add(Calendar.DATE, -1);
		cal1.set(Calendar.HOUR_OF_DAY, 0);
		cal1.set(Calendar.MINUTE, 0);
		cal1.set(Calendar.SECOND, 0);
		cal1.set(Calendar.MILLISECOND, 0);
		fromdate = cal1.getTime();
		
		Calendar cal2 = new GregorianCalendar();
		cal2.set(Calendar.HOUR_OF_DAY, 0);
		cal2.set(Calendar.MINUTE, 0);
		cal2.set(Calendar.SECOND, 0);
		cal2.set(Calendar.MILLISECOND, 0);
		todaydate = cal2.getTime();
	
			filename = "userPerDayData.xls";
		 file = new InputStreamResource( fileService.loadUserPerDayData(todaydate, fromdate));
	}else {
		 file = new InputStreamResource( fileService.loadUserPerDayData(todaydate, fromdate ));
	}
		// InputStreamResource file = new
		// InputStreamResource(fileService.loadinActive(-30));

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
	}
	
	
//	@GetMapping("/userPerDayCountDataViewList") 
//	@ResponseBody
//	public Object getUserPerday(Model model, @Param("todaydate")@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date  todaydate, 
//			@Param("fromdate")@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date  fromdate) {
//		
//		if ("".equals(todaydate)) {
//			todaydate=null;
//		}
//		
//		if ("".equals(fromdate)) {
//			fromdate=null;
//		}
//			
//		List<UserPerDayCountDataDto> userPerDayCountDatalist = userDetails.getUserPerDayCountDataDto(todaydate, fromdate);
//		
//		return userPerDayCountDatalist;
//	}
	
}	
	