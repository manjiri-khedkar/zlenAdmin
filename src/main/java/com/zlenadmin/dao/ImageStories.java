package com.zlenadmin.dao;

import java.util.Date;
import java.util.List;

import com.zlenadmin.dto.StoriesDto;

public interface ImageStories {
	
	List<StoriesDto> getStories(String zlenCode,String mimeType,Date uploadedDateTime);

}
