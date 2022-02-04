package com.zlenadmin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.zlenadmin.dao.Poll;
import com.zlenadmin.dto.PollOptionDto;
import com.zlenadmin.dto.UserLikesDto;

@Controller
public class PollOptionController {
	
	@Autowired
	private Poll poll;
	
	@GetMapping("/pollOptionView/{id}")
	public String viewLikes(@PathVariable Long id, Model model) {
	
//		UserStoriesCommentDetails userComments = userCommentsRepository.findById(id);

		//List <UserStoriesCommentDetails>  userComments = userCommentsRepository.findBySnapId(id);
		List<PollOptionDto> podList = poll.getPollOption(id);

		
		model.addAttribute("podList",   podList);	
		return "pollOptionView";
	} 

}
