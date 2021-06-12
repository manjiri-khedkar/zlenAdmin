package com.zlenadmin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zlenadmin.api.entity.UserStoriesDetails;
import com.zlenadmin.dao.UserStories;
import com.zlenadmin.repository.UserStoriesDetailsRepository;

@Controller
@RequestMapping("/stories")
public class UserStoriesController {

	@Autowired
	UserStoriesDetailsRepository userStoriesDetailsRepository;
	
	@Autowired
	private UserStories userStories;
	
	@GetMapping
	public ModelAndView list(Model model) {
		
		List storieslist = userStories.getUserStories(null, null);
		ModelAndView mav = new ModelAndView();
		mav.addObject("storiesList", storieslist);
		mav.setViewName("stories");
		return mav;
	}
	
}
