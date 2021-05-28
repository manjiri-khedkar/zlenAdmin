package com.zlenadmin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zlenadmin.model.UserStoriesDetails;

@Repository
public interface UserStoriesDetailsRepository extends JpaRepository<UserStoriesDetails, Long> {

	@Query(value = "Select s from UserStoriesDetails s where s.id = :snapId and s.isActive = 'Y'")
	UserStoriesDetails findBySnapIdAndIsActive(Long snapId);
	
	UserStoriesDetails findByUserId(String userId);
	
	List<UserStoriesDetails> findAll();
	
	@Query(value="select count(usd.id) as count, DATE(usd.uploaded_date_time) as createDate, usd.mime_type from public.user_stories_details usd group by DATE(usd.uploaded_date_time), usd.mime_type order by DATE(usd.uploaded_date_time) desc ", nativeQuery=true)
	List<Object[]> getStoriesGraphQuery();
	
	
}
