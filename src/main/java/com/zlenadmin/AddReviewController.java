package com.zlenadmin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.eclipse.jetty.server.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zlenadmin.api.entity.ReviewComments;
import com.zlenadmin.dao.UserFeedBack;
import com.zlenadmin.dto.ReviewCommentDto;
import com.zlenadmin.dto.RoleDto;
import com.zlenadmin.dto.UserFeedBackDto;
import com.zlenadmin.model.Role;
import com.zlenadmin.model.SessionUser;
import com.zlenadmin.repository.ReviewCommentRepository;
import com.zlenadmin.repository.UserFeedBackRepository;
import com.zlenadmin.service.ReviewCommentService;

@Controller
@RequestMapping("/review")
public class AddReviewController {
	
	@Autowired
	private ReviewCommentService reviewCommentService;
	
	@Autowired
	private ReviewCommentRepository reviewCommentRepository;
	
	@Autowired
	private SessionUser sessionUser;
	
	@Autowired
	private UserFeedBack userFeedBack;
	
	@RequestMapping("/addReview/{id}")
	public ModelAndView addRole(@PathVariable Long id,Model model) {
		List<UserFeedBackDto> username1 = userFeedBack.getUserFeedBack();
		List<String> name=new ArrayList<String>();
		for(UserFeedBackDto username:username1) {
				if(id==username.getId()) {
			String uname= username.getUser_name();
				 name.add(uname);
				}
		}
			ModelAndView mv = new ModelAndView();
		ReviewComments comment =  reviewCommentService.getReviewComment(id);
		if (comment==null) {
			ReviewCommentDto commentDto = new ReviewCommentDto();
			commentDto.setFeedbackId(id);
			mv.addObject("addreview", commentDto);	
		}else {
			ReviewCommentDto commentDto = new ReviewCommentDto();
			commentDto.setComments(comment.getComments());
			commentDto.setFeedbackId(comment.getFeedbackId());
			commentDto.setReviewId(comment.getReviewId());
			for(String name1:name) {
				if(commentDto.getFeedbackId()== id) {
			commentDto.setUsername(name1);
				}
			}
			mv.addObject("addreview", commentDto);
		}
		mv.setViewName("addReview");
		return mv;
	}
	
	@RequestMapping(value="/addReview1",method = RequestMethod.POST)
	public String saveRole(@ModelAttribute("addreview")  ReviewCommentDto reviewCommentDto, Model model) {
		
		reviewCommentService.save(reviewCommentDto);
		
		return "redirect:/userFeedBackList?success";		 
	}

}
