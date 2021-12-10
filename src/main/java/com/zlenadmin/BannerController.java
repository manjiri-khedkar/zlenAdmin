package com.zlenadmin;

import java.math.BigInteger;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;
import com.zlenadmin.api.entity.Banner;
import com.zlenadmin.dao.BannerDao;
import com.zlenadmin.dto.BannerDto;
import com.zlenadmin.dto.RoleDto;
import com.zlenadmin.model.Role;
import com.zlenadmin.repository.BannerRepository;
import com.zlenadmin.repository.RoleRepository;
import com.zlenadmin.service.BannerService;
import com.zlenadmin.service.CallableService;

import javassist.expr.NewArray;

@Controller
public class BannerController {
	@Value("${endPoint}")
	private String endpoint;
	
	@Value("${sdkKey}")
	private String key;
	
	@Value("${sdkSecret}")
	private String secret;
	
	private final Logger logger = LoggerFactory.getLogger(BannerRepository.class);
	
	@Autowired
	private BannerService bannerService;
	
	@Autowired
	private BannerRepository bannerRepository;
	
	@RequestMapping(value="/addBanner", method=RequestMethod.GET)
	public ModelAndView AddBanner(Model model, Banner banner) {

		ModelAndView mv = new ModelAndView();
		mv.addObject("addbanner", new Banner());  
		mv.setViewName("addBanner");
		return mv;
	}
	
	@RequestMapping(value="/addBanner", method=RequestMethod.POST)
	public String saveBanner(@ModelAttribute("addbanner")  @Valid BannerDto bannerDto, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return "addBanner";
		}
	
		
		bannerService.save(bannerDto);

		
		return "redirect:bannerList?success";		 
	}
	
	@RequestMapping(value="/editBanner", method=RequestMethod.GET)
	public String EditBanner(@RequestParam("id") int id, Model model) {
		//BannerDao bannerDao = new BannerDao();
		//ArrayList<Banner> banner1 = bannerDao.selectBanner(id);
		
		Banner banner = bannerRepository.findBanner(id);
		List<Banner> bannerList= new ArrayList<Banner>();
		bannerList.add(banner);
		model.addAttribute("editbanner", new Banner());
		model.addAttribute("bannerList", bannerList);
		
		
		return "editBanner";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String UpdateBanner(@ModelAttribute("editbanner")  @Valid BannerDto bannerDto, BindingResult result, Model model) {
		
		if (result.hasErrors()){
        	
			return "editBanner";
		}
	
		
		bannerService.update(bannerDto);

		
		return "redirect:bannerList?success";		 
	}
	
	@GetMapping("/bannerList")
	public ModelAndView list() {
		
		ModelAndView mv = new ModelAndView();
		List<Banner> bannerlist = bannerRepository.findAll(); 
		mv.addObject("addbanner", new Banner());
		mv.addObject("bannerlist", bannerlist);
		mv.setViewName("bannerList");
		return mv;
	}
	
	@RequestMapping(value="/deleteBanner", method=RequestMethod.GET)
	public String DeleteBanner(@RequestParam("id") int id, Model model) {
		
		bannerRepository.deleteBanner(id);
		
		//model.addAttribute("banner", banner);
			
		return "redirect:bannerList?success";
	}
	

	
	@RequestMapping(value="/activeBanner", method=RequestMethod.GET)
	public String ActiveBanner(@RequestParam("id") int id, Model model) throws Exception {
		
		 bannerRepository.isActive(id);
		 	
		return "redirect:bannerList?success";
	}
	
	
	@RequestMapping(value="/activeBannerApi", method=RequestMethod.GET)
	@ResponseBody
	public String ActiveBannerapi(Model model) { 
	
		List<Banner> banner = bannerRepository.findActiveBanner();
		
			String response=null;
			for (Banner banner1 : banner) {
				
				Date startDate = banner1.getStart_date();
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
			    String startdate= formatter.format(startDate);
			    
				Date endDate = banner1.getEnd_date();
				SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");  
			    String enddate= formatter.format(endDate);
			    
			    int frequency = banner1.getFrequency();
			    String freq = String.valueOf(frequency);
			    
				String content = banner1.getContent();
			
				JsonObject obj = new JsonObject();
				obj.addProperty("StartDate", startdate);
				obj.addProperty("EndDate", enddate);
				obj.addProperty("Frequency", freq);
				obj.addProperty("Content", content);
			 response=obj.toString();
				
				
				
			}
			return response;
	

	}

}
