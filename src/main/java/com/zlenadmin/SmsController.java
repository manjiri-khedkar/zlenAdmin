package com.zlenadmin;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zlenadmin.model.AppUser;

@Controller
public class SmsController<callableservice> {
	
	@Value("${EndPoint}")
	private String endpoint;
	
	@Value("${sdkKey}")
	private String key;
	
	@Value("${sdkSecret}")
	private String secret;
	

	 @GetMapping("/notification")
	 public ModelAndView SmsSender() {
		 ModelAndView mv = new ModelAndView();
		 mv.setViewName("smsSender");
		return mv;
	}
	
	@RequestMapping(value = "/smsSender", method = RequestMethod.POST)
	@ResponseBody
	public  String SendOTP(@RequestParam("groupNo") String groupNo, @RequestParam("message") String message) throws Exception {
		
		
		String url = endpoint+"&key="+key+"&secret="+secret+"&groupno="+groupNo+"&msg="+URLEncoder.encode(message, StandardCharsets.UTF_8.toString());
		String Response="Successfully notified";
		
		return Response;
		}
				
}


