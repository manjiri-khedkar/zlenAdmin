package com.zlenadmin;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SmsController<callableservice> {
	
	@Value("${yourMobileNo}")
	private String yourMobileNo;
	
	@Value("${yourPassword}")
	private String yourPassword;
	
	@Value("${senderID}")
	private String senderID;
	
	@Value("${msg}")
	private String otpMsg;

	@RequestMapping(value = "/view", method = {RequestMethod.GET})
	public  String SendOTP(String toMobile, String otp) throws Exception {
		
		
		String msg = otpMsg + " "+otp;
		//String url = "http://trans.smsfresh.co/api/sendmsg.php?user=" +"freshtranss"+ "&pass=" 
		//	       +yourPassword+ "&sender=" +senderID+ "&phone=" +yourMobileNo.trim()+"&to="+toMobile+ "&text=" +URLEncoder.encode(msg, StandardCharsets.UTF_8.toString());
		
		//https://www.twilio.com/
		
		
		
		String url="https://www.smsidea.co.in/smsstatuswithid.aspx?mobile="+yourMobileNo.trim()+"&pass="+yourPassword+"&senderid="+senderID+"&to="+toMobile+"&msg="+URLEncoder.encode(msg, StandardCharsets.UTF_8);
		com.zlenadmin.service.CallableService cs =new com.zlenadmin.service.CallableService(url,null,"GET",null);
		cs.call();
				
		
		return "sms";
		}
				
}
