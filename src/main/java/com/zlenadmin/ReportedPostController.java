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
import com.zlenadmin.dao.ReportedPost;
import com.zlenadmin.dto.PollDto;
import com.zlenadmin.dto.ReportPostDto;
import com.zlenadmin.repository.UserDetailsRepository;
import com.zlenadmin.repository.UserStoriesDetailsRepository;

@Controller
public class ReportedPostController {

	@Autowired
	private ReportedPost reportedPost;
	
	@Autowired
	private UserStoriesDetailsRepository userStoriesDetailsRepository;
	
	@Autowired
	private UserDetailsRepository userDetailsRepository;
	
	@GetMapping("/reportedPostList")
	public Object reportedPostList(@RequestParam(required = false) String userZlenCode, @RequestParam(required = false) String postZlenCode,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date createdAt, @RequestParam(required = false) String userMobile,Model model) {
		
		if ("".equals(userZlenCode)) {
			userZlenCode = null;
		}

		if ("".equals(userMobile)) {
			userMobile = null;
		}

		
		if ("".equals(postZlenCode)) {
			postZlenCode = null;
		}

		if ("".equals(createdAt)) {
			createdAt = null;
		}
		
		ModelAndView mv = new ModelAndView();
		List<ReportPostDto> rpdList = reportedPost.getReportPost(userZlenCode, postZlenCode, createdAt,userMobile);
		mv.addObject("rpdList", rpdList);
		mv.setViewName("reportedPostList");
		return mv;
		
	}
	
	@GetMapping("/reportedPostListContents")
	@ResponseBody
	public Object getReportedPost(Model model, @Param("userZlenCode") String userZlenCode, @Param("postZlenCode") String postZlenCode,
			@Param("createdAt") @DateTimeFormat(pattern = "yyyy-MM-dd") Date createdAt, @Param("userMobile") String userMobile) {
		// List result= service.queryForMovies();
		// List result = newrepo.getUserStories();

		if ("".equals(userZlenCode)) {
			userZlenCode = null;
		}

		if ("".equals(userMobile)) {
			userMobile = null;
		}
		
		if ("".equals(postZlenCode)) {
			postZlenCode = null;
		}

		if ("".equals(createdAt)) {
			createdAt = null;
		}

		List<ReportPostDto> rpdList = reportedPost.getReportPost(userZlenCode,postZlenCode,createdAt,userMobile);
		return rpdList;
	}
	
	@RequestMapping(value="/reportactivePosts", method=RequestMethod.GET)
	public String ActivePost(@RequestParam("id") long id,Model model) throws Exception {
		System.out.println("id=="+id);
		 UserStoriesDetails storiesDto = userStoriesDetailsRepository.findOne(id);
		 if(storiesDto.isBanned() == true) {
		 storiesDto.setBanned(false);
		 }
		 userStoriesDetailsRepository.save(storiesDto);
		return "redirect:reportedPostList?success";
	}
	
	@RequestMapping(value="/reportblockPosts", method=RequestMethod.GET)
	public String BlockPost(@RequestParam("id") long id,Model model) throws Exception {
		System.out.println("id=="+id);
		 UserStoriesDetails storiesDto = userStoriesDetailsRepository.findOne(id);
		 if(storiesDto.isBanned() == false) {
		 storiesDto.setBanned(true);
		 }
		 userStoriesDetailsRepository.save(storiesDto);
		return "redirect:reportedPostList?success";
	}

	
	@RequestMapping(value="/reportactiveUsers", method=RequestMethod.GET)
	public String ActiveUser(@RequestParam("id") long id, Model model) throws Exception {
		System.out.println("id=="+id);
		 UserDetails userDetails = userDetailsRepository.findById(id);
		 userDetails.setIs_banned(false);
		 userDetailsRepository.save(userDetails);
		 
		return "redirect:reportedPostList?success";
	}
	
	@RequestMapping(value="/reportblockUsers", method=RequestMethod.GET)
	public String BlockUser(@RequestParam("id") long id, Model model) throws Exception {
		System.out.println("id=="+id);
		 UserDetails userDetails = userDetailsRepository.findById(id);
		 userDetails.setIs_banned(true);
		 userDetailsRepository.save(userDetails);
		 
		return "redirect:reportedPostList?success";
	}
	
	
}
