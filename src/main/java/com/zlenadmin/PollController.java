package com.zlenadmin;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zlenadmin.dao.Poll;
import com.zlenadmin.dto.PollDto;

@Controller
public class PollController {
	
	@Autowired
	Poll poll;
	
	@GetMapping("/pollList")
	public Object pollList(@RequestParam(required = false) String zlenCode,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date createdAt, 
			@RequestParam(required = false) boolean zlenWorld) {
		
		if ("".equals(zlenCode)) {
			zlenCode = null;
		}


		if ("".equals(createdAt)) {
			createdAt = null;
		}
		
		if ("".equals(zlenWorld)) {
			zlenWorld = (Boolean) null;
		}
		
		ModelAndView mv = new ModelAndView();
		List<PollDto> pdList = poll.getPoll(zlenCode, zlenWorld, createdAt);
		mv.addObject("pdList", pdList);
		mv.setViewName("pollList");
		return mv;
		
	}
	
	@GetMapping("/pollListContents")
	@ResponseBody
	public Object getPoll(Model model, @Param("zlenCode") String zlenCode,
			@Param("createdAt") @DateTimeFormat(pattern = "yyyy-MM-dd") Date createdAt,
			@Param("zlenWorld") boolean zlenWorld) {
		// List result= service.queryForMovies();
		// List result = newrepo.getUserStories();

		if ("".equals(zlenCode)) {
			zlenCode = null;
		}

		if ("".equals(createdAt)) {
			createdAt = null;
		}
		
		if ("".equals(zlenWorld)) {
			zlenWorld = (Boolean) null;
		}

		List<PollDto> pdList = poll.getPoll(zlenCode, zlenWorld, createdAt);
		return pdList;
	}
		

}
