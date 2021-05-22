package com.zlenadmin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.zlenadmin.model.UserStoriesDetails;
import com.zlenadmin.repository.UserStoriesDetailsRepository;

@Controller
@RequestMapping("/stories")
public class UserStoriesController {

	@Autowired
	UserStoriesDetailsRepository userStoriesDetailsRepository;
	
	@GetMapping
	public ModelAndView list(Model model) {
		
		List<UserStoriesDetails> storieslist = userStoriesDetailsRepository.findAll();
		ModelAndView mav = new ModelAndView();
		mav.addObject("stories", new UserStoriesDetails());
		mav.addObject("storiesList", storieslist);
		mav.setViewName("stories");
		return mav;
	}
	
}
