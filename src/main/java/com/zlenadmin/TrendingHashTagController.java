package com.zlenadmin;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zlenadmin.api.entity.Banner;
import com.zlenadmin.api.entity.TrendingHashTag;
import com.zlenadmin.dto.BannerDto;
import com.zlenadmin.dto.TrendingHashTagDto;
import com.zlenadmin.repository.TrendingHashTagRepository;
import com.zlenadmin.service.TrendingHashTagService;

@Controller
public class TrendingHashTagController {

	@Autowired
	private TrendingHashTagRepository trendingHashTagRepository;
	
	@Autowired
	private TrendingHashTagService trendingHashTagService;
	
	@RequestMapping(value="/addTrendinghashTags", method=RequestMethod.GET)
	public ModelAndView AddTrendinghashTag(Model model, TrendingHashTag trendingHashTag) {

		ModelAndView mv = new ModelAndView();
		mv.addObject("trendinghashTag", new TrendingHashTag()); 
		mv.setViewName("addTrendinghashTags");
		return mv;
	}
	
	@RequestMapping(value="/addTrendinghashTags", method=RequestMethod.POST)
	public String saveBanner(@ModelAttribute  TrendingHashTag trendingHashTag, Model model,BindingResult result) {
		
	
		
		trendingHashTagService.save(trendingHashTag);

		
		return "redirect:trendingHashTaglist?success";		 
	}
	
	@GetMapping("/trendingHashTaglist")
	public ModelAndView list() {
		
		ModelAndView mv = new ModelAndView();
		List<TrendingHashTag> trendingHashTaglist = trendingHashTagRepository.findAllByIsDeleted(false); 
		mv.addObject("addTrendinghashTag", new TrendingHashTag());
		mv.addObject("trendingHashTaglist", trendingHashTaglist);
		mv.setViewName("trendingHashTaglist");
		return mv;
	}
	
	
	@RequestMapping(value="/editTrendinghashTags", method=RequestMethod.GET)
	public ModelAndView EditBanner(@RequestParam("id") long id, Model model) {
		//BannerDao bannerDao = new BannerDao();
		//ArrayList<Banner> banner1 = bannerDao.selectBanner(id);
		ModelAndView mv = new ModelAndView();
		TrendingHashTag trendingHashTag = trendingHashTagRepository.findById(id);
		List<TrendingHashTag> trendingHashTaglist= new ArrayList<TrendingHashTag>();
		trendingHashTaglist.add(trendingHashTag);
		mv.addObject("editTrendinghashTag", new TrendingHashTag());
		mv.addObject("trendingHashTaglist", trendingHashTaglist);
		mv.setViewName("editTrendinghashTags");
		return mv;
	}
	
	@RequestMapping(value="/edits", method=RequestMethod.POST)
	public String UpdateBanner(@ModelAttribute TrendingHashTag trendingHashTagDto, Model model) {
		
		trendingHashTagService.update(trendingHashTagDto);

		return "redirect:trendingHashTaglist?success";		 
	}

	
	@RequestMapping(value="/deleteTrendinghashTag", method=RequestMethod.GET)
	public String DeleteBanner(@RequestParam("id") long id, Model model) {
		
		TrendingHashTag trendingHashTag = trendingHashTagRepository.findOne(id);
		trendingHashTag.setIsDeleted(true);
		
		trendingHashTagRepository.save(trendingHashTag);
		
		return "redirect:trendingHashTaglist?success";
	}
}
