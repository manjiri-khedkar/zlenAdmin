package com.zlenadmin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zlenadmin.api.entity.UserActivityDetails;
import com.zlenadmin.api.entity.UserDetails;
import com.zlenadmin.dao.UserActivity;
import com.zlenadmin.dto.ActivityDto;
import com.zlenadmin.repository.UserActivityRepository;

@Controller
public class ActivityLogController {

	@Autowired
	UserActivityRepository useractivityRepo;

	@Autowired
	UserActivity userActivity;
	
	@GetMapping("/activitylog")
	public ModelAndView userActivityList() {
		
		ModelAndView mv = new ModelAndView();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		java.util.Date d=null;
		try {
			d = sdf.parse("2021-06-05");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<ActivityDto> userActivityList = userActivity.getUserActivity(null, null, null);
		mv.addObject("userActivityList", userActivityList);
		mv.setViewName("activityLog");
		return mv;
	}
	
	@GetMapping("/userActivityListContents") 
	@ResponseBody
	public Object getUserActivity(Model model,@Param("zlenCode") String  zlenCode,@Param("createdDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date  createdDate, @Param("userMobile") String userMobile) {
		
		if ("".equals(zlenCode)) {
			zlenCode=null;
		}
		
		if ("".equals(userMobile)) {
			userMobile=null;
		}
		
		if ("".equals(createdDate)) {
			createdDate=null;
		}
		
		List result = userActivity.getUserActivity(zlenCode,createdDate,userMobile);

		return  result;
		
	}
	
}

