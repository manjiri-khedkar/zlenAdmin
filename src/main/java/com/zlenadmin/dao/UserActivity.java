package com.zlenadmin.dao;

import java.util.Date;
import java.util.List;

import com.zlenadmin.dto.ActivityDto;

public interface UserActivity {


//	List<ActivityDto> getUserActivity(String zlenCode);

	List<ActivityDto> getUserActivity(String zlenCode, Date createdDate, String userMobile);

}
