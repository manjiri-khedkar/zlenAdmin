package com.zlenadmin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zlenadmin.dao.UserActivity;
import com.zlenadmin.dto.ActivityDto;
import com.zlenadmin.repository.UserActivityRepository;

@Controller
public class ActivityLogController {
	
	
	@Autowired
	UserActivityRepository useractivityRepo;
	
	@Autowired
	UserActivity useractivity;
	
	
	  @RequestMapping(value = "/activitylog", method = RequestMethod.GET)
	  
	  public ModelAndView list(Model model)
	  { 
		
	  
	  List<ActivityDto> userActivityList = useractivity.getUserActivity(null);
	  
	  ModelAndView mav = new ModelAndView();

	  mav.addObject("ActivityDto", new ActivityDto());
	  mav.addObject("userActivityList", userActivityList);
	  
	  mav.setViewName("activityLog");
	  return mav;
	  }
	
		
		
		  @RequestMapping(value = "/activity-log", method = RequestMethod.GET)
		  
		  @ResponseBody 
		  public Object getUserActivity(Model model, @Param("userId") String userId) {
		  
		  System.out.println("userId " +userId);
		  
		  List<ActivityDto> detailsList = useractivity.getUserActivity(userId);
		  
		  return detailsList;
		  
		  }
		 
	
}

