package mp.procurement;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.tomcat.jni.User;
import org.json.JSONArray;
import org.json.JSONObject;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import mp.procurement.dto.UserRegistrationDto;
import mp.procurement.model.AppUser;
import mp.procurement.repository.AppuserRepository;
import mp.procurement.service.RegistrationService;
import mp.procurement.service.RegistrationServiceImpl;

@Controller
@RequestMapping("/user")
public class UserRegistrationController {

	@Autowired
	AppuserRepository appuserRepository;
	
	@Autowired 
	private RegistrationService registrationService;
	
	@Autowired
	private RegistrationServiceImpl serviceimpl;

	  @Autowired HttpSession session;

	private String list;
	  
	  
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
	
	
		@GetMapping("/barChart")
		public String getAllAppUser1(Model model) {	
			
		    return "barGraph";
		
		}
		
		
		@GetMapping("/bar-chart")
		 @ResponseBody
		 public Map<String, Object> getAllAppUser(Model model)
		//public String getAllAppUser(Model model)
		  
		 {
			 int counter = 0;
			 List<AppUser> list= serviceimpl.findAll();
	         System.out.println("list====" +list.size());
	        
	         JSONArray appUserArray = new JSONArray();
	         JSONObject finalObj = new JSONObject();
			 Map<String, Object> finalMap = new HashMap();
		
			 int count = 0;
			
			 List<String> firstname = new ArrayList<>();
			 List<String> date = new ArrayList<>();
	        	AppUser appuser = new AppUser();
			      for(int i=0;i<list.size();i++) {
			  
			    	  Map<String, String> obj = new HashMap();
			    	  appuser = list.get(i);
			    	  firstname.add(list.get(i).getFirstName());
			    	  date.add(list.get(i).getDate());
			    	
			    	  	  
			  } 
			      
			      Map<String, String> dateCount = new HashMap<>();
			      Set<String> uniqueDate = new HashSet<String>(date);
			      
			      List<String> listt = new ArrayList<String> ();
			      List<Integer> dateCounter = new ArrayList<Integer> ();
			      for (String subset : uniqueDate) {
			    	  
			          listt.add(subset);
			          Collections.sort(listt);
			         
			      }
			      for(int i = 0; i < listt.size(); i++) {
			    	  int counterr = 0;
			    	  for(int j = 0; j< date.size(); j++) {
			    		  if(listt.get(i).equals(date.get(j)))
			    			  counterr++;
			    	  }
			    	  dateCounter.add(Integer.valueOf(counterr));
			      }
			     
			     
			
			    
			      //Unique date list
			      System.out.println("listt" + listt);
			      System.out.println("datecounter" +dateCounter);
			      
			      
			      //System.out.println("this is unique date list:" + dateCount);
			    
			      
			      System.out.println("name -->"+ firstname);
			      System.out.println("date -->"+ date);
			      
			      model.addAttribute("datecounter", dateCounter);
			      model.addAttribute("listt", listt);
			    
			     
			      
			      finalMap.put("name", firstname);
			      finalMap.put("date", date);
			      finalMap.put("listt", listt);
			      finalMap.put("datecounter", dateCounter);

			      return finalMap;
		  }

		@GetMapping("/edituser/{id}") /* /editUser/{id} */
	  
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



