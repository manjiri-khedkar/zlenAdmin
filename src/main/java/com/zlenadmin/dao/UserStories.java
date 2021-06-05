package com.zlenadmin.dao;

import java.util.List;

import com.zlenadmin.api.entity.UserDetails;
import com.zlenadmin.dto.StoriesDto;

public interface UserStories {

	List<StoriesDto> getUserStories(String name,String mimeType);
	
}
