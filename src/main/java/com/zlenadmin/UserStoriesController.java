package com.zlenadmin;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zlenadmin.api.entity.UserStoriesDetails;
import com.zlenadmin.dao.ImageStories;
import com.zlenadmin.dao.UserStories;
import com.zlenadmin.repository.UserStoriesDetailsRepository;

@Controller
@RequestMapping("/stories")
public class UserStoriesController {

	@Autowired
	UserStoriesDetailsRepository userStoriesDetailsRepository;
	
	@Autowired
	private UserStories userStories;
	
	@Autowired
	ImageStories imageStories;
	
	@GetMapping
	public ModelAndView list(Model model) {
		
		List storieslist = userStories.getUserStories(null, null, null);
		ModelAndView mav = new ModelAndView();
		mav.addObject("storiesList", storieslist);
		mav.setViewName("stories");
		return mav;
	}
	
	@GetMapping("/storiesImg") 
	@ResponseBody
	public Object getUserImg(Model model,@Param("zlenCode") String  zlenCode,@Param("mimeType") String  mimeType,@Param("uploadedDateTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date  uploadedDateTime) {
		
		if ("".equals(zlenCode)) {
			zlenCode=null;
		}
	
		if ("All".equals(mimeType)) {
			mimeType=null;
		}
		
		if ("".equals(uploadedDateTime)) {
			uploadedDateTime=null;
		}
		
		List result = imageStories.getStories(zlenCode,mimeType,uploadedDateTime);

		return  result;
		
	}
	
	
}
