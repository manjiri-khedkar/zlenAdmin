package com.zlenadmin.dao;

import java.util.List;

import com.zlenadmin.api.entity.UserDetails;

public interface UserStories {

	List<UserDetails> getUserStories(String name);
	
}
