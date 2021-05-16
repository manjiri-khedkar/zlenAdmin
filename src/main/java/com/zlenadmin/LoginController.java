package com.zlenadmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zlenadmin.email.ApplicationMailer;
import com.zlenadmin.model.AppUser;
import com.zlenadmin.model.Login;
import com.zlenadmin.model.SessionUser;
import com.zlenadmin.repository.AppuserRepository;
import com.zlenadmin.security.PasswordGenerator;

@Controller
@Component
public class LoginController {

	@Autowired
	ApplicationMailer mailer; 
	
	@Autowired
    private SessionUser sessionUser;
	
	@Autowired
	private  AppuserRepository appuserRepository;
 	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	  public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
		System.err.println("Inside login...");
		HttpSession session= request.getSession(false);
        SecurityContextHolder.clearContext();
        
        if(session != null) {
            session.invalidate();
        } 
	    ModelAndView mav = new ModelAndView("login");
	    mav.addObject("login", new Login());
	    return mav;
	  }
	
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	@ResponseBody
	  public String resetPassword(String emailId) {
		try{
			AppUser user = appuserRepository.findByEmail(emailId);
			if (user==null){
				return "User doesnt exist";
			}else{
				String newPassword = PasswordGenerator.generateRandomPassword(10);
				user.setPassword(newPassword);
				appuserRepository.save(user);
				List emails = new ArrayList();
				emails.add(emailId);
				HashMap<String, String> values = new HashMap<String, String>();
				String uname = user.getFirstName()+" "+user.getLastName();
				values.put("user_name", uname);
				values.put("new_password", newPassword);
				mailer.sendHTMLMail(emails, "Reset Password", "<html> <head></head><body>Hello ##user_name##,<br><br> Your new password is ##new_password##. Please change the password once you login in.<br><br> Thank you <br>Admin,<br>Infosane </body></html>", null,values);
				return "You will receive the new password on above email address.";
			}
		}catch(Exception  ex ){
			
		}
		return "There is some error while resetting password.";
	  }
	
	@RequestMapping(value = "/loginError", method = RequestMethod.GET)
	  public ModelAndView loginError(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session= request.getSession(false);
      SecurityContextHolder.clearContext();
      
      if(session != null) {
          session.invalidate();
      }
	    ModelAndView mav = new ModelAndView("login");
	    mav.addObject("message", "UserName or Password is wrong!");
	    mav.addObject("login", new Login());
	    return mav;
	  }
	
	@RequestMapping(value = "/invalid-session", method = {RequestMethod.GET,RequestMethod.POST})
	  public ModelAndView invalidateSession(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session= request.getSession(false);
        SecurityContextHolder.clearContext();
        if(session != null) {
            session.invalidate();
        }
	    ModelAndView mav = new ModelAndView("login");
	    mav.addObject("login", new Login());
	    return mav;
	  }
	
	  @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	  public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
	  @ModelAttribute("login") Login login) {
	    ModelAndView mav = null;
	    //AppUser user = appuserRepository.validateUser(login);
	    //if (null != user) {
	    	//mav = new ModelAndView("home");
	    	//mav.addObject("firstname", user.getFirstName());
	    //} else {
	    	//mav = new ModelAndView("login");
	    //	mav.addObject("message", "Username or Password is wrong!!");
	   // }
	    return mav;
	  }
}