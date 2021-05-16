package com.zlenadmin.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.zlenadmin.dto.AppUserRegistrationDto;
import com.zlenadmin.model.AppUser;
import com.zlenadmin.repository.AppuserRepository;


@Service
public class RegistrationServiceImpl implements RegistrationService {
	
	@Autowired
	AppuserRepository appuserRepository;
	
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public AppUser save(AppUserRegistrationDto registration) {
		
		  AppUser user = new AppUser();
	        
	        user.setEmail(registration.getEmail());
	        user.setUserId(registration.getUserId());
	        user.setPassword(registration.getPassword());
	        user.setFirstName(registration.getFirstName());
	        user.setLastName(registration.getLastName());
	        user.setMobileNo(registration.getMobileNo());
	        user.setDate(registration.getDate());
	      
		  return appuserRepository.save(user);
	}

	@Override
	public AppUser findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<AppUser> findAll() {
		// TODO Auto-generated method stub
		
		return appuserRepository.findAll();
	}

}
