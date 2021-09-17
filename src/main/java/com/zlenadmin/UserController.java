package com.zlenadmin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.apache.tomcat.jni.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.zlenadmin.dto.RoleDto;
import com.zlenadmin.dto.UserDto;
import com.zlenadmin.model.AppUser;
import com.zlenadmin.model.Role;
import com.zlenadmin.repository.AppuserRepository;
import com.zlenadmin.repository.RoleRepository;
import com.zlenadmin.service.RegistrationServiceImpl;
import com.zlenadmin.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

private final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	AppuserRepository appuserRepository;
	
	@Autowired 
	private UserService userService;

    @Autowired 
	HttpSession session;
    
    @Autowired
    RoleRepository roleRepository;
    
	 @GetMapping("/addUser")
	 public ModelAndView addUser(Model model) {
		
		 ModelAndView mv = new ModelAndView();
		 mv.addObject("user", new AppUser());
		 
		 mv.addObject("roleList", roleRepository.findAll());
		 
		 mv.setViewName("adduser");
		return mv;
	}

	@PostMapping
	public String saveUser(@ModelAttribute("user") @Valid UserDto userDto, 
			   BindingResult result, Model model){ 
	    
		if (result.hasErrors()){
        	for (ObjectError error : result.getAllErrors()) {
        		logger.error("Error :" + error.getDefaultMessage());
			}
        	return "adduser";
        }

		userService.save(userDto); 
		 	
	   return "redirect:/user?success"; 
	  }
	
	@GetMapping 
	public ModelAndView list() { 
				 
		ModelAndView mav = new ModelAndView(); 
		List<AppUser> appuserList = appuserRepository.findAll();
		mav.addObject("user", new User()); 
		mav.addObject("appuserList", appuserList);
		mav.setViewName("listuser"); 
		return mav;
	}
	
	@GetMapping("/edituser/{id}")
	public String editUser(@PathVariable int id, Model model) {
		
	  ModelAndView mv = new ModelAndView();
	  AppUser appuserList = appuserRepository.findById(id);
	  model.addAttribute("user",appuserList );
	  System.out.println("appuserList==="+ appuserList);
	  List<Role> roleList =roleRepository.findAll();
	  model.addAttribute("roleList", roleList) ;
	  return "edituser";
	}
	
    @PostMapping(value="/update")
    public String updateUser(@ModelAttribute("user") @Valid UserDto userDto,
                                      BindingResult result,Model model){
    	
        if (result.hasErrors()){
        	for (ObjectError error : result.getAllErrors()) {
        		logger.error("Error :" + error.getDefaultMessage());
			}
            return "edituser";
        }
        
//        AppUser user = appuserRepository.findById(userDto.getId());
        userService.update(userDto);
        
        return "redirect:/user?editSuccess";
    }

	@GetMapping("/deleteUser/{id}")
	public String deleteAppUser(@PathVariable long id, Model model) {
		appuserRepository.deleteById(id);
		return "redirect:/user?deleteSuccess";
    }
	
	
	 
	
//	 AppUser sameEmailId = userService.findByUserId(userDto.getId());
//     if(sameEmailId != null) {
//     	model.addAttribute("error", "EmailId Already Exist.");
// 	    	if(isAdmin) {
// 	    		model.addAttribute("user", appuserRepository.findAll());
// 		     	
// 	    		return "adduser";
// 	    	} else {
// 	    		model.addAttribute("user", appuserRepository.findAll());
// 		    
// 	    		return "redirect:/user?success"; 
// 	    	}
//     }

	
	
		
}
