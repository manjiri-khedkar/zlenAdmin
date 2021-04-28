package mp.procurement;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import mp.procurement.dto.UserRegistrationDto;
import mp.procurement.model.AppUser;
import mp.procurement.repository.AppuserRepository;
import mp.procurement.service.RegistrationService;

@Controller
@RequestMapping("/user")
public class UserRegistrationController {

	@Autowired
	AppuserRepository appuserRepository;
	
	@Autowired 
	private RegistrationService registrationService;

	  @Autowired HttpSession session;
	  
	  
	       //for display user list
			
			  @GetMapping public ModelAndView list() 
			  { 
				  ModelAndView mav = new ModelAndView(); 
				  List<AppUser> appuserList = appuserRepository.findAll();
			      mav.addObject("user", new User()); 
			      mav.addObject("AppUserList", appuserList);
			      mav.setViewName("userlist"); 
			      return mav;
			      }
			 
	  
	  
			/*
			 * @RequestMapping(value = "/display", method = RequestMethod.GET) public
			 * ModelAndView View(Integer id) { 
			 * List<AppUser> appuserlist = new ArrayList<AppUser>();
			 * ModelAndView mv = new ModelAndView();
			 *  appuserlist = appuserRepository.findAll(); 
			 * mv.addObject("AppUserlist", appuserlist);
			 * mv.setViewName("userlist");
			 * 
			 * return mv;
			 * 
			 * }
			 */
	  
	  
	

	    @GetMapping("/adduser")
	    public ModelAndView addUser() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", new AppUser());
		mv.setViewName("add_user");
		return mv;
	}

	@PostMapping
	public String registerUserAccount(@ModelAttribute("user")@Valid UserRegistrationDto userDto, BindingResult result, Model model){ 
		
		  AppUser existing = registrationService.findByEmail(userDto.getEmail());
		  String password = userDto.getPassword();
		  AppUser appuser = (AppUser) session.getAttribute("user");
		  
		registrationService.save(userDto); 
		
	   return "redirect:/registration?success"; 
	  }

	
		@GetMapping("/editUser") /* /editUser/{id} */
	  
	  public String editAppUser(@PathVariable String id, Model model)
	  {
	  model.addAttribute("user", appuserRepository.findByUserId(id));
	  
	  return "edituser";
	  }


		@GetMapping("/deleteUser/{id}")
		public String deleteAppUser(@PathVariable long id, Model model)
		{
			appuserRepository.deleteById(id);
		   return "redirect:/user?deleteSuccess";
		}
		
		
}



