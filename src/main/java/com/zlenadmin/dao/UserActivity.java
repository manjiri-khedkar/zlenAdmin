package com.zlenadmin.dao;

import java.util.List;

import com.zlenadmin.dto.ActivityDto;

public interface UserActivity {

	List<ActivityDto> getUserActivity(String userId);
	
}
