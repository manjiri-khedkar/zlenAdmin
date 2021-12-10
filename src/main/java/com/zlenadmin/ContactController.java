package com.zlenadmin;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.zlenadmin.api.entity.Contact;
import com.zlenadmin.api.entity.UserDetails;
import com.zlenadmin.repository.ContactRepository;
import com.zlenadmin.repository.UserDetailsRepository;


@Controller
public class ContactController {
	
	@Autowired
	ContactRepository contactRepository;
	
	@Autowired
	UserDetailsRepository userRepository;
	
	
//	  @RequestMapping(value = "/contactlist/{id}", method = RequestMethod.GET) 
//	  public String Contact(Map<String, Object> model, Contact contacttable)  
//	  { 
//		  List<Contact> contact = contactRepository.findAll();
//	  
//		  model.put("ContactList", contact); 
//	 
//		  return "contact";
//	  
//	  }
	
	@GetMapping("/contactlist/{id}")
	public String Contact(@PathVariable Long id, Model model) {
		UserDetails usd =  userRepository.findById(id);
		
		 Contact contact= contactRepository.getContactTable(usd.getUserId());
		 if (contact!=null) {
		 Gson gson = new Gson();
		 
		 Type listType = new TypeToken<List<HashMap<String, JsonElement>>>(){}.getType();
		 List<HashMap<String, JsonElement>> list = gson.fromJson(contact.getData(), listType);

		model.addAttribute("ContactList",contact);
		model.addAttribute("contacts",list);
		 }else {
			 model.addAttribute("ContactList",new Contact());
				model.addAttribute("contacts",new ArrayList<HashMap<String, String>>());
				
		 }
		return "contact";
	} 

}
