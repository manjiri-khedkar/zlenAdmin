package com.zlenadmin.dao;

import java.util.List;

import com.zlenadmin.dto.ImageDto;
import com.zlenadmin.dto.StoriesDto;

public interface ImageStories {
	
	List<StoriesDto> getStories(String name,String mimeType);

}
