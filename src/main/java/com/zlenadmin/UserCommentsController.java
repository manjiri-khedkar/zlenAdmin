package com.zlenadmin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.zlenadmin.api.entity.UserStoriesCommentDetails;
import com.zlenadmin.dao.UserStories;
import com.zlenadmin.dto.StoryCommentDto;
import com.zlenadmin.repository.UserCommentsRepository;

@Controller
public class UserCommentsController {
	
	@Autowired
	UserCommentsRepository userCommentsRepository; 
	
	@Autowired
	UserStories userStories ;
	
	@GetMapping("/userViewComment/{id}")
	public String viewComments(@PathVariable Long id, Model model) {
	
//		UserStoriesCommentDetails userComments = userCommentsRepository.findById(id);

		//List <UserStoriesCommentDetails>  userComments = userCommentsRepository.findBySnapId(id);
		List<StoryCommentDto>  userComments =userStories.getUserStoriesComments(id);

		
		model.addAttribute("userComments",   userComments);	
		return "userViewComments";
	} 

}
