package com.zlenadmin.service;

import com.zlenadmin.dto.AppUserRegistrationDto;
import com.zlenadmin.model.AppUser;

public interface RegistrationService {
	
	   AppUser save(AppUserRegistrationDto registration);

	   AppUser findByEmail(String email);
	    
	}
