package com.zlenadmin.dao;

import java.util.Date;
import java.util.List;

import com.zlenadmin.dto.StoriesDto;

public interface UserStories {

	List<StoriesDto> getUserStories(String name,String mimeType,Date uploadedDateTime);
	
}
