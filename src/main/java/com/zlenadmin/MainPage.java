package com.zlenadmin;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TimeZone;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;

import com.google.errorprone.annotations.RequiredModifiers;
import com.zlenadmin.api.entity.LastSeenSummary;
import com.zlenadmin.api.entity.UserDetails;
import com.zlenadmin.api.entity.UserFeedBack;
import com.zlenadmin.dao.Accounts;
import com.zlenadmin.dao.UserStories;
import com.zlenadmin.dto.AccountsDto;
import com.zlenadmin.dto.InactiveDto;
import com.zlenadmin.dto.PendingRegistrationDto;
import com.zlenadmin.dto.RegisterPendingDto;
import com.zlenadmin.dto.StoriesDto;
import com.zlenadmin.dto.UserDetailsDto;
import com.zlenadmin.dto.UserFeedBackDto;
import com.zlenadmin.dto.UsersDetailDto;
import com.zlenadmin.model.AppUser;
import com.zlenadmin.model.SessionUser;
import com.zlenadmin.repository.AppuserRepository;
import com.zlenadmin.repository.UserDetailsRepository;
import com.zlenadmin.repository.UserFeedBackRepository;
import com.zlenadmin.repository.UserStoriesDetailsRepository;
import com.zlenadmin.service.ExcelService;

@Controller
@Component
@SessionScope
public class MainPage {

	WebDriver dr = null;

	@Autowired
	private SessionUser sessionUser;

	@Autowired
	private AppuserRepository appuserRepository;

	@Autowired
	UserDetailsRepository userDetailsRepository;

	@Autowired
	UserStoriesDetailsRepository userStoriesDetailsRepository;

	@Autowired
	private UserStories userStories;

	@Autowired
	private Accounts accountDao;

	@Autowired
	private com.zlenadmin.dao.UserFeedBack userFeedBacks;

	@Autowired
	private ExcelService fileService;

	@Autowired
	private com.zlenadmin.dao.UserDetails userDetails;

	@GetMapping(value = "/")
	public String indexView(@ModelAttribute AppUser users) {
		return "redirect:/login";
	}

	@RequestMapping("/adminHomeDashboard")
	public ModelAndView showMessage() {

		 //appuserRepository.addActivity(sessionUser.getUserId(), "adminHomeDashboard",
		// System.currentTimeMillis());
		if (true) {
			ModelAndView model = new ModelAndView("home");
			return model;
		} else {
			ModelAndView model = new ModelAndView("licenseError");
			return model;
		}
	}

	private void addSameSiteCookieAttribute(HttpServletResponse response) {
		Collection<String> headers = response.getHeaders(HttpHeaders.SET_COOKIE);
		boolean firstHeader = true;
		// there can be multiple Set-Cookie attributes
		for (String header : headers) {
			if (firstHeader) {
				response.setHeader(HttpHeaders.SET_COOKIE, String.format("%s; %s", header, "SameSite=Strict"));
				firstHeader = false;
				continue;
			}
			response.addHeader(HttpHeaders.SET_COOKIE, String.format("%s; %s", header, "SameSite=Strict"));
		}
	}

	@RequestMapping("/dashboard")
	public ModelAndView dashboard(HttpServletResponse response) {
		// appuserRepository.addActivity(sessionUser.getUserId(), "dashboard",
		// System.currentTimeMillis());
		ModelAndView model = new ModelAndView("dashboard");
		Integer totalRegistrationCount = userDetailsRepository.getUserDetails1();
		model.addObject("totalRegistrationCount", totalRegistrationCount);
		Calendar cal = new GregorianCalendar(TimeZone.getTimeZone(
				"UTC"));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		Date daysAgo = cal.getTime();
		Integer last24HoursCount = userDetailsRepository.getUserDetails2(daysAgo);
		model.addObject("last24HoursCount", last24HoursCount);

		cal = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
		// cal.add(Calendar.DAY_OF_MONTH, -1);
		daysAgo = cal.getTime();
		addSameSiteCookieAttribute(response);
		LastSeenSummary summary = accountDao.getCreate(daysAgo);
		model.addObject("todaysActiveUser", summary.getCount());
		
		Integer age=0;Integer age1=19;
		Integer value=20;Integer value1=25;Integer value2=26;
		Integer value3=45;Integer value4=46;Integer value5=10000;
		Integer valuesage = 0;Integer valuesage1 = 0;
		Integer valuesage2 = 0;Integer valuesage3 = 0;
		
			List<UsersDetailDto> ageGroup = userDetails.getAgeGroup(age, age1);
			for (int i = 0; i < ageGroup.size(); i++) {
				valuesage = ageGroup.get(i).getAge();
			}
			model.addObject("valuesage", valuesage);
			
			ageGroup = userDetails.getAgeGroup(value, value1);
			for(int i=0;i<ageGroup.size();i++) {
				 valuesage1=ageGroup.get(i).getAge();
				}
			model.addObject("valuesage1", valuesage1);
			
			 ageGroup = userDetails.getAgeGroup(value2, value3);
			for(int i=0;i<ageGroup.size();i++) {
				 valuesage2=ageGroup.get(i).getAge();
				}
			model.addObject("valuesage2", valuesage2);
			
			ageGroup = userDetails.getAgeGroup(value4, value5);
			for(int i=0;i<ageGroup.size();i++) {
				 valuesage3=ageGroup.get(i).getAge();
				}
			model.addObject("valuesage3", valuesage3);
		
		model.addObject("genderCounts",userDetails.getGenderGroup());
		return model;
	}

