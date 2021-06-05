package com.zlenadmin.dao;

import java.util.Date;
import java.util.List;

<<<<<<< HEAD
import com.zlenadmin.api.entity.UserDetails;
=======

>>>>>>> 7fb8d8315599592e290f5955618ac35fdebdae67
import com.zlenadmin.dto.StoriesDto;

public interface UserStories {

<<<<<<< HEAD
	List<StoriesDto> getUserStories(String name,String mimeType);
=======
	List<StoriesDto> getUserStories(String mimeType, String zlenCode);

	List<StoriesDto> getUserStories(String zlenCode);
>>>>>>> 7fb8d8315599592e290f5955618ac35fdebdae67
	
}
