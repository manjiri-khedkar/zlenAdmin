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
import com.zlenadmin.api.entity.UserStoriesDetails;
import com.zlenadmin.dao.Poll;
import com.zlenadmin.dto.PollDto;
import com.zlenadmin.repository.PollRepository;
import com.zlenadmin.repository.UserDetailsRepository;
import com.zlenadmin.repository.UserStoriesDetailsRepository;

@Controller
public class PollController {
	
	@Autowired
	private Poll poll;
	
	
	@Autowired
	private UserDetailsRepository userDetailsRepository;
	
	@Autowired
	private PollRepository pollRepository;
	

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
			zlenWorld = null != null;
			
		}
		
		ModelAndView mv = new ModelAndView();
		List<PollDto> pdList = poll.getPoll(zlenCode,createdAt,zlenWorld);
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
			zlenWorld = null != null;
		}

		List<PollDto> pdList = poll.getPoll(zlenCode,createdAt,zlenWorld);
		return pdList;
	}
	
	@RequestMapping(value="/activePoll", method=RequestMethod.GET)
	public String ActivePoll(@RequestParam("id") long id,Model model) throws Exception {
		System.out.println("id=="+id);
		com.zlenadmin.api.entity.Poll poll = pollRepository.findOne(id);
		 if(poll.isBanned() == true) {
			 poll.setBanned(false);
		 }
		 pollRepository.save(poll);
		return "redirect:pollList?success";
	}
	
	@RequestMapping(value="/blockPoll", method=RequestMethod.GET)
	public String BlockPoll(@RequestParam("id") long id,Model model) throws Exception {
		System.out.println("id=="+id);
		com.zlenadmin.api.entity.Poll poll = pollRepository.findOne(id);
		 if(poll.isBanned() == false) {
			 poll.setBanned(true);
		 }
		 pollRepository.save(poll);
		return "redirect:pollList?success";
	}

	
	@RequestMapping(value="/activeUsers", method=RequestMethod.GET)
	public String ActiveUser(@RequestParam("id") long id, Model model) throws Exception {
		System.out.println("id=="+id);
		 UserDetails userDetails = userDetailsRepository.findById(id);
		 userDetails.setIs_banned(false);
		 userDetailsRepository.save(userDetails);
		 
		return "redirect:pollList?success";
	}
	
	@RequestMapping(value="/blockUsers", method=RequestMethod.GET)
	public String BlockUser(@RequestParam("id") long id, Model model) throws Exception {
		System.out.println("id=="+id);
		 UserDetails userDetails = userDetailsRepository.findById(id);
		 userDetails.setIs_banned(true);
		 userDetailsRepository.save(userDetails);
		 
		return "redirect:pollList?success";
	}
		

}
