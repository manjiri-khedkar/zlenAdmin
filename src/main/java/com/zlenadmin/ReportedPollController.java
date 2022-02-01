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

import com.zlenadmin.dao.ReportedPoll;
import com.zlenadmin.dto.ReportPostDto;
import com.zlenadmin.dto.ReportedPollDto;

@Controller
public class ReportedPollController {
	
	@Autowired
	private ReportedPoll reportedPoll;
	
	@GetMapping("/reportedpollList")
	public Object reportedPostList(@RequestParam(required = false) String userZlenCode, @RequestParam(required = false) String pollZlenCode,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date createdAt,Model model) {
		
		if ("".equals(userZlenCode)) {
			userZlenCode = null;
		}

		if ("".equals(pollZlenCode)) {
			pollZlenCode = null;
		}

		if ("".equals(createdAt)) {
			createdAt = null;
		}
		
		ModelAndView mv = new ModelAndView();
		List<ReportedPollDto> rpdList = reportedPoll.getReportPoll(userZlenCode, pollZlenCode, createdAt);
		mv.addObject("rpdList", rpdList);
		mv.setViewName("reportedpollList");
		return mv;
		
	}
	
	@GetMapping("/reportedPollListContents")
	@ResponseBody
	public Object getReportedPost(Model model, @Param("userZlenCode") String userZlenCode, @Param("pollZlenCode") String pollZlenCode,
			@Param("createdAt") @DateTimeFormat(pattern = "yyyy-MM-dd") Date createdAt) {
		// List result= service.queryForMovies();
		// List result = newrepo.getUserStories();

		if ("".equals(userZlenCode)) {
			userZlenCode = null;
		}

		if ("".equals(pollZlenCode)) {
			pollZlenCode = null;
		}
		
		if ("".equals(createdAt)) {
			createdAt = null;
		}

		List<ReportedPollDto> rpdList = reportedPoll.getReportPoll(userZlenCode, pollZlenCode, createdAt);
		return rpdList;
	}
	
	

}
