package com.zlenadmin.dao;

import java.util.Date;
import java.util.List;

import com.zlenadmin.dto.StoriesDto;
import com.zlenadmin.dto.StoryCommentDto;
import com.zlenadmin.dto.UserLikesDto;

public interface UserStories {

	List<StoriesDto> getUserStories(String name,String mimeType,Date uploadedDateTime,boolean zlenWorld,String userMobile);
	List<StoryCommentDto> getUserStoriesComments(Long storyId);
	List<StoriesDto> getLatestUserStories(String zlenCode, String mimeType, Date uploadedDateTime);
	List<UserLikesDto> getUserLikes(Long storyId);
	
}
