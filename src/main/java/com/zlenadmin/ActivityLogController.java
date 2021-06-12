package com.zlenadmin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zlenadmin.api.entity.UserActivityDetails;
import com.zlenadmin.api.entity.UserDetails;
import com.zlenadmin.dao.UserActivity;
import com.zlenadmin.dto.ActivityDto;
import com.zlenadmin.model.Role;
import com.zlenadmin.repository.UserActivityRepository;

@Controller
public class ActivityLogController {
	
	
	@Autowired
	UserActivityRepository useractivityRepo;
	
	@GetMapping("/activitylog")
	public ModelAndView userActivityList() 
	{
		ModelAndView mv = new ModelAndView();
		List<UserActivityDetails> userActivityList = useractivityRepo.findAll();
		mv.addObject("userActivity", new UserActivityDetails());
		mv.addObject("userActivity", userActivityList);
		mv.setViewName("activityLog");
		return mv;
	}
		 
	
}

