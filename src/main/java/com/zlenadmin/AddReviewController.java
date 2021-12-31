package com.zlenadmin;

import javax.validation.Valid;

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
import com.zlenadmin.dto.ReviewCommentDto;
import com.zlenadmin.dto.RoleDto;
import com.zlenadmin.model.Role;
import com.zlenadmin.repository.ReviewCommentRepository;
import com.zlenadmin.repository.UserFeedBackRepository;
import com.zlenadmin.service.ReviewCommentService;

@Controller
@RequestMapping("/review")
public class AddReviewController {
	
	@Autowired
	private ReviewCommentService reviewCommentService;
	
	@RequestMapping("/addReview/{id}")
	public ModelAndView addRole(@PathVariable Long id,Model model) {

		ReviewComments comment =  reviewCommentService.getReviewComment(id);
		
		ModelAndView mv = new ModelAndView();
		if (comment==null) {
			ReviewCommentDto commentDto = new ReviewCommentDto();
			commentDto.setFeedbackId(id);
			mv.addObject("addreview", commentDto);	
		}else {
			ReviewCommentDto commentDto = new ReviewCommentDto();
			commentDto.setComments(comment.getComments());
			commentDto.setFeedbackId(comment.getFeedbackId());
			commentDto.setReviewId(comment.getReviewId());
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
