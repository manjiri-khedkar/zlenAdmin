package com.zlenadmin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.zlenadmin.dao.UserActivity;
import com.zlenadmin.repository.UserActivityRepository;

@Controller
public class ActivityLogController {

	@Autowired
	UserActivityRepository useractivityRepo;

	@Autowired
	UserActivity userActivity;
	
//	@GetMapping("/activitylog")
//	public ModelAndView userActivityList() 
//	{
//		ModelAndView mv = new ModelAndView();
//		List<UserActivityDetails> userActivityList = useractivityRepo.findAll();
//		mv.addObject("userActivity", new UserActivityDetails());
//		mv.addObject("userActivity", userActivityList);
//		mv.setViewName("activityLog");
//		return mv;
//	}
	
	@GetMapping("/activitylog")
	public ModelAndView userActivityList() {
		
		ModelAndView mv = new ModelAndView();
		List userActivityList = userActivity.getUserActivity(0, null, null, null, null,null);
		mv.addObject("userActivityList", userActivityList);
		mv.setViewName("activityLog");
		return mv;
	}
	
}

