package com.zlenadmin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.zlenadmin.api.entity.UserStoriesCommentDetails;
import com.zlenadmin.repository.UserCommentsRepository;

@Controller
public class UserCommentsController {
	
	@Autowired
	UserCommentsRepository userCommentsRepository; 
	
	@GetMapping("/userViewComment/{id}")
	public String viewComments(@PathVariable Long id, Model model) {
	
//		UserStoriesCommentDetails userComments = userCommentsRepository.findById(id);

		List <UserStoriesCommentDetails>  userComments = userCommentsRepository.findAll();
//		model.addAttribute("userComments", userCommentsRepository.getUserComments(userComments.getSnapId()));

		
		model.addAttribute("userComments",   userComments);	
		return "userViewComments";
	} 

}
