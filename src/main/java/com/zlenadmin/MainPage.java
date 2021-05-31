package com.zlenadmin;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;

import com.zlenadmin.api.entity.UserDetails;
import com.zlenadmin.api.entity.UserStoriesDetails;
import com.zlenadmin.dao.UserStories;
import com.zlenadmin.email.ApplicationMailer;
import com.zlenadmin.model.AppUser;
import com.zlenadmin.model.SessionUser;
import com.zlenadmin.repository.AppuserRepository;
import com.zlenadmin.repository.UserDetailsRepository;
import com.zlenadmin.repository.UserStoriesDetailsRepository;
import com.zlenadmin.service.RegistrationServiceImpl;

@Controller
@Component
@SessionScope
public class MainPage {

	@Autowired
	ApplicationMailer mailer;
	WebDriver dr = null;

	@Autowired
	private SessionUser sessionUser;

	@Autowired
	private AppuserRepository appuserRepository;
	
	@Autowired
	private RegistrationServiceImpl serviceimpl;
	
	@Autowired
	UserDetailsRepository userDetailsRepository;
	
	@Autowired
	UserStoriesDetailsRepository userStoriesDetailsRepository;
	
	@Autowired
	private UserStories userStoriesRepo;
	
	
	

	
	@GetMapping(value = "/")
	public String indexView(@ModelAttribute AppUser users) 
	{
		return "redirect:/login";
	} 
	
	@RequestMapping("/adminHomeDashboard")
	public ModelAndView showMessage() {

		// appuserRepository.addActivity(sessionUser.getUserId(), "adminHomeDashboard",
		// System.currentTimeMillis());
		if (true) {
			ModelAndView model = new ModelAndView("home");
			return model;
		} else {
			ModelAndView model = new ModelAndView("licenseError");
			return model;
		}
	}

	@RequestMapping("/dashboard")
	public ModelAndView dashboard() {
		//appuserRepository.addActivity(sessionUser.getUserId(), "dashboard", System.currentTimeMillis());
		ModelAndView model = new ModelAndView("dashboard");
		return model;
	}
	
	@GetMapping("/dashboard/bar-chart")
	 @ResponseBody
	 public Map<String, Object> registrationGraphData(Model model) {
		 
		List<Object[]> list = userDetailsRepository.getGraphQuery();
		  
		List<BigInteger> list1 = new ArrayList<BigInteger>();
		List<Date> list2 = new ArrayList<Date>();
		  
		  
		  for(Object[] dataList : list) {
			BigInteger count =(BigInteger) dataList[0];
			Date createdDate = (Date)dataList[1];
			list1.add(count);
			list2.add(createdDate);
		  }
		  
		  Map<String, Object> finalMap = new HashMap();
		      
		 model.addAttribute("datecounter", list1);
		      
		 finalMap.put("date", list2);
		 finalMap.put("listt", list2);
		 finalMap.put("datecounter", list1);

        return finalMap;
	 }
	
