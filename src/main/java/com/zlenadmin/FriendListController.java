package com.zlenadmin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zlenadmin.api.entity.UserDetails;
import com.zlenadmin.repository.UserDetailsRepository;

@Controller
public class FriendListController {
	
	
	@Autowired
	UserDetailsRepository userdetailsRepository;
	  

	@GetMapping("/friendList/{id}")
	public String getUseStoriesDetails(@PathVariable Long id, Model model, @Param("userId") String userId) {
		
		UserDetails user = userdetailsRepository.findById(id);
		model.addAttribute("friendDetails", userdetailsRepository.getUserFriends(user.getUserId()));
		return "friendList";
	} 
}
