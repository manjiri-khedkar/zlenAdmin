package com.zlenadmin.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.zlenadmin.dto.RoleDto;
import com.zlenadmin.dto.UserDto;
import com.zlenadmin.model.AppUser;
import com.zlenadmin.model.Role;
import com.zlenadmin.repository.AppuserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	AppuserRepository appuserRepository;
	
	private BCryptPasswordEncoder passwordEncoder;

	private String userId;
	
	@Override
	public AppUser save(UserDto userDto) {
		
		    AppUser user = new AppUser();
	        
		    user.setEmail(userDto.getEmail());
//	        user.setUserId(userDto.getUserId());
	   
	        user.setPassword(userDto.getPassword());
	        user.setFirstName(userDto.getFirstName());
	        user.setLastName(userDto.getLastName());
	        user.setMobileNo(userDto.getMobileNo());
	        
	        for(int i=0; i<userDto.getRoles().size(); i++) {
	        	System.out.println("Inside FOR LOOP.....");
	        	 user.setRoles(Arrays.asList(userDto.getRoles().get(i))); 
	        }
	        
		  return appuserRepository.save(user);
	}
	
	@Override
	public AppUser findByEmail(String email) {
		// TODO Auto-generated method stub
		return appuserRepository.findByEmail(email);
	}


	@Override
	public AppUser update(UserDto userDto) {
		
		AppUser user = appuserRepository.findById(userDto.getId());
		user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setMobileNo(userDto.getMobileNo());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setConfirmPass(userDto.getConfirmPass());
        
//        if(userDto.getRoles().size() > 0 ) {
//            for(int i=0; i<userDto.getRoles().size(); i++) {
//	        	System.out.println("Inside FOR LOOP.....");
//	        	 user.setRoles(Arrays.asList(userDto.getRoles().get(i))); 
//	        }
//       } 
        
		return appuserRepository.save(user);
	}

	

}
