package com.zlenadmin.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zlenadmin.api.entity.UserStoriesDetails;

@Repository
public interface NewStoriesRepository extends JpaRepository<UserStoriesDetails, Long> {
	
//	@Query("SELECT usd.uploadedDateTime,usd.mimeType, ud.zlenCode FROM UserStoriesDetails usd INNER JOIN UserDetails ud ON usd.userId = ud.userId")
	
	@Query(value = "select usd.uploaded_date_time, usd.mime_type, ud.zlen_code from user_stories_details usd inner join user_details ud on usd.user_id = ud.user_id", nativeQuery=true)
//	ArrayList<UserStoriesDetails> getUserStories(@Param("uploadedDateTime") Date uploadedDateTime, @Param("mimeType") String mimeType, @Param("zlenCode") String zlenCode);
	List<Object[]> getUserStories();
	
	
	UserStoriesDetails findByUploadedDateTime(Date uploadedDateTime);
	UserStoriesDetails findByMimeType(String mimeType);
//	UserDetails findByZlenCode(String zlenCode);
	
}