	@GetMapping("/dashboard/stories-bar-chart")
	 @ResponseBody
	 public Object getStoriesGraphData(Model model) {
		 
		List<Object[]> userList= userStoriesDetailsRepository.getStoriesGraphQuery();
	    
	    List<BigInteger> list1 = new ArrayList<BigInteger>();
	    List<String> dateList = new ArrayList<String>();
	    List<BigInteger> list2 = new ArrayList<BigInteger>();
	    List<BigInteger> list3 = new ArrayList<BigInteger>();
	    List<HashMap<String,Object>> mainList = new ArrayList<HashMap<String,Object>>();
	    
	    HashMap<String, HashMap<String,BigInteger>> datewise_hm= new HashMap<String, HashMap<String,BigInteger>>();
	    Date curDate = null;
	    HashMap<String,BigInteger> inner_hm= null;
 	    for(Object[] list :userList) {
	    	BigInteger count = (BigInteger) list[0];
	    	Date createdDate = (Date)list[1];
	    	String mimeType = (String)list[2];
	    	
	    	if ((curDate==null) || (curDate!=null && !curDate.equals(createdDate))) {
	    		curDate= createdDate;
	    		inner_hm = new HashMap<String,BigInteger>();
	    			datewise_hm.put(createdDate.toString(),inner_hm);
	    	}
	    	inner_hm.put(mimeType, count);
	    }
 	    datewise_hm.put(curDate.toString(),inner_hm);
 	    
 	    for (String key : datewise_hm.keySet()) {
 	    	HashMap<String,BigInteger> datewiseData = datewise_hm.get(key);
 	    	dateList.add(key);
 	    		
 	    		if (datewiseData.containsKey("text/html")) {
 	    			
					list1.add(datewiseData.get("text/html"));
				}else {
					list1.add(BigInteger.ZERO);
				}
 	    		
 	    		BigInteger count = BigInteger.ZERO;
 	    		if (datewiseData.containsKey("image/jpg") ) {
 	    			count = count.add(datewiseData.get("image/jpg"));
				}

 	    		if (datewiseData.containsKey("image/png") ) {
 	    			count = count.add(datewiseData.get("image/png"));
				}
 	    		
 	    		if (datewiseData.containsKey("image") ) {
					count = count.add(datewiseData.get("image"));
				}

				list2.add(count);

 	    		
 	    		if (datewiseData.containsKey("video")) {
					list3.add(datewiseData.get("video"));
				}else {
					list3.add(BigInteger.ZERO);
				}
 	    }
 	    
 	     HashMap<String, Object> finalMap1 = new HashMap<String, Object>();
    	 finalMap1.put("name","text/html");
    	 finalMap1.put("data",list1);
    	 
    	 HashMap<String, Object> finalMap2 = new HashMap<String, Object>();
    	 finalMap2.put("name","image/jpg");
    	 finalMap2.put("data",list2);
    	 
    	 HashMap<String, Object> finalMap3 = new HashMap<String, Object>();
    	 finalMap3.put("name","video");
    	 finalMap3.put("data",list3);
    	 
    	 mainList.add(finalMap1);
    	 mainList.add(finalMap2);
    	 mainList.add(finalMap3);
    	 
    	 HashMap<String, Object> finalMap4 = new HashMap<String, Object>();
    	 finalMap4.put("dates",dateList);
    	 finalMap4.put("count",mainList);
    	 
    	return finalMap4;
	}
	
	@GetMapping("/usersList")
	public ModelAndView userDetailsList() {
		
		ModelAndView mv = new ModelAndView();
		List<UserDetails> userDetailsList = userDetailsRepository.findAll();
		mv.addObject("userListDetails", new UserDetails());
		mv.addObject("userListDetails", userDetailsList);
		mv.setViewName("userDetailsList");
		return mv;
	}
	
	@GetMapping("/userDetailsListContents") 
	@ResponseBody
	public ArrayList<UserDetails> getUserDetails(Model model ,@Param("userName") String userName, @Param("userMobile") String userMobile,@Param("zlenCode") String zlenCode,@Param("deviceType") String deviceType)
	{
//		List<Object[]> userDetailsContentList= userDetailsRepository.getDetailsData();
		
		//ArrayList<UserDetails> userDetailsList1=userDetailsRepository.getUserDetails();
		if ("All".equals(deviceType)) {
			deviceType=null;
		}
		if ("".equals(userMobile)) {
			userMobile=null;
		}
		if ("".equals(zlenCode)) {
			zlenCode=null;
		}
		
		ArrayList<UserDetails> userDetailsList = userDetailsRepository.getUserDetails(userName, userMobile, zlenCode, deviceType);
	
		return userDetailsList;
		
	}
	
	@GetMapping("/userStoriesListContents") 
	@ResponseBody
	public Object getUserStories(Model model)
	{

		//List result= service.queryForMovies();
		//List result = newrepo.getUserStories();
		List result = userStoriesRepo.getUserStories(null);
		return  result;
		
	}
	
	@GetMapping("/userStoriesList")
	public ModelAndView userStoriesList() {
		
		ModelAndView mv = new ModelAndView();
		List<UserStoriesDetails> userStoriesList = userStoriesDetailsRepository.findAll();
		System.err.println(userStoriesList);
		mv.addObject("userStoriesList", new UserStoriesDetails());
		mv.addObject("userStoriesList", userStoriesList);
	
		mv.setViewName("userStoriesList");
		return mv;
	}

	
		
}
