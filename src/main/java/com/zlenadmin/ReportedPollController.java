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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zlenadmin.api.entity.UserDetails;
import com.zlenadmin.dao.ReportedPoll;
import com.zlenadmin.dto.ReportPostDto;
import com.zlenadmin.dto.ReportedPollDto;
import com.zlenadmin.repository.PollRepository;
import com.zlenadmin.repository.UserDetailsRepository;

@Controller
public class ReportedPollController {
	
	@Autowired
	private ReportedPoll reportedPoll;
	
	@Autowired
	private UserDetailsRepository userDetailsRepository;
	
	@Autowired
	private PollRepository pollRepository;
	
	@GetMapping("/reportedpollList")
	public Object reportedPostList(@RequestParam(required = false) String userZlenCode, @RequestParam(required = false) String pollZlenCode,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date createdAt, @RequestParam(required = false) String userMobile,Model model) {
		
		if ("".equals(userZlenCode)) {
			userZlenCode = null;
		}
		
		if ("".equals(userMobile)) {
			userMobile = null;
		}

		if ("".equals(pollZlenCode)) {
			pollZlenCode = null;
		}

		if ("".equals(createdAt)) {
			createdAt = null;
		}
		
		ModelAndView mv = new ModelAndView();
		List<ReportedPollDto> rpdList = reportedPoll.getReportPoll(userZlenCode, pollZlenCode, createdAt,userMobile);
		mv.addObject("rpdList", rpdList);
		mv.setViewName("reportedpollList");
		return mv;
		
	}
	
	@GetMapping("/reportedPollListContents")
	@ResponseBody
	public Object getReportedPost(Model model, @Param("userZlenCode") String userZlenCode, @Param("pollZlenCode") String pollZlenCode,
			@Param("createdAt") @DateTimeFormat(pattern = "yyyy-MM-dd") Date createdAt, @Param("userMobile") String userMobile) {
		// List result= service.queryForMovies();
		// List result = newrepo.getUserStories();

		if ("".equals(userZlenCode)) {
			userZlenCode = null;
		}
		
		if ("".equals(userMobile)) {
			userMobile = null;
		}

		if ("".equals(pollZlenCode)) {
			pollZlenCode = null;
		}
		
		if ("".equals(createdAt)) {
			createdAt = null;
		}

		List<ReportedPollDto> rpdList = reportedPoll.getReportPoll(userZlenCode, pollZlenCode, createdAt,userMobile);
		return rpdList;
	}
	
	@RequestMapping(value="/reportActivePoll", method=RequestMethod.GET)
	public String ActivePoll(@RequestParam("id") long id,Model model) throws Exception {
		System.out.println("id=="+id);
		com.zlenadmin.api.entity.Poll poll = pollRepository.findByid(id);
		 if(poll.isBanned() == true) {
			 poll.setBanned(false);
		 }
		 pollRepository.save(poll);
		return "redirect:reportedpollList?success";
	}
	
	@RequestMapping(value="/reportBlockPoll", method=RequestMethod.GET)
	public String BlockPoll(@RequestParam("id") long id,Model model) throws Exception {
		System.out.println("id=="+id);
		com.zlenadmin.api.entity.Poll poll = pollRepository.findByid(id);
		 if(poll.isBanned() == false) {
			 poll.setBanned(true);
		 }
		 pollRepository.save(poll);
		return "redirect:reportedpollList?success";
	}
	
	@RequestMapping(value="/reportPollActiveUsers", method=RequestMethod.GET)
	public String ActiveUser(@RequestParam("id") long id, Model model) throws Exception {
		System.out.println("id=="+id);
		 UserDetails userDetails = userDetailsRepository.findById(id);
		 userDetails.setIs_banned(false);
		 userDetailsRepository.save(userDetails);
		 
		return "redirect:reportedpollList?success";
	}
	
	@RequestMapping(value="/reportPollBlockUsers", method=RequestMethod.GET)
	public String BlockUser(@RequestParam("id") long id, Model model) throws Exception {
		System.out.println("id=="+id);
		 UserDetails userDetails = userDetailsRepository.findById(id);
		 userDetails.setIs_banned(true);
		 userDetailsRepository.save(userDetails);
		 
		return "redirect:reportedpollList?success";
	}
	

}
