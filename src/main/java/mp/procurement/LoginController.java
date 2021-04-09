package mp.procurement;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import mp.procurement.email.ApplicationMailer;
import mp.procurement.model.Bid;
import mp.procurement.model.Financials;
import mp.procurement.model.Login;
import mp.procurement.model.Lot;
import mp.procurement.model.LotData;
import mp.procurement.model.LotList;
import mp.procurement.model.LotRate;
import mp.procurement.model.Party;
import mp.procurement.model.ReportBean;
import mp.procurement.model.ResultBid;
import mp.procurement.model.SessionUser;
import mp.procurement.model.Tender;
import mp.procurement.security.PasswordGenerator;



@Controller
@Component
public class LoginController {

	@Autowired
	CommonService common;
	@Autowired
	ApplicationMailer mailer; 
	WebDriver dr=null;	
	private static OnlineFormJava2 onlineForm = null;
	
	@Autowired
    private SessionUser sessionUser;
	
	//private String requiredMac="E8-39-DF-3E-EE-77";
	
	
	//kalpesh bhai
	//private String requiredMac="78-84-3C-EC-43-28";
	
	//kalpesh bhai
	//private String requiredMac="34-97-F6-9E-9B-4E";
	
	//private String requiredMac="00:15:5d:7b:ff:4d";
	//Kalpesh bhai Dell
	//private String requiredMac= "58-8A-5A-4A-70-6D";

	//Dell
	private String requiredMac= "8C-70-5A-73-D9-AC";
	
	//karan bhai..
	//private String requiredMac= "28-C6-3F-DE-43-4C"; 
 
	private HashMap<String,Integer> map_lot = new HashMap<String,Integer>();
	private HashMap<Integer,Integer> map_party = new HashMap<Integer,Integer>();
	private static int stateId;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	  public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
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
			Party user = common.loadUserByName(emailId);
			if (user==null){
				return "User doesnt exist";
			}else{
				String newPassword = PasswordGenerator.generateRandomPassword(10);
				user.setPassword(newPassword);
				common.updateParty(user);
				List emails = new ArrayList();
				emails.add(emailId);
				HashMap<String, String> values = new HashMap<String, String>();
				values.put("user_name", user.getParty_name());
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
	    Party user = common.validateUser(login);
	    if (null != user) {
	    	mav = new ModelAndView("home");
	    	mav.addObject("firstname", user.getParty_name());
	    } else {
	    	mav = new ModelAndView("login");
	    	mav.addObject("message", "Username or Password is wrong!!");
	    }
	    return mav;
	  }
	
	
	
	private boolean checkLiscence(){
		Date dt = new Date(2019,06,30);
		
		if (System.currentTimeMillis() < dt.getTime() ){
			System.out.println("valid Liscence");
			return true;
		}else{
			System.out.println("invalid Liscence");
			return false;
		}
		
	}

	
	
	private boolean checkMac(){
		try{
			Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
			while (networks.hasMoreElements()){
				//NetworkInterface cur_network = 
				final NetworkInterface netInf = networks.nextElement();
				final byte[] mac = netInf.getHardwareAddress();
				if (mac!=null){
					final StringBuilder sb = new StringBuilder();
					for (int i = 0; i < mac.length; i++) {
						sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));        
					}
					System.out.println(requiredMac+"checking.... " + sb.toString());
					if (requiredMac.equals(sb.toString())){
						return true;
					}
				}
			}
		}catch(Exception ex){
			System.out.println("checkMac : "+ ex);
			return false;
		}
		System.out.println("mac doesnot match: " );
		return false ;
	}
}
