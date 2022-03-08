package com.zlenadmin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.zlenadmin.dao.Poll;
import com.zlenadmin.dto.PollOptionDto;
import com.zlenadmin.dto.PollVoteDto;

@Controller
public class PollVoteController {
	
	@Autowired
	private Poll poll;
	
	@GetMapping("/pollVoteView/{id}")
	public String viewLikes(@PathVariable Long id, Model model) {
	
//		UserStoriesCommentDetails userComments = userCommentsRepository.findById(id);

		//List <UserStoriesCommentDetails>  userComments = userCommentsRepository.findBySnapId(id);
		List<PollVoteDto> pvdList = poll.getPollVote(id);

		
		model.addAttribute("pvdList",   pvdList);	
		return "pollVoteView";
	} 

}