	@GetMapping("/dashboard/bar-chart")
	@ResponseBody
	public Map<String, Object> registrationGraphData(Model model) {

		Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
		cal.add(Calendar.DAY_OF_MONTH, -7);
		Date daysAgo = cal.getTime();

		List<Object[]> list = userDetailsRepository.getGraphQuery(daysAgo);

		List<BigInteger> list1 = new ArrayList<BigInteger>();
		List<Date> list2 = new ArrayList<Date>();

		for (Object[] dataList : list) {
			BigInteger count = (BigInteger) dataList[0];
			Date createdDate = (Date) dataList[1];
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
	public Object getStoriesGraphData(Model model, @Param("date") Date date) {

		Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
		cal.add(Calendar.DAY_OF_MONTH, -7);
		Date daysAgo = cal.getTime();
		List<Object[]> userList = userStoriesDetailsRepository.getStoriesGraphQuery(daysAgo);
		List<Object[]> poll = userStoriesDetailsRepository.getPollGraphQuery(daysAgo);
		System.out.println("poll==" + poll.size());
		List<BigInteger> list1 = new ArrayList<BigInteger>();
		List<Date> dateList = new ArrayList<Date>();
		List<BigInteger> list2 = new ArrayList<BigInteger>();
		List<BigInteger> list3 = new ArrayList<BigInteger>();
		List<BigInteger> list4 = new ArrayList<BigInteger>();
		List<Date> polldate = new ArrayList<Date>();
		List<HashMap<String, Object>> mainList = new ArrayList<HashMap<String, Object>>();

		SortedMap<Date, HashMap<String, BigInteger>> datewise_hm = new TreeMap<Date, HashMap<String, BigInteger>>();
		SortedMap<Date, HashMap<String, BigInteger>> datewise_hm1 = new TreeMap<Date, HashMap<String, BigInteger>>();
		Date curDate = null;
		Date pollDate = null;
		HashMap<String, BigInteger> inner_hm = null;
		HashMap<String, BigInteger> inner_hm1 = null;

		for (Object[] list : userList) {
			BigInteger count = (BigInteger) list[0];
			Date createdDate = (Date) list[1];
			String mimeType = (String) list[2];

			if ((curDate == null) || (curDate != null && !curDate.equals(createdDate))) {
				curDate = createdDate;
				System.out.println(curDate);
				inner_hm = new HashMap<String, BigInteger>();
				datewise_hm.put(createdDate, inner_hm);
			}
			inner_hm.put(mimeType, count);
		}
		datewise_hm.put(curDate, inner_hm);

		for (Date key : datewise_hm.keySet()) {
			System.out.println("Processing:" + key);
			HashMap<String, BigInteger> datewiseData = datewise_hm.get(key);
			dateList.add(key);
			// polldate.add(key);

			if (datewiseData.containsKey("text/html")) {

				list1.add(datewiseData.get("text/html"));
			} else {
				list1.add(BigInteger.ZERO);
			}

			BigInteger count = BigInteger.ZERO;
			if (datewiseData.containsKey("image/jpg")) {
				count = count.add(datewiseData.get("image/jpg"));
			}
			if (datewiseData.containsKey("image/jpeg")) {
				count = count.add(datewiseData.get("image/jpeg"));
			}

			if (datewiseData.containsKey("image/png")) {
				count = count.add(datewiseData.get("image/png"));
			}

			if (datewiseData.containsKey("image")) {
				count = count.add(datewiseData.get("image"));
			}
			if (datewiseData.containsKey("")) {
				count = count.add(datewiseData.get(""));
			}

			list2.add(count);

			if (datewiseData.containsKey("video/mp4")) {
				list3.add(datewiseData.get("video/mp4"));
			} else {
				list3.add(BigInteger.ZERO);
			}
		}

		for (Object[] poll1 : poll) {
			BigInteger pollcount = (BigInteger) poll1[0];
			Date createAt = (Date) poll1[1];
			// String mimeType = (String)list[2];

			if ((pollDate == null) || (pollDate != null && !pollDate.equals(createAt))) {
				pollDate = createAt;
				System.out.println(pollDate);
				inner_hm1 = new HashMap<String, BigInteger>();
				datewise_hm1.put(createAt, inner_hm1);
			}
			inner_hm1.put("pollcount", pollcount);
		}
		datewise_hm1.put(pollDate, inner_hm1);
		for (Date keyvalue : datewise_hm1.keySet()) {
			System.out.println("Processing:" + keyvalue);
			HashMap<String, BigInteger> datewiseData1 = datewise_hm1.get(keyvalue);
			// dateList.add(key);
			polldate.add(keyvalue);

			if (datewiseData1.containsKey("pollcount")) {

				list4.add(datewiseData1.get("pollcount"));
			} else {
				list4.add(BigInteger.ZERO);
			}
		}

		HashMap<String, Object> finalMap1 = new HashMap<String, Object>();
		finalMap1.put("name", "text/html");
		finalMap1.put("data", list1);

		HashMap<String, Object> finalMap2 = new HashMap<String, Object>();
		finalMap2.put("name", "image/jpg");
		finalMap2.put("data", list2);

		HashMap<String, Object> finalMap3 = new HashMap<String, Object>();
		finalMap3.put("name", "video");
		finalMap3.put("data", list3);

		HashMap<String, Object> finalMap4 = new HashMap<String, Object>();
		finalMap4.put("name", "pollcount");
		finalMap4.put("data", list4);

		List<String> types = new ArrayList<String>();
		types.add("text");
		types.add("image");
		types.add("video");
		types.add("pollcount");

		mainList.add(finalMap1);
		mainList.add(finalMap2);
		mainList.add(finalMap3);
		mainList.add(finalMap4);

		HashMap<String, Object> finalMap5 = new HashMap<String, Object>();
		finalMap5.put("dates", dateList);
		finalMap5.put("count", mainList);
		finalMap5.put("types", types);

		return finalMap5;
	}

	@GetMapping("/dashboard/lastseen")
	@ResponseBody
	public Map<String, Object> registrationGraphData1(Model model) {
		Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
		cal.add(Calendar.DAY_OF_MONTH, -7);
		Date daysAgo = cal.getTime();
		List<AccountsDto> list = accountDao.getGraphQuery31(daysAgo);
		System.out.println("list :" + list);

		List<Date> list1 = new ArrayList<Date>();
		List<Integer> list2 = new ArrayList<Integer>();

		for (int j = 0; j <= list.size() - 1; j++) {
			Date cdate = list.get(j).getCdate();
			Integer count = list.get(j).getCount();
			list1.add(cdate);
			list2.add(count);
			System.out.println("date==" + cdate);
			System.out.println("count===" + count);

		}

		Map<String, Object> finalMap = new HashMap();

		model.addAttribute("datescount", list1);

		finalMap.put("datescount", list1);
		finalMap.put("count", list2);

//			System.out.println("last==>"+lastSeen);
		// finalMap.put("lastSeen", lastSeen);

		return finalMap;
	}

	@GetMapping("/userDetailsList/{pageid}")
	//public ModelAndView userDetailsList() {
	
	public Object userDetailsList(@PathVariable(required = false) Integer pageid, Model model,@RequestParam(required=false) String userName, 
			@RequestParam(required=false) String userMobile, @RequestParam(required=false) String zlenCode,@RequestParam(required=false) String deviceType, 
			@RequestParam(required=false) String gender, @RequestParam(required=false) Integer age, @RequestParam(required=false) Integer age1, 
			@RequestParam(required=false) Integer friendNumber, @RequestParam(required=false) Integer friendNumber1, @RequestParam(required=false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date  createdOn)
	{	
		if ("All".equals(deviceType)) {
			deviceType=null;
		}
		if ("".equals(userMobile)) {
			userMobile=null;
		}
		if ("".equals(zlenCode)) {
			zlenCode=null;
		}
		
		if ("".equals(userName)) {
			userName=null;
		}
		
		if ("All".equals(gender)) {
			gender=null;
		}
		
		if("All".equals(age)) {
			age = null;
		}
		
		if("All".equals(age1)) {
			age1 = null;
		}
		
		if("All".equals(friendNumber)) {
			age = null;
		}
		
		if("All".equals(friendNumber1)) {
			age1 = null;
		}
		
		if ("".equals(createdOn)) {
			createdOn=null;
		}
		
		 Integer total=1000;    
	        if(pageid==null){}    
	        else{  
	        	pageid=1;
	        	System.out.println("pageid==:"+pageid);
	            pageid=(pageid-1)*total+1;    
	        }    
	        
		ModelAndView mv = new ModelAndView();

		List<UsersDetailDto> userDetailsList = userDetails.getUserDetails(userName, userMobile, zlenCode, deviceType, createdOn, gender, age,age1,friendNumber,friendNumber1,pageid,total);
//		PagedListHolder<UsersDetailDto> pagedListHolder = new PagedListHolder(userDetailsList);
//		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
//		pagedListHolder.setPage(page);
//		pagedListHolder.setPageSize(1000);
//		pagedListHolder.getSource();
////		List<Object> userDetailsList1 = new ArrayList<Object>();
////		userDetailsList1.add(pagedListHolder);
//		mv.addObject("pagedListHolder", pagedListHolder);
		mv.addObject("userListDetails", userDetailsList);
		mv.setViewName("userDetailsList");
		return mv;

	}

	@GetMapping("/userDetailsListContents")
	@ResponseBody
	public List<UsersDetailDto> getUserDetails(Model model, @Param("userName") String userName,
			@Param("userMobile") String userMobile, @Param("gender") String gender, @Param("age") Integer age,
			@Param("age1") Integer age1, @Param("friendNumber") Integer friendNumber, @Param("friendNumber1") Integer friendNumber1, @Param("zlenCode") String zlenCode, @Param("deviceType") String deviceType,
			@Param("createdOn") @DateTimeFormat(pattern = "yyyy-MM-dd") Date createdOn ) {

		if ("All".equals(deviceType)) {
			deviceType = null;
		}
		if ("".equals(userMobile)) {
			userMobile = null;
		}
		if ("".equals(zlenCode)) {
			zlenCode = null;
		} else {
			zlenCode = zlenCode.toLowerCase();
		}

		if ("".equals(userName)) {
			userName = null;
		} else {
			userName = userName.toLowerCase();
		}

		if ("All".equals(gender)) {
			gender = null;
		} else {
			gender = gender.toLowerCase();
		}
		
		if ("All".equals(age) || "All".equals(age1)) {
			age = null;
			age1 = null;
		}
		
			
		if ("".equals(createdOn)) {
			createdOn = null;
		}

		Integer valueAge = 0;
		Integer valueAge1 = 0;
		
		if (age == 0 && age1 == 0) {
			age = null;
			age1 = null;
		}
		valueAge = age;
		valueAge1 = age1;

		Integer friendC = 0;
		Integer friendC1 = 0;
		if (friendNumber == 0 || friendNumber1 == 0) {
			friendNumber = null;
			friendNumber1 = null;
		}
		friendC = friendNumber;
		friendC1 = friendNumber1;
		
		
		
		Integer total= null; 
		Integer pageid = null;
//		for (int i = 0; i < data.size(); i++) {	
//		pageid=Integer.valueOf(data.get(i));
//		}
//        if(pageid==1){}    
//        else{    
//            pageid=(pageid-1)*total+1;    
//        }    
        
		List<UsersDetailDto> userDetailsList = userDetails.getUserDetails(userName, userMobile, zlenCode, deviceType,
				createdOn, gender, valueAge, valueAge1, friendC, friendC1, pageid, total);

		return userDetailsList;

	}

	@GetMapping("/userDetailsDownload")
	@ResponseBody
	public ResponseEntity<InputStreamResource> getuserDetailsDownload(@RequestParam(required = false) String userName,
			@RequestParam(required = false) String userMobile, @RequestParam(required = false) String gender,
			@RequestParam(required = false) Integer age, @RequestParam(required = false) Integer age1,
			@RequestParam(required = false) Integer friendNumber, @RequestParam(required = false) Integer friendNumber1,
			@RequestParam(required = false) String zlenCode, @RequestParam(required = false) String deviceType,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date createdOn) {
		
		if ("All".equals(deviceType)) {
			deviceType = null;
		}
		if ("".equals(userMobile)) {
			userMobile = null;
		}
		if ("".equals(zlenCode)) {
			zlenCode = null;
		}

		if ("".equals(userName)) {
			userName = null;
		}

		if ("All".equals(gender)) {
			gender = null;
		}

		if ("All".equals(age) || "All".equals(age1)) {
			age = null;
			age1 = null;
		}

		if ("All".equals(friendNumber) || "All".equals(friendNumber1)) {
			friendNumber = null;
			friendNumber1 = null;
		}
		
		if ("".equals(createdOn)) {
			createdOn = null;
		}
		
		Integer total=null;  
		Integer pageid=null;
//        if(pageid==1){}    
//        else{    
//            pageid=(pageid-1)*total+1;    
//        }    
		// List<UserDetails> uu = userDetailsRepository.getUserDetails(userName,
		// userMobile, zlenCode, deviceType, createdOn);
		// System.out.println("uu=="+ uu);
		String filename = "UserDetails.xls";
		InputStreamResource file = new InputStreamResource(
				fileService.loadUserDetails(deviceType, userMobile, userName, zlenCode, gender, age, age1, friendNumber, friendNumber1,pageid,total, createdOn));
		// InputStreamResource file = new
		// InputStreamResource(fileService.loadinActive(-30));

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
	}

	@GetMapping("/userStoriesListContents")
	@ResponseBody
	public Object getUserStories(Model model, @Param("zlenCode") String zlenCode, @Param("mimeType") String mimeType,
			@Param("uploadedDateTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date uploadedDateTime) {
		// List result= service.queryForMovies();
		// List result = newrepo.getUserStories();

		if ("".equals(zlenCode)) {
			zlenCode = null;
		}

		if ("All".equals(mimeType)) {
			mimeType = null;
		}

		if ("".equals(uploadedDateTime)) {
			uploadedDateTime = null;
		}

		List result = userStories.getUserStories(zlenCode, mimeType, uploadedDateTime);

		return result;

	}

	@GetMapping("/userStoriesList")
	public Object userStoriesList(@RequestParam(required = false) String mimeType,
			@RequestParam(required = false) String zlenCode,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date uploadedDateTime) {

		if ("".equals(zlenCode)) {
			zlenCode = null;
		}

		if ("All".equals(mimeType)) {
			mimeType = null;
		}

		if ("".equals(uploadedDateTime)) {
			uploadedDateTime = null;
		}

		ModelAndView mv = new ModelAndView();

		Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
		cal.add(Calendar.DAY_OF_MONTH, -3);
		Date daysAgo = cal.getTime();

		if (uploadedDateTime != null && mimeType == null) {

			List<StoriesDto> userStoriesList = userStories.getUserStories(zlenCode, mimeType, uploadedDateTime);
			mv.addObject("userStoriesList", userStoriesList);
			mv.setViewName("userStoriesList");
			return mv;
		} else if (uploadedDateTime != null && mimeType != null) {

			List<StoriesDto> userStoriesList = userStories.getUserStories(zlenCode, mimeType, uploadedDateTime);
			mv.addObject("userStoriesList", userStoriesList);
			mv.setViewName("userStoriesList");
			return mv;

		}

		else {
			List userStoriesList = userStories.getLatestUserStories(null, null, daysAgo);
			// List<UserStoriesDetails> userStoriesList =
			// userStoriesDetailsRepository.findAll();
			mv.addObject("userStoriesList", userStoriesList);
			mv.setViewName("userStoriesList");
			return mv;
		}
	}

	@GetMapping("/userFeedBackList")
	public ModelAndView userFeedBackList() {
		ModelAndView mv = new ModelAndView();
		
		List<UserFeedBackDto> userFeedBackList = userFeedBacks.getUserFeedBack();
				
		mv.addObject("userFeedBackList", userFeedBackList);
		mv.setViewName("userFeedBackList");
		return mv;
	}

	@GetMapping("/pendingRegistration")
	public ModelAndView pendingRegitration() {
		ModelAndView mv = new ModelAndView();
		List<PendingRegistrationDto> pendingRegistration = accountDao.getPendingRegistrationDto(null);
		mv.addObject("pendingRegistration", pendingRegistration);
		List<RegisterPendingDto> registerPending = accountDao.getPendingRegistrations(null);
		mv.addObject("registerPending", registerPending);
		mv.setViewName("pendingRegistration");
		return mv;
	}

	@GetMapping("/pendingRegistrationlist")
	@ResponseBody
	public List<RegisterPendingDto> getPendingRegistrations(Model model, @Param("days") Integer days) {

		if ("All".equals(days)) {
			days = null;
		}
		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.DAY_OF_YEAR, days);
		Date daysAgo = cal.getTime();
		List<RegisterPendingDto> registerPending = accountDao.getPendingRegistrations(daysAgo);

		return registerPending;
	}

	@GetMapping("/pendingRegistrationDownload")
	public ResponseEntity<InputStreamResource> pendingRegitrationDownload(
			@RequestParam(required = false, defaultValue = "-15") Integer days) {

		if ("All".equals(days)) {
			days = null;
		}
		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.DAY_OF_YEAR, days);
		Date daysAgo = cal.getTime();

		String filename = "PendingRegistration.xls";
		InputStreamResource file = new InputStreamResource(fileService.loadPending(days));

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
	}

	@SuppressWarnings("unlikely-arg-type")
	@GetMapping("/inActive")
	public Object InactiveUsers(@RequestParam(required = false, defaultValue = "-30") Integer days) {

		if ("All".equals(days)) {
			days = null;
		}

		ModelAndView mv = new ModelAndView();

		Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
		cal.add(Calendar.DAY_OF_YEAR, days);
		Date daysAgo = cal.getTime();

		List<InactiveDto> inActive = accountDao.getInactiveDto(daysAgo);
		mv.addObject("inActive", inActive);
		mv.setViewName("inActive");
		return mv;
	}

	@GetMapping("/inActivelist")
	@ResponseBody
	public List<InactiveDto> getInactiveDto(Model model, @Param("days") Integer days) {

		if ("All".equals(days)) {
			days = null;
		}
		Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
		cal.add(Calendar.DAY_OF_YEAR, days);
		Date daysAgo = cal.getTime();
		List<InactiveDto> inActivelist = accountDao.getInactiveDto(daysAgo);

		return inActivelist;
	}

	@GetMapping("/inActivedownload")
	@ResponseBody
	public ResponseEntity<InputStreamResource> getinActiveDownload(
			@RequestParam(required = false, defaultValue = "-30") Integer days) {

		if ("All".equals(days)) {
			days = null;
		}
		Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
		cal.add(Calendar.DAY_OF_YEAR, days);
		Date daysAgo = cal.getTime();
		String filename = "Inactive.xls";
		InputStreamResource file = new InputStreamResource(fileService.loadinActive(days));

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
	}

//	@GetMapping("/userStoriesListContents") 
//	@ResponseBody
//	public List<StoriesDto> getUserStories(Model model,@Param("uploadedDateTime") Date uploadedDateTime,@Param("mimeType") String mimeType,@Param("zlenCode") String zlenCode)
//	{
////		List<Object[]> userDetailsContentList= userDetailsRepository.getDetailsData();
//		
//		//ArrayList<UserDetails> userDetailsList1=userDetailsRepository.getUserDetails();
//		
////		if ("".equals(uploadedDateTime)) {
////		uploadedDateTime=null;
////		}
////	
////		if ("".equals(mimeType)) {
////			mimeType=null;
////		}
////		if ("".equals(zlenCode)) {
////			zlenCode=null;
////		}
//			
//		List<StoriesDto> result = userStories.getUserStories(uploadedDateTime, mimeType, "%" + zlenCode + "%");
//	
//		return result;
//		
//	}
//	
}

//@GetMapping("/userStoriesList")
//public ModelAndView userStoriesList(@Valid StoriesDto storiesDto) {
//	
//	ModelAndView mv = new ModelAndView();
////	String uploadDateTime = " ";
////	
////	if(storiesDto.getUploadedDateTime()!=null) {
////		uploadDateTime = storiesDto.getUploadedDateTime().toString();
////	}
//	List<StoriesDto> userStoriesList = userStories.getUserStories(storiesDto.getMimeType(), storiesDto.getZlenCode());
//	System.err.println(userStoriesList);
//	mv.addObject("userStoriesList", new StoriesDto());
//	mv.addObject("userStoriesList", userStoriesList);
//
//	mv.setViewName("userStoriesList");
//	return mv;
//}
