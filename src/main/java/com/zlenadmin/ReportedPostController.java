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

import com.zlenadmin.dao.ReportedPost;
import com.zlenadmin.dto.PollDto;
import com.zlenadmin.dto.ReportPostDto;

@Controller
public class ReportedPostController {

	@Autowired
	private ReportedPost reportedPost;
	
	@GetMapping("/reportedPostList")
	public Object reportedPostList(@RequestParam(required = false) String userZlenCode, @RequestParam(required = false) String postZlenCode,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date createdAt,Model model) {
		
		if ("".equals(userZlenCode)) {
			userZlenCode = null;
		}

		if ("".equals(postZlenCode)) {
			postZlenCode = null;
		}

		if ("".equals(createdAt)) {
			createdAt = null;
		}
		
		ModelAndView mv = new ModelAndView();
		List<ReportPostDto> rpdList = reportedPost.getReportPost(userZlenCode, postZlenCode, createdAt);
		mv.addObject("rpdList", rpdList);
		mv.setViewName("reportedPostList");
		return mv;
		
	}
	
	@GetMapping("/reportedPostListContents")
	@ResponseBody
	public Object getReportedPost(Model model, @Param("userZlenCode") String userZlenCode, @Param("postZlenCode") String postZlenCode,
			@Param("createdAt") @DateTimeFormat(pattern = "yyyy-MM-dd") Date createdAt) {
		// List result= service.queryForMovies();
		// List result = newrepo.getUserStories();

		if ("".equals(userZlenCode)) {
			userZlenCode = null;
		}

		if ("".equals(postZlenCode)) {
			postZlenCode = null;
		}

		if ("".equals(createdAt)) {
			createdAt = null;
		}

		List<ReportPostDto> rpdList = reportedPost.getReportPost(userZlenCode,postZlenCode,createdAt);
		return rpdList;
	}
	
	
}
