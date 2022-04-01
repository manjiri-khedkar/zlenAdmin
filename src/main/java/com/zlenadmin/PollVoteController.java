package com.zlenadmin;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zlenadmin.dao.Poll;
import com.zlenadmin.dto.PollOptionDto;
import com.zlenadmin.dto.PollVoteDto;
import com.zlenadmin.service.ExcelService;

@Controller
public class PollVoteController {
	
	@Autowired
	private Poll poll;
	
//	@Autowired
//	private ExcelService fileService;
	
	@GetMapping("/pollVoteView/{id}")
	public String viewLikes(@PathVariable Long id, Model model) {
	
//		UserStoriesCommentDetails userComments = userCommentsRepository.findById(id);

		//List <UserStoriesCommentDetails>  userComments = userCommentsRepository.findBySnapId(id);
		List<PollVoteDto> pvdList = poll.getPollVote(id);

		
		model.addAttribute("pvdList",   pvdList);	
		return "pollVoteView";
	} 
	
	
//	@GetMapping("/pollVoteViewDownloads")
//	@ResponseBody
//	public ResponseEntity<InputStreamResource> getPollVoteViewDownload(@RequestParam(required = false) Long id) {
//		
//		String filename = "PollResponse.xls";
//		InputStreamResource file = new InputStreamResource( fileService.loadPollVoteViewDownload(id));
//	
//		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
//				.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
//	}

}
