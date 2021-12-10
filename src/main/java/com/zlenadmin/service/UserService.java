package com.zlenadmin.service;


import com.zlenadmin.dto.UserDto;
import com.zlenadmin.model.AppUser;

public interface UserService {
	
	   AppUser save(UserDto userDto);

	   AppUser findByEmail(String email);
	   
	   AppUser update(UserDto userDto);

//	   AppUser findByUserId(Integer id);

	}
