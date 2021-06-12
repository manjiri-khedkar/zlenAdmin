package com.zlenadmin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.zlenadmin.repository.UserDetailsRepository;

@Controller
public class UserFormController {
	
	@Autowired
	UserDetailsRepository userDetailsRepository;
	
//	@Autowired
//	UserDto userDto;
	
//	@GetMapping("/userViewForm")
//	public ModelAndView userInfo(Model model)
//	{
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("user", new User());
//		mv.setViewName("userForm");
//		return mv;	
//	}
	
	@GetMapping("/userViewForm/{id}")
	public String viewProfile(@PathVariable Long id, Model model) {
		model.addAttribute("user", userDetailsRepository.findById(id));
		return "userViewForm";
	} 
	


	
}
