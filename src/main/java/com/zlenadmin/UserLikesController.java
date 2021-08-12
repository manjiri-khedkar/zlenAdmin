package com.zlenadmin;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.zlenadmin.dao.UserStories;
import com.zlenadmin.dto.UserLikesDto;

@Controller

public class UserLikesController {
	

	
	@Autowired
	UserStories userStories ;
	
	@GetMapping("/userviewLikes/{id}")
	public String viewLikes(@PathVariable Long id, Model model) {
	
//		UserStoriesCommentDetails userComments = userCommentsRepository.findById(id);

		//List <UserStoriesCommentDetails>  userComments = userCommentsRepository.findBySnapId(id);
		List<UserLikesDto>  userLikes =userStories.getUserLikes(id);

		
		model.addAttribute("userLikes",   userLikes);	
		return "userviewLikes";
	} 

	
	

}
