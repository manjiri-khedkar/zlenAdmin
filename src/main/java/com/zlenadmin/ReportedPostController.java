package com.zlenadmin;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zlenadmin.dao.ReportedPost;
import com.zlenadmin.dto.PollDto;
import com.zlenadmin.dto.ReportPostDto;

@Controller
public class ReportedPostController {

	@Autowired
	private ReportedPost reportedPost;
	
	@GetMapping("/reportedPostList")
	public Object pollList(Model model) { 
		ModelAndView mv = new ModelAndView();
		List<ReportPostDto> rpdList = reportedPost.getReportPost();
		mv.addObject("rpdList", rpdList);
		mv.setViewName("reportedPostList");
		return mv;
		
	}
	
	
}
