package com.zlenadmin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zlenadmin.api.entity.UserStoriesDetails;
import com.zlenadmin.repository.UserStoriesDetailsRepository;

@Controller
@RequestMapping("/stories")
public class UserStoriesController {

	@Autowired
	UserStoriesDetailsRepository userStoriesDetailsRepository;

	@GetMapping
	public ModelAndView list(
			@RequestParam(name = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(name = "size", defaultValue = "9", required = false) Integer size) {

		Pageable pageable = new PageRequest(pageNumber, size);

		// List<UserStoriesDetails> storieslist =
		// userStoriesDetailsRepository.findAll();

		Page<UserStoriesDetails> storiesPage = userStoriesDetailsRepository.findAll(pageable);
		System.out.println(storiesPage.getTotalElements());
		ModelAndView mav = new ModelAndView();
		mav.addObject("stories", new UserStoriesDetails());
		mav.addObject("storiesList", storiesPage.getContent());
		mav.addObject("totalElement", storiesPage.getTotalElements());

		mav.setViewName("stories");
		return mav;
	}

	@GetMapping("/paginated")
	@ResponseBody
	public List<UserStoriesDetails> getStories(
			@RequestParam(name = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(name = "size", defaultValue = "9", required = false) Integer size) {

		Pageable pageable = new PageRequest(pageNumber, size);

		// List<UserStoriesDetails> storieslist =
		// userStoriesDetailsRepository.findAll();

		Page<UserStoriesDetails> storiesPage = userStoriesDetailsRepository.findAll(pageable);

		return storiesPage.getContent();
	}

}
