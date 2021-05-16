package com.zlenadmin;

import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.zlenadmin.dto.RoleDto;
import com.zlenadmin.model.Role;
import com.zlenadmin.repository.RoleRepository;
import com.zlenadmin.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
	
	private final Logger logger = LoggerFactory.getLogger(RoleRepository.class);
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	RoleService roleService;
	
	@RequestMapping("/addRole")
	public ModelAndView addRole(Model model) {

		ModelAndView mv = new ModelAndView();
		mv.addObject("role", new Role());
		mv.setViewName("addrole");
		return mv;
	}
	
	@PostMapping
	public String saveRole(@ModelAttribute("role") @Valid RoleDto roleDto, 
			               BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return "addrole";
		}
		
		roleService.save(roleDto);
		
		return "redirect:/role?success";		 
	}
	
	@GetMapping
	public ModelAndView list() {
		
		ModelAndView mv = new ModelAndView();
		List<Role> roleList = roleRepository.findAll();
		mv.addObject("role", new Role());
		mv.addObject("roleList", roleList);
		mv.setViewName("listrole");
		return mv;
	}
			
	@GetMapping("/editRole/{id}")
	public String editRole(@PathVariable Long id, Model model) {
		model.addAttribute("role", roleRepository.findById(id));
		return "editrole";
	} 
	
	@PostMapping(value="/update")
    public String editRole(@ModelAttribute("role") @Valid RoleDto roleDto,
                                      BindingResult result,Model model){
		
		 if (result.hasErrors()){
	        	for (ObjectError error : result.getAllErrors()) {
	        		logger.error("Error :" + error.getDefaultMessage());
				}
	          return "editrole";
	        }
		
         roleService.update(roleDto);   
        
        return "redirect:/role?editSuccess";
    }
	
	 @GetMapping("/deleteRole/{id}")
	 public String deleteRole(@PathVariable long id, Model model) {
	  System.out.println("DELETE....");
	  roleRepository.deleteById(id);
	  System.out.println("DELETE Successfully....");
	  return "redirect:/role?deleteSuccess";
    } 

}
